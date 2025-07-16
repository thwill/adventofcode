package de.twisssow.adventofcode.common;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

public class InputLoader {

  public static String loadInput(String fileName) {
    return loadFromClassPath(fileName);
  }

  public static String loadFromClassPath(String fileName) {
    try (var in = InputLoader.class.getClassLoader().getResourceAsStream(fileName)) {
      if (in == null) {
        throw new IllegalArgumentException("File not found on classpath: " + fileName);
      }
      return new String(in.readAllBytes(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
