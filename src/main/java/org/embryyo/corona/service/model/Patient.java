package org.embryyo.corona.service.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<HealthRecord> healthRecords;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToMany(mappedBy = "patients", fetch = FetchType.LAZY)
    Set<HealthWorker> healthWorkers;

    @Column(unique = true)
    private String patientId; // this is to match with his global patient-code

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String mobileNumber;
    private Date dob;
    private int age;
    private String gender;

    private String address;
    private String city;
    private String state;
    private int pincode;

    // Covid Related Data;
    private String coMorbidities;
    private String covidRiskFactors;
    private String covidState; // +ive, -ive
    private String moniorState; // suspected, confirmed, active, recovered, deceased
    private String transmissionType; // local, international
    private String caseType; // mild, moderate, severe
    private String advice; // Home Quarantine, Admin and Test, call helpline, symptomatic management
    private boolean isHighRisk; // high-risk individuals

    @Enumerated(EnumType.STRING)
    private QuarantineType quarantineType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public Set<HealthRecord> getHealthRecords() {
        return healthRecords;
    }

    public void setHealthRecords(Set<HealthRecord> healthRecords) {
        this.healthRecords = healthRecords;
    }

    public String getCovidState() {
        return covidState;
    }

    public void setCovidState(String covidState) {
        this.covidState = covidState;
    }

    public String getMoniorState() {
        return moniorState;
    }

    public void setMoniorState(String moniorState) {
        this.moniorState = moniorState;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public boolean isHighRisk() {
        return isHighRisk;
    }

    public void setHighRisk(boolean highRisk) {
        isHighRisk = highRisk;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCoMorbidities() {
        return coMorbidities;
    }

    public void setCoMorbidities(String coMorbidities) {
        this.coMorbidities = coMorbidities;
    }

    public String getCovidRiskFactors() {
        return covidRiskFactors;
    }

    public void setCovidRiskFactors(String covidRiskFactors) {
        this.covidRiskFactors = covidRiskFactors;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<HealthWorker> getHealthWorkers() {
        return healthWorkers;
    }

    public void setHealthWorkers(Set<HealthWorker> healthWorkers) {
        this.healthWorkers = healthWorkers;
    }

    public QuarantineType getQuarantineType() {
        return quarantineType;
    }

    public void setQuarantineType(QuarantineType quarantineType) {
        this.quarantineType = quarantineType;
    }
}
