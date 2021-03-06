package com.epam.golubeva.jwd.fourthTask.comparator;

import com.epam.golubeva.jwd.fourthTask.entity.ComponentText;

import java.util.Comparator;

public class TextComparator implements Comparator<ComponentText> {
    @Override
    public int compare(ComponentText component1, ComponentText component2) {
        return Integer.compare(component1.getComponents().size(), component2.getComponents().size());
    }
}
