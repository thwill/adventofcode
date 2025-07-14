package de.twisssow.adventofcode.mmxv;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day20_part1 {


    public static Set<Integer> positiveTeilerAlsSet(int n) {
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

    public static void main(String[] args) {

        int n = 1;
        int maxPresents = 33100000;
        maxPresents = 10000;
        int presents = 0;
        while (presents < maxPresents) {
            Set<Integer> teiler = positiveTeilerAlsSet(n);
            presents = teiler.stream().mapToInt(Integer::intValue).sum() * 10;
            System.out.println("Haus " + n + " hat " + presents + " presents.");
            if (presents >= maxPresents) {// 10 presents per house
                System.out.println("Haus " + n + " hat " + presents + " presents.");
            }
            n++;
        }

    }


}

