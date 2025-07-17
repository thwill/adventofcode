package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.mmxv.day6.LightGrid;

import java.io.IOException;
import java.util.List;

public class Day6_part2 {

    static LightGrid grid = new LightGrid(1000  );

    public static void main(String[] args) throws IOException {
        List<String> lines = LineReader.readInputFile("mmxv/lightgrid_commands.txt");
        lines.forEach(line -> {grid.workCommandWithBrighness(line);});
        System.out.println("Total Brightness of lights on: " + grid.calculateBrightness());
    }
}
