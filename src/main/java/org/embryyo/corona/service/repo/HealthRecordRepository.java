package org.embryyo.corona.service.repo;

import org.embryyo.corona.service.model.HealthRecord;
import org.springframework.data.repository.CrudRepository;

public interface HealthRecordRepository extends CrudRepository<HealthRecord, Integer> {
}
