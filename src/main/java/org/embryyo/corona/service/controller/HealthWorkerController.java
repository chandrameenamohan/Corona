package org.embryyo.corona.service.controller;

import org.embryyo.corona.service.dto.HealthWorkerDTO;
import org.embryyo.corona.service.dto.LocationDTO;
import org.embryyo.corona.service.dto.PatientDTO;
import org.embryyo.corona.service.model.HealthWorker;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HealthWorkerController {

    @PostMapping(path = "/healthworkers")
    public void addHealthWorker(@RequestBody HealthWorkerDTO healthWorkerDTO) {

    }

    @GetMapping(path = "/healthworkers")
    public List<HealthWorkerDTO> addAllHealthWorker() {
        return null;
    }

    @GetMapping(path = "/healthworkers/{id}")
    public HealthWorkerDTO addHealthWorker(@PathVariable int id) {
        return null;
    }

    @PutMapping(path = "/healthworkers/{workerId}/patients/{patientId}")
    public void assignPatientToHealthWorker(@PathVariable(name = "workerId") int workerId,
                                            @PathVariable(name = "patientId") int patientId) {

    }

    @PutMapping(path = "/healthworkers/{workerId}/locations/{locationId}")
    public void assignHealthWorkerToLocation(@PathVariable(name = "workerId") int workerId,
                                             @PathVariable(name = "locationId") int patientId) {

    }

    @PutMapping(path = "/healthworkers/{workerId}/patients")
    public List<PatientDTO> getHealthWorkerPatients(@PathVariable(name = "workerId") int workerId) {
        return null;
    }

    @PutMapping(path = "/healthworkers/{workerId}/locations")
    public List<LocationDTO> getHealthWorkerLocations(@PathVariable(name = "workerId") int workerId) {
        return null;
    }
}
