package de.twisssow.adventofcode.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public  class LineReader {

    public  static List<String> readInputFile(String fileName) {
        List<String> inputLines = new ArrayList<String>();
        try (InputStream inputStream = LineReader.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputLines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputLines;
    }

}
