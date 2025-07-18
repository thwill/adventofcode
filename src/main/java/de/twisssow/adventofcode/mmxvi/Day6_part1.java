package de.twisssow.adventofcode.mmxvi;

import de.twisssow.adventofcode.common.LineReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6_part1 {

    public static void main(String[] args) {

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] solution = new char[8];
        Map<Integer, int[]> frequencyMap = new HashMap<Integer, int[]>();
        for (int i = 0; i < 8; i++) {
            frequencyMap.put(i, new int[26]);
        }

        List<String> inputLines = LineReader.readInputFile("mmxvi/message.txt");
        inputLines.forEach(line -> {
            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = alphabet.indexOf(chars[i]);
                if (index != -1) {
                    frequencyMap.get(i)[index]++;
                }
            }
        });
        for (int i = 0; i < solution.length; i++) {
            int[] frequencies = frequencyMap.get(i);
            int maxIndex = 0;
            for (int j = 1; j < frequencies.length; j++) {
                if (frequencies[j] > frequencies[maxIndex]) {
                    maxIndex = j;
                }
            }
            solution[i] = alphabet.charAt(maxIndex);
        }

        System.out.println("Lösung: " + new String(solution));


    }

}
