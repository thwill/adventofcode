package de.twisssow.adventofcode.mmxv;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.twisssow.adventofcode.common.LineReader;

import java.util.List;

public class Day12 {


    public static int sumNumbersOutsideQuotes(String input) {
        int sum = 0;
        boolean insideQuotes = false;
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '"') {
                insideQuotes = !insideQuotes;  // toggle quote state
                if (!insideQuotes) {
                    token.setLength(0);  // clear any leftover token when closing quote
                }
            } else if (!insideQuotes) {
                if (Character.isDigit(c) || (c == '-' && token.length() == 0)) {
                    token.append(c);
                } else {
                    if (token.length() > 0) {
                        sum += Integer.parseInt(token.toString());
                        token.setLength(0);
                    }
                }
            }
        }

        // Add last number if present
        if (token.length() > 0 && !insideQuotes) {
            sum += Integer.parseInt(token.toString());
        }

        return sum;
    }

    public static int sumIgnoringRed(JsonNode node) {
        if (node.isInt()) {
            return node.intValue();
        }

        if (node.isArray()) {
            int sum = 0;
            for (JsonNode element : node) {
                sum += sumIgnoringRed(element);
            }
            return sum;
        }

        if (node.isObject()) {
            for (JsonNode value : node) {
                if (value.isTextual() && value.textValue().equals("red")) {
                    return 0;  // Skip object
                }
            }
            int sum = 0;
            for (JsonNode value : node) {
                sum += sumIgnoringRed(value);
            }
            return sum;
        }

        return 0;  // Ignore other types
    }


    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<String> lines = LineReader.readInputFile("mmxv/account.json");
        System.out.println(lines.size());
        String line = lines.get(0);
        int sum = sumNumbersOutsideQuotes(line);
        System.out.println("Sum: " + sum);
        JsonNode root = mapper.readTree(line);
        int sum2 = sumIgnoringRed(root);
        System.out.println("Sum: " + sum2);
    }
}
