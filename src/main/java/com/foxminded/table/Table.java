package com.foxminded.table;

import java.util.List;

public interface Table<T> extends Iterable<Row<T>>{
    List<T> getListValue();

    int getNumberOfRows();

    T getRawRow(int i);

    Row<T> getRaw(int i);

    <S>S getRawCell(int rowIndex, int cellIndex, Class<S> sClass);

    Object getCell(int rowIndex, int cellIndex);

    <R> int getMaxCellLength(int cellIndex, Class<R> rClass);

}
