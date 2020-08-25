package com.foxminded.reader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class ResourceFileReader implements Reader {
    private final String fileName;

    public ResourceFileReader(String fileName) {
        this.fileName = fileName;
    }

    public Stream<String> read() throws IOException, URISyntaxException {
        Path path = Paths.get(Objects.requireNonNull(this.getClass()
                .getClassLoader()
                .getResource(fileName))
                .toURI());
        return Files.lines(path);
    }
}
