package de.twisssow.adventofcode.mmxv;

import java.security.MessageDigest;

public class Day4 {
    private static final String INPUT = "bgvyzdsv";

   public static String md5(String input) throws Exception {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(input.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash)
                sb.append(String.format("%02x", b));
            return sb.toString();
        }

    public static void main(String[] args) throws Exception {
        String input = INPUT;
        int part1 = 0;
        int part2 = 0;

        while (true) {
            String hash = md5(input + part1);
            if (hash.startsWith("00000")) {
                System.out.println("Part 1: " + part1);
                break;
            }
            part1++;
        }

        while (true) {
            String hash = md5(input + part2);
            if (hash.startsWith("000000")) {
                System.out.println("Part 2: " + part2);
                break;
            }
            part2++;
        }

    }

}
