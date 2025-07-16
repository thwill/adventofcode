package de.twisssow.adventofcode.mmxv.helper.day25;

public class Position {

    int xPosition;
    int yPosition;

    public Position(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        if (xPosition < 1 || yPosition < 1) {
            throw new IllegalArgumentException("Position coordinates must be greater than 0");
        }
    }

    public int getXPosition() {
        return xPosition;
    }

    public  int getYPosition() {
        return  yPosition;
    }

    @Override
    public String toString() {
        return "( " + xPosition + ", " + yPosition + " )";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;
        return xPosition == position.xPosition && yPosition == position.yPosition;
    }

    @Override
    public int hashCode() {
        int result = xPosition;
        result = 31 * result + yPosition;
        return result;
    }
}
