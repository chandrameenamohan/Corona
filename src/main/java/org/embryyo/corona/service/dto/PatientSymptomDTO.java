package org.embryyo.corona.service.dto;

import java.util.Objects;

public class PatientSymptomDTO {
    private String name;
    private String severity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientSymptomDTO that = (PatientSymptomDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(severity, that.severity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, severity);
    }

    @Override
    public String toString() {
        return "PatientSymptomDTO{" +
                "name='" + name + '\'' +
                ", severity='" + severity + '\'' +
                '}';
    }
}
