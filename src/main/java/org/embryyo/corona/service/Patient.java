package org.embryyo.corona.service;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<PatientSymptom> patientSymptoms;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
//    private Set<PatientFlow> patientFlows;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<PatientSymptom> getPatientSymptoms() {
        return patientSymptoms;
    }

    public void setPatientSymptoms(Set<PatientSymptom> patientSymptoms) {
        this.patientSymptoms = patientSymptoms;
    }

//    public Set<PatientFlow> getPatientFlows() {
//        return patientFlows;
//    }
//
//    public void setPatientFlows(Set<PatientFlow> patientFlows) {
//        this.patientFlows = patientFlows;
//    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
}
