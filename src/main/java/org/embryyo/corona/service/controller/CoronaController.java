package org.embryyo.corona.service.controller;

import org.embryyo.corona.service.core.Enricher;
import org.embryyo.corona.service.core.ServiceManager;
import org.embryyo.corona.service.dto.*;
import org.embryyo.corona.service.model.Patient;
import org.embryyo.corona.service.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
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
        LoginResponse loginResponse = serviceManager.login(loginRequest);
        return loginResponse;
    }

    @GetMapping("/otp")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendOTP(@RequestParam("number") String number, @RequestParam("role") String role,
                        HttpServletResponse response) {
        serviceManager.getOtp(number, role);
    }

    @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody PatientDTO patient, HttpServletRequest request,
                         @RequestParam("healthWorkerId") int workerId,
                         HttpServletResponse response) {
        String token = request.getHeader("token");
        serviceManager.requestValidation(patient.getMobileNumber(), workerId, token);
        int id = serviceManager.register(patient);
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/patients/" + id).toUriString());
    }

    @PostMapping("/patients/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void editProfile(@RequestBody PatientDTO patient, @PathVariable("id") int id,
                            @RequestParam("healthWorkerId") int workerId,
                            HttpServletRequest request,
                         HttpServletResponse response) {
        String token = request.getHeader("token");
        serviceManager.requestValidation(patient.getMobileNumber(), workerId, token);
        serviceManager.editProfile(patient,id);
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/patients/" + id).toUriString());
    }

    @GetMapping("/patients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientDTO getPatient(@PathVariable("id") int id,
                                 @RequestParam("healthWorkerId") int workerId,
                                 HttpServletRequest request) {
        String token = request.getHeader("token");
        // TODO: Bad Auth Check; Just for testing level
        PatientDTO p = serviceManager.getPatient(id);
        serviceManager.requestValidation(p.getMobileNumber(), workerId, token);
        return p;
    }

    @PostMapping("/patients/{id}/symptoms")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPatientSymptom(@RequestBody RecordDTO record,
                                  @RequestParam("healthWorkerId") int workerId,
                           @PathVariable("id") int id, HttpServletRequest request) {
        serviceManager.addPatientSymptom(record,id,workerId,request.getHeader("token"));
    }

    @GetMapping("/patients/{id}/symptoms")
    public List<RecordDTO> getPatientSymptom(@PathVariable("id") int id,
                                             @RequestParam("healthWorkerId") int workerId,
                                             HttpServletRequest request) {
        return serviceManager.getPatientSymptoms(id,workerId,request.getHeader("token"));
    }

    @PostMapping("/symptoms")
    public void addSymptoms(@RequestBody SymptomDTO s) {
        serviceManager.addSymptom(s);
    }

    @GetMapping("/symptoms")
    public List<SymptomDTO> getSymptoms() {
        return serviceManager.getSymptoms();
    }


    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/testing/patients")
    public List<Patient> getALlPatients() {
        List<Patient> patients = new ArrayList<>();
        Iterator<Patient> patientIterator = patientRepository.findAll().iterator();
        while (patientIterator.hasNext()) {
            Patient p = patientIterator.next();
            p.setHealthWorkers(null);
            p.setHealthRecords(null);
            p.setLocation(null);
            patients.add(p);
        }
        return patients;
    }

    @GetMapping("/encrypt")
    public void encrypt() {
//        serviceManager.encryptAll();
    }
}
