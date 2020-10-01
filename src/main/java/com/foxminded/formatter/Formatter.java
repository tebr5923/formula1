package com.foxminded.formatter;

import com.foxminded.table.Table;

import java.util.Iterator;
import java.util.List;

public interface Formatter<T> {
    List<String> format(List<T> inputList);

    Iterator<String> formatTable(Table<T> inputTable);

}
