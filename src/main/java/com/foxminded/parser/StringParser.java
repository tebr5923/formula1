package com.foxminded.parser;

import com.foxminded.Racer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringParser {
    private static final String DELIMITER = "_";

    public List<Racer> parse(Stream<String> stringStream) {
        return stringStream.map(this::parseString).collect(Collectors.toList());
    }

    private Racer parseString(String string) {
        String[] strings = string
                .split(DELIMITER, -1);
        return new Racer(strings[0], strings[1], strings[2]);
    }

    private void checkString(String string){

    }
}
