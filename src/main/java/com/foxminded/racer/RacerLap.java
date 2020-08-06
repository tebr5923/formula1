package com.foxminded.racer;

import java.time.Duration;
import java.time.LocalTime;

public class RacerLap {
    private final Racer racer;
    private final RacerTime startTime;
    private final RacerTime endTime;
    private final LocalTime racerResult;

    public RacerLap(Racer racer, RacerTime startTime, RacerTime endTime) {
        this.racer = racer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.racerResult = LocalTime
                .ofNanoOfDay(Duration
                        .between(endTime.getLocalDateTime(), startTime.getLocalDateTime())
                        .toNanos());
    }
}
