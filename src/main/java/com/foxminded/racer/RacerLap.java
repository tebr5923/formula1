package com.foxminded.racer;

import java.time.LocalTime;
import java.util.Objects;

public class RacerLap {
    private final Racer racer;
    private final LocalTime racerResult;

    public RacerLap(Racer racer, LocalTime racerResult) {
        this.racer = racer;
        this.racerResult = racerResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RacerLap racerLap = (RacerLap) o;
        return Objects.equals(racer, racerLap.racer) &&
                Objects.equals(racerResult, racerLap.racerResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(racer, racerResult);
    }

    @Override
    public String toString() {
        return "RacerLap{" +
                "racer=" + racer +
                ", racerResult=" + racerResult +
                '}';
    }
}
