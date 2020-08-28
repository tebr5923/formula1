package com.foxminded.racer;

import com.foxminded.parser.Parser;
import com.foxminded.reader.Reader;
import com.foxminded.util.RacerLapBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultRacerLapsFactoryTest {
    private static final String ABBREVIATIONS = "abbreviations.txt";
    private static final String START_LOG = "start.log";
    private static final String END_LOG = "end.log";

    @Mock
    private Parser<Racer> mockRacerParser;

    @Mock
    private Parser<RacerTime> mockRacerTimeParser;

    @Mock
    private Reader mockResourceFileReader;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        setMockRacer();
        setMockStartTime();
        setMockEndTime();
    }

    @Test
    void create_shouldReturnCorrectResult() throws IOException, URISyntaxException {
        List<RacerLap> expected = new ArrayList<>();
        RacerLapBuilder racerLapBuilder = new RacerLapBuilder();
        expected.add(racerLapBuilder.build("SVF", "Sebastian Vettel", "FERRARI", LocalTime.of(0, 1, 4, 415000000)));
        expected.add(racerLapBuilder.build("FAM", "Fernando Alonso", "MCLAREN RENAULT", LocalTime.of(0, 1, 12, 657000000)));

        RacerLapsFactory racerLapsFactory = new DefaultRacerLapsFactory(mockRacerParser, mockRacerTimeParser, mockResourceFileReader);
        List<RacerLap> actual = racerLapsFactory.create(ABBREVIATIONS, START_LOG, END_LOG);

        assertEquals(expected, actual);
    }

    private void setMockRacer() throws IOException, URISyntaxException {
        Stream<String> racerStringStream = Stream.of("SVF_Sebastian Vettel_FERRARI", "FAM_Fernando Alonso_MCLAREN RENAULT");
        Map<String, Racer> racerMap = new HashMap<>();
        racerMap.put("SVF", new Racer("SVF", "Sebastian Vettel", "FERRARI"));
        racerMap.put("FAM", new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT"));
        when(mockRacerParser.parse(Mockito.eq(racerStringStream))).thenReturn(racerMap);
        when(mockResourceFileReader.read(Mockito.eq(ABBREVIATIONS))).thenReturn(racerStringStream);
    }

    private void setMockStartTime() throws IOException, URISyntaxException {
        Stream<String> startTimesStringStream = Stream.of("SVF2018-05-24_12:02:58.917", "FAM2018-05-24_12:13:04.512");
        Map<String, RacerTime> startRacerTimeMap = new HashMap<>();
        startRacerTimeMap.put("SVF", new RacerTime("SVF", LocalDateTime.of(2018, 5, 24, 12, 2, 58, 917000000)));
        startRacerTimeMap.put("FAM", new RacerTime("FAM", LocalDateTime.of(2018, 5, 24, 12, 13, 4, 512000000)));
        when(mockRacerTimeParser.parse(Mockito.eq(startTimesStringStream))).thenReturn(startRacerTimeMap);
        when(mockResourceFileReader.read(Mockito.eq(START_LOG))).thenReturn(startTimesStringStream);
    }

    private void setMockEndTime() throws IOException, URISyntaxException {
        Stream<String> endTimesStringStream = Stream.of("SVF2018-05-24_12:04:03.332", "FAM2018-05-24_12:14:17.169");
        Map<String, RacerTime> endRacerTimeMap = new HashMap<>();
        endRacerTimeMap.put("SVF", new RacerTime("SVF", LocalDateTime.of(2018, 5, 24, 12, 4, 3, 332000000)));
        endRacerTimeMap.put("FAM", new RacerTime("FAM", LocalDateTime.of(2018, 5, 24, 12, 14, 17, 169000000)));
        when(mockRacerTimeParser.parse(Mockito.eq(endTimesStringStream))).thenReturn(endRacerTimeMap);
        when(mockResourceFileReader.read(Mockito.eq(END_LOG))).thenReturn(endTimesStringStream);
    }
}
