package de.twisssow.adventofcode.mmxvi.day11;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Floor {

    private final static String EMPTY_ELEMENT_MASK = "  -   ";
    private final static String GENERATOR_SUFFIX = "-G  ";
    private final static String MICROCHIP_SUFFIX = "-M  ";

    private int number;
    private Set<TransportItem> items = new HashSet<>();

    public Floor(int number, Set<TransportItem> items) {
        this.number = number;
        this.items = items;
    }

    public boolean canTakeItem(TransportItem item) {
        if (items.isEmpty()) {
            return true;
        }
        if (item.getType() == Type.MICROCHIP) {
            return items.stream()
                    .anyMatch(i -> i.getType() == Type.GENERATOR && i.getElement().equals(item.getElement()));
        }
        return true;
    }


    public Set<TransportItem> getItems() {
        return items;
    }

    public void addItem(TransportItem item) {
        items.add(item);
    }

    public void removeItem(TransportItem item) {
        if (items.contains(item)) {
            items.remove(item);
        } else {
            throw new IllegalStateException("Item not found on floor " + number + ": " + item);
        }
    }

    public boolean isFilled1() {
        return items.size() == (Element.values().length - 2 ) * Type.values().length;
    }

    public boolean isFilled2() {
        return items.size() == Element.values().length * Type.values().length;
    }


    public boolean isSafe() {
        Set<Element> generators = items.stream().filter(i -> i.getType() == Type.GENERATOR).map(TransportItem::getElement).collect(Collectors.toSet());
        if (generators.isEmpty()) {
            return true;
        }
        for (TransportItem item : items) {
            if (item.getType() == Type.MICROCHIP && !generators.contains(item.getElement())) {
                return false;
            }
        }
        return true;
    }

    public Floor copy() {
        return new Floor(this.number, new HashSet<>(this.items));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return number == floor.number && Objects.equals(items, floor.items);
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + Objects.hashCode(items);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Element e : Element.values()) {
            TransportItem gen = new TransportItem(e, Type.GENERATOR);
            TransportItem chip = new TransportItem(e, Type.MICROCHIP);
            sb.append(items.contains(gen) ? e + GENERATOR_SUFFIX : EMPTY_ELEMENT_MASK);
            sb.append(items.contains(chip) ? e + MICROCHIP_SUFFIX : EMPTY_ELEMENT_MASK);
        }
        return sb.toString();
    }
}