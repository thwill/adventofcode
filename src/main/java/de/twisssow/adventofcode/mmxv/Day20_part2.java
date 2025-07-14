package de.twisssow.adventofcode.mmxv;


import de.twisssow.adventofcode.mmxv.helper.TreeSetUtils;

public class Day20_part2 {

    public static void main(String[] args) {
        int n = 1;
        int maxPresents = 33100000;
        int presents = 0;

        while (presents < maxPresents) {
            int house = n;
            presents = TreeSetUtils.positivDivisorSet(house).stream()
                    .filter(e -> house / e <= 50) // Elf beliefert max. 50 Häuser
                    .mapToInt(Integer::intValue)
                    .sum() * 11;

            n++;
        }
        System.out.println("Haus " + (n - 1) + " hat " + presents + " presents.");
    }


}

