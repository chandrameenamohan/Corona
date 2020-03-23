package org.embryyo.corona.service;

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
    private PatientEnricher patientEnricher;

    public LoginResponse login(LoginRequest loginRequest) {
        /**
         * TODO: Add the logic of login
         * verify number and otp
         * if new user then send register request
         * else context
         */
        Patient patient = verifyAndGet(loginRequest);
        if (patient == null) {
            return new LoginResponse(true);
        }
        return new LoginResponse(patientEnricher.from(patient));
    }

    private Patient verifyAndGet(LoginRequest loginRequest) {
        Otp otpObj = otpRepository.findByMobileNumber(loginRequest.getNumber());
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

    public void getOtp(String number) {
        // TODO: Send otp using SMS Service;
        String otp = generateOtp(number);
        Otp otpObj = new Otp();
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
        // TODO: send randomNum as otp through sms service
        return "000000";
    }

    public Patient register(Patient patient) {
        Patient p = patientRepository.save(patient);
        return p;
    }

    public Patient getPatient(int id) {
        return patientRepository.findById(id).get();
    }

    public void addPatientSymptom(List<SymptomRecord> record, int patientId) {
        List<Symptom> symptoms = new ArrayList<>();
        Map<String,SymptomRecord> symptomToRecord = new HashMap<>();
        for (SymptomRecord sr : record) {
            Symptom s = symptopRepository
                    .findByName(sr.getSymptom());
            symptoms.add(s);
            symptomToRecord.put(sr.getSymptom(),sr);
        }
        List<PatientSymptom> patientSymptoms = new ArrayList<>();
        Patient p = patientRepository.findById(patientId).get();
        for (Symptom s : symptoms) {
            PatientSymptom patientSymptom = new PatientSymptom();
            patientSymptom.setPatient(p);
            patientSymptom.setSymptom(s);
            SymptomRecord sr = symptomToRecord.get(s.getName());
            patientSymptom.setSeverity(sr.getSeverity());
            patientSymptom.setNote(sr.getComment());
            patientSymptom.setTime(new Timestamp(sr
                    .getTime().getTime()));
            patientSymptoms.add(patientSymptom);
        }
        for (PatientSymptom ps : patientSymptoms) patientSymptomRepository.save(ps);
    }

    public void addSymptom(Symptom s) {
        symptopRepository.save(s);
    }
}
