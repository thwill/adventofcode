package de.twisssow.adventofcode.mmxv;

public class Day10 {

    private static final String INPUT = "1113222113";


    public static void main(String[] args) {
        lookAndSayLoop(40);
        lookAndSayLoop(50);
    }

    private static void lookAndSayLoop(int iterations) {
        String input = INPUT;
        for (int i = 1; i <= iterations; i++) {
            input = lookAndSay(input);
        }
        System.out.println("Length after "  + iterations + " iterations: " + input.length());
    }

    private static String lookAndSay(String input) {
        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        char currentChar = chars[0];
        int currentCount = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == currentChar) {
                currentCount++;
            } else if (chars[i] != currentChar) {
                result.append(currentCount).append(currentChar);
                currentChar = chars[i];
                currentCount = 1;
            }
        }
        result.append(currentCount).append(currentChar);
        return result.toString();
    }
}