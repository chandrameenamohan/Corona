package org.embryyo.corona.service.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class HealthWorker {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String mobile;
    private String emailId;

    @Enumerated(EnumType.STRING)
    private HealthWorkerType workerType;

    //    @JoinTable(name = "worker_location",
//    JoinColumns = {(
//            @JoinColumn(name = "health_worker_id", referencedColumnName = "id", nullable = false, updatable = false)
//    )}, inverseJoinColumns = {
//            @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false, updatable = false)
//        })

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "worker_location",
    joinColumns = @JoinColumn(name = "health_worker_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "id"))
    Set<Location> workLocations;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "worker_patient",
            joinColumns = @JoinColumn(name = "health_worker_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"))
    Set<Patient> patients;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public HealthWorkerType getWorkerType() {
        return workerType;
    }

    public void setWorkerType(HealthWorkerType workerType) {
        this.workerType = workerType;
    }

    public Set<Location> getWorkLocations() {
        return workLocations;
    }

    public void setWorkLocations(Set<Location> workLocations) {
        this.workLocations = workLocations;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
