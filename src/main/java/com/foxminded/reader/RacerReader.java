package com.foxminded.reader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

@FunctionalInterface
public interface RacerReader {
    Stream<String> read() throws IOException, URISyntaxException;
}
