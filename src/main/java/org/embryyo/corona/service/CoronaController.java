package org.embryyo.corona.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CoronaController {

    @Autowired
    private ServiceManager serviceManager;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = serviceManager.login(loginRequest);
        return loginResponse;
    }

    @GetMapping("/otp")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendOTP(@RequestParam("number") String number) {
        serviceManager.getOtp(number);
    }

    @PostMapping("/register")
    public Patient register(@RequestBody Patient patient) {
        Patient p = serviceManager.register(patient);
        return p;
    }

    @GetMapping("/patient/{guid}")
    public Patient getPatient(@PathVariable("guid") String id) {
        Patient p = serviceManager.getPatient(id);
        return p;
    }
}
