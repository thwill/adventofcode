package de.twisssow.adventofcode.mmxv;

import java.util.ArrayList;
import java.util.List;

public class Day11 {

    private static final String INPUT = "cqjxjnds";

    public static void main(String[] args) {
        String nextPassword = findNextPassword(INPUT);
        System.out.println("Next password: " + nextPassword);
        System.out.println("Next password after: " + nextPassword + "-> " + findNextPassword(nextPassword));
    }

    private static String findNextPassword(String input) {
        String password = incrementPassword(input);
        while (!isValid(password)) {
            password = incrementPassword(password);
        }
        return password;
    }

    private static boolean isValid(String password) {
        return containsTwoValidPairs(password) &&
                containsValidStraight(password);
    }

    private static boolean containsTwoValidPairs(String password) {
        int count = 0;
        for (int i = 0; i < password.length() - 1; i++) {
            String pair = password.substring(i, i + 2);
            if (pair.charAt(0) == pair.charAt(1)) {
                count++;
                i++; // skip the next character to avoid overlapping pairs
            }
            if (count >= 2) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsValidStraight(String password) {
        String alphabetWithoutForbiddenChars = "abcdefghjkmnpqrstuvwxyz";
        List<String> validStraights = new ArrayList<>();
        for (int i = 0; i < alphabetWithoutForbiddenChars.length() - 2; i++) {
            validStraights.add(alphabetWithoutForbiddenChars.substring(i, i + 3));
        }
        for (String straight : validStraights) {
            if (password.contains(straight)) {
                return true;
            }
        }
        return false;
    }

    private static String incrementPassword(String input) {
        String alphabetWithoutForbiddenChars = "abcdefghjkmnpqrstuvwxyz";
        char[] alphabet = alphabetWithoutForbiddenChars.toCharArray();
        char[] password = input.toCharArray();
        for (int i = password.length - 1; i >= 0; i--) {
            int index = new String(alphabet).indexOf(password[i]);
            if (index < alphabet.length - 1) {
                password[i] = alphabet[index + 1];
                return new String(password);
            } else {
                password[i] = alphabet[0]; // wrap around to 'a'
            }
        }
        return new String(password); // if we reach here, it means we wrapped around the entire password
        // e.g., "zzzz" becomes "aaaa"}
    }

}
