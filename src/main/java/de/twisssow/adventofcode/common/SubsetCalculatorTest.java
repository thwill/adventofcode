package de.twisssow.adventofcode.common;

import java.util.HashSet;
import java.util.Set;

public class SubsetCalculatorTest {

    public static void main(String[] args) {

        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('b');
        set.add('c');

        Set<Set<Character>> subsets = SubsetCalculator.calculate(set);

        System.out.println("Subsets of " + set + ":" + subsets);
    }

}
