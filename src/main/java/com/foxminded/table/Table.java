package com.foxminded.table;

public interface Table<T> {
    T getRawRow(int i);

    Row<T> getRaw(int i);

    <S>S getRawCell(int rowIndex, int cellIndex, Class<S> sClass);

    Object getCell(int rowIndex, int cellIndex);

    <R> int getMaxCellLength(int cellIndex, Class<R> rClass);

}
