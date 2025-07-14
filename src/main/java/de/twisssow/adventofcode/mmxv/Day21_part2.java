package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.mmxv.helper.day21.EquipmentItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day21_part2 {

    public static void main(String[] args) {
        List<EquipmentItem> weapons = Arrays.asList(
                new EquipmentItem("Dagger", "Weapon", 8, 4, 0),
                new EquipmentItem("Shortsword", "Weapon", 10, 5, 0),
                new EquipmentItem("Warhammer", "Weapon", 25, 6, 0),
                new EquipmentItem("Longsword", "Weapon", 40, 7, 0),
                new EquipmentItem("Greataxe", "Weapon", 74, 8, 0)
        );

        List<EquipmentItem> armors = Arrays.asList(
                new EquipmentItem("Leather", "Armor", 13, 0, 1),
                new EquipmentItem("Chainmail", "Armor", 31, 0, 2),
                new EquipmentItem("Splintmail", "Armor", 53, 0, 3),
                new EquipmentItem("Bandedmail", "Armor", 75, 0, 4),
                new EquipmentItem("Platemail", "Armor", 102, 0, 5),
                null // keine Rüstung
        );

        List<EquipmentItem> rings = Arrays.asList(
                new EquipmentItem("Damage +1", "Ring", 25, 1, 0),
                new EquipmentItem("Damage +2", "Ring", 50, 2, 0),
                new EquipmentItem("Damage +3", "Ring", 100, 3, 0),
                new EquipmentItem("Defense +1", "Ring", 20, 0, 1),
                new EquipmentItem("Defense +2", "Ring", 40, 0, 2),
                new EquipmentItem("Defense +3", "Ring", 80, 0, 3)
        );

        int maximalCosts = 0;
        Set<EquipmentItem> worstEquipment = null;

        for (EquipmentItem weapon : weapons) {
            for (EquipmentItem armor : armors) {
                for (Set<EquipmentItem> ringCombo : ringCombinations(rings)) {
                    Set<EquipmentItem> equipment = new HashSet<>();
                    equipment.add(weapon);
                    if (armor != null) equipment.add(armor);
                    equipment.addAll(ringCombo);

                    if (! fightWithEquipment(equipment)) {
                        int cost = equipment.stream().mapToInt(EquipmentItem::getCost).sum();
                        if (cost > maximalCosts) {
                            maximalCosts = cost;
                            worstEquipment = new HashSet<>(equipment);
                        }
                    }
                }
            }
        }

        System.out.println("Maximal cost loosing : " + maximalCosts);
        System.out.println("Equipment:");
        for (EquipmentItem item : worstEquipment) {
            System.out.println("  " + item);
        }
    }

    static List<Set<EquipmentItem>> ringCombinations(List<EquipmentItem> rings) {
        List<Set<EquipmentItem>> result = new ArrayList<>();
        result.add(new HashSet<>()); // keine Ringe
        for (int i = 0; i < rings.size(); i++) {
            // Einzelring
            result.add(Set.of(rings.get(i)));
            // Doppelring
            for (int j = i + 1; j < rings.size(); j++) {
                result.add(Set.of(rings.get(i), rings.get(j)));
            }
        }
        return result;
    }

    static boolean fightWithEquipment(Set<EquipmentItem> equipment) {
        int bossHealth = 103;
        int bossDamage = 9;
        int bossArmor = 2;
        int playerHealth = 100;

        int playerDamage = equipment.stream()
                .mapToInt(EquipmentItem::getDamage)
                .sum();
        int playerArmor = equipment.stream()
                .mapToInt(EquipmentItem::getArmour)
                .sum();

        while (playerHealth > 0 && bossHealth > 0) {
            bossHealth -= Math.max(1, playerDamage - bossArmor);
            if (bossHealth <= 0) return true;

            playerHealth -= Math.max(1, bossDamage - playerArmor);
            if (playerHealth <= 0) return false;
        }
        return false;
    }


}
