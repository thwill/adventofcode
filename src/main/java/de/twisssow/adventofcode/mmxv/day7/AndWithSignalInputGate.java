package de.twisssow.adventofcode.mmxv.day7;

import java.util.ArrayList;
import java.util.List;

public class AndWithSignalInputGate implements Gate {

    U16 signal;
    U16 inputSignal;

    List<Wire> inputWires = new ArrayList<>();

    public AndWithSignalInputGate(U16 inputSignal, Wire input) {
        this.inputSignal = inputSignal;
        inputWires.add(input);
    }

    @Override
    public void calculate() {
        if (inputWires.get(0).hasSignal()) {
            signal = inputSignal.and(inputWires.get(0).getSignal());
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
