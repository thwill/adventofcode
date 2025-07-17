package de.twisssow.adventofcode.mmxv.day25;

public class PositionCalculator {

    public static Position calculateNextPosition(Position position) {
        int x = position.getXPosition();
        int y = position.getYPosition();

        if (x == 1) {
            return new Position(y + 1, 1);
        } else {
            return new Position(x - 1, y + 1);
        }
    }



}
