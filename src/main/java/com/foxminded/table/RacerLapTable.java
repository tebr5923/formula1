package com.foxminded.table;

import com.foxminded.racer.RacerLap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class RacerLapTable implements Table<RacerLap>, Iterable<Row<RacerLap>> {
    private final List<RacerLap> racerLapList;

    public RacerLapTable(List<RacerLap> racerLapList) {
        this.racerLapList = racerLapList;
    }

    @Override
    public Iterator<Row<RacerLap>> iterator() {
        return new RacerLapTableIterator(racerLapList);
    }

    @Override
    public RacerLap getRawRow(int i) {
        return racerLapList.get(i);
    }

    @Override
    public Row<RacerLap> getRaw(int i) {
        return new RacerLapRow(getRawRow(i));
    }

    @Override
    public <S> S getRawCell(int rowIndex, int cellIndex, Class<S> sClass) {
        final Object cell;
        switch (cellIndex) {
            case 1:
                cell = getRaw(rowIndex).getCell(1, sClass);
                break;
            case 2:
                cell = getRaw(rowIndex).getCell(2, sClass);
                break;
            case 3:
                cell = getRaw(rowIndex).getCell(3, sClass);
                break;
            default:
                throw new IllegalStateException("No cell is mapped to the index " + cellIndex);
        }
        return sClass.cast(cell);
    }

    @Override
    public Object getCell(int rowIndex, int cellIndex) {
        final Object cell;
        switch (cellIndex) {
            case 1:
                cell = getRawRow(rowIndex).getRacer().getFullName();
                break;
            case 2:
                cell = getRawRow(rowIndex).getRacer().getTeamName();
                break;
            case 3:
                cell = getRawRow(rowIndex).getRacerResult();
                break;
            default:
                throw new IllegalStateException("No cell is mapped to the index " + cellIndex);
        }
        return cell;
    }

    @Override
    public <R> int getMaxCellLength(int cellIndex, Class<R> rClass) {
        List<R> rList = new ArrayList<>();
        for (int i = 0; i < racerLapList.size(); i++) {
            rList.add(getRawCell(i, cellIndex, rClass));
        }
        return rList.stream().map(Object::toString)
                .map(String::length)
                .max(Integer::compareTo)
                .orElse(0);

       /*return Stream.generate(i -> getRawCell(i, cellIndex, rClass))
                .limit(size)
                .map(Object::toString)
                .map(String::length)
                .max(Integer::compareTo);*/
    }

    private List<RacerLap> sortByTime() {
        return racerLapList.stream()
                .sorted(Comparator.comparing(RacerLap::getRacerResult))
                .collect(Collectors.toList());
    }
}
