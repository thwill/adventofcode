package de.twisssow.adventofcode.mmxvi.day11;

import java.util.Objects;

public class TransportItem {

    private final Element element;
    private final Type type;

    public TransportItem(Element element, Type type) {
        this.element = element;
        this.type = type;
    }

    public Element getElement() {
        return element;
    }

    public Type getType() {
        return type;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TransportItem that = (TransportItem) o;
        return element == that.element && type == that.type;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(element);
        result = 31 * result + Objects.hashCode(type);
        return result;
    }

    @Override
    public String toString() {
        return "TransportItem{" +
                "element=" + element +
                ", type=" + type +
                '}';
    }

}
