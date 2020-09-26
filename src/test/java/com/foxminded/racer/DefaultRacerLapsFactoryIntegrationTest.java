package com.foxminded.racer;

import com.foxminded.parser.RacerParser;
import com.foxminded.parser.TimeParser;
import com.foxminded.reader.ResourceFileReader;
import com.foxminded.util.RacerLapBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultRacerLapsFactoryIntegrationTest {
    private static final String ABBREVIATIONS = "racer_test/abbreviations.txt";
    private static final String START_LOG = "racer_test/start.log";
    private static final String END_LOG = "racer_test/end.log";

    @Test
    void create_shouldReturnCorrectResult() throws IOException, URISyntaxException {
        List<RacerLap> expected = new ArrayList<>();
        RacerLapBuilder racerLapBuilder = new RacerLapBuilder();
        expected.add(racerLapBuilder.build("SVF", "Sebastian Vettel", "FERRARI", LocalTime.of(0, 1, 4, 415000000)));
        expected.add(racerLapBuilder.build("FAM", "Fernando Alonso", "MCLAREN RENAULT", LocalTime.of(0, 1, 12, 657000000)));

        RacerLapsFactory racerLapsFactory = new DefaultRacerLapsFactory(new RacerParser(), new TimeParser(), new ResourceFileReader());
        List<RacerLap> actual = racerLapsFactory.create(ABBREVIATIONS, START_LOG, END_LOG);

        assertEquals(expected, actual);
    }
}
