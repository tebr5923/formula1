package com.foxminded.formatter;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class DelimiterStingIterator implements Iterator<String> {
    private static final int FIRST_ROUND_QUALIFIED_POSITION = 15;

    private final List<String> stringList;
    private final String delimiter;
    private final int qualifiedPosition;
    private int currentIndex;

    public DelimiterStingIterator(List<String> stringList, String delimiter) {
        this(stringList, delimiter, FIRST_ROUND_QUALIFIED_POSITION);
    }

    public DelimiterStingIterator(List<String> stringList, String delimiter, int qualifiedPosition) {
        this.stringList = stringList;
        this.delimiter = delimiter;
        this.qualifiedPosition = qualifiedPosition;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < stringList.size() && stringList.get(currentIndex) != null;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException("not have next element!!");
        }
        if (currentIndex == qualifiedPosition) {
            return String.format("%s%n%s", delimiter, stringList.get(currentIndex++));
        }
        return stringList.get(currentIndex++);
    }
}
