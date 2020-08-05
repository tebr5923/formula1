package com.foxminded.reader;

import java.util.stream.Stream;

@FunctionalInterface
public interface RacerReader {
    Stream<String> read();
}
