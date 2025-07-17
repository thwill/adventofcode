package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.mmxv.day23.Instruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day23_part2 {

    public static void main(String[] args) {


        Map<String, Long> registerMap = new HashMap<>(Map.of("a", 1l, "b", 0l));
        List<Instruction> instructions = new ArrayList<>();

        // Read instructions from file
        List<String> lines = LineReader.readInputFile("mmxv/instructions.txt");
        for (int i = 0; i < lines.size(); i++) {
            instructions.add(new Instruction(lines.get(i)));
        }

        Integer nextInstruction = 0;
        while (nextInstruction >= 0 && nextInstruction < instructions.size() ) {

            System.out.printf("Executing %d: %s, registers: %s%n",
                    nextInstruction,
                    instructions.get(nextInstruction),
                    registerMap);

            Instruction instruction = instructions.get(nextInstruction);
            Integer offset = instruction.execute(registerMap);
            nextInstruction += offset;
        }
        System.out.println("Register A: " + registerMap.get("a"));
        System.out.println("Register B: " + registerMap.get("b"));

    }


}
