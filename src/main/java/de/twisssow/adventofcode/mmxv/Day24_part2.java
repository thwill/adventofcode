package de.twisssow.adventofcode.mmxv;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day24_part2 {

    static class BestSolution {
        List<HashSet<Integer>> parts = null;
        int minSize = Integer.MAX_VALUE;
        long minProduct = Long.MAX_VALUE;

        synchronized void consider(List<Set<Integer>> candidate) {
            int smallest = candidate.stream().mapToInt(Set::size).min().orElse(0);
            long product = candidate.stream()
                    .filter(s -> s.size() == smallest)
                    .mapToLong(Day24_part2::product)
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

        findBestPartition(input);
    }

    public static void findBestPartition(Set<Integer> input) throws InterruptedException {
        List<Integer> nums = new ArrayList<>(input);
        nums.sort(Comparator.reverseOrder());
        int totalSum = nums.stream().mapToInt(Integer::intValue).sum();

        if (totalSum % 4 != 0) {
            System.out.println("Summe nicht durch 4 teilbar.");
            return;
        }

        int target = totalSum / 4;
        BestSolution best = new BestSolution();

        int cpuCount = Runtime.getRuntime().availableProcessors();
        int prefixDepth = 1;
        while (Math.pow(4, prefixDepth) < 2 * cpuCount && prefixDepth < 4) {
            prefixDepth++;
        }

        int totalJobs = (int) Math.pow(4, prefixDepth);
        AtomicInteger completedJobs = new AtomicInteger(0);

        System.out.println("Starte mit prefixDepth=" + prefixDepth + ", " + totalJobs + " Jobs…");

        ExecutorService executor = Executors.newFixedThreadPool(cpuCount);

        Deque<Job> jobs = new ArrayDeque<>();
        jobs.add(new Job(0, new ArrayList<>(), new HashSet<>(), 0,
                new HashSet<>(), 0, new HashSet<>(), 0, new HashSet<>(), 0));

        while (!jobs.isEmpty()) {
            Job job = jobs.poll();
            if (job.idx >= prefixDepth) {
                executor.submit(() -> {
                    backtrack(nums, job.idx,
                            job.path, job.p1, job.s1, job.p2, job.s2, job.p3, job.s3, job.p4, job.s4,
                            target, best);
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
            jobs.add(job.next(4, num));
        }

        executor.shutdown();
        executor.awaitTermination(7, TimeUnit.DAYS);

        if (best.parts != null) {
            System.out.println("✅ Optimale Lösung gefunden:");
            for (int i = 0; i < 4; i++) {
                System.out.println("Teilmenge " + (i + 1) + ": " + best.parts.get(i));
            }
            System.out.println("Kleinste Teilmenge: " + best.minSize + " Elemente, Produkt: " + best.minProduct);
        } else {
            System.out.println("Keine Lösung gefunden.");
        }
    }

    private static void backtrack(List<Integer> nums, int idx, List<Integer> path,
                                  Set<Integer> p1, int s1, Set<Integer> p2, int s2,
                                  Set<Integer> p3, int s3, Set<Integer> p4, int s4,
                                  int target, BestSolution best) {
        if (idx == nums.size()) {
            if (s1 == target && s2 == target && s3 == target && s4 == target) {
                best.consider(java.util.List.of(
                        new HashSet<>(p1), new HashSet<>(p2),
                        new HashSet<>(p3), new HashSet<>(p4)
                ));
            }
            return;
        }

        int num = nums.get(idx);

        if (s1 + num <= target) {
            p1.add(num);
            backtrack(nums, idx + 1, path, p1, s1 + num, p2, s2, p3, s3, p4, s4, target, best);
            p1.remove(num);
        }

        if (s2 + num <= target) {
            p2.add(num);
            backtrack(nums, idx + 1, path, p1, s1, p2, s2 + num, p3, s3, p4, s4, target, best);
            p2.remove(num);
        }

        if (s3 + num <= target) {
            p3.add(num);
            backtrack(nums, idx + 1, path, p1, s1, p2, s2, p3, s3 + num, p4, s4, target, best);
            p3.remove(num);
        }

        if (s4 + num <= target) {
            p4.add(num);
            backtrack(nums, idx + 1, path, p1, s1, p2, s2, p3, s3, p4, s4 + num, target, best);
            p4.remove(num);
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
        Set<Integer> p1, p2, p3, p4;
        int s1, s2, s3, s4;

        Job(int idx, List<Integer> path,
            Set<Integer> p1, int s1, Set<Integer> p2, int s2,
            Set<Integer> p3, int s3, Set<Integer> p4, int s4) {
            this.idx = idx;
            this.path = new ArrayList<>(path);
            this.p1 = new HashSet<>(p1);
            this.p2 = new HashSet<>(p2);
            this.p3 = new HashSet<>(p3);
            this.p4 = new HashSet<>(p4);
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
            this.s4 = s4;
        }

        Job next(int which, int num) {
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(which);
            Set<Integer> np1 = new HashSet<>(p1);
            Set<Integer> np2 = new HashSet<>(p2);
            Set<Integer> np3 = new HashSet<>(p3);
            Set<Integer> np4 = new HashSet<>(p4);
            int ns1 = s1, ns2 = s2, ns3 = s3, ns4 = s4;

            if (which == 1) {
                np1.add(num);
                ns1 += num;
            } else if (which == 2) {
                np2.add(num);
                ns2 += num;
            } else if (which == 3) {
                np3.add(num);
                ns3 += num;
            } else {
                np4.add(num);
                ns4 += num;
            }

            return new Job(idx + 1, newPath, np1, ns1, np2, ns2, np3, ns3, np4, ns4);
        }
    }
}
