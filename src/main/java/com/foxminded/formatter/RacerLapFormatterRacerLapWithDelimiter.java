package com.foxminded.formatter;

import com.foxminded.racer.RacerLap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RacerLapFormatterRacerLapWithDelimiter extends FormatterRacerLapDecorator {
    private static final int FIRST_ROUND_QUALIFIED_POSITION = 15;

    public RacerLapFormatterRacerLapWithDelimiter(Formatter<RacerLap> racerLapFormatter) {
        super(racerLapFormatter);
    }

    @Override
    public List<String> format(List<RacerLap> inputList) {
        List<String> stringList = super.format(inputList);
        int maxStringLength = stringList.stream().max(Comparator.comparing(String::length)).toString().length();
        if (FIRST_ROUND_QUALIFIED_POSITION <= stringList.size()) {
            stringList.add(FIRST_ROUND_QUALIFIED_POSITION, repeatChar('-',maxStringLength));
        }
        return stringList;
    }

    private String repeatChar(char ch, int times) {
        char[] chars = new char[times];
        Arrays.fill(chars, ch);
        return new String(chars);
    }
}
