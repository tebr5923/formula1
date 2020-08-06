package com.foxminded.racer;

import java.time.LocalDateTime;
import java.util.Objects;

public class RacerTime {
    private final String abbreviation;
    private final LocalDateTime localDateTime;

    public RacerTime(String abbreviation, LocalDateTime localDateTime) {
        this.abbreviation = abbreviation;
        this.localDateTime = localDateTime;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RacerTime racerTime = (RacerTime) o;
        return abbreviation.equals(racerTime.abbreviation) &&
                localDateTime.equals(racerTime.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbreviation, localDateTime);
    }

    @Override
    public String toString() {
        return "RacerTime{" +
                "abbreviation='" + abbreviation + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
