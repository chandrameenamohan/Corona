package org.embryyo.corona.service.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.embryyo.corona.service.dto.HealthWorkerDTO;
import org.embryyo.corona.service.dto.LocationDTO;
import org.embryyo.corona.service.dto.PatientDTO;
import org.embryyo.corona.service.dto.RecordDTO;
import org.embryyo.corona.service.exception.EnricherException;
import org.embryyo.corona.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

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

    public Location fromLocationDTO(LocationDTO locationDTO) {
        Location location = new Location();
        location.setName(locationDTO.getName());
        location.setAddress(locationDTO.getAddress());
        location.setCity(locationDTO.getCity());
        location.setCountry(locationDTO.getCountry());
        location.setLatitute(locationDTO.getLatitude());
        location.setLongitude(locationDTO.getLongitude());
        location.setPincode(locationDTO.getPincode());
        location.setState(locationDTO.getState());
        location.setTown(locationDTO.getTown());
        LocationType locationType = getLocationType(locationDTO);
        String contactNumbers = getContactNumber(locationDTO);
        location.setQuarantineType(locationType);
        location.setContactNumbers(contactNumbers);
        return location;
    }

    private String getContactNumber(LocationDTO locationDTO) {
        String contacts = null;
        try {
            Map<String,String> contactNumbers = locationDTO.getContactNumbers();
            if (contactNumbers == null || contactNumbers.isEmpty()) return contacts;
            ObjectMapper objectMapper = new ObjectMapper();
            contacts = objectMapper.writeValueAsString(contactNumbers);
        } catch (JsonProcessingException e) {
            throw new EnricherException("Failed to parse contactMap data from db to dto");
        }
        return contacts;
    }

    private LocationType getLocationType(LocationDTO locationDTO) {
        if (LocationType.HOSPITAL.toString().equalsIgnoreCase(locationDTO.getLocationType())) {
            return LocationType.HOSPITAL;
        } else if (LocationType.HOSTEL.toString().equalsIgnoreCase(locationDTO.getLocationType())) {
            return LocationType.HOSTEL;
        }
        return LocationType.UNKNOWN;
    }

    public HealthWorker fromHealthWorkerDTO(HealthWorkerDTO healthWorkerDTO) {
        HealthWorker healthWorker = new HealthWorker();
        healthWorker.setName(healthWorkerDTO.getName());
        HealthWorkerType workerType = whichRole(healthWorkerDTO.getRole());
        healthWorker.setWorkerType(workerType);
        healthWorker.setMobile(healthWorkerDTO.getMobile());
        healthWorker.setEmailId(healthWorkerDTO.getEmailId());
        return healthWorker;
    }

    private HealthWorkerType whichRole(String role) {
        if (HealthWorkerType.DOCTOR.toString().equalsIgnoreCase(role)) {
            return HealthWorkerType.DOCTOR;
        } else if (HealthWorkerType.NURSE.toString().equalsIgnoreCase(role)) {
            return HealthWorkerType.NURSE;
        }
        return HealthWorkerType.UNKNOWN;
    }

    public HealthWorkerDTO fromHealthWorkerDO(HealthWorker healthWorker) {
        HealthWorkerDTO healthWorkerDTO = new HealthWorkerDTO();
        healthWorkerDTO.setEmailId(healthWorker.getEmailId());
        healthWorkerDTO.setId(healthWorker.getId());
        healthWorkerDTO.setMobile(healthWorker.getMobile());
        healthWorkerDTO.setName(healthWorker.getName());
        healthWorkerDTO.setRole(healthWorker.getWorkerType().toString());
        return healthWorkerDTO;
    }

    public LocationDTO fromLocationDO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setAddress(location.getAddress());
        locationDTO.setCity(location.getCity());
        locationDTO.setCountry(location.getCountry());
        locationDTO.setId(location.getId());
        locationDTO.setLongitude(location.getLongitude());
        locationDTO.setLatitude(location.getLatitute());
        locationDTO.setLocationType(location.getQuarantineType().toString());
        locationDTO.setPincode(location.getPincode());
        locationDTO.setState(location.getState());
        locationDTO.setName(location.getName());
        locationDTO.setTown(location.getTown());
        Map<String, String> contactMap = getContactMap(location);
        locationDTO.setContactNumbers(contactMap);
        return locationDTO;
    }

    private Map<String, String> getContactMap(Location location) {
        String contacts = location.getContactNumbers();
        Map<String, String> contactMap = new HashMap<>();
        if (contacts == null || contacts.isEmpty()) return contactMap;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            contactMap = objectMapper.readValue(contacts,
                    new TypeReference<Map<String,String>>() {
            });
        } catch (IOException e) {
            throw new EnricherException("Failed to parse contactMap data from db to dto");
        }
        return contactMap;
    }
}
