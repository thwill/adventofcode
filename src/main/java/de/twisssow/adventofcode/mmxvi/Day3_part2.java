package de.twisssow.adventofcode.mmxvi;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.mmxvi.day3.TriangleChecker;

import java.util.ArrayList;
import java.util.List;

public class Day3_part2 {

    public static void main(String[] args) {
        int validCounter = 0;

        List<String> inputLines = LineReader.readInputFile("mmxvi/triangels_or_not.txt");
        List<Integer> firstColumn = new ArrayList<>();
        List<Integer> secondColumn = new ArrayList<>();
        List<Integer> thirdColumn = new ArrayList<>();

        for (String line : inputLines) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length == 3) {
                firstColumn.add(Integer.parseInt(parts[0]));
                secondColumn.add(Integer.parseInt(parts[1]));
                thirdColumn.add(Integer.parseInt(parts[2]));
            }
        }

        List<Integer> allTriangles = new ArrayList<>();
        allTriangles.addAll(firstColumn);
        allTriangles.addAll(secondColumn);
        allTriangles.addAll(thirdColumn);
        // Check triangles in columns
        for (int i = 0 ; i < allTriangles.size(); i+= 3) {
            int a = allTriangles.get(i);
            int b = allTriangles.get(i+1);
            int c = allTriangles.get(i+2);
            if (TriangleChecker.checkTriangle(a, b, c)) {
                validCounter++;
            }
        }

        System.out.println("valid triangles: " + validCounter);

    }


}
