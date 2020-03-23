package org.embryyo.corona.service.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Otp {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String mobileNumber;

    private String otp;

    private Timestamp storingTime;

    private int expireTimeInSeconds;

    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Timestamp getStoringTime() {
        return storingTime;
    }

    public void setStoringTime(Timestamp storingTime) {
        this.storingTime = storingTime;
    }

    public int getExpireTimeInSeconds() {
        return expireTimeInSeconds;
    }

    public void setExpireTimeInSeconds(int expireTimeInSeconds) {
        this.expireTimeInSeconds = expireTimeInSeconds;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
