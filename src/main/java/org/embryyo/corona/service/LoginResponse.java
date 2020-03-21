package org.embryyo.corona.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

public class LoginResponse {
    private boolean isNewUser;
    private Patient patient;

    public LoginResponse(boolean isNewUser) {
        this.isNewUser = isNewUser;
    }

    public LoginResponse(Patient patient) {
        this.patient = patient;
    }

    public boolean getIsNewUser() {
        return isNewUser;
    }

    public void setIsNewUser(boolean newUser) {
        isNewUser = newUser;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
