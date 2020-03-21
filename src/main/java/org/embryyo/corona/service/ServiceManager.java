package org.embryyo.corona.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceManager {

    private Map<String,Patient> memDB = new HashMap<>();

    public LoginResponse login(LoginRequest loginRequest) {
        /**
         * TODO: Add the logic of login
         */
        LoginResponse loginResponse = new LoginResponse(loginRequest.getNumber(),loginRequest.getNumber());
        return loginResponse;
    }

    public String getOtp(String number) {
        // TODO: Send otp using SMS Service;
        return "000000";
    }

    public Patient register(Patient patient) {
        memDB.put(patient.getGuid(),patient);
        return patient;
    }

    public Patient getPatient(String id) {
        return memDB.get(id);
    }
}
