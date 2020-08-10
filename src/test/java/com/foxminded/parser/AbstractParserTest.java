package com.foxminded.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class AbstractParserTest {
    private static Stream<Arguments> parserGenerator() {
        return Stream.of(
                Arguments.of(new RacerParser()),
                Arguments.of(new TimeParser()));
    }

    private static Stream<Arguments> streamGenerator() {
        return Stream.of(
                Arguments.of(new RacerParser()),
                Arguments.of(new TimeParser()));
    }

    @ParameterizedTest
    @MethodSource({"parserGenerator", "streamGenerator"})
    void parse_shouldThrowIllegalArgumentException_whenWrongStringFormat(ParserStream<String> stringStream){

        assertThrows(IllegalArgumentException.class, () -> parser.parse(stringStream));
    }


    /*@Test
    abstract void parse_shouldThrowIllegalArgumentException_whenWrongStringFormat();*/

    @Test
    abstract void parse_shouldReturnCorrectResult_whenCorrectStringFormat();
}
