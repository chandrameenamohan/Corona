package org.embryyo.corona.service.controller;

import org.embryyo.corona.service.core.Enricher;
import org.embryyo.corona.service.core.ServiceManager;
import org.embryyo.corona.service.dto.*;
import org.embryyo.corona.service.model.Patient;
import org.embryyo.corona.service.model.Symptom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public PatientDTO register(@RequestBody Patient patient) {
        Patient p = serviceManager.register(patient);
        return patientEnricher.fromPatientDO(p);
    }

    @GetMapping("/patients/{id}")
    public PatientDTO getPatient(@PathVariable("id") int id) {
        Patient p = serviceManager.getPatient(id);
        return patientEnricher.fromPatientDO(p);
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
