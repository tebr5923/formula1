package com.foxminded.parser;

import com.foxminded.racer.Racer;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RacerParserTest extends AbstractParserTest{
    public RacerParserTest() {
        super(new RacerParser());
    }

    @Test
    void parse_shouldReturnCorrectResult_whenCorrectStringFormat() {
        String vettel = "SVF_Sebastian Vettel_FERRARI";
        String alonso = "FAM_Fernando Alonso_MCLAREN RENAULT";
        Map<String, Racer> expected = new HashMap<>();
        expected.put("SVF", new Racer("SVF", "Sebastian Vettel", "FERRARI"));
        expected.put("FAM", new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT"));

        Stream<String> stringStream = Stream.of(vettel, alonso);
        Map<String, ?> actual = parser.parse(stringStream);

        assertEquals(expected, actual);
    }
}
