package org.embryyo.corona.service.controller;

import org.embryyo.corona.service.dto.HealthWorkerDTO;
import org.embryyo.corona.service.dto.LocationDTO;
import org.embryyo.corona.service.dto.PatientDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {

    @PostMapping(path = "/locations")
    public void addLocation(@RequestBody LocationDTO locationDTO) {

    }

    @GetMapping(path = "/locations/{id}")
    public List<LocationDTO> getLocations(@PathVariable int id) {
        return null;
    }

    @GetMapping(path = "/locations")
    public List<LocationDTO> getAllLocations() {
        return null;
    }

    @PutMapping(path = "/locations/{locationId}/patients/{patientId}")
    public PatientDTO assignPatientToLocation(@PathVariable(name = "locationId") int workerId,
                                                    @PathVariable(name = "patientId") int patientId) {
        return null;
    }

    @GetMapping(path = "/locations/{locationId}/patients/{patientId}")
    public PatientDTO getPatientOfLocation(@PathVariable(name = "locationId") int workerId,
                                                     @PathVariable(name = "patientId") int patientId) {
        return null;
    }

    @GetMapping(path = "/locations/{locationId}/patients")
    public List<PatientDTO> getAllPatientsOfLocation(@PathVariable(name = "locationId") int workerId) {
        return null;
    }

    @GetMapping(path = "/locations/{locationId}/healthworkers")
    public List<HealthWorkerDTO> getAllHealthWorkersOfLocation(@PathVariable(name = "locationId") int workerId) {
        return null;
    }
}
