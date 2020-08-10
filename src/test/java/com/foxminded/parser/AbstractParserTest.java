package com.foxminded.parser;

import org.junit.jupiter.api.Test;

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

    @Test
    abstract void parse_shouldReturnCorrectResult_whenCorrectStringFormat();
}
