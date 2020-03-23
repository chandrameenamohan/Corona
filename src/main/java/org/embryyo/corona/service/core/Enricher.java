package org.embryyo.corona.service.core;

import org.embryyo.corona.service.dto.PatientDTO;
import org.embryyo.corona.service.dto.RecordDTO;
import org.embryyo.corona.service.model.HealthRecord;
import org.embryyo.corona.service.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        patient.setDob(p.getDob().getTime());
        patient.setGender(p.getGender());
        patient.setAge(p.getAge());

        // contact details
        patient.setMobileNumber(p.getMobileNumber());
        patient.setAddress(p.getAddress());
        patient.setCity(p.getCity());
        patient.setPincode(p.getPincode());
        patient.setState(p.getState());

        // covid related data
        patient.setAdvice(p.getAdvice());
        patient.setCaseType(p.getCaseType());
        patient.setCovidState(p.getCovidState());
        patient.setIsHighRisk(p.isHighRisk());
        patient.setMoniorState(p.getMoniorState());
        patient.setPatientId(p.getPatientId());
        patient.setTransmissionType(p.getTransmissionType());
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

        return patient;
    }

    public HealthRecord fromRecordDTO(RecordDTO recordDTO) {
        HealthRecord healthRecord = new HealthRecord();
        return healthRecord;
    }
}
