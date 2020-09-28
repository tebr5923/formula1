package com.foxminded.table;

import com.foxminded.racer.RacerLap;

public class RacerLapRow implements Row<RacerLap> {
    private final int index;
    private final RacerLap racerLap;

    public RacerLapRow(int index, RacerLap racerLap) {
        this.index = index;
        this.racerLap = racerLap;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public RacerLap getValue() {
        return racerLap;
    }

    @Override
    public <R> R getCell(int index, Class<R> rClass) {
        final Object cell;
        switch (index) {
            case 0:
                cell = this.index;
                break;
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
