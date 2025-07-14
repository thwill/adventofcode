package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.mmxv.helper.LineReader;

import java.util.List;

public class Day8_part1 {


    public static void main(String[] args) {

        List<String> lines = LineReader.readInputFile("strings.txt");
        int memoryLength = 0;
        int codeLength = 0;

        for (String line : lines) {
            System.out.println(line);
            System.out.println(line.length());
            codeLength += line.length();
            String trimmedLineFromQuotes = new String(line.substring(1, line.length() - 1));
            System.out.println(trimmedLineFromQuotes);
            String newLine = new String(trimmedLineFromQuotes.replaceAll("\\\\\"", "q"));
            String newLine2 = new String(newLine.replaceAll("\\\\\\\\", "b"));
            String newLine3 = new String (newLine2.replaceAll("\\\\x[0-9A-Fa-f]{2}", "x"));
            System.out.println(newLine3);
            System.out.println(newLine3.length());
            memoryLength += newLine3.length();
        }
        System.out.println("Code length: " + codeLength + " Memory length: " + memoryLength + " Difference: " + (codeLength - memoryLength));
    }
}
