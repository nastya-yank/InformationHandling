package com.epam.golubeva.jwd.fourthTask.entity;

import java.util.List;

public class Symbol implements ComponentText{
    public static final int SYMBOL_COUNTER = 1;
    private ComponentType type;
    private char value;

    public Symbol() {
    }

    public Symbol(ComponentType type, char value) {
        this.type = type;
        this.value = value;
    }

    public Symbol(char value) {
        this.value = value;
    }

    @Override
    public boolean addComponent(ComponentText text) {
        return false;
    }

    @Override
    public boolean removeComponent(ComponentText text) {
        return false;
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public int countSymbol() {
        return SYMBOL_COUNTER;
    }

    @Override
    public List<ComponentText> getComponents() {
        throw new UnsupportedOperationException("Unsupported operation for class CompositeSymbol");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + value;
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
        Symbol other = (Symbol) obj;
        if (type != other.type)
            return false;
        if (value != other.value)
            return false;
        return true;
    }


    @Override
    public String toString() {
        return Character.toString(value);
    }
}
