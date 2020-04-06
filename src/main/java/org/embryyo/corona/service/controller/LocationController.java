package org.embryyo.corona.service.controller;

import org.embryyo.corona.service.core.ServiceManager;
import org.embryyo.corona.service.dto.HealthWorkerDTO;
import org.embryyo.corona.service.dto.LocationDTO;
import org.embryyo.corona.service.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private ServiceManager serviceManager;

    @PostMapping(path = "/locations")
    public void addLocation(@RequestBody LocationDTO locationDTO,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        int id = serviceManager.addLocation(locationDTO);
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/locations/" + id).toUriString());
    }

    @GetMapping(path = "/locations/{id}")
    public LocationDTO getLocations(@PathVariable int id) {
        LocationDTO locationDTO = serviceManager.getLocation(id);
        return locationDTO;
    }

    @GetMapping(path = "/locations")
    public List<LocationDTO> getAllLocations() {
        List<LocationDTO> locationDTOS = serviceManager.getAllLocations();
        return locationDTOS;
    }

    @PutMapping(path = "/locations/{locationId}/patients/{patientId}")
    public void assignPatientToLocation(@PathVariable(name = "locationId") int locationId,
                                                    @PathVariable(name = "patientId") int patientId) {
        serviceManager.mapPatientAndLocation(locationId,patientId);
    }

    @GetMapping(path = "/locations/{locationId}/patients/{patientId}")
    public PatientDTO getPatientOfLocation(@PathVariable(name = "locationId") int workerId,
                                                     @PathVariable(name = "patientId") int patientId) {
        return null;
    }

    @GetMapping(path = "/locations/{locationId}/patients")
    public List<PatientDTO> getAllPatientsOfLocation(@PathVariable(name = "locationId") int locationId) {
        List<PatientDTO> patientDTOS = serviceManager.getAllPatientsOfLocation(locationId);
        return patientDTOS;
    }

    @GetMapping(path = "/locations/{locationId}/healthworkers")
    public List<HealthWorkerDTO> getAllHealthWorkersOfLocation(@PathVariable(name = "locationId")
                                                                           int locationId) {
        List<HealthWorkerDTO> healthWorkerDTOS = serviceManager.getHealthWorkersOfLocation(locationId);
        return healthWorkerDTOS;
    }
}
