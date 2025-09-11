package de.twisssow.adventofcode.mmxvi.day11;

import java.util.Arrays;

public class Elevator {

    private final static String EMPTY_ELEMENT_MASK = "  -   ";
    private final static String GENERATOR_SUFFIX = "-G  ";
    private final static String MICROCHIP_SUFFIX = "-M  ";


    private int currentFloor;
    private TransportItem[] carriage = new TransportItem[2]; // Assuming the elevator can carry 2 items


    public Elevator(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Elevator() {
        this.currentFloor = 1; // Default to ground floor
    }

   public int getCurrentFloor() {
        return currentFloor;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Elevator elevator = (Elevator) o;
        return currentFloor == elevator.currentFloor && Arrays.equals(carriage, elevator.carriage);
    }

    @Override
    public int hashCode() {
        int result = currentFloor;
        result = 31 * result + Arrays.hashCode(carriage);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("E |");
        if (carriage[0] != null) {
            sb.append(carriage[0].getElement());
            if (carriage[0].getType() == Type.GENERATOR) {
                sb.append(GENERATOR_SUFFIX);
            } else {
                sb.append(MICROCHIP_SUFFIX);
            }
        } else {
            sb.append(EMPTY_ELEMENT_MASK);
        }
        if (carriage[1] != null) {
            sb.append(carriage[1].getElement());
            if (carriage[1].getType() == Type.GENERATOR) {
                sb.append(GENERATOR_SUFFIX);
            } else {
                sb.append(MICROCHIP_SUFFIX);
            }
        } else {
            sb.append(EMPTY_ELEMENT_MASK);
        }
        sb.append("|");
        return sb.toString();
    }
}
