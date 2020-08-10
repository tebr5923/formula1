package com.foxminded.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class AbstractParserTest {
    protected final Parser<?> parser;

    protected AbstractParserTest(Parser<?> parser) {
        this.parser = parser;
    }

    @Test
    void parse_shouldThrowIllegalArgumentException_whenWrongStringFormat() {
        Stream<String> stringStream = Stream.of("wrong sting");

        assertThrows(IllegalArgumentException.class, () -> parser.parse(stringStream));
    }
    /*@Test
    abstract void parse_shouldThrowIllegalArgumentException_whenWrongStringFormat();*/

    @Test
    abstract void parse_shouldReturnCorrectResult_whenCorrectStringFormat();
}
