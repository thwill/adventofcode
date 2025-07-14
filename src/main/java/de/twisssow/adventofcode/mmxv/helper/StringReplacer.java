package de.twisssow.adventofcode.mmxv.helper;

import java.util.ArrayList;
import java.util.List;

public class StringReplacer {

    /**
     * Findet alle Start-Positionen des Substrings in text.
     */
    public static List<Integer> findOccurrences(String text, String substring) {
        List<Integer> positions = new ArrayList<>();
        int index = text.indexOf(substring);
        while (index >= 0) {
            positions.add(index);
            index = text.indexOf(substring, index + substring.length());
        }
        return positions;
    }

    /**
     * Erzeugt eine Liste aller Strings, bei denen jeweils nur ein Vorkommen ersetzt wurde.
     */
    public static List<String> replaceOccurrencesIndividually(
            String text, String substring, String replacement) {
        List<String> results = new ArrayList<>();
        List<Integer> positions = findOccurrences(text, substring);

        for (int pos : positions) {
            StringBuilder sb = new StringBuilder();
            sb.append(text, 0, pos);
            sb.append(replacement);
            sb.append(text, pos + substring.length(), text.length());
            results.add(sb.toString());
        }
        return results;
    }

    public static void main(String[] args) {
        String text = "abc def abc ghi abc";
        String substring = "abc";
        String replacement = "XYZ";

        System.out.println("Original: " + text);
        List<String> results = replaceOccurrencesIndividually(text, substring, replacement);

        System.out.println("\nErgebnisse:");
        for (String s : results) {
            System.out.println(s);
        }
    }
}
