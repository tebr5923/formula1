package com.foxminded.table;

import com.foxminded.racer.RacerLap;

import java.util.List;
import java.util.stream.Stream;

public class RacerLapTable implements Table<RacerLap> {
    private List<RacerLap> racerLapList;

    @Override
    public RacerLap getRawRow(int i) {
        return racerLapList.get(i);
    }

    @Override
    public Row<RacerLap> getRaw(int i) {
        return new RacerLapRow(i, getRawRow(i));
    }

    @Override
    public <S> S getRawCell(int i, int j) {
        switch (j) {  return field by index  }
    }


    @Override
  ?

    getCell(int i, int j) {
        return ?;
    }

    @Override
    public int getMaxCellLength(int j) {
        return Stream.generate(i -> getRawCell(i, j))
                .limit(4)
                .map(Object::toString)
                .map(String::length)
                .max();
    }
}
