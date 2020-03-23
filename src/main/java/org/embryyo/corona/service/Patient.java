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
    private Set<PatientSymptoms> patientSymptoms;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<PatientFlow> patientFlows;

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
}
