package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.mmxv.day6.Light;
import de.twisssow.adventofcode.mmxv.day6.LightGrid;

import java.util.List;

public class Day18_part1 {


    private static LightGrid calculateNextGeneration(LightGrid grid) {
        int size = grid.getGridSize();
        LightGrid nextGrid = new LightGrid(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Light currentLight = grid.getLight(i, j);
                List<Light> neighbors = grid.getNeighbours(i, j);
                int onNeighbors = (int) neighbors.stream().filter(light -> light.getState() == Light.State.ON).count();
                if (currentLight.getState() == Light.State.ON) {
                    if (onNeighbors < 2 || onNeighbors > 3) {
                        nextGrid.getLight(i, j).turnOff();
                    } else {
                        nextGrid.getLight(i, j).turnOn();
                    }
                } else {
                    if (onNeighbors == 3) {
                        nextGrid.getLight(i, j).turnOn();
                    } else {
                        nextGrid.getLight(i, j).turnOff();
                    }
                }

            }
        }
        return nextGrid;
    }

    public static void main(String[] args) {

        List<String> lines = LineReader.readInputFile("mmxv/lightgrid.txt");
        LightGrid grid = new LightGrid(lines.size());

        for (int i = 0; i < lines.size(); i++) {
            char[] chars = lines.get(i).toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == '#') {
                    grid.getLight(i, j).turnOn();
                } else if (chars[j] == '.') {
                    grid.getLight(i, j).turnOff();
                } else {
                    throw new IllegalArgumentException("Invalid character in input: " + chars[j]);
                }
            }
        }
        System.out.println("Initial grid:\n\n" + grid);

        for (int i = 0; i < 100; i++) {
            grid = calculateNextGeneration(grid);
            System.out.println("Count ligths on after generation " + (i + 1) + ": " + grid.countLightsOn());
        }
    }

}

