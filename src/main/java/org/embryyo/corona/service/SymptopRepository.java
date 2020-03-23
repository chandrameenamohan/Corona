package org.embryyo.corona.service;

import org.springframework.data.repository.CrudRepository;

public interface SymptopRepository extends CrudRepository<Symptom, Integer> {
    public Symptom findByName(String name);
}
