package org.embryyo.corona.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class PatientEnricher {

    public PatientDTO from(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setAddress(patient.getAddress());
        dto.setAge(patient.getAge());
        dto.setCity(patient.getCity());
        dto.setDob(patient.getDob());
        dto.setFirstName(patient.getFirstName());
        dto.setGender(patient.getGender());
        dto.setLastName(patient.getLastName());
        dto.setMobileNumber(patient.getMobileNumber());
        dto.setPincode(patient.getPincode());
        dto.setState(patient.getState());
        Set<PatientSymptom> symptoms = patient.getPatientSymptoms();
        List<PatientSymptomDTO> symptomDTOS = new ArrayList<>();
        dto.setSymptoms(symptomDTOS);
        for (PatientSymptom ps : symptoms) {
            PatientSymptomDTO symptomDTO = new PatientSymptomDTO();
            symptomDTO.setSymptom(ps.getSymptom().getName());
            symptomDTO.setSeverity(ps.getSeverity());
            symptomDTO.setRecordTime(ps.getTime());
            symptomDTO.setComment(ps.getNote());
            symptomDTOS.add(symptomDTO);
        }
        return dto;
    }
}
