package de.twisssow.adventofcode.mmxvi;

import de.twisssow.adventofcode.mmxvi.day11.Element;
import de.twisssow.adventofcode.mmxvi.day11.Elevator;
import de.twisssow.adventofcode.mmxvi.day11.Floor;
import de.twisssow.adventofcode.mmxvi.day11.Solver;
import de.twisssow.adventofcode.mmxvi.day11.State;
import de.twisssow.adventofcode.mmxvi.day11.TransportItem;
import de.twisssow.adventofcode.mmxvi.day11.Type;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day11 {


    public static void main(String[] args) {
        State initialState = initialState();
        System.out.println(Solver.solve(initialState));
        State initialState2 = initialState2();
        System.out.println(Solver.solve2(initialState2));
    }


    private static State initialState() {
        /*
        The first floor contains a thulium generator, a thulium-compatible microchip, a plutonium generator, and a strontium generator.
        The second floor contains a plutonium-compatible microchip and a strontium-compatible microchip.
        The third floor contains a promethium generator, a promethium-compatible microchip, a ruthenium generator, and a ruthenium-compatible microchip.
        The fourth floor contains nothing relevant.
         */

        Elevator e = new Elevator();
        Floor f1 = new Floor(1, new HashSet<>(Set.of(
                new TransportItem(Element.THULIUM, Type.GENERATOR),
                new TransportItem(Element.THULIUM, Type.MICROCHIP),
                new TransportItem(Element.PLUTONIUM, Type.GENERATOR),
                new TransportItem(Element.STRONTIUM, Type.GENERATOR))
        ));
        Floor f2 = new Floor(2, new HashSet<>(Set.of(
                new TransportItem(Element.PLUTONIUM, Type.MICROCHIP),
                new TransportItem(Element.STRONTIUM, Type.MICROCHIP))
        ));
        Floor f3 = new Floor(3, new HashSet<>(Set.of(
                new TransportItem(Element.PROMETHIUM, Type.GENERATOR),
                new TransportItem(Element.PROMETHIUM, Type.MICROCHIP),
                new TransportItem(Element.RUTHENIUM, Type.GENERATOR),
                new TransportItem(Element.RUTHENIUM, Type.MICROCHIP))
        ));
        Floor f4 = new Floor(4, new HashSet<>());

        Map<Integer, Floor> floors = new HashMap<>(Map.of(
                1, f1,
                2, f2,
                3, f3,
                4, f4
        ));
        return new State(e, floors);

    }

    private static State initialState2() {
        /*
        The first floor contains a thulium generator, a thulium-compatible microchip, a plutonium generator, and a strontium generator.
        The second floor contains a plutonium-compatible microchip and a strontium-compatible microchip.
        The third floor contains a promethium generator, a promethium-compatible microchip, a ruthenium generator, and a ruthenium-compatible microchip.
        The fourth floor contains nothing relevant.
         */

        Elevator e = new Elevator();
        Floor f1 = new Floor(1, new HashSet<>(Set.of(
                new TransportItem(Element.THULIUM, Type.GENERATOR),
                new TransportItem(Element.THULIUM, Type.MICROCHIP),
                new TransportItem(Element.PLUTONIUM, Type.GENERATOR),
                new TransportItem(Element.STRONTIUM, Type.GENERATOR),
                new TransportItem(Element.ELERIUM, Type.GENERATOR),
                new TransportItem(Element.ELERIUM, Type.MICROCHIP),
                new TransportItem(Element.DILITHIUM, Type.GENERATOR),
                new TransportItem(Element.DILITHIUM, Type.MICROCHIP))
        ));
        Floor f2 = new Floor(2, new HashSet<>(Set.of(
                new TransportItem(Element.PLUTONIUM, Type.MICROCHIP),
                new TransportItem(Element.STRONTIUM, Type.MICROCHIP))
        ));
        Floor f3 = new Floor(3, new HashSet<>(Set.of(
                new TransportItem(Element.PROMETHIUM, Type.GENERATOR),
                new TransportItem(Element.PROMETHIUM, Type.MICROCHIP),
                new TransportItem(Element.RUTHENIUM, Type.GENERATOR),
                new TransportItem(Element.RUTHENIUM, Type.MICROCHIP))
        ));
        Floor f4 = new Floor(4, new HashSet<>());

        Map<Integer, Floor> floors = new HashMap<>(Map.of(
                1, f1,
                2, f2,
                3, f3,
                4, f4
        ));
        return new State(e, floors);

    }


}
