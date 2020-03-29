package org.embryyo.corona.service.core;

import com.twilio.exception.ApiException;
import org.embryyo.corona.service.dto.*;
import org.embryyo.corona.service.exception.AuthFailException;
import org.embryyo.corona.service.exception.InvalidOTPException;
import org.embryyo.corona.service.exception.NotFoundException;
import org.embryyo.corona.service.model.*;
import org.embryyo.corona.service.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ServiceManager {

    @Value( "${app.twilio.enable}" )
    private String isTwilioEnabled;

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
    private LocationRepository locationRepository;

    @Autowired
    private HealthWorkerRepository healthWorkerRepository;

    @Autowired
    private Enricher enricher;

    @Autowired
    private SmsSender smsSender;

    public LoginResponse login(LoginRequest loginRequest) {
        /**
         * TODO: Add the logic of login
         * verify number and otp
         * if new user then send register request
         * else context
         */
        Otp otpObj = otpRepository.findByMobileNumber(loginRequest.getNumber());
        Patient patient = verifyAndGet(otpObj,loginRequest);
        if (patient == null) {
            patient = new Patient();
            patient.setMobileNumber(loginRequest.getNumber());
            patientRepository.save(patient);

            return new LoginResponse(true,otpObj.getToken(),
                    enricher.fromPatientDO(patient));
        }
        return new LoginResponse(enricher.fromPatientDO(patient), otpObj.getToken());
    }

    private Patient verifyAndGet(Otp otpObj, LoginRequest loginRequest) {
        if (otpObj == null) {
            if (otpObj == null) {
                throw new NotFoundException(String
                        .format("Patient is not registered with us: %s",
                                loginRequest.getNumber()));
            }
        }
        // TODO: Add otp expires logic
        if (!otpObj.getOtp().equals(loginRequest.getOtp())) {
            throw new InvalidOTPException(String
                    .format("Given OTP:%s has not matched",loginRequest.getOtp()));
        }
        otpObj.setToken(UUID.randomUUID().toString());
        otpRepository.save(otpObj);
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
                    .format("Patient is not registered for mobile: %s", number));
        }
        if (!otpObj.getToken().equalsIgnoreCase(token)) {
            throw new AuthFailException("authorization failed");
        }
    }

    public void getOtp(String number) {
        String otp = generateOtp(number);
        if ("true".equalsIgnoreCase(isTwilioEnabled)) {
            String number91 = "+91" + number;
            try {
                smsSender.send(otp, number91);
            } catch (ApiException ex) {
                otp = "000000";
            }
        } else {
            otp = "000000";
        }
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
        otpRepository.save(otpObj);
    }

    private String generateOtp(String number) {
        int randomNum = ThreadLocalRandom.current()
                .nextInt(Constant.OTP_MIN, Constant.OTP_MAX + 1);
        return Integer.toString(randomNum);
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
                patientSymptomDTO.setName(ps.getSymptom().getName());
                patientSymptomDTO.setSeverity(ps.getSeverity());
                dtos.add(patientSymptomDTO);
            }
        }
        return recordDTOS;
    }

    public void editProfile(PatientDTO patient, int id) {
        Patient p = enricher.fromPatientDTO(patient);
        p.setId(id);
        patientRepository.save(p);
    }

    public int addLocation(LocationDTO locationDTO) {
        Location location = enricher.fromLocationDTO(locationDTO);
        locationRepository.save(location);
        return location.getId();
    }

    public int addHealthWorker(HealthWorkerDTO healthWorkerDTO) {
        HealthWorker healthWorker = enricher.fromHealthWorkerDTO(healthWorkerDTO);
        healthWorkerRepository.save(healthWorker);
        return healthWorker.getId();
    }

    public void mapWorkerAndLocation(int workerId, int locationId) {
        HealthWorker healthWorker = healthWorkerRepository.findById(workerId).get();
        Location location = locationRepository.findById(locationId).get();
        if (healthWorker.getWorkLocations() == null) {
            healthWorker.setWorkLocations(new HashSet<>());
        }
        healthWorker.getWorkLocations().add(location);
        if (location.getHealthWorkers() == null) {
            location.setHealthWorkers(new HashSet<>());
        }
        location.getHealthWorkers().add(healthWorker);
        healthWorkerRepository.save(healthWorker);
        locationRepository.save(location);
    }
}
