package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.mmxv.helper.day2.Box;

import java.util.List;
import java.util.stream.Collectors;

public class Day2 {



    public static void main(String[] args) {
        List<Box> boxes = LineReader.readInputFile("mmxv/boxes.txt").stream().map(Box::new).collect(Collectors.toList());
        int totalWrappingPaper = boxes.stream().mapToInt(Box::wrappedArea).sum();
        System.out.println(totalWrappingPaper);
        int totalRibbonLength = boxes.stream().mapToInt(Box::ribbonLength).sum();
        System.out.println(totalRibbonLength);

    }




}
