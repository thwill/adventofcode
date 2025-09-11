package de.twisssow.adventofcode.mmxvi.day11;

import de.twisssow.adventofcode.mmxvi.day11.State;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Solver {

    public static int solve(State initialState) {
        PriorityQueue<State> openSet = new PriorityQueue<>(Comparator.comparingInt(State::getTotalEstimatedCost));
        Set<State> visited = new HashSet<>();

        initialState.setCost(0);
        openSet.add(initialState);

        while (!openSet.isEmpty()) {
            State current = openSet.poll();

            if (current.hasReachedGoal1()) {
                return current.getCost();
            }

            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            for (State neighbor : current.getNextStates()) {
                if (visited.contains(neighbor)) {
                    continue;
                }

                neighbor.setCost(current.getCost() + 1);
                openSet.add(neighbor);
            }
        }

        throw new IllegalStateException("No solution found.");
    }

    public static int solve2(State initialState) {
        PriorityQueue<State> openSet = new PriorityQueue<>(Comparator.comparingInt(State::getTotalEstimatedCost));
        Set<State> visited = new HashSet<>();

        initialState.setCost(0);
        openSet.add(initialState);

        while (!openSet.isEmpty()) {
            State current = openSet.poll();

            if (current.hasReachedGoal2()) {
                return current.getCost();
            }

            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            for (State neighbor : current.getNextStates()) {
                if (visited.contains(neighbor)) {
                    continue;
                }

                neighbor.setCost(current.getCost() + 1);
                openSet.add(neighbor);
            }
        }

        throw new IllegalStateException("No solution found.");
    }

}