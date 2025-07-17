package de.twisssow.adventofcode.mmxv.day19;

public class Replacement {

    private final String from;
    private final String to;

    public Replacement(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return from + " -> " + to  ;
    }



}
