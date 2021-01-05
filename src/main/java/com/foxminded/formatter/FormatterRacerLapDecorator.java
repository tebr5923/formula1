package com.foxminded.formatter;

import com.foxminded.racer.RacerLap;

import java.util.List;

public abstract class FormatterRacerLapDecorator implements Formatter<RacerLap> {
    private final Formatter<RacerLap> racerLapFormatter;

    protected FormatterRacerLapDecorator(Formatter<RacerLap> racerLapFormatter) {
        this.racerLapFormatter = racerLapFormatter;
    }

    @Override
    public List<String> format(List<RacerLap> inputList) {
        return racerLapFormatter.format(inputList);
    }
}
