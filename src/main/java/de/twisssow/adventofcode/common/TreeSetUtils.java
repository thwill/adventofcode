package de.twisssow.adventofcode.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TreeSetUtils {

    public static <T> List<T> getFirstNElements(TreeSet<T> set, int n) {
        List<T> result = new ArrayList<>(n);
        Iterator<T> it = set.iterator();

        while (it.hasNext() && result.size() < n) {
            result.add(it.next());
        }

        return result;
    }

    public static <T> void removeFirstNElements(TreeSet<T> set, int n) {
        // Hole die ersten n Elemente als Set
        Set<T> toRemove = set.stream()
                .limit(n)
                .collect(Collectors.toSet());

        // Entferne diese aus dem TreeSet
        set.removeAll(toRemove);
    }

    public static Set<Integer> positivDivisorSet(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n muss > 0 sein");
        }

        return IntStream.rangeClosed(1, (int) Math.sqrt(n))
                .filter(i -> n % i == 0)
                .flatMap(i -> {
                    int j = n / i;
                    if (i == j) {
                        return IntStream.of(i);
                    } else {
                        return IntStream.of(i, j);
                    }
                })
                .boxed()
                .collect(Collectors.toCollection(TreeSet::new));  // sortiertes Set
    }

}