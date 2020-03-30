package org.embryyo.corona.service.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;

public class PatientDTO {
    private int id;

    private String firstName;
    private String lastName;

    private String mobileNumber;
    private long dob;
    private int age;
    private String gender;

    private String address;
    private String city;
    private String state;
    private int pincode;

    // Covid Related Data;
    private String quarantineType; // home, quarantine-centre
    private long quarantineStartDate;
    private long getQuarantineEndDate;
    private List<String> coMorbidities;
    private List<String> covidRiskFactors;
    private String patientId; //patient code
    private String covidState; // +ive, -ive
    private String moniorState; // suspected, confirmed, active, recovered, deceased
    private String transmissionType; // local, international
    private String caseType; // mild, moderate, severe
    private String advice; // Home Quarantine, Admin and Test, call helpline, symptomatic management
    private boolean isHighRisk; // high-risk individuals

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public boolean getIsHighRisk() {
        return isHighRisk;
    }

    public void setIsHighRisk(boolean highRisk) {
        isHighRisk = highRisk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getDob() {
        return dob;
    }

    public void setDob(long dob) {
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

    public List<String> getCoMorbidities() {
        return coMorbidities;
    }

    public void setCoMorbidities(List<String> coMorbidities) {
        this.coMorbidities = coMorbidities;
    }

    public List<String> getCovidRiskFactors() {
        return covidRiskFactors;
    }

    public void setCovidRiskFactors(List<String> covidRiskFactors) {
        this.covidRiskFactors = covidRiskFactors;
    }

    public String getQuarantineType() {
        return quarantineType;
    }

    public void setQuarantineType(String quarantineType) {
        this.quarantineType = quarantineType;
    }

    public long getQuarantineStartDate() {
        return quarantineStartDate;
    }

    public void setQuarantineStartDate(long quarantineStartDate) {
        this.quarantineStartDate = quarantineStartDate;
    }

    public long getGetQuarantineEndDate() {
        return getQuarantineEndDate;
    }

    public void setGetQuarantineEndDate(long getQuarantineEndDate) {
        this.getQuarantineEndDate = getQuarantineEndDate;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode=" + pincode +
                ", patientId='" + patientId + '\'' +
                ", covidState='" + covidState + '\'' +
                ", moniorState='" + moniorState + '\'' +
                ", transmissionType='" + transmissionType + '\'' +
                ", caseType='" + caseType + '\'' +
                ", advice='" + advice + '\'' +
                ", isHighRisk=" + isHighRisk +
                '}';
    }

    public static void main(String[] args) throws JsonProcessingException {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setState("Karnataka");
        patientDTO.setPincode(560037);
        patientDTO.setMobileNumber("9686399655");
        patientDTO.setLastName("Meena");
        patientDTO.setFirstName("Chandra Mohan");
        patientDTO.setGender("Male");
        patientDTO.setCity("Bangalore");
        patientDTO.setAge(31);
        patientDTO.setAddress("604 1B Soul Space Arista");
        patientDTO.setDob(new Date().getTime());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(patientDTO);
        System.out.println(json);
    }
}
