package de.twisssow.adventofcode.mmxvi.day3;

public class TriangleChecker {

    public static boolean checkTriangle(int a, int b, int c) {
        // Check if the sum of the lengths of any two sides is greater than the length of the third side
        return (a + b > c) && (a + c > b) && (b + c > a);

    }

}
