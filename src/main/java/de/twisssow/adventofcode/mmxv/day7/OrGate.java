package de.twisssow.adventofcode.mmxv.day7;

import java.util.ArrayList;
import java.util.List;

public class OrGate implements Gate {

    U16 signal;
    List<Wire> inputWires = new ArrayList<>();

    public OrGate( Wire inputWire1, Wire inputWire2) {
        inputWires.add(inputWire1);
        inputWires.add(inputWire2);
    }

    @Override
    public void calculate() {
        if (inputWires.get(0).hasSignal() && inputWires.get(1).hasSignal() ) {
            signal = inputWires.get(0).getSignal().or(inputWires.get(1).getSignal());
        }
    }

    @Override
    public List<Wire> getInputWires() {
        return inputWires;
    }

    @Override
    public U16 getSignal() {
        return signal;
    }
}
