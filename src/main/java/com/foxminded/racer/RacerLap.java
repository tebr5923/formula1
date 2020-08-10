package com.foxminded.racer;

import java.time.LocalTime;

public class RacerLap {
    private final Racer racer;
    private final LocalTime racerResult;

    public RacerLap(Racer racer, LocalTime racerResult) {
        this.racer = racer;
        this.racerResult = racerResult;
    }
}
