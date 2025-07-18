package de.twisssow.adventofcode.mmxvi.day7;

import java.util.ArrayList;
import java.util.List;

public class IpV7 {

    List<String> hypernetSequences = new ArrayList<>();
    List<String> supernetSequences = new ArrayList<>();

    public IpV7(String ip) {
        String[] parts = ip.split("\\[|\\]");
        for (int i = 0; i < parts.length; i++) {
            if (i % 2 == 0) {
                supernetSequences.add(parts[i]);
            } else {
                hypernetSequences.add(parts[i]);
            }
        }
    }

    public boolean supportsTLS() {
        return hasAbba(supernetSequences) && !hasAbba(hypernetSequences);
    }

    public boolean supportsSSL() {
        List<String> abaList = findAbaInSupernetSequences();

        return abaList.stream()
                        .anyMatch(aba -> hasCorrespondingBabInHypernetSequences(aba.toCharArray()));
    }

    private List<String> findAbaInSupernetSequences() {
        List<String> result = new ArrayList<>();
        for (String sequence : supernetSequences) {
            for (int i = 0; i < sequence.length() - 2; i++) {
                char a = sequence.charAt(i);
                char b = sequence.charAt(i + 1);
                char c = sequence.charAt(i + 2);
                if (a == c && a != b) {
                    result.add(sequence.substring(i, i + 3));
                }
            }
        }
        return result;
    }

    private  boolean hasCorrespondingBabInHypernetSequences(char[] aba) {
        for (String sequence : hypernetSequences) {
            for (int i = 0; i < sequence.length() - 2; i++) {
                char a = sequence.charAt(i);
                char b = sequence.charAt(i + 1);
                char c = sequence.charAt(i + 2);
                if (a == aba[1] && b == aba[0] && c == aba[1]) {
                    return true;
                }
            }
        }
        return false;
    }



    private boolean hasAbba(List<String> sequences) {
        for (String sequence : sequences) {
            for (int i = 0; i < sequence.length() - 3; i++) {
                char a = sequence.charAt(i);
                char b = sequence.charAt(i + 1);
                char c = sequence.charAt(i + 2);
                char d = sequence.charAt(i + 3);
                if (a == d && b == c && a != b) {
                    return true;
                }
            }
        }
        return false;
    }
}
