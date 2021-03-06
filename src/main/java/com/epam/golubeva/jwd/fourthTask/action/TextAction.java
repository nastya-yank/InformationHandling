package com.epam.golubeva.jwd.fourthTask.action;

import com.epam.golubeva.jwd.fourthTask.comparator.TextComparator;
import com.epam.golubeva.jwd.fourthTask.entity.ComponentText;
import com.epam.golubeva.jwd.fourthTask.entity.ComponentType;
import com.epam.golubeva.jwd.fourthTask.entity.CompositeText;
import com.epam.golubeva.jwd.fourthTask.exception.TextException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TextAction {
    public static final int WORD_COUNT_ONE = 1;
    private static final String VOWELS_REGEX = "[a,e,i,o,u,y,а,у,о,ы,и,э,ю,ё,е]";
    private static Logger logger = LogManager.getLogger();

    public ComponentText sortParagraphsBySentences(CompositeText text) throws TextException {
        if (text == null || text.getType() != ComponentType.TEXT) {
            throw new TextException("component is invalid");
        }
        ComponentText sortedText = new CompositeText(ComponentType.PARAGRAPH);
        List<ComponentText> paragraphs = new ArrayList<>(text.getComponents());
        paragraphs.sort(new TextComparator());
        for (ComponentText paragraph : paragraphs) {
            sortedText.addComponent(paragraph);
        }
        return text;
    }

    public ComponentText findLongestSentence(ComponentText text) throws TextException {
        if (text == null || text.getType() != ComponentType.TEXT) {
            throw new TextException("component is invalid");
        }
        ComponentText longestSentence = new CompositeText(ComponentType.SENTENCE);
        List<ComponentText> paragraphList = text.getComponents();
        int maxLength = 0;
        for (ComponentText text1 : paragraphList) {
            List<ComponentText> sentenceList = text1.getComponents();
            for (ComponentText text2 : sentenceList) {
                if (text2.getType() == ComponentType.SENTENCE) {
                    if (text2.getComponents().size() > maxLength) {
                        maxLength = text2.getComponents().size();
                        longestSentence = text2;
                    }

                }
            }
        }
        return longestSentence;
    }

    public ComponentText removeSentencesWithWordLessThan(ComponentText text, int wordSize) throws TextException {
        if (text == null || text.getType() != ComponentType.TEXT) {
            throw new TextException("component is invalid");
        }
        List<ComponentText> removeSentences = new ArrayList<>();
        for (ComponentText paragraph : text.getComponents()) {
            for (ComponentText sentence : paragraph.getComponents()) {
                int numberWords = 0;
                for (ComponentText lexem : sentence.getComponents()) {
                    for (ComponentText part : lexem.getComponents()) {
                        if (part.getType() == ComponentType.WORD) {
                            numberWords++;
                        }
                    }
                }
                if (numberWords < wordSize) {
                    removeSentences.add(sentence);
                }
            }
        }
        for (ComponentText paragraph : text.getComponents()) {
            List<ComponentText> sentences = paragraph.getComponents();
            List<ComponentText> sentencesCopy = new ArrayList<>(sentences);
            sentencesCopy.removeAll(removeSentences);
            ((CompositeText) paragraph).setComponents(sentencesCopy);
        }
        return text;
    }

    public Map<String, Integer> findSameWordsAndCountAmount(CompositeText text) throws TextException {
        if (text == null || text.getType() != ComponentType.TEXT) {
            throw new TextException("component is invalid");
        }
        Map<String, Integer> identicalWords = new LinkedHashMap<>();
        for (ComponentText paragraph : text.getComponents()) {
            for (ComponentText sentence : paragraph.getComponents()) {
                for (ComponentText lexeme : sentence.getComponents()) {
                    for (ComponentText elementLexeme : lexeme.getComponents()) {
                        if (elementLexeme.getType() == ComponentType.WORD) {
                            String word = elementLexeme.toString().toLowerCase();
                            if (!identicalWords.containsKey(word)) {
                                identicalWords.put(word, 1);
                            } else {
                                int count = identicalWords.get(word);
                                identicalWords.put(word, WORD_COUNT_ONE);
                            }
                        }
                    }
                }
            }
        }
        Iterator<Map.Entry<String, Integer>> iterator = identicalWords.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> wordAndNumber = iterator.next();
            Integer number = wordAndNumber.getValue();
            if (number == WORD_COUNT_ONE) {
                iterator.remove();
            }
        }
        logger.log(Level.INFO, "identical words in the text and their number: " + identicalWords.toString());
        return identicalWords;
    }

}
