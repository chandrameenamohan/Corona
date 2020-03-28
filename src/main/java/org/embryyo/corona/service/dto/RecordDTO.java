package org.embryyo.corona.service.dto;

import java.util.List;
import java.util.Objects;

public class RecordDTO {
    private long sequenceNo; // use the date to store it
    private long date;
    private List<PatientSymptomDTO> symptoms;
    private String note;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<PatientSymptomDTO> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<PatientSymptomDTO> symptoms) {
        this.symptoms = symptoms;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(long sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordDTO recordDTO = (RecordDTO) o;
        return sequenceNo == recordDTO.sequenceNo &&
                date == recordDTO.date &&
                Objects.equals(symptoms, recordDTO.symptoms) &&
                Objects.equals(note, recordDTO.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequenceNo, date, symptoms, note);
    }

    @Override
    public String toString() {
        return "RecordDTO{" +
                "sequenceNo=" + sequenceNo +
                ", date=" + date +
                ", symptoms=" + symptoms +
                ", note='" + note + '\'' +
                '}';
    }
}
