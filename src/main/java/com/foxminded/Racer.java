package com.foxminded;

import java.time.LocalTime;
import java.util.Objects;

public class Racer {
    private final String abbreviation;
    private final String fullName;
    private final String teamName;
    private LocalTime startTime;
    private LocalTime endTime;

    public Racer(String abbreviation, String fullName, String teamName) {
        this.abbreviation = abbreviation;
        this.fullName = fullName;
        this.teamName = teamName;
    }

    public Racer(String abbreviation, String fullName, String teamName, LocalTime startTime, LocalTime endTime) {
        this.abbreviation = abbreviation;
        this.fullName = fullName;
        this.teamName = teamName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTeamName() {
        return teamName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Racer racer = (Racer) o;
        return abbreviation.equals(racer.abbreviation) &&
                fullName.equals(racer.fullName) &&
                teamName.equals(racer.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbreviation, fullName, teamName);
    }

    @Override
    public String toString() {
        return "Racer{" +
                "abbreviation='" + abbreviation + '\'' +
                ", fullName='" + fullName + '\'' +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
