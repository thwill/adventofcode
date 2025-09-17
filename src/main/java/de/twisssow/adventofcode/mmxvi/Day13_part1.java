package de.twisssow.adventofcode.mmxvi;

import de.twisssow.adventofcode.common.GridPoint;
import de.twisssow.adventofcode.mmxvi.day13.Cabe;
import de.twisssow.adventofcode.mmxvi.day13.CabeTypeFinder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Day13_part1 {


    public static void main(String[] args) {
        int magicNumber = 1350;
        int bestSoFar = Integer.MAX_VALUE;
        GridPoint start = new GridPoint(1, 1);
        GridPoint target = new GridPoint(31, 39);
        Set<LinkedList<GridPoint>> ways = new HashSet<>();
        LinkedList<GridPoint> way = new LinkedList<>();
        way.add(start);
        ways.add(way);
        while (needsFurtherCheck(ways, target, bestSoFar)) {
            ways = expand(ways, magicNumber);
            bestSoFar = checkFinished(ways, target, bestSoFar);
        }
        System.out.println("The shortest way took " + (bestSoFar -1) + " steps.");

    }

    private static int checkFinished(Set<LinkedList<GridPoint>> ways, GridPoint target, int bestSoFar) {
        int bestSoFarNow = bestSoFar;
        for (LinkedList<GridPoint> way : ways) {
            if (isFinished(way, target) && way.size() < bestSoFarNow) {
                bestSoFarNow = way.size();
                System.out.println("New best so far: " + bestSoFarNow + " with way: " + way);
            }
        }
        return bestSoFarNow;
    }


    private static Set<GridPoint> findNeighboursOfTypeSpace(GridPoint current, int magicNumber) {
        int x = current.getX();
        int y = current.getY();
        Set<GridPoint> neighbours = new java.util.HashSet<>();

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

    private static boolean isFinished(LinkedList<GridPoint> way, GridPoint target) {
        return way.getLast().equals(target);
    }

    private static Set<LinkedList<GridPoint>> expand(Set<LinkedList<GridPoint>> ways, int magicNumber) {
        Set<LinkedList<GridPoint>> newWays = new HashSet<>();
        for (LinkedList<GridPoint> way : ways) {
            GridPoint current = way.getLast();
            Set<GridPoint> neighbours = findNeighboursOfTypeSpace(current, magicNumber);
            for (GridPoint neighbour : neighbours) {
                if (!way.contains(neighbour)) {
                    LinkedList<GridPoint> newWay = new LinkedList<>(way);
                    newWay.add(neighbour);
                    newWays.add(newWay);
                }
            }
        }
        return newWays;
    }

    private static boolean needsFurtherCheck(Set<LinkedList<GridPoint>> ways, GridPoint target, int bestSoFar) {
        boolean needsFurtherCheck = false;
        for (LinkedList<GridPoint> way : ways) {
            if (way.size() < bestSoFar && !isFinished(way, target)) {
                needsFurtherCheck = true;
                break;
            }
        }
        return needsFurtherCheck;
    }
}
