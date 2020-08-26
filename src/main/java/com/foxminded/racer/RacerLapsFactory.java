package com.foxminded.racer;

import com.foxminded.parser.RacerParser;
import com.foxminded.parser.TimeParser;
import com.foxminded.reader.Reader;
import com.foxminded.reader.ResourceFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacerLapsFactory {
    public List<RacerLap> create(String abbreviationFileName, String startTimeFileName, String endTimeFileName) throws IOException, URISyntaxException {
        Map<String, Racer> racers = parseRacer(read(abbreviationFileName));
        Map<String, RacerTime> startTimes = parseRacerTime(read(startTimeFileName));
        Map<String, RacerTime> endTimes = parseRacerTime(read(endTimeFileName));

        return racers
                .values()
                .stream()
                .map(racer -> createRacerLap(racer, startTimes, endTimes))
                .collect(Collectors.toList());
    }

    private Stream<String> read(String fileName) throws IOException, URISyntaxException {
        Reader reader = new ResourceFileReader(fileName);
        return reader.read();
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
