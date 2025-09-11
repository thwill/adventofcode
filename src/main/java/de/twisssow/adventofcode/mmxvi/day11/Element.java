package de.twisssow.adventofcode.mmxvi.day11;

public enum Element {

    PLUTONIUM("Pl"),
    PROMETHIUM("Pr"),
    RUTHENIUM("Ru"),
    STRONTIUM("St"),
    THULIUM("Th"),
    ELERIUM("El"),
    DILITHIUM("Di");

    private final String shortName;

    Element(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    @Override
    public String toString() {
        return shortName;
    }


}
