package com.foxminded.racer;

import com.foxminded.parser.RacerParser;
import com.foxminded.parser.TimeParser;
import com.foxminded.reader.ResourceFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacerLapsFactory {
    public List<RacerLap> create(String abbreviationFileName, String startTimeFileName, String endTimeFileName) throws IOException, URISyntaxException {
        Map<String, Stream<String>> readResult = read(abbreviationFileName, startTimeFileName, endTimeFileName);
        Map<String, Racer> racers = parseRacer(readResult.get("racers"));
        Map<String, RacerTime> startTimes = parseRacerTime(readResult.get("startTimes"));
        Map<String, RacerTime> endTimes = parseRacerTime(readResult.get("endTimes"));

        return racers
                .values()
                .stream()
                .map(racer -> createRacerLap(racer, startTimes, endTimes))
                .collect(Collectors.toList());
    }

    private Map<String, Stream<String>> read(String abbreviationFileName, String startTimeFileName, String endTimeFileName) throws IOException, URISyntaxException {
        Map<String, Stream<String>> result = new HashMap<>();
        result.put("racers", new ResourceFileReader(abbreviationFileName).read());
        result.put("startTimes", new ResourceFileReader(startTimeFileName).read());
        result.put("endTimes", new ResourceFileReader(endTimeFileName).read());
        return result;
    }

    private Map<String, Racer> parseRacer(Stream<String> stringStreamRacers) {
        return new RacerParser().parse(stringStreamRacers);
    }

    private Map<String, RacerTime> parseRacerTime(Stream<String> stringStreamTimes) {
        return new TimeParser().parse(stringStreamTimes);
    }

    private RacerLap createRacerLap(Racer racer, Map<String, RacerTime> startTimes, Map<String, RacerTime> endTimes) {
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
