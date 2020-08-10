package com.foxminded.racer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RacerResult {
    private final String abbreviation;
    private final LocalDateTime endTime;
    private final LocalDateTime startTime;
    private LocalTime timeResult;

    public RacerResult(String abbreviation, LocalDateTime endTime, LocalDateTime startTime) {
        this.abbreviation = abbreviation;
        this.endTime = endTime;
        this.startTime = startTime;
    }

    public LocalTime compute() {
        if (timeResult == null) {
            timeResult = LocalTime
                    .ofNanoOfDay(Duration
                            .between(endTime, startTime)
                            .toNanos());
        }
        return timeResult;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
