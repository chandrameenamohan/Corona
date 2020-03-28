package org.embryyo.corona.service.repo;

import org.embryyo.corona.service.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
    public Patient findByMobileNumber(String mobileNumber);
}
