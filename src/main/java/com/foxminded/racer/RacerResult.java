package com.foxminded.racer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RacerResult {
    // might be static?
    public LocalTime compute(LocalDateTime endTime, LocalDateTime startTime) {
        return LocalTime
                .ofNanoOfDay(Duration
                        .between(endTime, startTime)
                        .toNanos());
    }
}
