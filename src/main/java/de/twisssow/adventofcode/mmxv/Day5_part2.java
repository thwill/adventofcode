package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.mmxv.helper.LineReader;

import java.util.List;

public class Day5_part2 {

    private static boolean isNice(String s) {
        return hasPairOfLetters(s) && hasRepeatingLetterWithGap(s);
    }

    private static boolean hasPairOfLetters(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            String pair = s.substring(i, i + 2);
            int nextIndex = s.indexOf(pair, i + 2);
            if (nextIndex != -1) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasRepeatingLetterWithGap(String s) {
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        List<String> lines = LineReader.readInputFile("nice.txt");
        int niceCount = lines.stream().filter(line -> isNice(line)).mapToInt(line -> 1).sum();
        System.out.println("Number of nice strings: " + niceCount);
    }
}

