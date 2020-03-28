package org.embryyo.corona.service.repo;

import org.embryyo.corona.service.model.Symptom;
import org.springframework.data.repository.CrudRepository;

public interface SymptopRepository extends CrudRepository<Symptom, Integer> {
    public Symptom findByName(String name);
}
