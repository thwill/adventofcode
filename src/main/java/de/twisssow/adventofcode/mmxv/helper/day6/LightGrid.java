package de.twisssow.adventofcode.mmxv.helper.day6;

import java.util.ArrayList;
import java.util.List;

public class  LightGrid {


    int gridSize;
    Light[][] lights;

    public LightGrid(int gridSize) {
        this.gridSize = gridSize;
        lights = new Light[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                lights[i][j] = new Light(i, j);
            }
        }
    }

    public int countLightsOn() {
        int count = 0;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (lights[i][j].getState() == Light.State.ON) {
                    count++;
                }
            }
        }
        return count;
    }

    public int calculateBrightness() {
        int brightness = 0;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                brightness += lights[i][j].brightness;
            }
        }
        return brightness;
    }

    public void workCommand(String command) {
        String[] parts = command.split(" ");

        if ("toggle".equals(parts[0])) {
            String[] coordinates = parts[1].split(",");
            int x1 = Integer.parseInt(coordinates[0]);
            int y1 = Integer.parseInt(coordinates[1]);
            coordinates = parts[3].split(",");
            int x2 = Integer.parseInt(coordinates[0]);
            int y2 = Integer.parseInt(coordinates[1]);
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    lights[i][j].toggle();
                }
            }
        } else if ("turn on".equals(parts[0] + " " + parts[1])) {
            String[] coordinates = parts[2].split(",");
            int x1 = Integer.parseInt(coordinates[0]);
            int y1 = Integer.parseInt(coordinates[1]);
            coordinates = parts[4].split(",");
            int x2 = Integer.parseInt(coordinates[0]);
            int y2 = Integer.parseInt(coordinates[1]);
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    lights[i][j].turnOn();
                }
            }
        } else if ("turn off".equals(parts[0] + " " + parts[1])) {
            String[] coordinates = parts[2].split(",");
            int x1 = Integer.parseInt(coordinates[0]);
            int y1 = Integer.parseInt(coordinates[1]);
            coordinates = parts[4].split(",");
            int x2 = Integer.parseInt(coordinates[0]);
            int y2 = Integer.parseInt(coordinates[1]);
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    lights[i][j].turnOff();
                }
            }
        } else {
            throw new IllegalArgumentException("Unknown command: " + command);
        }
        System.out.println("lights on after command: " + command + " = " + countLightsOn());
    }

    public void workCommandWithBrighness(String command) {
        String[] parts = command.split(" ");

        if ("toggle".equals(parts[0])) {
            String[] coordinates = parts[1].split(",");
            int x1 = Integer.parseInt(coordinates[0]);
            int y1 = Integer.parseInt(coordinates[1]);
            coordinates = parts[3].split(",");
            int x2 = Integer.parseInt(coordinates[0]);
            int y2 = Integer.parseInt(coordinates[1]);
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    lights[i][j].toggleBrightness();
                }
            }
        } else if ("turn on".equals(parts[0] + " " + parts[1])) {
            String[] coordinates = parts[2].split(",");
            int x1 = Integer.parseInt(coordinates[0]);
            int y1 = Integer.parseInt(coordinates[1]);
            coordinates = parts[4].split(",");
            int x2 = Integer.parseInt(coordinates[0]);
            int y2 = Integer.parseInt(coordinates[1]);
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    lights[i][j].turnOnBrightness();
                }
            }
        } else if ("turn off".equals(parts[0] + " " + parts[1])) {
            String[] coordinates = parts[2].split(",");
            int x1 = Integer.parseInt(coordinates[0]);
            int y1 = Integer.parseInt(coordinates[1]);
            coordinates = parts[4].split(",");
            int x2 = Integer.parseInt(coordinates[0]);
            int y2 = Integer.parseInt(coordinates[1]);
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    lights[i][j].turnOffBrightness();
                }
            }
        } else {
            throw new IllegalArgumentException("Unknown command: " + command);
        }
        System.out.println("brightness after command: " + command + " = " + calculateBrightness());
    }

    public Light getLight(int x, int y) {
        if (x < 0 || x >= gridSize || y < 0 || y >= gridSize) {
            throw new IndexOutOfBoundsException("Coordinates out of bounds: (" + x + ", " + y + ")");
        }
        return lights[x][y];
    }

    public List<Light> getNeighbours(int x, int y) {
        List<Light> neighbours = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Skip the light itself
                int newX = x + i;
                int newY = y + j;
                if (newX >= 0 && newX < gridSize && newY >= 0 && newY < gridSize) {
                    neighbours.add(lights[newX][newY]);
                }
            }
        }
        return neighbours;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    @Override
    public String toString() {
        if (lights == null || lights.length == 0) {
            return "LightGrid is empty";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    sb.append(lights[i][j].getState() == Light.State.ON ? "#" : ".");
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}