package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.common.LineReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day16_part1 {

   /*
    children: 3
    cats: 7
    samoyeds: 2
    pomeranians: 3
    akitas: 0
    vizslas: 0
    goldfish: 5
    trees: 3
    cars: 2
    perfumes: 1
    */

    static Map<String, Integer> resultMap = new HashMap<>();

    public static void main(String[] args) {

        resultMap.put("children", 3);
        resultMap.put("cats", 7);
        resultMap.put("samoyeds", 2);
        resultMap.put("pomeranians", 3);
        resultMap.put("akitas", 0);
        resultMap.put("vizslas", 0);
        resultMap.put("goldfish", 5);
        resultMap.put("trees", 3);
        resultMap.put("cars", 2);
        resultMap.put("perfumes", 1);

        /*
        Sue 135: vizslas: 1, cats: 1, trees: 8
         */

        int countMatch = 0;
        List<String> lines = LineReader.readInputFile("mmxv/aunts.txt");
        for (String line : lines) {
            countMatch = 0;
            int idx = line.indexOf(":");
            String name =  line.substring(0, idx);
            String attributesString = line.substring(idx + 1, line.length()).trim();
            String[] attributes = attributesString.trim().split(", ");

            for (String attribute : attributes) {

                String[] keyValue = attribute.split(": ");
                String key = keyValue[0];
                int value = Integer.parseInt(keyValue[1]);

                if (resultMap.containsKey(key) && resultMap.get(key) == value) {
                    countMatch++;
                }
                if (countMatch == 3 ) {
                    System.out.println(name + " matches all known criteria.");
                    break;
                }
            }
        }
    }
}
