package org.embryyo.corona.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ServiceManager {

    private Map<String,Patient> memDB = new HashMap<>();

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private PatientRepository patientRepository;

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
        return new LoginResponse(patient);
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
}
