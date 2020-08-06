package com.foxminded.racer;

import java.time.LocalTime;
import java.util.Objects;

public class RacerLap {
    private final Racer racer;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public RacerLap(Racer racer, LocalTime startTime, LocalTime endTime) {
        this.racer = racer;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Racer getRacer() {
        return racer;
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
        RacerLap racerLap = (RacerLap) o;
        return racer.equals(racerLap.racer) &&
                startTime.equals(racerLap.startTime) &&
                endTime.equals(racerLap.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(racer, startTime, endTime);
    }

    @Override
    public String toString() {
        return "RacerLap{" +
                "racer=" + racer +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
