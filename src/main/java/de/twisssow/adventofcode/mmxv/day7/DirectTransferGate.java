package de.twisssow.adventofcode.mmxv.day7;

import java.util.ArrayList;
import java.util.List;

public class DirectTransferGate implements Gate {

    U16 signal;
    List<Wire> inputWires = new ArrayList<>();

    public DirectTransferGate (Wire inputWire) {
        inputWires.add(inputWire);
    }


    @Override
    public void calculate() {
        if (inputWires.get(0).hasSignal()) {
            signal = inputWires.get(0).getSignal();
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
