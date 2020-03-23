package org.embryyo.corona.service.dto;

import org.embryyo.corona.service.dto.PatientDTO;

public class LoginResponse {
    private boolean isNewUser;
    private PatientDTO patient;

    public LoginResponse(boolean isNewUser) {
        this.isNewUser = isNewUser;
    }

    public LoginResponse(PatientDTO patient) {
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
}
