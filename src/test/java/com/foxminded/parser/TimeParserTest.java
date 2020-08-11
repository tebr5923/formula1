package com.foxminded.parser;

import com.foxminded.racer.RacerTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeParserTest {
    private final TimeParser timeParser;

    public TimeParserTest() {
        this.timeParser = new TimeParser();
    }

    @Test
    void parse_shouldThrowIllegalArgumentException_whenWrongStringFormat() {
        Stream<String> stringStream = Stream.of("NHR2018-05-24_12:04:02.979", "wrong sting");

        assertThrows(IllegalArgumentException.class, () -> timeParser.parse(stringStream));
    }

    @Test
    void parse_shouldReturnCorrectResult_whenCorrectStringFormat() {
        String vettel = "SVF2018-05-24_12:04:03.332";
        String alonso = "FAM2018-05-24_12:14:17.169";
        Map<String, RacerTime> expected = new HashMap<>();
        LocalDateTime vettelTime = LocalDateTime.of(2018, 5, 24, 12, 4, 3, 332000000);
        LocalDateTime alonsoTime = LocalDateTime.of(2018, 5, 24, 12, 14, 17, 169000000);
        expected.put("SVF", new RacerTime("SVF", vettelTime));
        expected.put("FAM", new RacerTime("FAM", alonsoTime));

        Stream<String> stringStream = Stream.of(vettel, alonso);
        Map<String, RacerTime> actual = timeParser.parse(stringStream);

        assertEquals(expected, actual);
    }
}
