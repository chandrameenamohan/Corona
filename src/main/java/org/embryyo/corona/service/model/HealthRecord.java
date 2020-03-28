package org.embryyo.corona.service.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
public class HealthRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private long recordSequence;
    private Timestamp timestamp;
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "healthRecord")
    private Set<PatientSymptom> patientSymptoms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getRecordSequence() {
        return recordSequence;
    }

    public void setRecordSequence(long recordSequence) {
        this.recordSequence = recordSequence;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<PatientSymptom> getPatientSymptoms() {
        return patientSymptoms;
    }

    public void setPatientSymptoms(Set<PatientSymptom> patientSymptoms) {
        this.patientSymptoms = patientSymptoms;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
