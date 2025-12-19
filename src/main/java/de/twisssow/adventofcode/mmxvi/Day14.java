package de.twisssow.adventofcode.mmxvi;

import de.twisssow.adventofcode.common.MD5HashCreator;
import de.twisssow.adventofcode.mmxvi.day14.MD5HashChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Day14 {

    public static void main(String[] args) {
        String salt = "abc";  // your input
        int index = 0;

        TreeSet<MD5HashChecker> checkers = new TreeSet<>();
        List<MD5HashChecker> keys = new ArrayList<>();

        while (keys.size() <= 64) {
            String plain = salt + index;
            String md5Hash = MD5HashCreator.md5(plain);

            // Step 1: check for triple
            Character tripleChar = findTriple(md5Hash);
            if (tripleChar != null) {
                MD5HashChecker checker = new MD5HashChecker(tripleChar, index);
                checker.setCandidateHash(md5Hash);
                checkers.add(checker);
            }

            // Step 2: keep only non-expired checkers (index <= endIndex)

            int finalIndex = index;
            List<MD5HashChecker> relevantCheckers = checkers.stream()
                    .filter(c -> finalIndex <= c.getEndIndex())
                    .toList();

            // Step 3: validate candidates

            for (MD5HashChecker checker : relevantCheckers) {
                if (findQuintett(md5Hash, checker.getCharacterToCheck())) {
                    checker.setFullFillmentHash(md5Hash);
                    checker.setFullFillmentIndex(index);
                    keys.add(checker);
                }
            }
            index++;
        }


        System.out.println("Index of 64th key: " + keys.get(63).getStartIndex());

    }

    private static boolean findQuintett(String md5Hash, Character character) {
        for (int i = 0; i < md5Hash.length() - 4; i++) {
            char c = md5Hash.charAt(i);
            if (c == md5Hash.charAt(i + 1) && c == md5Hash.charAt(i + 2) && c == md5Hash.charAt(i + 3) && c == md5Hash.charAt(i + 4) && c == character) {
                return true;
            }
        }
        return false;
    }


    private static Character findTriple(String md5Hash) {
        for (int i = 0; i < md5Hash.length() - 2; i++) {
            char c = md5Hash.charAt(i);
            if (c == md5Hash.charAt(i + 1) && c == md5Hash.charAt(i + 2)) {
                return c;
            }
        }
        return null;
    }

}
