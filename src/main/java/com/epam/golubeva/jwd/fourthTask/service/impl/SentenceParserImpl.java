package com.epam.golubeva.jwd.fourthTask.service.impl;

import com.epam.golubeva.jwd.fourthTask.entity.ComponentText;
import com.epam.golubeva.jwd.fourthTask.entity.ComponentType;
import com.epam.golubeva.jwd.fourthTask.entity.CompositeText;
import com.epam.golubeva.jwd.fourthTask.exception.TextException;
import com.epam.golubeva.jwd.fourthTask.service.DataParser;

public class SentenceParserImpl implements DataParser {
    private static final SentenceParserImpl INSTANCE = new SentenceParserImpl();
    private static final String SENTENCE_SPLIT = "\s";
    private WordParserImpl wordParser = WordParserImpl.getInstance();

    private SentenceParserImpl(){

    }
    public static SentenceParserImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ComponentText parseText(String sentence) throws TextException {
        if (sentence == null || sentence.isEmpty()) {
            throw new TextException("sentence is null or empty");
        }
        CompositeText sentenceComponent = new CompositeText(ComponentType.SENTENCE);
        String[] words = sentence.split(SENTENCE_SPLIT);
        for (String word : words) {
            ComponentText wordComponent = wordParser.parseText(word);
            sentenceComponent.addComponent(wordComponent);
        }
        return sentenceComponent;
    }
}
