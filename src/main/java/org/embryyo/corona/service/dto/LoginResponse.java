package org.embryyo.corona.service.dto;

import org.embryyo.corona.service.dto.PatientDTO;

public class LoginResponse {
    private boolean isNewUser;
    private PatientDTO patient;
    private String authToken;

    public LoginResponse(boolean isNewUser, String authToken) {
        this.isNewUser = isNewUser;
    }

    public LoginResponse(PatientDTO patient, String authToken) {
        this.patient = patient;
    }

    public boolean getIsNewUser() {
        return isNewUser;
    }

    public void setIsNewUser(boolean newUser) {
        isNewUser = newUser;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public String getAuthToken() {
        return authToken;
    }
}
