package com.foxminded.formatter;

import java.util.List;

public interface Formatter<T> {
    List<String> format(List<T> inputList);

}
