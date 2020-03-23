package org.embryyo.corona.service;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class PatientFlow {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private Date start;
    private Date end;

    private String travelMode;
    private String note; // Met father at Father's home

    private String place;
    private String city;
    private String state;
}
