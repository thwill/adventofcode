package de.twisssow.adventofcode.mmxvi;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.mmxvi.day3.TriangleChecker;

import java.util.List;

public class Day3_part1 {

    public static void main(String[] args) {
        int validCounter = 0;

        List<String> inputLines = LineReader.readInputFile("mmxvi/triangels_or_not.txt");

        for (String line : inputLines) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length == 3) {
                int a = Integer.parseInt(parts[0]);
                int b = Integer.parseInt(parts[1]);
                int c = Integer.parseInt(parts[2]);
                System.out.println("checking triangle: " + a + ", " + b + ", " + c);
                if (TriangleChecker.checkTriangle(a, b, c)) {
                    validCounter++;
                }
            }

        }
        System.out.println("valid triangles: " + validCounter);

    }


}
