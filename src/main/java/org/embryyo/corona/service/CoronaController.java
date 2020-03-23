package org.embryyo.corona.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoronaController {

    @Autowired
    private ServiceManager serviceManager;

    @Autowired
    private PatientEnricher patientEnricher;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = serviceManager.login(loginRequest);
        return loginResponse;
    }

    @GetMapping("/otp")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendOTP(@RequestParam("number") String number) {
        serviceManager.getOtp(number);
    }

    @PostMapping("/patients")
    public PatientDTO register(@RequestBody Patient patient) {
        Patient p = serviceManager.register(patient);
        return patientEnricher.from(p);
    }

    @GetMapping("/patients/{id}")
    public PatientDTO getPatient(@PathVariable("id") int id) {
        Patient p = serviceManager.getPatient(id);
        return patientEnricher.from(p);
    }

    @PostMapping("/patients/{id}/symptoms")
    public void addPatientSymptom(@RequestBody List<SymptomRecord> record,
                           @PathVariable("id") int id) {
        serviceManager.addPatientSymptom(record,id);
    }

    @PostMapping("/symptoms")
    public void addSymproms(@RequestBody Symptom s) {
        serviceManager.addSymptom(s);
    }
}
