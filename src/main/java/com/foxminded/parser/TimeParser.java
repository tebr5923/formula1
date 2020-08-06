package com.foxminded.parser;

import com.foxminded.racer.RacerTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimeParser implements Parser<RacerTime> {

    public Map<String, RacerTime> parse(Stream<String> stringStream) {
        return stringStream.map(this::parseTime)
                .collect(Collectors.toMap(RacerTime::getAbbreviation, Function.identity()));
    }

    private RacerTime parseTime(String string) {
        checkStringFormat(string);
        String abbreviation = string.substring(0, 3);
        String localDateTime = string.substring(3);
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(localDateTime, formatter);
        return new RacerTime(abbreviation, dateTime);
    }

    private void checkStringFormat(String string) {
        if (!Pattern.matches("[A-Z]{3}\\d{4}-\\d{2}-\\d{2}_\\d{2}:\\d{2}:\\d{2}\\.\\d{3}", string)) {
            throw new IllegalArgumentException("Wrong string format");
        }
    }
}
