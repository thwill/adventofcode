package de.twisssow.adventofcode.mmxv.day7;

import java.util.ArrayList;
import java.util.List;

public class LeftShiftGate implements Gate {

    private U16 signal;
    private int shift;
    List<Wire> inputWires = new ArrayList<>();

    public LeftShiftGate(Wire inputWire, int shift) {
        inputWires.add(inputWire);
        this.shift = shift;
    }

    @Override
    public void calculate() {
        if (inputWires.get(0).hasSignal()) {
            signal = inputWires.get(0).getSignal().shiftLeft(shift);
        }
    }

    @Override
    public U16 getSignal() {
        return signal;
    }

    @Override
    public List<Wire> getInputWires() {
        return inputWires;
    }


}
