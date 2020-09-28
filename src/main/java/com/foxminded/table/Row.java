package com.foxminded.table;

public interface Row<T> {

    T getValue();

    int getIndex();

    <R> R getCell(int index, Class<R> rClass);
}
