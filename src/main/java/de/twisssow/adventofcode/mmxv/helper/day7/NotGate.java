package de.twisssow.adventofcode.mmxv.helper.day7;

import java.util.ArrayList;
import java.util.List;

public class NotGate implements Gate {

    U16 signal;
    List<Wire> inputWires = new ArrayList<>();

    public NotGate(Wire inputWire) {
        inputWires.add(inputWire);
    }

    @Override
    public void calculate() {
        if (inputWires.get(0).hasSignal()) {
            signal = inputWires.get(0).getSignal().not();
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
