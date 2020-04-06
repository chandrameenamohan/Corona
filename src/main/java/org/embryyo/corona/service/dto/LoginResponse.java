package org.embryyo.corona.service.dto;

import org.embryyo.corona.service.dto.PatientDTO;

public class LoginResponse {
    private boolean isNewUser;
    private PatientDTO patient;
    private HealthWorkerDTO healthWorkerDTO;
    private String authToken;

    public LoginResponse(boolean isNewUser, String authToken, PatientDTO patient) {
        this.isNewUser = isNewUser;
        this.authToken = authToken;
        this.patient = patient;
    }

    public LoginResponse(PatientDTO patient, String authToken) {
        this.patient = patient;
        this.authToken = authToken;
    }

    public LoginResponse(HealthWorkerDTO healthWorkerDTO, String authToken) {
        this.healthWorkerDTO = healthWorkerDTO;
        this.authToken = authToken;
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

    public HealthWorkerDTO getHealthWorkerDTO() {
        return healthWorkerDTO;
    }

    public void setHealthWorkerDTO(HealthWorkerDTO healthWorkerDTO) {
        this.healthWorkerDTO = healthWorkerDTO;
    }
}
