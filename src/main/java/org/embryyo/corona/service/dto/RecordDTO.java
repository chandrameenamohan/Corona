package org.embryyo.corona.service.dto;

import java.util.List;
import java.util.Objects;

public class RecordDTO {
    private int id; // use the date to store it
    private int date;
    private List<PatientSymptomDTO> symptoms;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordDTO recordDTO = (RecordDTO) o;
        return id == recordDTO.id &&
                date == recordDTO.date &&
                Objects.equals(symptoms, recordDTO.symptoms) &&
                Objects.equals(note, recordDTO.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, symptoms, note);
    }

    @Override
    public String toString() {
        return "RecordDTO{" +
                "id=" + id +
                ", date=" + date +
                ", symptoms=" + symptoms +
                ", note='" + note + '\'' +
                '}';
    }
}
