package com.foxminded.table;

import com.foxminded.racer.RacerLap;

import java.util.Iterator;

public class RacerLapRow implements Row<RacerLap>, Iterable<Object> {
    private final RacerLap racerLap;

    public RacerLapRow(RacerLap racerLap) {
        this.racerLap = racerLap;
    }

    @Override
    public Iterator<Object> iterator() {
        return new RowIterator(this);
    }

    @Override
    public RacerLap getValue() {
        return racerLap;
    }

    @Override
    public <R> R getCell(int index, Class<R> rClass) {
        final Object cell;
        switch (index) {
            case 1:
                cell = racerLap.getRacer().getFullName();
                break;
            case 2:
                cell = racerLap.getRacer().getTeamName();
                break;
            case 3:
                cell = racerLap.getRacerResult();
                break;
            default:
                throw new IllegalStateException("No cell is mapped to the index " + index);
        }
        return rClass.cast(cell);
    }
}
