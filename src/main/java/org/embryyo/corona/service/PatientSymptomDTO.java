package org.embryyo.corona.service;

import java.util.Date;
import java.util.Objects;

public class PatientSymptomDTO {
    private String symptom;
    private String severity;
    private Date recordTime;
    private String comment;

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

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "PatientSymptomDTO{" +
                "symptom='" + symptom + '\'' +
                ", severity='" + severity + '\'' +
                ", recordTime=" + recordTime +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientSymptomDTO that = (PatientSymptomDTO) o;
        return Objects.equals(symptom, that.symptom) &&
                Objects.equals(severity, that.severity) &&
                Objects.equals(recordTime, that.recordTime) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symptom, severity, recordTime, comment);
    }
}
