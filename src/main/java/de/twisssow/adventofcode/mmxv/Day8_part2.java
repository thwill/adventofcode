package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.common.LineReader;

import java.util.List;

public class Day8_part2 {


    public static void main(String[] args) {

        List<String> lines = LineReader.readInputFile("mmxv/strings.txt");
        int escapedLength = 0;
        int codeLength = 0;


        for (String line : lines) {


            System.out.println(line);
            System.out.println(line.length());
            codeLength += line.length();
            String trimmedLineFromQuotes = new String(line.substring(1, line.length() - 1));
            String escapedLine = trimmedLineFromQuotes
                    .replace("\\", "\\\\")  // must be first!
                    .replace("\"", "\\\"");
            String newLine = new String("\"\\\"" + escapedLine + "\\\"\"");
            System.out.println(newLine);
            System.out.println(newLine.length());

            escapedLength += newLine.length();
        }
        System.out.println("Escaped length: " + escapedLength + " Code length: " + codeLength + " Difference: " + (escapedLength - codeLength));
    }


}
