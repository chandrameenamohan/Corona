package org.embryyo.corona.service.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    private String address;
    private String city;
    private String town;
    private String state;
    private String country;

    private String contactNumbers;

    @Enumerated(EnumType.STRING)
    private LocationType quarantineType;

    private String latitute;
    private String longitude;

    @ManyToMany(mappedBy = "workLocations", fetch = FetchType.LAZY)
    Set<HealthWorker> healthWorkers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    private Set<Patient> patients;
}
