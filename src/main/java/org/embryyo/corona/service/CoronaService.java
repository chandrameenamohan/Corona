package org.embryyo.corona.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class CoronaService {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CoronaService.class);
//        app.setDefaultProperties(Collections
//                .singletonMap("server.port", "5000"));
        app.run(args);
    }
}
