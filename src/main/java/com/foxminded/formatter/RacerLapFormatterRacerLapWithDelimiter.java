package com.foxminded.formatter;

import com.foxminded.racer.RacerLap;

import java.util.Arrays;
import java.util.List;

public class RacerLapFormatterRacerLapWithDelimiter extends FormatterRacerLapDecorator {
    private static final int FIRST_ROUND_QUALIFIED_POSITION = 15;
    private final int qualifiedPosition;

    public RacerLapFormatterRacerLapWithDelimiter(Formatter<RacerLap> racerLapFormatter) {
        super(racerLapFormatter);
        this.qualifiedPosition = FIRST_ROUND_QUALIFIED_POSITION;
    }

    public RacerLapFormatterRacerLapWithDelimiter(Formatter<RacerLap> racerLapFormatter, int qualifiedPosition) {
        super(racerLapFormatter);
        this.qualifiedPosition = qualifiedPosition;
    }

    @Override
    public List<String> format(List<RacerLap> inputList) {
        List<String> stringList = super.format(inputList);
        return addDelimiter(stringList);
    }

    private List<String> addDelimiter(List<String> stringList) {
        int maxStringLength = stringList.get(0).length();
        if (qualifiedPosition <= stringList.size()) {
            stringList.add(qualifiedPosition, repeatChar('-', maxStringLength));
        }
        return stringList;
    }

    private String repeatChar(char ch, int times) {
        char[] chars = new char[times];
        Arrays.fill(chars, ch);
        return new String(chars);
    }
}
