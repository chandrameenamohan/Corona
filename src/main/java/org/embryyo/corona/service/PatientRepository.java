package org.embryyo.corona.service;

import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
    public Patient findByMobileNumber(String mobileNumber);
}
