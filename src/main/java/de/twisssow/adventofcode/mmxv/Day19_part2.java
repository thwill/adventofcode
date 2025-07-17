package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.mmxv.day19.Replacement;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day19_part2 {


    public static int solveGreedy(String target, Set<Replacement> replacements) {
        // Sortiere nach Länge von `to` absteigend, um lange Stücke zuerst zu ersetzen
        List<Replacement> sorted = replacements.stream()
                .sorted(Comparator.comparingInt((Replacement r) -> r.getTo().length()).reversed())
                .collect(Collectors.toList());

        String molecule = target;
        int steps = 0;

        while (!molecule.equals("e")) {
            boolean replaced = false;

            for (Replacement r : sorted) {
                if (molecule.contains(r.getTo())) {
                    molecule = molecule.replaceFirst(r.getTo(), r.getFrom());
                    steps++;
                    replaced = true;
                    break;
                }
            }

            if (!replaced) {
                throw new RuntimeException("Keine weiteren Ersetzungen möglich, aber nicht bei 'e' angekommen!");
            }
        }

        return steps;
    }


    public static void main(String[] args) {

        Set<Replacement> replacements = new HashSet<>();
        List<String> lines = LineReader.readInputFile("mmxv/replacements.txt");
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

        for (Replacement r : replacements) {
            System.out.println(r);
        }

        int steps = solveGreedy(input,replacements);
        System.out.println(steps);


    }
}
