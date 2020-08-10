package com.foxminded.parser;

import com.foxminded.racer.Racer;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RacerParserTest extends AbstractParserTest{
    //private final RacerParser racerParser;

    /*public RacerParserTest() {
        this.racerParser = new RacerParser();
    }*/

    public RacerParserTest() {
        super(new RacerParser());
    }

    /*@Test
    void parse_shouldThrowIllegalArgumentException_whenWrongStringFormat() {
        Stream<String> stringStream = Stream.of("ths_correct_string", "wrong sting");

        assertThrows(IllegalArgumentException.class, () -> racerParser.parse(stringStream));
    }*/

    @Test
    void parse_shouldReturnCorrectResult_whenCorrectStringFormat() {
        String vettel = "SVF_Sebastian Vettel_FERRARI";
        String alonso = "FAM_Fernando Alonso_MCLAREN RENAULT";
        Map<String, Racer> expected = new HashMap<>();
        expected.put("SVF", new Racer("SVF", "Sebastian Vettel", "FERRARI"));
        expected.put("FAM", new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT"));

        Stream<String> stringStream = Stream.of(vettel, alonso);
        //Map<String, Racer> actual = racerParser.parse(stringStream);
        Map<String, ?> actual = parser.parse(stringStream);

        assertEquals(expected, actual);
    }
}
