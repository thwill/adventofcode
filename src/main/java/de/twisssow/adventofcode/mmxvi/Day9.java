package de.twisssow.adventofcode.mmxvi;

import de.twisssow.adventofcode.common.InputLoader;

public class Day9 {

    public static void main(String[] args) {
        String input = InputLoader.loadInput("mmxvi/compressed.txt");
        //System.out.println("Input : " + input.length());
        //String input = "(27x12)(20x12)(13x14)(7x10)(1x12)A";
        long decompressedLength = calculateDecompressedLength(input);
        System.out.println("Decompressed Length: " + decompressedLength);
        long decompressedLengthRecursive = calculateDecompressedLengthRecursive(input);
        System.out.println("Decompressed Length Recursive: " + decompressedLengthRecursive);
    }

    private static long calculateDecompressedLength(String input) {

        long length = 0;
        int i = 0;
        while (i < input.length()) {
            if (input.charAt(i) == '(') {
                int end = input.indexOf(')', i);
                String marker = input.substring(i + 1, end);
                String[] parts = marker.split("x");
                int charsToRepeat = Integer.parseInt(parts[0]);
                int repeatCount = Integer.parseInt(parts[1]);
                length += charsToRepeat * repeatCount;
                i = end + 1 + charsToRepeat; // Move past the marker and the repeated characters
            } else {
                length++;
                i++;
            }
        }
        return length;
    }

    private  static long calculateDecompressedLengthRecursive(String input) {
        long length = 0;
        int i = 0;
        while (i < input.length()) {
            if (input.charAt(i) == '(') {
                int end = input.indexOf(')', i);
                String marker = input.substring(i + 1, end);
                String[] parts = marker.split("x");
                int charsToRepeat = Integer.parseInt(parts[0]);
                int repeatCount = Integer.parseInt(parts[1]);
                String repeatedSection = input.substring(end + 1, end + 1 + charsToRepeat);
                length += calculateDecompressedLengthRecursive(repeatedSection) * repeatCount;
                i = end + 1 + charsToRepeat; // Move past the marker and the repeated characters
            } else {
                length++;
                i++;
            }
        }
        return length;
    }
}
