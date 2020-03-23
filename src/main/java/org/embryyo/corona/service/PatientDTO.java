package org.embryyo.corona.service;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class PatientDTO {
    private int id;

    private String firstName;
    private String lastName;

    private String mobileNumber;
    private Date dob;
    private int age;
    private String gender;

    private String address;
    private String city;
    private String state;
    private int pincode;

    private List<PatientSymptomDTO> symptoms;

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
                ", symptoms=" + symptoms +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDTO dto = (PatientDTO) o;
        return id == dto.id &&
                age == dto.age &&
                pincode == dto.pincode &&
                Objects.equals(firstName, dto.firstName) &&
                Objects.equals(lastName, dto.lastName) &&
                Objects.equals(mobileNumber, dto.mobileNumber) &&
                Objects.equals(dob, dto.dob) &&
                Objects.equals(gender, dto.gender) &&
                Objects.equals(address, dto.address) &&
                Objects.equals(city, dto.city) &&
                Objects.equals(state, dto.state) &&
                Objects.equals(symptoms, dto.symptoms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, mobileNumber, dob, age, gender, address, city, state, pincode, symptoms);
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

    public List<PatientSymptomDTO> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<PatientSymptomDTO> symptoms) {
        this.symptoms = symptoms;
    }

}
