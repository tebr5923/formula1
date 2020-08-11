package com.foxminded.racer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.stream.Collectors;

public class RacerLapsCreator {
    private final Map<String, Racer> racers;
    private final Map<String, RacerTime> endTimes;
    private final Map<String, RacerTime> startTimes;
    private Map<String, RacerLap> racerLaps;

    public RacerLapsCreator(Map<String, Racer> racers, Map<String, RacerTime> endTimes, Map<String, RacerTime> startTimes) {
        this.racers = racers;
        this.endTimes = endTimes;
        this.startTimes = startTimes;
    }

    public Map<String, RacerLap> getRacerLaps() {
        if (racerLaps == null) {
            createRacerLaps();
        }
        return racerLaps;
    }

    private void createRacerLaps() {
        Map<String, LocalTime> racerLapResults = startTimes
                .entrySet()
                .stream()
                .collect(Collectors
                        .toMap(Map.Entry::getKey, v -> computeLapResult(v.getValue().getLocalDateTime()
                                , endTimes.get(v.getKey()).getLocalDateTime())));
        racerLaps = racers
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, v -> createRacerLap(v.getValue()
                        , racerLapResults.get(v.getKey()))));
    }

    private RacerLap createRacerLap(Racer racer, LocalTime lapResult) {
        return new RacerLap(racer, lapResult);
    }

    private LocalTime computeLapResult(LocalDateTime startTime, LocalDateTime endTime) {
        return LocalTime
                .ofNanoOfDay(Duration
                        .between(startTime, endTime)
                        .toNanos());
    }
}
