package de.twisssow.adventofcode.mmxv.helper.day7;

import java.util.List;

public interface Gate {
    void calculate();

    List<Wire> getInputWires();

    U16 getSignal();
}

