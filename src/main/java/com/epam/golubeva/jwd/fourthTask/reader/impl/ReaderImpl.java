package com.epam.golubeva.jwd.fourthTask.reader.impl;

import com.epam.golubeva.jwd.fourthTask.exception.TextException;
import com.epam.golubeva.jwd.fourthTask.reader.ReaderFromFile;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReaderImpl implements ReaderFromFile {
    public static Logger logger = LogManager.getLogger();
    @Override
    public String readAll(String filePath) throws TextException {
        String text;
        if (filePath == null) {
            throw new TextException("filePath is null");
        }
        Path path = Paths.get(filePath);
        try (Stream<String> streamLines = Files.lines(path)) {
            text = streamLines.collect(Collectors.joining());
        } catch (IOException e) {
            logger.log(Level.ERROR, "file " + filePath + " not found", e);
            throw new TextException("File input exception",e);
        }
        logger.log(Level.INFO, "read data from file : " + text);
        return text;
    }
}
