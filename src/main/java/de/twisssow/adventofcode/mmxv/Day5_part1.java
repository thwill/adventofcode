package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.mmxv.helper.LineReader;

import java.util.List;

public class Day5_part1 {

    public static boolean isNice(String s) {
        return hasThreeVowels(s) && hasDoubleLetter(s) && hasNoForbiddenStrings(s);
    }

    private static boolean hasThreeVowels(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {
                count++;
            }
        }
        return count >= 3;
    }

    private static boolean hasDoubleLetter(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNoForbiddenStrings(String s) {
        if (s.contains("ab") || s.contains("cd") || s.contains("pq") || s.contains("xy")) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        List<String> lines = LineReader.readInputFile("nice.txt");
        int niceCount = lines.stream().filter(line -> isNice(line)).mapToInt(line -> 1).sum();
        System.out.println("Number of nice strings: " + niceCount);
    }
}


