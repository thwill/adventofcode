package de.twisssow.adventofcode.mmxv;


import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.common.SubsetCalculator;
import de.twisssow.adventofcode.mmxv.helper.day17.Container;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day17 {

    public static void main(String[] args) {
        // Example usage
        Set<Container> containers = new HashSet<>();
        List<String> lines = LineReader.readInputFile("mmxv/container_list.txt");


        int idx = 1;
        for (String line : lines) {
            Integer volume = Integer.parseInt(line.trim());
            containers.add(new Container("Container_" + idx + "_With_Volume_" + volume, volume));
            idx++;
        }

        Set<Set<Container>> containerSets = SubsetCalculator.calculate(containers);
        List<Set<Container>> validCombinations = new ArrayList<>();

        for (Set<Container> containerSet : containerSets) {
            int totalVolume = 0;
            for (Container container : containerSet) {
                totalVolume += container.getVolume();
            }
            if (totalVolume == 150) {
                validCombinations.add(containerSet);
            }
        }
        System.out.println("Anzahl der Kombinationen, die genau 150 Volumen ergeben: " + validCombinations.size());

        int minContainers = validCombinations.stream().mapToInt(Set::size).min().getAsInt();
        long countMinContainers = validCombinations.stream()
                .filter(set -> set.size() == minContainers)
                .count();

        System.out.println("Minimale Anzahl an Containern in einer Kombination: " + minContainers);
        System.out.println("Anzahl der Kombinationen  mit " + minContainers + " Containern: " + countMinContainers);
    }

}

