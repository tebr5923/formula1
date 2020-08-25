package com.foxminded.racer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacerLapsCreator {
    private final Map<String, Racer> racers;
    private final Map<String, RacerTime> startTimes;
    private final Map<String, RacerTime> endTimes;

    public RacerLapsCreator(Map<String, Racer> racers, Map<String, RacerTime> startTimes, Map<String, RacerTime> endTimes) {
        this.racers = racers;
        this.startTimes = startTimes;
        this.endTimes = endTimes;
    }

    public List<RacerLap> createRacerLaps() {
        return racers
                .values()
                .stream()
                .map(this::createRacerLap)
                .collect(Collectors.toList());
    }

    private RacerLap createRacerLap(Racer racer) {
        return new RacerLap(racer, computeLapResult(
                startTimes.get(racer.getAbbreviation()).getLocalDateTime(),
                endTimes.get(racer.getAbbreviation()).getLocalDateTime()));
    }

    private LocalTime computeLapResult(LocalDateTime startTime, LocalDateTime endTime) {
        return LocalTime
                .ofNanoOfDay(Duration
                        .between(startTime, endTime)
                        .toNanos());
    }
}
