package com.epam.golubeva.jwd.fourthTask.reader;

import com.epam.golubeva.jwd.fourthTask.exception.TextException;

public interface ReaderFromFile {
    String readAll(String path) throws TextException;
}
