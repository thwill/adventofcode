package de.twisssow.adventofcode.common;

import java.util.ArrayList;
import java.util.List;

public class PermutatorTest {


    public static void main(String[] args) {

        String test = "abc";
        char[] arr = test.toCharArray();
        List<Character> characters = new ArrayList<>();
        for (char c : arr) {
            characters.add(c);
        }


        List<List<Character>> permuted = Permutator.permutiere(characters);
        System.out.println(permuted);
    }
}