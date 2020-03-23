package org.embryyo.corona.service.core;

import org.embryyo.corona.service.dto.*;
import org.embryyo.corona.service.exception.AuthFailException;
import org.embryyo.corona.service.exception.InvalidOTPException;
import org.embryyo.corona.service.exception.NotFoundException;
import org.embryyo.corona.service.model.*;
import org.embryyo.corona.service.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ServiceManager {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private SymptopRepository symptopRepository;

    @Autowired
    private PatientSymptomRepository patientSymptomRepository;

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    @Autowired
    private Enricher enricher;

    public LoginResponse login(LoginRequest loginRequest, String token) {
        /**
         * TODO: Add the logic of login
         * verify number and otp
         * if new user then send register request
         * else context
         */
        Patient patient = verifyAndGet(loginRequest,token);
        if (patient == null) {
            return new LoginResponse(true);
        }
        return new LoginResponse(enricher.fromPatientDO(patient));
    }

    private Patient verifyAndGet(LoginRequest loginRequest, String token) {
        Otp otpObj = otpRepository.findByMobileNumber(loginRequest.getNumber());
        if (!otpObj.getToken().equalsIgnoreCase(token)) {
            throw new AuthFailException("authorization failed");
        }
        // TODO: Add otp expires logic
        if (!otpObj.getOtp().equals(loginRequest.getOtp())) {
            throw new InvalidOTPException(String
                    .format("Given OTP:%s has not matched",loginRequest.getOtp()));
        }
        Patient p = patientRepository.findByMobileNumber(otpObj.getMobileNumber());
        return p;
    }

    private boolean isOtpExpires(Otp otpObj) {
        return false;
    }

    public void requestValidation(String number, String token) {
        Otp otpObj = otpRepository.findByMobileNumber(number);
        if (otpObj == null) {
            throw new NotFoundException(String
                    .format("Patient is not registed yet for mobile: %s", number));
        }
        if (!otpObj.getToken().equalsIgnoreCase(token)) {
            throw new AuthFailException("authorization failed");
        }
    }

    public String getOtp(String number) {
        // TODO: Send otp using SMS Service;
        String otp = generateOtp(number);
        Otp otpObj = otpRepository.findByMobileNumber(number);
        if (otpObj == null) {
            otpObj = new Otp();
        }
        otpObj.setMobileNumber(number);
        Timestamp timestamp = new Timestamp(Calendar
                .getInstance().getTimeInMillis());
        otpObj.setStoringTime(timestamp);
        otpObj.setOtp(otp);
        otpObj.setExpireTimeInSeconds(180);
        otpObj.setToken(UUID.randomUUID().toString());
        otpRepository.save(otpObj);
        return otpObj.getToken();
    }

    private String generateOtp(String number) {
        int randomNum = ThreadLocalRandom.current()
                .nextInt(Constant.OTP_MIN, Constant.OTP_MAX + 1);
        // TODO: send randomNum as otp through sms service
        return "000000";
    }

    public int register(PatientDTO patient) {
        Patient storeP = enricher.fromPatientDTO(patient);
        Patient storedP = patientRepository.save(storeP);
        return storedP.getId();
    }

    public PatientDTO getPatient(int id) {
        Patient p = patientRepository.findById(id).get();
        return enricher.fromPatientDO(p);
    }

    public void addPatientSymptom(RecordDTO record, int patientId, String token) {
        Patient p = patientRepository.findById(patientId).get();
        requestValidation(p.getMobileNumber(),token);

        HealthRecord healthRecord = new HealthRecord();
        healthRecord.setNote(record.getNote());
        healthRecord.setTimestamp(new Timestamp(record.getDate()));
        healthRecord.setRecordSequence(System.nanoTime());
        healthRecord.setPatient(p);
        healthRecordRepository.save(healthRecord);

        List<PatientSymptomDTO> patientSymptoms = record.getSymptoms();
        List<PatientSymptom> patientSymptomsDO = new ArrayList<>();
        for (PatientSymptomDTO dto : patientSymptoms) {
            PatientSymptom patientSymptom = new PatientSymptom();
            patientSymptom.setHealthRecord(healthRecord);
            patientSymptom.setSeverity(dto.getSeverity());
            Symptom symptom = symptopRepository.findByName(dto.getName());
            patientSymptom.setSymptom(symptom);
            patientSymptomsDO.add(patientSymptom);
        }
        patientSymptomRepository.saveAll(patientSymptomsDO);
    }

    public void addSymptom(SymptomDTO s) {
        Symptom symptom = new Symptom();
        symptom.setDisplayName(s.getDisplayName());
        symptom.setName(s.getName());
        symptopRepository.save(symptom);
    }

    public List<SymptomDTO> getSymptoms() {
        Iterator<Symptom> symptoms = symptopRepository.findAll().iterator();
        List<SymptomDTO> symptomDTOS = new ArrayList<>();
        while (symptoms.hasNext()) {
            Symptom s = symptoms.next();
            SymptomDTO symptomDTO = new SymptomDTO();
            symptomDTO.setName(s.getName());
            symptomDTO.setDisplayName(s.getDisplayName());
            symptomDTOS.add(symptomDTO);
        }
        return symptomDTOS;
    }

    public List<RecordDTO> getPatientSymptoms(int patientId, String token) {
        Patient p = patientRepository.findById(patientId).get();
        requestValidation(p.getMobileNumber(),token);

        Set<HealthRecord> healthRecords = p.getHealthRecords();

        if (healthRecords == null) {
            return new ArrayList<>();
        }

        List<RecordDTO> recordDTOS = new ArrayList<>();
        for (HealthRecord healthRecord : healthRecords) {
            RecordDTO recordDTO = new RecordDTO();
            recordDTO.setSequenceNo(healthRecord.getRecordSequence());
            recordDTO.setDate(healthRecord.getTimestamp().getTime());
            recordDTO.setNote(healthRecord.getNote());
            recordDTOS.add(recordDTO);

            Set<PatientSymptom> symptomSet = healthRecord.getPatientSymptoms();

            if (symptomSet == null) continue;

            List<PatientSymptomDTO> dtos = new ArrayList<>();
            recordDTO.setSymptoms(dtos);

            for (PatientSymptom ps : symptomSet) {
                PatientSymptomDTO patientSymptomDTO = new PatientSymptomDTO();
                patientSymptomDTO.setName(ps.getSymptom().getDisplayName());
                patientSymptomDTO.setSeverity(ps.getSeverity());
                dtos.add(patientSymptomDTO);
            }
        }
        return recordDTOS;
    }
}
