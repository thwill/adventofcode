package de.twisssow.adventofcode.mmxvi;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.mmxvi.day7.IpV7;

import java.util.List;

public class Day7_part2 {


    public static void main(String[] args) {
        List<String> inputLines = LineReader.readInputFile("mmxvi/ipv7.txt");
        long tlsCount = inputLines.stream()
                .map(IpV7::new)
                .filter(IpV7::supportsSSL)
                .count();
        System.out.println("Anzahl der IPs, die SSL unterstützen: " + tlsCount);
    }

}
