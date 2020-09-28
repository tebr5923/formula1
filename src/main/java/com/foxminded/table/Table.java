package com.foxminded.table;

public interface Table<T> {
    T getRawRow(int i);

    Row<T> getRaw(int i);

    <S>S getRawCell(int i, int j);


    int getMaxCellLength(int j);

}
