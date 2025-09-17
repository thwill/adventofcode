package de.twisssow.adventofcode.mmxvi;

import de.twisssow.adventofcode.common.GridPoint;
import de.twisssow.adventofcode.mmxvi.day13.Cabe;
import de.twisssow.adventofcode.mmxvi.day13.CabeTypeFinder;

import java.util.HashSet;
import java.util.Set;

public class Day13_part2 {


    public static void main(String[] args) {
        int magicNumber = 1350;
        int steps = 0;
        int maxSteps = 50;
        Set<GridPoint> reachablePoints = new HashSet<>();
        GridPoint start = new GridPoint(1, 1);
        reachablePoints.add(start);

        while (steps < maxSteps) {
            reachablePoints = expand(reachablePoints, magicNumber);
            steps++;
        }
        System.out.println("count of reachable cabes in " + maxSteps + " steps: " + reachablePoints.size());


    }


    private static Set<GridPoint> findNeighboursOfTypeSpace(GridPoint current, int magicNumber) {
        int x = current.getX();
        int y = current.getY();
        Set<GridPoint> neighbours = new HashSet<>();

        if (y > 0) {
            if (CabeTypeFinder.findType(x, y - 1, magicNumber) == Cabe.Space) {
                neighbours.add(new GridPoint(x, y - 1));

            }
        }
        if (x > 0) {
            if (CabeTypeFinder.findType(x - 1, y, magicNumber) == Cabe.Space) {
                neighbours.add(new GridPoint(x - 1, y));
            }

        }
        if (CabeTypeFinder.findType(x + 1, y, magicNumber) == Cabe.Space) {
            neighbours.add(new GridPoint(x + 1, y));
        }

        if (CabeTypeFinder.findType(x, y + 1, magicNumber) == Cabe.Space) {
            neighbours.add(new GridPoint(x, y + 1));
        }
        return neighbours;
    }


    private static Set<GridPoint> expand(Set<GridPoint> reachablePoints, int magicNumber) {
        Set<GridPoint> newReachablePoints = new HashSet<>(reachablePoints);
        for (GridPoint point : reachablePoints) {
            Set<GridPoint> neighbours = findNeighboursOfTypeSpace(point, magicNumber);
            newReachablePoints.addAll(neighbours);
        }
        return newReachablePoints;
    }
}
