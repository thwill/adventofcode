package de.twisssow.adventofcode.common;

import java.util.HashSet;
import java.util.Set;

public class SubsetCalculator {

    public static <T> Set<Set<T>> calculate(Set<T> originalSet) {
        Set<Set<T>> result = new HashSet<>();
        result.add(new HashSet<>()); // leere Menge

        for (T element : originalSet) {
            Set<Set<T>> newSetOfSubsets = new HashSet<>();
            for (Set<T> set : result) {
                Set<T> newSet = new HashSet<>(set);
                newSet.add(element);
                newSetOfSubsets.add(newSet);
            }
            result.addAll(newSetOfSubsets);
        }
        return result;
    }
}
