package com.foxminded.parser;

import java.util.Map;
import java.util.stream.Stream;

public interface Parser<T> {
    Map<String, T> parse(Stream<String> stringStream);
}
