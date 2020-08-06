package com.foxminded.parser;

import com.foxminded.racer.Racer;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacerParser {
    private static final String DELIMITER = "_";

    public List<Racer> parse(Stream<String> stringStream) {
        return stringStream.map(this::parseRacer).collect(Collectors.toList());
    }

    private Racer parseRacer(String string) {
        checkStringFormat(string);
        String[] strings = string
                .split(DELIMITER, -1);
        return new Racer(strings[0], strings[1], strings[2]);
    }

    private void checkStringFormat(String string) {
        if (!Pattern.matches(".{3}_.+_.+", string)) {
            throw new IllegalArgumentException("Wrong string format");
        }
    }
}
