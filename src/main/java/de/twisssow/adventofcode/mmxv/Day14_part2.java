package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.mmxv.helper.day14.Reindeer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14_part2 {

    public static int calculateDistance(Reindeer reindeer, int time) {
        return reindeer.distanceAfterTime(time);
    }


    public static void main(String[] args) {

        List<Reindeer> reindeerList = new ArrayList<>();
        Map<Reindeer, Integer> reindeerRanking = new HashMap<>();

        List<String> lines = LineReader.readInputFile("mmxv/reindeer.txt");
        for (String line : lines) {
            String[] parts = line.split(" ");
            String name = parts[0];
            int speed = Integer.parseInt(parts[3]);
            int flyTime = Integer.parseInt(parts[6]);
            int restTime = Integer.parseInt(parts[13]);
            Reindeer reindeer = new Reindeer(name, speed, flyTime, restTime);
            reindeerList.add(reindeer);
            reindeerRanking.put(reindeer, 0);
        }
        int totalTime = 2503;
        for (int time = 1; time <= totalTime; time++) {
            Map<Reindeer, Integer> reindeerDistances = new HashMap<>();
            for (Reindeer reindeer : reindeerList) {
                int distance = calculateDistance(reindeer, time);
                reindeer.setCurrentDistance(distance);
                reindeerDistances.put(reindeer, distance);
            }
            int maxDistance = reindeerDistances.values().stream()
                    .max(Integer::compareTo)
                    .orElseThrow(() -> new IllegalStateException("No reindeer found"));
            for (Reindeer reindeer : reindeerList) {
                if (reindeerDistances.get(reindeer) == maxDistance) {
                    reindeerRanking.put(reindeer, reindeerRanking.get(reindeer) + 1);
                }
            }
        }
        Reindeer bestReindeer = reindeerRanking.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new IllegalStateException("No reindeer found"))
                .getKey();
        System.out.println("Best reindeer: " + bestReindeer.getName() + " with ranking: " + reindeerRanking.get(bestReindeer));
    }


}