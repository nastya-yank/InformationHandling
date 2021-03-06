package com.epam.golubeva.jwd.fourthTask.service.impl;

import com.epam.golubeva.jwd.fourthTask.entity.ComponentText;
import com.epam.golubeva.jwd.fourthTask.entity.ComponentType;
import com.epam.golubeva.jwd.fourthTask.entity.CompositeText;
import com.epam.golubeva.jwd.fourthTask.exception.TextException;
import com.epam.golubeva.jwd.fourthTask.service.DataParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParserImpl implements DataParser {
    public static Logger logger = LogManager.getLogger();
    private static final TextParserImpl INSTANCE = new TextParserImpl();
    public static final String PARAGRAPH_SPLIT = "\s{4}";
    private ParagraphParserImpl paragraphParser = ParagraphParserImpl.getInstance();

    private TextParserImpl(){

    }
    public static TextParserImpl getInstance(){
        return INSTANCE;
    }
    @Override
    public ComponentText parseText(String text) throws TextException {
        if (text == null || text.isEmpty()) {
            throw new TextException("text is null or empty");
        }
        CompositeText textComponent = new CompositeText(ComponentType.TEXT);
        String[] paragraphData = text.split(PARAGRAPH_SPLIT);
        for (String paragraph : paragraphData) {
            if (!paragraph.isEmpty()) {
                ComponentText paragraphComponent = paragraphParser.parseText(paragraph);
                textComponent.addComponent(paragraphComponent);
            }
        }
        logger.log(Level.INFO, "after parsing the component text is created: " + textComponent.toString());
        return textComponent;
    }
}
