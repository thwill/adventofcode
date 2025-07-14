package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.mmxv.helper.LineReader;
import de.twisssow.adventofcode.mmxv.helper.day19.Replacement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day19_part1 {

    public static List<Integer> findOccurrences(String text, String substring) {
        List<Integer> positions = new ArrayList<>();
        int index = text.indexOf(substring);
        while (index >= 0) {
            positions.add(index);
            index = text.indexOf(substring, index + substring.length());
        }
        return positions;
    }

    /**
     * Erzeugt eine Liste aller Strings, bei denen jeweils nur ein Vorkommen ersetzt wurde.
     */
    public static List<String> replaceOccurrencesIndividually(
            String text, String substring, String replacement) {
        List<String> results = new ArrayList<>();
        List<Integer> positions = findOccurrences(text, substring);

        for (int pos : positions) {
            StringBuilder sb = new StringBuilder();
            sb.append(text, 0, pos);
            sb.append(replacement);
            sb.append(text, pos + substring.length(), text.length());
            results.add(sb.toString());
        }
        return results;
    }


    public static void main(String[] args) {

        Set<Replacement> replacements = new HashSet<>();
        Set<String> allResults = new HashSet<>();
        List<String> lines = LineReader.readInputFile("replacements.txt");
        //String input = "HOHOHO";
        String input = "CRnSiRnCaPTiMgYCaPTiRnFArSiThFArCaSiThSiThPBCaCaSiRnSiRnTiTiMgArPBCaPMgYPTiRnFArFArCaSiRnBPMgArPRnCaPTiRnFArCaSiThCaCaFArPBCaCaPTiTiRnFArCaSiRnSiAlYSiThRnFArArCaSiRnBFArCaCaSiRnSiThCaCaCaFYCaPTiBCaSiThCaSiThPMgArSiRnCaPBFYCaCaFArCaCaCaCaSiThCaSiRnPRnFArPBSiThPRnFArSiRnMgArCaFYFArCaSiRnSiAlArTiTiTiTiTiTiTiRnPMgArPTiTiTiBSiRnSiAlArTiTiRnPMgArCaFYBPBPTiRnSiRnMgArSiThCaFArCaSiThFArPRnFArCaSiRnTiBSiThSiRnSiAlYCaFArPRnFArSiThCaFArCaCaSiThCaCaCaSiRnPRnCaFArFYPMgArCaPBCaPBSiRnFYPBCaFArCaSiAl";


        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            }
            if (!line.contains(" => ")) {
                continue;
            }
            String[] parts = line.split(" => ");
            replacements.add(new Replacement(parts[0], parts[1]));
        }

        for (Replacement replacement : replacements) {
            List<String> results = replaceOccurrencesIndividually(input, replacement.getFrom(), replacement.getTo());
            //System.out.println("Ersetze '" + replacement.getFrom() + "' durch '" + replacement.getTo() + "':");
            /*for (String result : results) {
                System.out.println(result);
            }*/
            allResults.addAll(results);
        }
        System.out.println("All results:" + allResults.size());

    }
}
