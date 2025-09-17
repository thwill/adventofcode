package de.twisssow.adventofcode.mmxvi.day13;

public class CabeTypeFinder {


    public static Cabe findType(int x, int y, int magicNumber) {
        int value = x * x + 3 * x + 2 * x * y + y + y * y + magicNumber;
        String binary = Integer.toBinaryString(value);
        long countOfOnes = binary.chars().filter(ch -> ch == '1').count();
        if (countOfOnes % 2 == 0) {
            return Cabe.Space;
        } else {
            return Cabe.Wall;

        }
    }
}
