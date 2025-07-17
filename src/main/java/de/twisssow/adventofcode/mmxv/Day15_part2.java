package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.mmxv.day15.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class Day15_part2 {

    static Ingredient[] ingredients = {
            new Ingredient("Sugar", 3, 0, 0, -3, 2),
            new Ingredient("Sprinkles", -3, 3, -0, 0, 9),
            new Ingredient("Candy", -1, 0, 4, 0, 1),
            new Ingredient("Chocolate", 0, 0, -2, 2, 8)
    };


    public static List<int[]> generateCombinations() {
        List<int[]> combinations = new ArrayList<>();
        for (int a = 0; a <= 100; a++) {
            for (int b = 0; b <= 100 - a; b++) {
                for (int c = 0; c <= 100 - a - b; c++) {
                    int d = 100 - a - b - c;
                    combinations.add(new int[]{a, b, c, d});
                }
            }
        }
        return combinations;
    }

    public static void main(String[] args) {

        List<int[]> combinations = generateCombinations();
        int maxScore = 0;
        for (int[] combination : combinations) {
            if (calculateCalories(combination) != 500) {
                continue; // Skip combinations that do not have exactly 500 calories
            }
            int currentScore = calculateScore(combination);
            if (currentScore > maxScore) {
                maxScore = currentScore;
            }
        }
        System.out.println("max score is :" + maxScore);


    }

    private static int calculateCalories(int[] combination) {
        int amountSugar = combination[0];
        int amountSprinkles = combination[1];
        int amountChocolate = combination[2];
        int amountCandy = combination[3];
        return amountSugar * ingredients[0].getCalories() +
                amountSprinkles * ingredients[1].getCalories() +
                amountChocolate * ingredients[3].getCalories() +
                amountCandy * ingredients[2].getCalories();
    }


    private static int calculateScore(int[] combination) {
        int amountSugar = combination[0];
        int amountSprinkles = combination[1];
        int amountChocolate = combination[2];
        int amountCandy = combination[3];
        int totalCapacity = amountSugar * ingredients[0].getCapacity() +
                amountSprinkles * ingredients[1].getCapacity() +
                amountChocolate * ingredients[3].getCapacity() +
                amountCandy * ingredients[2].getCapacity();
        int totalDurability = amountSugar * ingredients[0].getDurability() +
                amountSprinkles * ingredients[1].getDurability() +
                amountChocolate * ingredients[3].getDurability() +
                amountCandy * ingredients[2].getDurability();
        int totalFlavor = ingredients[0].getFlavor() * amountSugar +
                ingredients[1].getFlavor() * amountSprinkles +
                ingredients[2].getFlavor() * amountCandy +
                ingredients[3].getFlavor() * amountChocolate;
        int totalTexture = ingredients[0].getTexture() * amountSugar +
                ingredients[1].getTexture() * amountSprinkles +
                ingredients[2].getTexture() * amountCandy +
                ingredients[3].getTexture() * amountChocolate;

        totalCapacity = Math.max(totalCapacity, 0);
        totalDurability = Math.max(totalDurability, 0);
        totalFlavor = Math.max(totalFlavor, 0);
        totalTexture = Math.max(totalTexture, 0);
        return totalCapacity * totalDurability * totalFlavor * totalTexture;
    }
}
