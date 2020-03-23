package org.embryyo.corona.service.dto;

import java.util.Objects;

public class SymptomDTO {
    private String name;
    private String displayName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SymptomDTO that = (SymptomDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(displayName, that.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, displayName);
    }

    @Override
    public String toString() {
        return "SymptomDTO{" +
                "name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
