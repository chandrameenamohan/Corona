package org.embryyo.corona.service.controller;

import org.embryyo.corona.service.core.Enricher;
import org.embryyo.corona.service.core.ServiceManager;
import org.embryyo.corona.service.dto.*;
import org.embryyo.corona.service.model.Patient;
import org.embryyo.corona.service.model.Symptom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class CoronaController {

    @Autowired
    private ServiceManager serviceManager;

    @Autowired
    private Enricher patientEnricher;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        String token = request.getHeader("token");
        serviceManager.requestValidation(loginRequest.getNumber(),token);
        LoginResponse loginResponse = serviceManager.login(loginRequest,token);
        return loginResponse;
    }

    @GetMapping("/otp")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendOTP(@RequestParam("number") String number, HttpServletResponse response) {
        String token = serviceManager.getOtp(number);
        response.setHeader("token",token);
    }

    @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody PatientDTO patient, HttpServletRequest request,
                               HttpServletResponse response) {
        String token = request.getHeader("token");
        serviceManager.requestValidation(patient.getMobileNumber(),token);
        int id = serviceManager.register(patient);
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/patients/" + id).toUriString());
    }

    @GetMapping("/patients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientDTO getPatient(@PathVariable("id") int id, HttpServletRequest request) {
        String token = request.getHeader("token");
        // TODO: Bad Auth Check; Just for testing level
        PatientDTO p = serviceManager.getPatient(id);
        serviceManager.requestValidation(p.getMobileNumber(),token);
        return p;
    }

    @PostMapping("/patients/{id}/symptoms")
    public void addPatientSymptom(@RequestBody RecordDTO record,
                           @PathVariable("id") int id) {
        serviceManager.addPatientSymptom(record,id);
    }

    @PostMapping("/symptoms")
    public void addSymproms(@RequestBody Symptom s) {
        serviceManager.addSymptom(s);
    }

    @GetMapping("/symptoms")
    public List<SymptomDTO> getSymptoms() {
        return serviceManager.getSymptoms();
    }
}
