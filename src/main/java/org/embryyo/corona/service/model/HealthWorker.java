package org.embryyo.corona.service.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class HealthWorker {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
    private String number;

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
}
