package com.foxminded.reader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileRacerReader implements RacerReader {
    private final String fileName;

    public FileRacerReader(String fileName) {
        this.fileName = fileName;
    }

    public Stream<String> read() {
        Stream<String> stringStream = Stream.empty();

        try {
            Path path = Paths.get(this.getClass()
                    .getClassLoader()
                    .getResource(fileName)
                    .toURI());
            stringStream = Files.lines(path);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return stringStream;
    }
}
