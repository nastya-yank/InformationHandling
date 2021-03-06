package com.epam.golubeva.jwd.fourthTask.service.impl;

import com.epam.golubeva.jwd.fourthTask.entity.ComponentText;
import com.epam.golubeva.jwd.fourthTask.entity.ComponentType;
import com.epam.golubeva.jwd.fourthTask.entity.CompositeText;
import com.epam.golubeva.jwd.fourthTask.entity.Symbol;
import com.epam.golubeva.jwd.fourthTask.exception.TextException;
import com.epam.golubeva.jwd.fourthTask.service.DataParser;

public class LexemeParserImpl implements DataParser {
    private static final LexemeParserImpl INSTANCE = new LexemeParserImpl();
    public static final String WORD_CODE_REGEX = "\\w+\\.\\w+\\(.*\\)";
    public static final String PUNCTUATION_MARK_REGEX = "(\\p{Punct})";
    public static final String MARK_WORD_MARK_REGEX = "(\\p{Punct}.+\\p{Punct})";
    public static final String MARK_WORD_REGEX = "(\\p{Punct}.+)";
    public static final String CODE_MARK_REGEX = "\\w+\\.\\w+\\(.*\\)\\p{Punct}";
    public static final String WORD_MARK_REGEX = ".+\\p{Punct}";
    public static final String WORD_MARK_SPLIT = "(?=[,.!?)])";
    private LexemeParserImpl lexemeParser = LexemeParserImpl.getInstance();

    public LexemeParserImpl() {
    }

    public static LexemeParserImpl getInstance() {
        return INSTANCE;
    }
    @Override
    public ComponentText parseText(String lexeme) throws TextException {
        if (lexeme == null || lexeme.isEmpty()) {
            throw new TextException("lexeme is null or empty");
        }
        CompositeText lexemeComponent = new CompositeText(ComponentType.LEXEME);
        String word;
        Symbol symbol;
        if (lexeme.matches(WORD_CODE_REGEX)) {
            ComponentText wordComponent = lexemeParser.parseText(lexeme);
            lexemeComponent.addComponent(wordComponent);
        } else if (lexeme.matches(PUNCTUATION_MARK_REGEX)) {
            symbol = new Symbol(ComponentType.PUNCTUATION_MARK, lexeme.charAt(0));
            lexemeComponent.addComponent(symbol);
        } else if (lexeme.matches(MARK_WORD_MARK_REGEX)) {
            Character firstSymbol = lexeme.charAt(0);
            symbol = new Symbol(ComponentType.PUNCTUATION_MARK, firstSymbol);
            lexemeComponent.addComponent(symbol);
            word = lexeme.substring(1, lexeme.length() - 1);
            ComponentText wordComponent = lexemeParser.parseText(word);
            lexemeComponent.addComponent(wordComponent);
            Character lastSymbol = lexeme.charAt(lexeme.length() - 1);
            symbol = new Symbol(ComponentType.PUNCTUATION_MARK, lastSymbol);
            lexemeComponent.addComponent(symbol);
        } else if (lexeme.matches(MARK_WORD_REGEX)) {
            Character firstSymbol = lexeme.charAt(0);
            symbol = new Symbol(ComponentType.PUNCTUATION_MARK, firstSymbol);
            lexemeComponent.addComponent(symbol);
            word = lexeme.substring(1, lexeme.length());
            ComponentText wordComponent = lexemeParser.parseText(word);
            lexemeComponent.addComponent(wordComponent);
        } else if (lexeme.matches(CODE_MARK_REGEX)) {
            word = lexeme.substring(0, lexeme.length() - 1);
            ComponentText wordComponent = lexemeParser.parseText(word);
            lexemeComponent.addComponent(wordComponent);
            Character lastSymbol = lexeme.charAt(lexeme.length() - 1);
            symbol = new Symbol(ComponentType.PUNCTUATION_MARK, lastSymbol);
            lexemeComponent.addComponent(symbol);
        } else if (lexeme.matches(WORD_MARK_REGEX)) {
            String[] lexemeEelements = lexeme.split(WORD_MARK_SPLIT);
            for (String element : lexemeEelements) {
                if (element.matches(PUNCTUATION_MARK_REGEX)) {
                    Character markSymbol = element.charAt(0);
                    symbol = new Symbol(ComponentType.PUNCTUATION_MARK, markSymbol);
                    lexemeComponent.addComponent(symbol);
                } else {
                    ComponentText wordComponent = lexemeParser.parseText(element);
                    lexemeComponent.addComponent(wordComponent);
                }
            }
        } else {
            ComponentText wordComponent = lexemeParser.parseText(lexeme);
            lexemeComponent.addComponent(wordComponent);
        }
        return lexemeComponent;
    }
}
