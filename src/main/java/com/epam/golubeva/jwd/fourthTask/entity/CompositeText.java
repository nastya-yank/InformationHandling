package com.epam.golubeva.jwd.fourthTask.entity;

import com.epam.golubeva.jwd.fourthTask.comparator.TextComparator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompositeText implements ComponentText {
    public static Logger logger = LogManager.getLogger();
    private static final String TAB = "\t";
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";
    private ComponentType type;
    private List<ComponentText> components = new ArrayList<>();

    public CompositeText() {
    }

    public CompositeText(ComponentType type) {
        this.type = type;
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    @Override
    public List<ComponentText> getComponents() {
        return Collections.unmodifiableList(components);
    }

    public void setComponents(List<ComponentText> components) {
        this.components = components;
    }

    public boolean addComponent(ComponentText componentText) {
        return components.add(componentText);
    }

    public boolean removeComponent(ComponentText componentText) {
        return components.remove(componentText);
    }

    @Override
    public int countSymbol() {
        int counter = 0;
        for (ComponentText component : this.components) {
            if (component.getType() == ComponentType.SYMBOL) {
                counter += component.countSymbol();
            }
        }
        return counter;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((components == null) ? 0 : components.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CompositeText other = (CompositeText) obj;
        if (components == null) {
            if (other.components != null)
                return false;
        } else if (!components.equals(other.components))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (ComponentText component : components) {
            int index = components.indexOf(components);
            int size = components.size();
            if (component.getType() == ComponentType.PARAGRAPH) {
                text.append(TAB);
            }
            if (component.getType() == ComponentType.SENTENCE && index != 0) {
                text.append(SPACE);
            }
            text.append(component);
            if (component.getType() == ComponentType.PARAGRAPH && index != size - 1) {
                text.append(NEW_LINE);
            }
            if (component.getType() == ComponentType.LEXEME && index != size - 1) {
                text.append(SPACE);
            }
        }
        return text.toString();
    }
}

