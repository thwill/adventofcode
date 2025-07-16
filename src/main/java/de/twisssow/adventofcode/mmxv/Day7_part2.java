package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.mmxv.helper.day7.AndGate;
import de.twisssow.adventofcode.mmxv.helper.day7.AndWithSignalInputGate;
import de.twisssow.adventofcode.mmxv.helper.day7.DirectTransferGate;
import de.twisssow.adventofcode.mmxv.helper.day7.LeftShiftGate;
import de.twisssow.adventofcode.mmxv.helper.day7.NotGate;
import de.twisssow.adventofcode.mmxv.helper.day7.OrGate;
import de.twisssow.adventofcode.mmxv.helper.day7.RightShiftGate;
import de.twisssow.adventofcode.mmxv.helper.day7.U16;
import de.twisssow.adventofcode.mmxv.helper.day7.Wire;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7_part2 {

    public static boolean isInteger(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void resolveWire(String name, Map<String, Wire> wireMap) {
        Wire wireToSolve = wireMap.get(name);
        if (wireToSolve.getSignal() != null) {
            return; // Signal already calculated
        } else {
            wireToSolve.getSignalGate().getInputWires().forEach(
                    wire -> {
                            resolveWire(wire.getName(), wireMap);
                    }
            );
            wireToSolve.getSignalGate().calculate();
            wireToSolve.setSignal(wireToSolve.getSignalGate().getSignal());
        }
    }


    public static void addSourceGates(String[] inputs, Map<String, Wire> wireMap, String output) {
        if (inputs.length == 1) {
            if (isInteger(inputs[0])) {
                wireMap.get(output).setSignal(new U16(Integer.parseInt(inputs[0])));
            } else {
                wireMap.computeIfAbsent(inputs[0], Wire::new);
                wireMap.get(output).setSignalGate(new DirectTransferGate(wireMap.get(inputs[0])));
            }
        } else if (inputs.length == 2) {
            wireMap.computeIfAbsent(inputs[1], Wire::new);
            wireMap.get(output).setSignalGate(new NotGate(wireMap.get(inputs[1])));
        } else if (inputs.length == 3) {
            if (inputs[1].equals("AND")) {
                if (!isInteger(inputs[0]) && !isInteger(inputs[2])) {
                    wireMap.computeIfAbsent(inputs[0], Wire::new);
                    wireMap.computeIfAbsent(inputs[2], Wire::new);
                    wireMap.get(output).setSignalGate(new AndGate(wireMap.get(inputs[0]), wireMap.get(inputs[2])));
                } else if (isInteger(inputs[0]) && !isInteger(inputs[2])) {
                    wireMap.computeIfAbsent(inputs[2], Wire::new);
                    wireMap.get(output).setSignalGate(new AndWithSignalInputGate(new U16(Integer.parseInt(inputs[0])), wireMap.get(inputs[2])));
                } else {
                    throw new IllegalArgumentException("Invalid AND gate inputs: " + inputs[0] + " AND " + inputs[2]);
                }
            } else if (inputs[1].equals("OR")) {
                wireMap.computeIfAbsent(inputs[0], Wire::new);
                wireMap.computeIfAbsent(inputs[2], Wire::new);
                wireMap.get(output).setSignalGate(new OrGate(wireMap.get(inputs[0]), wireMap.get(inputs[2])));
            } else if (inputs[1].equals("LSHIFT")) {
                wireMap.computeIfAbsent(inputs[0], Wire::new);
                wireMap.get(output).setSignalGate(
                        new LeftShiftGate(wireMap.get(inputs[0]), Integer.parseInt(inputs[2])));
            } else if (inputs[1].equals("RSHIFT")) {
                wireMap.computeIfAbsent(inputs[0], Wire::new);
                wireMap.get(output).setSignalGate(
                        new RightShiftGate(wireMap.get(inputs[0]), Integer.parseInt(inputs[2])));
            } else {
                throw new IllegalArgumentException("Unknown gate: " + inputs[1]);
            }
        }
    }


    public static void main(String[] args) {

        Map<String, Wire> wireMap = new HashMap<>();

        List<String> lines = LineReader.readInputFile("mmxv/circuit_new_b.txt");
        lines.forEach(
                line -> {
                    String[] parts = line.split(" -> ");
                    String[] inputs = parts[0].split(" ");
                    String output = parts[1];
                    wireMap.computeIfAbsent(output, Wire::new);
                    addSourceGates(inputs, wireMap, output);
                }
        );

        resolveWire("a", wireMap);
        System.out.println("Signal on wire 'a': " + wireMap.get("a").getSignal());


    }
}
