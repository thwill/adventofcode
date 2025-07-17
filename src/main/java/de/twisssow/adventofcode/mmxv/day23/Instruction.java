package de.twisssow.adventofcode.mmxv.day23;

import java.util.Map;

public class Instruction {


    String targetRegister;

    String cmd;

    int offset;

    String instruction;


    public Instruction(String instruction) {
        this.instruction = instruction;
        String[] parts = instruction.split(" ");
        this.cmd = parts[0];
        this.targetRegister = null;
        if (cmd.equals("jmp")) {
            this.offset = Integer.parseInt(parts[1]);
        } else if (cmd.equals("jie") || cmd.equals("jio")) {
            this.targetRegister = parts[1].replace(",", "");
            this.offset = Integer.parseInt(parts[2]);
        } else {
            this.targetRegister = parts[1];
        }
    }


    public Integer execute(Map<String,Long> registerMap ) {
        if (cmd.equals("hlf")) {
            registerMap.put(targetRegister, registerMap.get(targetRegister) / 2);
            return 1;
        } else if (cmd.equals("tpl")) {
            registerMap.put(targetRegister, registerMap.get(targetRegister) * 3);
            return 1;
        } else if (cmd.equals("inc")) {
            registerMap.put(targetRegister, registerMap.get(targetRegister) + 1);
            return 1;
        } else if (cmd.equals("jmp")) {
            return offset;
        } else if (cmd.equals("jie")) {
            if (registerMap.get(targetRegister) % 2 == 0) {
                return offset;
            } else {
                return 1;
            }
        } else if (cmd.equals("jio")) {
            if (registerMap.get(targetRegister) == 1) {
                return offset;
            } else {
                return 1;
            }
        }
        throw new IllegalStateException(" could not execute instruction: " + cmd + " " + targetRegister + " " + offset);

    }

    @Override
    public String toString() {
        return instruction;
    }
}
