package com.epam.golubeva.jwd.fourthTask.entity;

import java.util.List;

public interface ComponentText {

   boolean addComponent(ComponentText text);

    boolean removeComponent(ComponentText text);

   ComponentType getType();

    List<ComponentText> getComponents();

    int countSymbol();

}
