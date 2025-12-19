package de.twisssow.adventofcode.mmxxv;

import de.twisssow.adventofcode.common.LineReader;

import java.util.List;

public class Day1_part2 {


    public static void main(String[] args) {

        //List<String> lines = LineReader.readInputFile("mmxxv/dialsSample.txt");
        List<String> lines = LineReader.readInputFile("mmxxv/dials.txt");
        long zeroCLicks = 0;
        int pos = 50;
        System.out.println("- The dial starts by pointing at " + pos);
        for (String line : lines) {


            char dir = line.charAt(0);
            int n = Integer.parseInt(line.substring(1));

            if (dir == 'R') {
                zeroCLicks += (pos == 0) ? (n / 100) : ((pos + n) / 100);
                pos = (pos + n) % 100;
            } else { // 'L'
                zeroCLicks += (pos == 0) ? (n / 100)
                        : (n < pos) ? 0
                        : 1 + (n - pos) / 100;
                pos = (pos - (n % 100) + 100) % 100;   // safe modulo
            }
        }
        System.out.println(zeroCLicks);
    }
}
