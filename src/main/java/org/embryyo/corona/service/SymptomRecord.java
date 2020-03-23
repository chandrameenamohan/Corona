package org.embryyo.corona.service;

import java.sql.Date;
import java.util.Objects;

public class SymptomRecord {
    private String symptom;
    private String severity;
    private String comment;
    private Date time;


    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SymptomRecord{" +
                "symptom='" + symptom + '\'' +
                ", severity='" + severity + '\'' +
                ", comment='" + comment + '\'' +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SymptomRecord that = (SymptomRecord) o;
        return Objects.equals(symptom, that.symptom) &&
                Objects.equals(severity, that.severity) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symptom, severity, comment, time);
    }

}
