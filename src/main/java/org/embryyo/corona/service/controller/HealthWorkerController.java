package org.embryyo.corona.service.controller;

import org.embryyo.corona.service.core.ServiceManager;
import org.embryyo.corona.service.dto.HealthWorkerDTO;
import org.embryyo.corona.service.dto.LocationDTO;
import org.embryyo.corona.service.dto.PatientDTO;
import org.embryyo.corona.service.model.HealthWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class HealthWorkerController {

    @Autowired
    private ServiceManager serviceManager;

    @PostMapping(path = "/healthworkers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addHealthWorker(@RequestBody HealthWorkerDTO healthWorkerDTO,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        int id = serviceManager.addHealthWorker(healthWorkerDTO);
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/healthworkers/" + id).toUriString());
    }

    @GetMapping(path = "/healthworkers")
    public List<HealthWorkerDTO> addAllHealthWorker() {
        return null;
    }

    @GetMapping(path = "/healthworkers/{id}")
    public HealthWorkerDTO addHealthWorker(@PathVariable int id) {
        HealthWorkerDTO healthWorkerDTO = serviceManager.getHealthWorker(id);
        return healthWorkerDTO;
    }

    @PutMapping(path = "/healthworkers/{workerId}/patients/{patientId}")
    public void assignPatientToHealthWorker(@PathVariable(name = "workerId") int workerId,
                                            @PathVariable(name = "patientId") int patientId,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
        serviceManager.addPatientToHealthWorker(workerId,patientId);
    }

    @PutMapping(path = "/healthworkers/{workerId}/locations/{locationId}")
    public void assignHealthWorkerToLocation(@PathVariable(name = "workerId") int workerId,
                                             @PathVariable(name = "locationId") int patientId,
                                             HttpServletRequest request,
                                             HttpServletResponse response) {
        serviceManager.mapWorkerAndLocation(workerId,patientId);
    }

    @PutMapping(path = "/healthworkers/{workerId}/locations")
    public void assignHealthWorkerToLocations(@PathVariable(name = "workerId") int workerId,
                                             @RequestBody List<Integer> ids,
                                             HttpServletRequest request,
                                             HttpServletResponse response) {
        serviceManager.mapWorkerAndLocations(workerId,ids);
    }

    @GetMapping(path = "/healthworkers/{workerId}/patients")
    public List<PatientDTO> getHealthWorkerPatients(@PathVariable(name = "workerId")
                                                                int workerId) {
        List<PatientDTO> patients = serviceManager.getHealthWorkerPatients(workerId);
        return patients;
    }

    @GetMapping(path = "/healthworkers/{workerId}/locations")
    public List<LocationDTO> getHealthWorkerLocations(@PathVariable(name = "workerId") int workerId) {
        List<LocationDTO> locations = serviceManager.getHealthWorkerLocations(workerId);
        return locations;
    }
}
