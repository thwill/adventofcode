package de.twisssow.adventofcode.mmxvi.day8;

import de.twisssow.adventofcode.common.Matrix;

public class Screen {


    // Matrix to represent the screen lights
    private final Matrix<Light> screenLights;

    public Screen(int rows, int cols) {
        screenLights = new Matrix<>(rows, cols);
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                screenLights.set(row, col, new Light());
            }
        }
    }

    public void executeCommand(String command) {
        String[] parts = command.split(" ");
        switch (parts[0]) {
            case "rect":
                String[] dimensions = parts[1].split("x");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                rect(width, height);
                break;
            case "rotate":
                if (parts[1].equals("row")) {
                    int row = Integer.parseInt(parts[2].split("=")[1]);
                    int shift = Integer.parseInt(parts[4]);
                    rotateRow(row + 1, shift);
                } else if (parts[1].equals("column")) {
                    int col = Integer.parseInt(parts[2].split("=")[1]);
                    int shift = Integer.parseInt(parts[4]);
                    rotateCol(col + 1, shift);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown command: " + command);
        }
    }

    public int countLitLights() {
        int count = 0;
        for (int row = 1; row <= screenLights.rows(); row++) {
            for (int col = 1; col <= screenLights.cols(); col++) {
                if (screenLights.get(row, col).getState() == Light.State.ON) {
                    count++;
                }
            }
        }
        return count;
    }

    private void rect(int width, int height) {
        for (int row = 1; row <= height; row++) {
            for (int col = 1; col <= width; col++) {
                screenLights.get(row, col).setState(Light.State.ON);
            }
        }
    }


    private void rotateRow(int row, int shift) {
        int cols = screenLights.cols();
        Light[] rowLights = new Light[cols];

        for (int col = 1; col <= cols; col++) {
            rowLights[col - 1] = screenLights.get(row, col);
        }

        for (int col = 0; col < cols; col++) {
            int newCol = (col + shift) % cols;
            screenLights.set(row, newCol + 1, rowLights[col]);
        }
    }

    private void rotateCol(int col, int shift) {
        int rows = screenLights.rows();
        Light[] colLights = new Light[rows];

        for (int row = 1; row <= rows; row++) {
            colLights[row - 1] = screenLights.get(row, col);
        }

        for (int row = 0; row < rows; row++) {
            int newRow = (row + shift) % rows;
            screenLights.set(newRow + 1, col, colLights[row]);
        }
    }

    public void printScreen() {
        for (int row = 1; row <= screenLights.rows(); row++) {
            for (int col = 1; col <= screenLights.cols(); col++) {
                System.out.print(screenLights.get(row, col).getState() == Light.State.ON ? "#" : ".");
            }
            System.out.println();
        }
    }

    public void printLetterByLetter() {

        int letterLength = 5;
        int startCol = 1;

        for (int letter = 1; letter <= 10; letter++) {
            System.out.println("Buchstabe" +letter); // Assuming 10 letters
            for (int row = 1; row <= screenLights.rows(); row++) {
                for (int col = startCol; col <= startCol + (letterLength-1); col++) {
                    System.out.print(screenLights.get(row, col).getState() == Light.State.ON ? "#" : " ");
                }
                System.out.println();
            }
            startCol += letterLength;
            // Move to the next letter's starting column
        }
    }
}
