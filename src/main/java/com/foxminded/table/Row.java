package com.foxminded.table;

public interface Row<T> {

    T getValue();

    <R> R getCell(int index, Class<R> rClass);
}
