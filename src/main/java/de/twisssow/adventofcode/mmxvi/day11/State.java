package de.twisssow.adventofcode.mmxvi.day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class State {

    public static final int TOP_FLOOR_NUMBER = 4;

    private Elevator elevator;
    private Map<Integer, Floor> floors = new HashMap<>();

    private int cost; // g(n): bisherige Schritte

    public State(Elevator elevator, Map<Integer, Floor> floors) {
        this.elevator = elevator;
        this.floors = floors;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public Map<Integer, Floor> getFloors() {
        return floors;
    }

    public void setFloors(Map<Integer, Floor> floors) {
        this.floors = floors;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<State> getNextStates() {
        List<State> nextStates = new ArrayList<>();

        int currentFloor = elevator.getCurrentFloor();
        Floor floor = floors.get(currentFloor);
        Set<TransportItem> items = floor.getItems();

        List<List<TransportItem>> combinations = generateCombinations(items);

        if (currentFloor < TOP_FLOOR_NUMBER) {
            for (List<TransportItem> combo : combinations) {
                State newState = createMovedState(currentFloor, currentFloor + 1, combo);
                if (newState != null && newState.isValid()) {
                    nextStates.add(newState);
                }
            }
        }

        if (currentFloor > 1) {
            for (List<TransportItem> combo : combinations) {
                State newState = createMovedState(currentFloor, currentFloor - 1, combo);
                if (newState != null && newState.isValid() && hasItemsBelow(currentFloor)) {
                    nextStates.add(newState);
                }
            }
        }

        return nextStates;
    }

    private List<List<TransportItem>> generateCombinations(Set<TransportItem> items) {
        List<TransportItem> itemList = new ArrayList<>(items);
        List<List<TransportItem>> result = new ArrayList<>();

        for (int i = 0; i < itemList.size(); i++) {
            result.add(List.of(itemList.get(i)));
            for (int j = i + 1; j < itemList.size(); j++) {
                result.add(List.of(itemList.get(i), itemList.get(j)));
            }
        }

        return result;
    }

    private State


    createMovedState(int from, int to, List<TransportItem> itemsToMove) {
        Map<Integer, Floor> newFloors = new HashMap<>();
        for (Map.Entry<Integer, Floor> entry : floors.entrySet()) {
            newFloors.put(entry.getKey(), entry.getValue().copy());
        }

        Floor fromFloor = newFloors.get(from);
        Floor toFloor = newFloors.get(to);
        for (TransportItem item : itemsToMove) {
            fromFloor.removeItem(item);
            toFloor.addItem(item);
        }

        Elevator newElevator = new Elevator(to);
        State newState = new State(newElevator, newFloors);
        newState.setCost(this.cost + 1);
        return newState;
    }

    private boolean hasItemsBelow(int floor) {
        for (int f = 1; f < floor; f++) {
            if (!floors.get(f).getItems().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean isValid() {
        for (Floor floor : floors.values()) {
            if (!floor.isSafe()) {
                return false;
            }
        }
        return true;
    }

    public boolean hasReachedGoal1() {
        return floors.get(TOP_FLOOR_NUMBER).isFilled1();
    }

    public boolean hasReachedGoal2() {
        return floors.get(TOP_FLOOR_NUMBER).isFilled2();
    }

    public int getHeuristicScore() {
        int score = 0;
        for (Map.Entry<Integer, Floor> entry : floors.entrySet()) {
            int floorNumber = entry.getKey();
            int distance = TOP_FLOOR_NUMBER - floorNumber;
            score += distance * entry.getValue().getItems().size();
        }
        return score;
    }

    public int getTotalEstimatedCost() {
        return cost + getHeuristicScore();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        State other = (State) obj;
        return this.elevator.getCurrentFloor() == other.elevator.getCurrentFloor()
                && this.normalizedState().equals(other.normalizedState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(elevator.getCurrentFloor(), normalizedState());
    }

    private String normalizedState() {
        Map<Element, Integer> chipFloors = new HashMap<>();
        Map<Element, Integer> generatorFloors = new HashMap<>();

        for (Map.Entry<Integer, Floor> entry : floors.entrySet()) {
            int floor = entry.getKey();
            for (TransportItem item : entry.getValue().getItems()) {
                if (item.getType() == Type.MICROCHIP) {
                    chipFloors.put(item.getElement(), floor);
                } else {
                    generatorFloors.put(item.getElement(), floor);
                }
            }
        }

        // Erzeuge Liste aus Paaren (ChipEtage, GenEtage)
        List<String> pairs = new ArrayList<>();
        for (Element element : chipFloors.keySet()) {
            int chip = chipFloors.get(element);
            int gen = generatorFloors.getOrDefault(element, -1);
            pairs.add(chip + "," + gen);
        }

        // Sortieren für kanonische Darstellung
        Collections.sort(pairs);

        // Füge Fahrstuhlposition dazu
        return elevator.getCurrentFloor() + "|" + String.join("|", pairs);
    }
}
