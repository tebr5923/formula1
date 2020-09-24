package com.foxminded.parser;

import com.foxminded.racer.Racer;

import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacerParser implements Parser<Racer> {
    private static final String DELIMITER = "_";

    public Map<String, Racer> parse(Stream<String> stringStream) {
        return stringStream.map(this::parseRacer)
                .collect(Collectors.toMap(Racer::getAbbreviation, Function.identity()));
    }

    private Racer parseRacer(String string) {
        checkStringFormat(string);
        String[] strings = string.split(DELIMITER, -1);
        return new Racer(strings[0], strings[1], strings[2]);
    }

    private void checkStringFormat(String string) {
        if (!Pattern.matches("[A-Z]{3}_.+_.+", string)) {
            throw new IllegalArgumentException("Wrong string format");
        }
    }
}
