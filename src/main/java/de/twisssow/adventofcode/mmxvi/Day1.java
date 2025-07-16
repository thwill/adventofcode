package de.twisssow.adventofcode.mmxvi;

import de.twisssow.adventofcode.common.InputLoader;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Day1 {

    static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int manhattanDistance() {
            return Math.abs(x) + Math.abs(y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Position)) return false;
            Position pos = (Position) o;
            return x == pos.x && y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static void computeManhattanDistanceWithRepeats(String commands) {
        // Richtungen: N(0), O(1), S(2), W(3)
        int[][] directions = {
                {0, 1},   // N
                {1, 0},   // O
                {0, -1},  // S
                {-1, 0}   // W
        };

        int x = 0, y = 0;
        int dir = 0; // Start: Norden

        String[] steps = commands.split(",");

        Set<Position> visited = new HashSet<>();
        Position start = new Position(0, 0);
        visited.add(start);

        boolean repeatFound = false;

        outer:
        for (String step : steps) {
            step = step.trim().toUpperCase();
            if (step.length() < 2) {
                throw new IllegalArgumentException("Ungültiges Kommando: " + step);
            }

            char turn = step.charAt(0);
            int distance = Integer.parseInt(step.substring(1));

            if (turn == 'R') {
                dir = (dir + 1) % 4;
            } else if (turn == 'L') {
                dir = (dir + 3) % 4;
            } else {
                throw new IllegalArgumentException("Ungültige Drehung: " + turn);
            }

            for (int i = 0; i < distance; i++) {
                x += directions[dir][0];
                y += directions[dir][1];
                Position current = new Position(x, y);
                if (visited.contains(current)) {
                    System.out.println("Erster wiederholter Punkt: " + current +
                            " mit Manhattan-Abstand: " + current.manhattanDistance());
                    repeatFound = true;
                    break outer;
                } else {
                    visited.add(current);
                }
            }
        }
    }


    public static int computeManhattanDistance(String commands) {
        // Richtungen in Reihenfolge: N(0), O(1), S(2), W(3)
        int[][] directions = {
                {0, 1},   // N
                {1, 0},   // O
                {0, -1},  // S
                {-1, 0}   // W
        };

        int x = 0, y = 0;
        int dir = 0; // Start: Norden

        String[] steps = commands.split(",");

        for (String step : steps) {
            step = step.trim().toUpperCase();
            if (step.length() < 2) {
                throw new IllegalArgumentException("Ungültiges Kommando: " + step);
            }

            char turn = step.charAt(0);
            int distance = Integer.parseInt(step.substring(1));

            // Richtung anpassen
            if (turn == 'R') {
                dir = (dir + 1) % 4;
            } else if (turn == 'L') {
                dir = (dir + 3) % 4; // -1 mod 4 = +3 mod 4
            } else {
                throw new IllegalArgumentException("Ungültige Drehung: " + turn);
            }

            // Position anpassen
            x += directions[dir][0] * distance;
            y += directions[dir][1] * distance;
        }

        return Math.abs(x) + Math.abs(y);
    }


    public static void main(String[] args) {

        String input = InputLoader.loadInput("mmxvi/grid_navigation.txt");
        int distance = computeManhattanDistance(input);
        System.out.println(distance);
        computeManhattanDistanceWithRepeats(input);


    }


}
