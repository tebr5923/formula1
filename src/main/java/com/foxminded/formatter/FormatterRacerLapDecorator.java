package com.foxminded.formatter;

import com.foxminded.racer.RacerLap;
import com.foxminded.table.Table;

import java.util.Iterator;
import java.util.List;

public class FormatterRacerLapDecorator implements Formatter<RacerLap> {
    private final Formatter<RacerLap> racerLapFormatter;

    public FormatterRacerLapDecorator(Formatter<RacerLap> racerLapFormatter) {
        this.racerLapFormatter = racerLapFormatter;
    }

    @Override
    public List<String> format(List<RacerLap> inputList) {
        return racerLapFormatter.format(inputList);
    }

    @Override
    public Iterator<String> formatTable(Table<RacerLap> inputTable) {
        return racerLapFormatter.formatTable(inputTable);
    }
}
