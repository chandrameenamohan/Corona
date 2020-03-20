package org.embryyo.corona.service;

import lombok.Getter;
import lombok.Setter;

public class LoginResponse {
    private String userGUID;
    private String name;

    public LoginResponse(String userGUID, String name) {
        this.userGUID = userGUID;
        this.name = name;
    }

    public String getUserGUID() {
        return userGUID;
    }

    public void setUserGUID(String userGUID) {
        this.userGUID = userGUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "userGUID='" + userGUID + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
