package org.embryyo.corona.service;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Symptom {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "symptom")
    private Set<PatientSymptoms> patientSymptoms;
}
