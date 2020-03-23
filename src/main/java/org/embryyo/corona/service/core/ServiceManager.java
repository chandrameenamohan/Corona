package org.embryyo.corona.service.core;

import org.embryyo.corona.service.dto.*;
import org.embryyo.corona.service.exception.AuthFailException;
import org.embryyo.corona.service.exception.InvalidOTPException;
import org.embryyo.corona.service.model.Otp;
import org.embryyo.corona.service.model.Patient;
import org.embryyo.corona.service.model.PatientSymptom;
import org.embryyo.corona.service.model.Symptom;
import org.embryyo.corona.service.repo.OtpRepository;
import org.embryyo.corona.service.repo.PatientRepository;
import org.embryyo.corona.service.repo.PatientSymptomRepository;
import org.embryyo.corona.service.repo.SymptopRepository;
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

    public Patient register(Patient patient) {
        Patient p = patientRepository.save(patient);
        return p;
    }

    public Patient getPatient(int id) {
        return patientRepository.findById(id).get();
    }

    public void addPatientSymptom(RecordDTO record, int patientId) {
    }

    public void addSymptom(Symptom s) {
        symptopRepository.save(s);
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
}
