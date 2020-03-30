package org.embryyo.corona.service.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.embryyo.corona.service.dto.PatientDTO;
import org.embryyo.corona.service.dto.RecordDTO;
import org.embryyo.corona.service.exception.EnricherException;
import org.embryyo.corona.service.model.HealthRecord;
import org.embryyo.corona.service.model.Patient;
import org.embryyo.corona.service.model.QuarantineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Enricher {

    public PatientDTO fromPatientDO(Patient p) {
        PatientDTO patient = new PatientDTO();
        patient.setId(p.getId());

        // personal info
        patient.setFirstName(p.getFirstName());
        patient.setLastName(p.getLastName());
        patient.setDob(p.getDob() == null ? 0 : p.getDob().getTime());
        patient.setGender(p.getGender());
        patient.setAge(p.getAge());

        // contact details
        patient.setMobileNumber(p.getMobileNumber());
        patient.setAddress(p.getAddress());
        patient.setCity(p.getCity());
        patient.setPincode(p.getPincode());
        patient.setState(p.getState());

        String type = p.getQuarantineType() == null ? null : p.getQuarantineType().toString();
        patient.setQuarantineType(type);

        patient.setQuarantineStartDate(p.getQuarantineStartDate() == null ? 0 :
                p.getQuarantineStartDate().getTime());
        patient.setQuarantineEndDate(p.getQuarantineEndDate() == null ? 0 :
                p.getQuarantineEndDate().getTime());

        // covid related data
        patient.setAdvice(p.getAdvice());
        patient.setCaseType(p.getCaseType());
        patient.setCovidState(p.getCovidState());
        patient.setIsHighRisk(p.isHighRisk());
        patient.setMoniorState(p.getMoniorState());
        patient.setPatientId(p.getPatientId());
        patient.setTransmissionType(p.getTransmissionType());

        try {
            String coMorbidities = p.getCoMorbidities();
            String covidRiskFactors = p.getCovidRiskFactors();
            ObjectMapper objectMapper = new ObjectMapper();
            if (coMorbidities != null && !coMorbidities.isEmpty()) {
                List<String> coMorbiditiesList =
                        objectMapper.readValue(coMorbidities,
                                new TypeReference<List<String>>() {
                                });
                patient.setCoMorbidities(coMorbiditiesList);
            }
            if (covidRiskFactors != null && !covidRiskFactors.isEmpty()) {
                List<String> covidRiskFactorsList = objectMapper.readValue(covidRiskFactors,
                        new TypeReference<List<String>>() {
                        });
                patient.setCovidRiskFactors(covidRiskFactorsList);
            }

        } catch (IOException e) {
            throw new EnricherException("Cannot parse morbidities/riskfactors from DO to DTO");
        }
        return patient;
    }

    public Patient fromPatientDTO(PatientDTO p) {
        Patient patient = new Patient();
        // personal info
        patient.setDob(new Date(p.getDob()));
        patient.setFirstName(p.getFirstName());
        patient.setLastName(p.getLastName());
        patient.setGender(p.getGender());
        patient.setAge(p.getAge());

        // contact info
        patient.setMobileNumber(p.getMobileNumber());
        patient.setAddress(p.getAddress());
        patient.setCity(p.getCity());
        patient.setPincode(p.getPincode());
        patient.setState(p.getState());

        // covid info
        patient.setAdvice(p.getAdvice());
        patient.setCaseType(p.getCaseType());
        patient.setCovidState(p.getCovidState());
        patient.setHighRisk(p.getIsHighRisk());
        patient.setMoniorState(p.getMoniorState());
        patient.setPatientId(p.getPatientId());
        patient.setTransmissionType(p.getTransmissionType());

        QuarantineType quarantineType = getQuarantineType(p.getQuarantineType());
        patient.setQuarantineType(quarantineType);
        patient.setQuarantineStartDate(new Timestamp(p.getQuarantineStartDate()));
        patient.setQuarantineEndDate(new Timestamp(p.getQuarantineEndDate()));

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            if (p.getCoMorbidities() != null) {
                String coMorbidities = objectMapper.writeValueAsString(p.getCoMorbidities());
                patient.setCoMorbidities(coMorbidities);
            }
            if (p.getCovidRiskFactors() != null) {
                String covidRiskFactors = objectMapper.writeValueAsString(p.getCovidRiskFactors());
                patient.setCovidRiskFactors(covidRiskFactors);
            }
        } catch (JsonProcessingException e) {
            throw new EnricherException("Cannot parse morbidities/riskfactors from DTO to DO");
        }
        return patient;
    }

    private QuarantineType getQuarantineType(String quarantineType) {
        if (QuarantineType.HOME.toString().equalsIgnoreCase(quarantineType)) {
            return QuarantineType.HOME;
        } else if (QuarantineType.CENTRE.toString().equalsIgnoreCase(quarantineType)) {
            return QuarantineType.CENTRE;
        }
        return QuarantineType.UNKNOWN;
    }

    public HealthRecord fromRecordDTO(RecordDTO recordDTO) {
        HealthRecord healthRecord = new HealthRecord();
        return healthRecord;
    }
}
