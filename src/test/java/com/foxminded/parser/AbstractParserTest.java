package com.foxminded.parser;

import org.junit.jupiter.api.Test;

abstract class AbstractParserTest {
    @Test
    abstract void parse_shouldThrowIllegalArgumentException_whenWrongStringFormat();

    @Test
    abstract void parse_shouldReturnCorrectResult_whenCorrectStringFormat();
}
