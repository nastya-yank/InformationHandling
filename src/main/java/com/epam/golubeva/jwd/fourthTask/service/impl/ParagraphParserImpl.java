package com.epam.golubeva.jwd.fourthTask.service.impl;

import com.epam.golubeva.jwd.fourthTask.entity.ComponentText;
import com.epam.golubeva.jwd.fourthTask.entity.ComponentType;
import com.epam.golubeva.jwd.fourthTask.entity.CompositeText;
import com.epam.golubeva.jwd.fourthTask.exception.TextException;
import com.epam.golubeva.jwd.fourthTask.service.DataParser;

public class ParagraphParserImpl implements DataParser {
    private static final ParagraphParserImpl INSTANCE = new ParagraphParserImpl();
    public static final String PARAGRAPH_SPLIT = "(?=(\\t))";
    private SentenceParserImpl sentenceParser = SentenceParserImpl.getInstance();

    private ParagraphParserImpl(){}

    public static ParagraphParserImpl getInstance() {
        return INSTANCE;
    }


    @Override
    public ComponentText parseText(String paragraph) throws TextException {
        if (paragraph == null || paragraph.isEmpty()) {
            throw new TextException("paragraph is null or empty");
        }
        CompositeText paragraphComponent = new CompositeText(ComponentType.PARAGRAPH);
        String[] paragraphs = paragraph.split(PARAGRAPH_SPLIT);
        for (String sentence : paragraphs) {
            ComponentText sentenceComponent = sentenceParser.parseText(sentence);
            paragraphComponent.addComponent(sentenceComponent);
        }
        return paragraphComponent;
    }
}
