package de.twisssow.adventofcode.mmxvi;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.common.Matrix;
import de.twisssow.adventofcode.common.MatrixPosition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day2_part1 {


    private static enum Move {
        UP, DOWN, LEFT, RIGHT
    }

    private static Matrix<Integer> initializeKeypad() {
        Matrix<Integer> keypad = new Matrix<>(3, 3);
        keypad.set(new MatrixPosition(1, 1), 1);
        keypad.set(new MatrixPosition(1, 2), 2);
        keypad.set(new MatrixPosition(1, 3), 3);
        keypad.set(new MatrixPosition(2, 1), 4);
        keypad.set(new MatrixPosition(2, 2), 5);
        keypad.set(new MatrixPosition(2, 3), 6);
        keypad.set(new MatrixPosition(3, 1), 7);
        keypad.set(new MatrixPosition(3, 2), 8);
        keypad.set(new MatrixPosition(3, 3), 9);
        return keypad;
    }

    private static void fillMoveMap(List<String> instructionLines, HashMap<Integer, List<Move>> movesMap) {
        for(int i = 0; i < instructionLines.size(); i++) {
            String line = instructionLines.get(i);
            List<Move> moves = new ArrayList<>();
            for (char c : line.toCharArray()) {
                switch (c) {
                    case 'U':
                        moves.add(Move.UP);
                        break;
                    case 'D':
                        moves.add(Move.DOWN);
                        break;
                    case 'L':
                        moves.add(Move.LEFT);
                        break;
                    case 'R':
                        moves.add(Move.RIGHT);
                        break;
                }
            }
            movesMap.put(i + 1, moves);
        }
    }

    private static MatrixPosition findNewPosition(MatrixPosition currentPosition, Move move) {
        int newRow = currentPosition.getRow();
        int newCol = currentPosition.getCol();
        switch (move) {
            case UP:
                newRow =  newRow - 1;
                if (newRow < 1) {
                    return Matrix.INVALID_POSITION; // Invalid move
                }
                break;
            case DOWN:
                newRow = newRow + 1;
                if (newRow > 3) {
                    return Matrix.INVALID_POSITION; // Invalid move
                }
                break;
            case LEFT:
                newCol = newCol - 1;
                if (newCol < 1) {
                    return Matrix.INVALID_POSITION; // Invalid move
                }
                break;
            case RIGHT:
                newCol =  newCol + 1;
                if (newCol > 3) {
                    return Matrix.INVALID_POSITION; // Invalid move
                }
                break;
        }
        return new MatrixPosition(newRow, newCol);
    }

    public static void main(String[] args) {
        Matrix<Integer> keypad = initializeKeypad();
        HashMap<Integer,List<Move>> movesMap = new HashMap<>();
        List<String> instructionLines = LineReader.readInputFile("mmxvi/key_instructions.txt");
        fillMoveMap(instructionLines, movesMap);
        final String[] solution = {""};
        final MatrixPosition[] currentPosition = {new MatrixPosition(2, 2)}; // Start at 5
        movesMap.forEach(
            (key, moves) -> {
                for (Move move : moves) {
                    MatrixPosition newPosition = findNewPosition(currentPosition[0], move);
                    if (!newPosition.equals(Matrix.INVALID_POSITION)) {
                        currentPosition[0] = newPosition;
                    }
                }
                System.out.println("Key for instruction " + key + ": " + keypad.get(currentPosition[0]));
                solution[0] = solution[0] + keypad.get(currentPosition[0]).toString();
            }
        );
        System.out.println("Final solution: " + solution[0]);














    }




}
