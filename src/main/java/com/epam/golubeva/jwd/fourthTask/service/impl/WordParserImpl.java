package com.epam.golubeva.jwd.fourthTask.service.impl;

import com.epam.golubeva.jwd.fourthTask.entity.ComponentText;
import com.epam.golubeva.jwd.fourthTask.entity.ComponentType;
import com.epam.golubeva.jwd.fourthTask.entity.CompositeText;
import com.epam.golubeva.jwd.fourthTask.entity.Symbol;
import com.epam.golubeva.jwd.fourthTask.exception.TextException;
import com.epam.golubeva.jwd.fourthTask.service.DataParser;

public class WordParserImpl implements DataParser {
    private static final WordParserImpl INSTANCE = new WordParserImpl();

    private WordParserImpl(){}

    public static WordParserImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ComponentText parseText(String word) throws TextException {
        CompositeText wordComponent = new CompositeText(ComponentType.WORD);
        char[] symbols = word.toCharArray();
        Symbol symbolComponent;
        for (char symbol : symbols) {
            symbolComponent = new Symbol(ComponentType.SYMBOL, symbol);
            wordComponent.addComponent(symbolComponent);
        }
        return wordComponent;
    }
}
