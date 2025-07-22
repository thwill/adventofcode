package de.twisssow.adventofcode.mmxvi;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.mmxvi.day8.Screen;

import java.util.List;

public class Day8_part1 {

    public static void main(String[] args) {

        List<String> inputLines = LineReader.readInputFile("mmxvi/screen_commands.txt");
        Screen screen = new Screen(6, 50);
        for (String line : inputLines) {
            screen.executeCommand(line);
            System.out.println("Executing command: " + line);
            System.out.println("Current screen state:");
            System.out.println();
            screen.printScreen();
            System.out.println();
            System.out.println();
        }

        int litLightsCount = screen.countLitLights();
        System.out.println("Anzahl der eingeschalteten Lichter: " + litLightsCount);
        screen.printLetterByLetter();
        //RURUCEOEIL
    }

}
