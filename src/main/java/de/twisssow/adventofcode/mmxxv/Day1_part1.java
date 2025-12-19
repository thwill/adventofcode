package de.twisssow.adventofcode.mmxxv;

import de.twisssow.adventofcode.common.LineReader;

import java.util.List;

public class Day1_part1 {


    public static void main(String[] args) {

        //List<String> lines = LineReader.readInputFile("mmxxv/dialsSample.txt");
        List<String> lines = LineReader.readInputFile("mmxxv/dials.txt");
        int zeroCounter = 0;
        int position = 50;
        System.out.println("- The dial starts by pointing at " + position);
        for (String line : lines) {
            String direction = line.substring(0, 1);
            int steps = Integer.parseInt(line.substring(1));
            if (steps > 100) {
                steps = steps % 100;

            }
            if (direction.equals("L")) {
                position -= steps;
                if (position < 0) {
                    position += 100;

                }

            } else if (direction.equals("R")) {
                position += steps;
                if (position > 99) {
                    position -= 100;

                }
            }
            if (position == 0) {
                zeroCounter++;
            }
            System.out.println("- The dial is rotated by : " + line + " to point at " + position);
            System.out.println(" zeroCounter is now: " + zeroCounter);
        }
    }


}
