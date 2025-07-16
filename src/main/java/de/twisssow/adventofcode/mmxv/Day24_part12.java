package de.twisssow.adventofcode.mmxv;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Day24_part12 {

    static class BestSolution {
        List<HashSet<Integer>> parts = null;
        int minSize = Integer.MAX_VALUE;
        long minProduct = Long.MAX_VALUE;

        synchronized void consider(List<Set<Integer>> candidate) {
            int smallest = candidate.stream().mapToInt(Set::size).min().orElse(0);
            long product = candidate.stream()
                    .filter(s -> s.size() == smallest)
                    .mapToLong(Day24_part12::product)
                    .min().orElse(Long.MAX_VALUE);

            if (smallest < minSize || (smallest == minSize && product < minProduct)) {
                minSize = smallest;
                minProduct = product;
                parts = candidate.stream().map(HashSet::new).toList();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Set<Integer> input = Set.of(
                1, 3, 5, 11, 13, 17, 19, 23, 29, 31,
                41, 43, 47, 53, 59, 61, 67, 71, 73, 79,
                83, 89, 97, 101, 103, 107, 109, 113);
        Set<Integer> inputTest = Set.of(1,2,3,4,5,7,8,9,10,11);
        findBestPartition(inputTest);
        findBestPartition(input);
    }

    public static void findBestPartition(Set<Integer> input) throws InterruptedException {
        List<Integer> nums = new ArrayList<>(input);
        nums.sort(Comparator.reverseOrder());
        int totalSum = nums.stream().mapToInt(Integer::intValue).sum();

        if (totalSum % 3 != 0) {
            System.out.println("Summe nicht durch 3 teilbar.");
            return;
        }

        int target = totalSum / 3;
        BestSolution best = new BestSolution();

        int cpuCount = Runtime.getRuntime().availableProcessors();
        int prefixDepth = 1;
        while (Math.pow(3, prefixDepth) < 2 * cpuCount && prefixDepth < 4) {
            prefixDepth++;
        }

        int totalJobs = (int) Math.pow(3, prefixDepth);
        AtomicInteger completedJobs = new AtomicInteger(0);

        System.out.println("Starte mit prefixDepth=" + prefixDepth + ", " + totalJobs + " Jobs…");

        ExecutorService executor = Executors.newFixedThreadPool(cpuCount);

        Deque<Job> jobs = new ArrayDeque<>();
        jobs.add(new Job(0, new ArrayList<>(), new HashSet<>(), 0,
                new HashSet<>(), 0, new HashSet<>(), 0));

        while (!jobs.isEmpty()) {
            Job job = jobs.poll();
            if (job.idx >= prefixDepth) {
                executor.submit(() -> {
                    backtrack(nums, job.idx,
                            job.path, job.p1, job.s1, job.p2, job.s2, job.p3, job.s3, target, best);
                    int done = completedJobs.incrementAndGet();
                    System.out.printf("Fortschritt: %d/%d (%.1f%%)%n",
                            done, totalJobs, 100.0 * done / totalJobs);
                });
                continue;
            }

            int num = nums.get(job.idx);

            jobs.add(job.next(1, num));
            jobs.add(job.next(2, num));
            jobs.add(job.next(3, num));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);

        if (best.parts != null) {
            System.out.println("✅ Optimale Lösung gefunden:");
            for (int i = 0; i < 3; i++) {
                System.out.println("Teilmenge " + (i + 1) + ": " + best.parts.get(i));
            }
            System.out.println("Kleinste Teilmenge: " + best.minSize + " Elemente, Produkt: " + best.minProduct);
        } else {
            System.out.println("Keine Lösung gefunden.");
        }
    }

    private static void backtrack(List<Integer> nums, int idx, List<Integer> path,
                                  Set<Integer> p1, int s1, Set<Integer> p2, int s2,
                                  Set<Integer> p3, int s3,
                                  int target, BestSolution best) {
        if (idx == nums.size()) {
            if (s1 == target && s2 == target && s3 == target) {
                best.consider(List.of(
                        new HashSet<>(p1), new HashSet<>(p2), new HashSet<>(p3)
                ));
            }
            return;
        }

        int num = nums.get(idx);

        if (s1 + num <= target) {
            p1.add(num);
            backtrack(nums, idx + 1, path, p1, s1 + num, p2, s2, p3, s3, target, best);
            p1.remove(num);
        }

        if (s2 + num <= target) {
            p2.add(num);
            backtrack(nums, idx + 1, path, p1, s1, p2, s2 + num, p3, s3, target, best);
            p2.remove(num);
        }

        if (s3 + num <= target) {
            p3.add(num);
            backtrack(nums, idx + 1, path, p1, s1, p2, s2, p3, s3 + num, target, best);
            p3.remove(num);
        }


    }

    private static long product(Set<Integer> set) {
        long prod = 1;
        for (int n : set) prod *= n;
        return prod;
    }

    static class Job {
        int idx;
        List<Integer> path;
        Set<Integer> p1, p2, p3;
        int s1, s2, s3;

        Job(int idx, List<Integer> path,
            Set<Integer> p1, int s1, Set<Integer> p2, int s2,
            Set<Integer> p3, int s3) {
            this.idx = idx;
            this.path = new ArrayList<>(path);
            this.p1 = new HashSet<>(p1);
            this.p2 = new HashSet<>(p2);
            this.p3 = new HashSet<>(p3);
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
        }

        Job next(int which, int num) {
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(which);
            Set<Integer> np1 = new HashSet<>(p1);
            Set<Integer> np2 = new HashSet<>(p2);
            Set<Integer> np3 = new HashSet<>(p3);
            int ns1 = s1, ns2 = s2, ns3 = s3;

            if (which == 1) {
                np1.add(num);
                ns1 += num;
            } else if (which == 2) {
                np2.add(num);
                ns2 += num;
            } else {
                np3.add(num);
                ns3 += num;
            }

            return new Job(idx + 1, newPath, np1, ns1, np2, ns2, np3, ns3);
        }
    }
}
