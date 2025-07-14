package de.twisssow.adventofcode.mmxv.helper.day7;


public class Wire {

    private String name;
    private Gate signalGate;
    private U16 signal;

    public Wire(String name) {
        this.name = name;
    }

    public U16 getSignal() {
        return signal;
    }

    public void setSignal(U16 signal) {
        this.signal = signal;
    }

    public void setSignalGate(Gate signalGate) {
        this.signalGate = signalGate;
    }

    public Gate getSignalGate() {
        return signalGate;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " = " + signal;
    }

    public boolean hasSignal() {
        return signal != null;
    }
}
