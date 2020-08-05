package com.foxminded.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileRacerReader implements RacerReader{
    private final File file;

    public FileRacerReader(File file) {
        this.file = file;
    }

    public Stream<String> read() {
        Stream<String> stringStream = Stream.empty();
        try {
            stringStream = Files.lines(Paths.get(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringStream;
    }
}
