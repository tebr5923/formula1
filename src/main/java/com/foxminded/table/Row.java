package com.foxminded.table;

public interface Row<T> extends Iterable<Object>{

    T getValue();

    <R> R getCell(int index, Class<R> rClass);
}
