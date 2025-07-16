package de.twisssow.adventofcode.mmxv;

import java.util.*;

public class Day24_part1 {

    static class BestSolution {
        List<HashSet<Integer>> parts = null;
        int minSize = Integer.MAX_VALUE;
        long minProduct = Long.MAX_VALUE;

        void consider(List<Set<Integer>> candidate) {
            int smallest = candidate.stream().mapToInt(Set::size).min().orElse(0);
            long product = candidate.stream()
                    .filter(s -> s.size() == smallest)
                    .mapToLong(Day24_part1::product)
                    .min().orElse(Long.MAX_VALUE);

            if (smallest < minSize ||
                    (smallest == minSize && product < minProduct)) {
                minSize = smallest;
                minProduct = product;
                parts = candidate.stream().map(HashSet::new).toList();
            }
        }
    }

    public static void main(String[] args) {
        Set<Integer> input = Set.of(
                1, 3, 5, 11, 13, 17, 19, 23, 29, 31,
                41, 43, 47, 53, 59, 61, 67, 71, 73, 79,
                83, 89, 97, 101, 103, 107, 109, 113);

        findBestPartition(input);
    }

    public static void findBestPartition(Set<Integer> input) {
        List<Integer> nums = new ArrayList<>(input);
        nums.sort(Comparator.reverseOrder());
        int totalSum = nums.stream().mapToInt(Integer::intValue).sum();

        if (totalSum % 3 != 0) {
            System.out.println("Summe nicht durch 3 teilbar.");
            return;
        }

        int target = totalSum / 3;
        BestSolution best = new BestSolution();

        backtrack(nums, 0,
                new HashSet<>(), 0,
                new HashSet<>(), 0,
                new HashSet<>(), 0,
                target, best);

        if (best.parts != null) {
            System.out.println("Optimale Lösung gefunden:");
            for (int i = 0; i < 3; i++) {
                System.out.println("Teilmenge " + (i + 1) + ": " + best.parts.get(i));
            }
            System.out.println("Kleinste Teilmenge: " + best.minSize + " Elemente, Produkt: " + best.minProduct);
        } else {
            System.out.println("Keine Lösung gefunden.");
        }
    }

    private static void backtrack(List<Integer> nums, int idx,
                                  Set<Integer> part1, int sum1,
                                  Set<Integer> part2, int sum2,
                                  Set<Integer> part3, int sum3,
                                  int target,
                                  BestSolution best) {
        if (idx == nums.size()) {
            if (sum1 == target && sum2 == target && sum3 == target) {
                best.consider(List.of(
                        new HashSet<>(part1),
                        new HashSet<>(part2),
                        new HashSet<>(part3)
                ));
            }
            return;
        }

        int num = nums.get(idx);

        if (sum1 + num <= target) {
            part1.add(num);
            backtrack(nums, idx + 1, part1, sum1 + num, part2, sum2, part3, sum3, target, best);
            part1.remove(num);
        }

        if (sum2 + num <= target) {
            part2.add(num);
            backtrack(nums, idx + 1, part1, sum1, part2, sum2 + num, part3, sum3, target, best);
            part2.remove(num);
        }

        if (sum3 + num <= target) {
            part3.add(num);
            backtrack(nums, idx + 1, part1, sum1, part2, sum2, part3, sum3 + num, target, best);
            part3.remove(num);
        }
    }

    private static long product(Set<Integer> set) {
        long prod = 1;
        for (int n : set) prod *= n;
        return prod;
    }
}
