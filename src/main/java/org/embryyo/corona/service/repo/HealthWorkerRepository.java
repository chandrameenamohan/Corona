package org.embryyo.corona.service.repo;

import org.embryyo.corona.service.model.HealthWorker;
import org.springframework.data.repository.CrudRepository;

public interface HealthWorkerRepository extends CrudRepository<HealthWorker, Integer> {
    public HealthWorker findByMobile(String mobile);
}
