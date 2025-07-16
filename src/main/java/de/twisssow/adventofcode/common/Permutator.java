package de.twisssow.adventofcode.common;


import java.util.ArrayList;
import java.util.List;

public class Permutator {

    private Permutator() {
    }

    public static <T> List<List<T>> permutiere(List<T> input) {
        List<List<T>> result = new ArrayList<>();
        if (input.isEmpty()) {
            result.add(new ArrayList<>());
            return result;
        }

        for (int i = 0; i < input.size(); i++) {
            T current = input.get(i);
            List<T> remaining = new ArrayList<>(input);
            remaining.remove(i);

            List<List<T>> subPermutations = permutiere(remaining);
            for (List<T> sub : subPermutations) {
                List<T> newPermutation = new ArrayList<>();
                newPermutation.add(current);
                newPermutation.addAll(sub);
                result.add(newPermutation);
            }
        }

        return result;
    }
}
