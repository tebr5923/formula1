package com.foxminded.racer;

import com.foxminded.parser.Parser;
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

public class RacerLapsFactory {
    private final Parser<Racer> racerParser;
    private final Parser<RacerTime> timeParser;
    private final Reader reader;

    public RacerLapsFactory() {
        this.racerParser = new RacerParser();
        this.timeParser = new TimeParser();
        this.reader = new ResourceFileReader();
    }

    public List<RacerLap> create(String abbreviationFileName, String startTimeFileName, String endTimeFileName) throws IOException, URISyntaxException {
        Map<String, Racer> racers = racerParser.parse(reader.read(abbreviationFileName));
        Map<String, RacerTime> startTimes = timeParser.parse(reader.read(startTimeFileName));
        Map<String, RacerTime> endTimes = timeParser.parse(reader.read(endTimeFileName));

        return racers
                .values()
                .stream()
                .map(racer -> createRacerLap(racer, startTimes, endTimes))
                .collect(Collectors.toList());
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
