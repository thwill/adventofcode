package de.twisssow.adventofcode.mmxvi;

import de.twisssow.adventofcode.common.LineReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12_part2 {


    public static Map<String, Integer> registers = new HashMap<>(
            Map.of(
                    "a", 0,
                    "b", 0,
                    "c", 1,
                    "d", 0
            )
    );


    public static void main(String[] args) {

        Map<Integer, String> instructions = new HashMap<>();
        List<String> inputLines = LineReader.readInputFile("mmxvi/assembunny.txt");
        final int[] i = {1};
        inputLines.forEach(
                input -> {
                    instructions.put(i[0], input);
                    i[0]++;
                }
        );
        int nextInstruction = 1;
        while (nextInstruction <= instructions.size()) {
            String instruction = instructions.get(nextInstruction);
            String[] parts = instruction.split(" ");
            switch (parts[0]) {
                case "cpy":
                    String x = parts[1];
                    String y = parts[2];
                    int valueToCopy;
                    if (isNumeric(x)) {
                        valueToCopy = Integer.parseInt(x);
                    } else {
                        valueToCopy = registers.get(x);
                    }
                    registers.put(y, valueToCopy);
                    nextInstruction++;
                    break;
                case "inc":
                    String regToInc = parts[1];
                    registers.put(regToInc, registers.get(regToInc) + 1);
                    nextInstruction++;
                    break;
                case "dec":
                    String regToDec = parts[1];
                    registers.put(regToDec, registers.get(regToDec) - 1);
                    nextInstruction++;
                    break;
                case "jnz":
                    String check = parts[1];
                    String offsetStr = parts[2];
                    int checkValue;
                    if (isNumeric(check)) {
                        checkValue = Integer.parseInt(check);
                    } else {
                        checkValue = registers.get(check);
                    }
                    int offset;
                    if (isNumeric(offsetStr)) {
                        offset = Integer.parseInt(offsetStr);
                    } else {
                        offset = registers.get(offsetStr);
                    }
                    if (checkValue != 0) {
                        nextInstruction += offset;
                    } else {
                        nextInstruction++;
                    }
                    break;
                default:
                    System.out.println("Unknown instruction: " + instruction);
                    nextInstruction++;
            }
        }

        for (Map.Entry<String, Integer> entry : registers.entrySet()) {
            System.out.println("Register " + entry.getKey() + ": " + entry.getValue());
        }
    }


    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
