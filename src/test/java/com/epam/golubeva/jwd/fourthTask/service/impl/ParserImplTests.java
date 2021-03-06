package com.epam.golubeva.jwd.fourthTask.service.impl;

import com.epam.golubeva.jwd.fourthTask.entity.ComponentText;
import com.epam.golubeva.jwd.fourthTask.entity.ComponentType;
import com.epam.golubeva.jwd.fourthTask.entity.CompositeText;
import com.epam.golubeva.jwd.fourthTask.entity.Symbol;
import com.epam.golubeva.jwd.fourthTask.exception.TextException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParserImplTests {
    private TextParserImpl textParser;

    @BeforeClass
    public void setUp() {
        textParser = TextParserImpl.getInstance();
    }

    @Test
    public void textParserTest(ComponentText expected, String text) throws TextException {
        ComponentText actual = textParser.parseText(text);
        Assert.assertEquals(actual.toString(),expected.toString());
    }
    @AfterClass
    public void tearDown() {
        textParser = null;
    }
    }