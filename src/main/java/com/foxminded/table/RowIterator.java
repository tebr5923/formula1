package com.foxminded.table;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RowIterator implements Iterator<Object> {
    private final RacerLapRow racerLapRow;
    private int currentIndex;

    public RowIterator(RacerLapRow racerLapRow) {
        this.racerLapRow = racerLapRow;
        this.currentIndex = 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex <= 3;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException("not have next element!!");
        }
        return racerLapRow.getCell(currentIndex++, Object.class);
    }
}
