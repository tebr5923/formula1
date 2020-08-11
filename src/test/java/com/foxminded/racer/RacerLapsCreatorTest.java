package com.foxminded.racer;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RacerLapsCreatorTest {

    @Test
    void getRacerLaps_shouldReturnCorrectResult() {
        String vettel = "SVF_Sebastian Vettel_FERRARI";
        String alonso = "FAM_Fernando Alonso_MCLAREN RENAULT";
        Map<String, Racer> racers = new HashMap<>();
        racers.put("SVF", new Racer("SVF", "Sebastian Vettel", "FERRARI"));
        racers.put("FAM", new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT"));
        Map<String, RacerLap> expected = new HashMap<>();
        expected.put("SVF", new RacerLap(racers.get("SVF"), LocalTime.of(0,1,4,415000000)));
        expected.put("FAM", new RacerLap(racers.get("FAM"), LocalTime.of(0,1,12,657000000)));

        Map<String, RacerTime> startTimes= new HashMap<>();
        startTimes.put("SVF",new RacerTime("SVF", LocalDateTime.of(2018,5,24,12,2,58,917000000)));
        startTimes.put("FAM",new RacerTime("FAM", LocalDateTime.of(2018,5,24,12,13,4,512000000)));
        Map<String, RacerTime> endTimes= new HashMap<>();
        endTimes.put("SVF",new RacerTime("SVF", LocalDateTime.of(2018,5,24,12,4,3,332000000)));
        endTimes.put("FAM",new RacerTime("FAM", LocalDateTime.of(2018,5,24,12,14,17,169000000)));
        RacerLapsCreator racerLapsCreator = new RacerLapsCreator(racers,startTimes,endTimes);
        Map<String, RacerLap> actual = racerLapsCreator.getRacerLaps();

        assertEquals(expected, actual);
    }
}