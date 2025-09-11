package de.twisssow.adventofcode.mmxvi.day11;

public enum Type {
    GENERATOR("Generator"),
    MICROCHIP("Microchip");

    private final String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
