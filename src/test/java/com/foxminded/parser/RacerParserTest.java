package com.foxminded.parser;

import com.foxminded.racer.Racer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RacerParserTest {
    private final RacerParser racerParser;

    public RacerParserTest() {
        this.racerParser = new RacerParser();
    }

    @Test
    void parse_shouldThrowIllegalArgumentException_whenWrongStringFormat() {
        Stream<String> stringStream = Stream.of("ths_correct_string", "wrong sting");

        assertThrows(IllegalArgumentException.class, () -> racerParser.parse(stringStream));
    }

    @Test
    void parse_shouldReturnCorrectRacers_whenCorrectStringFormat() {
        String vettel = "SVF_Sebastian Vettel_FERRARI";
        String alonso = "FAM_Fernando Alonso_MCLAREN RENAULT";
        List<Racer> expected = new ArrayList<>();
        expected.add(new Racer("SVF", "Sebastian Vettel", "FERRARI"));
        expected.add(new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT"));

        Stream<String> stringStream = Stream.of(vettel, alonso);
        List<Racer> actual = racerParser.parse(stringStream);

        assertEquals(expected, actual);
    }
}
