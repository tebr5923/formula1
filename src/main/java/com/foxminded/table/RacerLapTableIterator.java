package com.foxminded.table;

import com.foxminded.racer.RacerLap;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class RacerLapTableIterator implements Iterator<Row<RacerLap>> {
    private final List<RacerLap> racerLapList;
    private int currentIndex;

    public RacerLapTableIterator(List<RacerLap> racerLapList) {
        this.racerLapList = racerLapList;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < racerLapList.size() && racerLapList.get(currentIndex) != null;
    }

    @Override
    public Row<RacerLap> next() {
        if (!hasNext()) {
            throw new NoSuchElementException("not have next element!!");
        }
        return new RacerLapRow(racerLapList.get(currentIndex++));
    }
}
