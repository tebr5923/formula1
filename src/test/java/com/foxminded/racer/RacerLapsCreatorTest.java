package com.foxminded.racer;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RacerLapsCreatorTest {

    @Test
    void createRacerLaps_shouldReturnCorrectResult() {
        Map<String, Racer> racers = new HashMap<>();
        racers.put("SVF", new Racer("SVF", "Sebastian Vettel", "FERRARI"));
        racers.put("FAM", new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT"));
        List<RacerLap> expected = new ArrayList<>();
        expected.add(new RacerLap(racers.get("SVF"), LocalTime.of(0, 1, 4, 415000000)));
        expected.add(new RacerLap(racers.get("FAM"), LocalTime.of(0, 1, 12, 657000000)));

        Map<String, RacerTime> startTimes = new HashMap<>();
        startTimes.put("SVF", new RacerTime("SVF", LocalDateTime.of(2018, 5, 24, 12, 2, 58, 917000000)));
        startTimes.put("FAM", new RacerTime("FAM", LocalDateTime.of(2018, 5, 24, 12, 13, 4, 512000000)));
        Map<String, RacerTime> endTimes = new HashMap<>();
        endTimes.put("SVF", new RacerTime("SVF", LocalDateTime.of(2018, 5, 24, 12, 4, 3, 332000000)));
        endTimes.put("FAM", new RacerTime("FAM", LocalDateTime.of(2018, 5, 24, 12, 14, 17, 169000000)));
        RacerLapsCreator racerLapsCreator = new RacerLapsCreator(racers, startTimes, endTimes);
        List<RacerLap> actual = racerLapsCreator.createRacerLaps();

        assertEquals(expected, actual);
    }
}
