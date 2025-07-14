package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.mmxv.helper.LineReader;
import de.twisssow.adventofcode.mmxv.helper.day14.Reindeer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14_part1 {

    public static int calculateDistance(Reindeer reindeer, int time) {
        return reindeer.distanceAfterTime(time);
    }


    public static void main(String[] args) {

        List<Reindeer> reindeerList = new ArrayList<>();
        Map<Reindeer, Integer> reindeerDistances = new HashMap<>();

        List<String> lines = LineReader.readInputFile("reindeer.txt");
        for (String line : lines) {
            String[] parts = line.split(" ");
            String name = parts[0];
            int speed = Integer.parseInt(parts[3]);
            int flyTime = Integer.parseInt(parts[6]);
            int restTime = Integer.parseInt(parts[13]);
            reindeerList.add(new Reindeer(name, speed, flyTime, restTime));
        }
        int totalTime = 2503;
        reindeerList.forEach(reindeer -> {
            int distance = calculateDistance(reindeer, totalTime);
            reindeerDistances.put(reindeer, distance);
        });
        Reindeer bestReindeer = reindeerDistances.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new IllegalStateException("No reindeer found"))
                .getKey();
        System.out.println("Best reindeer: " + bestReindeer.getName() + " with distance: " + reindeerDistances.get(bestReindeer));
    }


}