package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.mmxv.day25.Position;
import de.twisssow.adventofcode.mmxv.day25.PositionCalculator;

public class Day25 {

    public static void main(String[] args) {
        long start = 20151125;

        long mulitplier = 252533;
        long divisor = 33554393;

        Position position = new Position(1, 1);

        while (!position.equals(new Position(3010, 3019))) {
            start = (start * mulitplier) % divisor;
            position = PositionCalculator.calculateNextPosition(position);
        }
        System.out.println("Code for position: " + position + " is: " + start);
    }
}
