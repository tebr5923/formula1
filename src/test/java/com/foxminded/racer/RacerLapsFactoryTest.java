package com.foxminded.racer;

import com.foxminded.util.RacerLapBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RacerLapsFactoryTest {

    @Test
    void create_shouldReturnCorrectResult() throws IOException, URISyntaxException {
        List<RacerLap> expected = new ArrayList<>();
        RacerLapBuilder racerLapBuilder = new RacerLapBuilder();
        expected.add(racerLapBuilder.build("SVF","Sebastian Vettel","FERRARI", LocalTime.of(0, 1, 4, 415000000)));
        expected.add(racerLapBuilder.build("FAM","Fernando Alonso","MCLAREN RENAULT", LocalTime.of(0, 1, 12, 657000000)));

        RacerLapsFactory racerLapsFactory = new RacerLapsFactory();
        List<RacerLap> actual = racerLapsFactory.create("abbreviations.txt","start.log","end.log");

        assertEquals(expected, actual);
    }
}