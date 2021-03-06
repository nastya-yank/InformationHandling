package com.epam.golubeva.jwd.fourthTask.service;

import com.epam.golubeva.jwd.fourthTask.entity.ComponentText;
import com.epam.golubeva.jwd.fourthTask.exception.TextException;

public interface DataParser {
   ComponentText parseText(String text) throws TextException;
}
