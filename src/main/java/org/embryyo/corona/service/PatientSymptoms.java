package org.embryyo.corona.service;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class PatientSymptoms {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "symptom_id", nullable = false)
    private Symptom symptom;

    private String severity;
    private String note;
    private Date time;

}
