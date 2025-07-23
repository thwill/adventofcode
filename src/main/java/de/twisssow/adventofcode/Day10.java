package de.twisssow.adventofcode;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.mmxvi.day10.Bot;
import de.twisssow.adventofcode.mmxvi.day10.Output;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 {


    public static void main(String[] args) {
        List<String> lines = LineReader.readInputFile("mmxvi/bot_instructions.txt");
        Map<Integer, Bot> botMap = new HashMap<>();
        Map<Integer, Output> outputMap = new HashMap<>();


        lines.forEach(line -> {
            if (line.startsWith("value")) {
                //value a goes to bot x
                String parts[] = line.split(" ");
                int value = Integer.parseInt(parts[1]);
                int botId = Integer.parseInt(parts[5]);
                botMap.computeIfAbsent(botId, Bot::new).addValue(value);
            } else if (line.startsWith("bot")) {
                //bot x gives low to bot/output a and high to bot/output b
                String parts[] = line.split(" ");
                int botId = Integer.parseInt(parts[1]);
                int lowId = Integer.parseInt(parts[6]);
                int highId = Integer.parseInt(parts[11]);
                String lowType = parts[5];
                String highType = parts[10];
                Bot bot = botMap.computeIfAbsent(botId, Bot::new);
                bot.addLowTarget(lowType, lowId);
                bot.addHighTarget(highType, highId);
            }
        });
        processBots(botMap, outputMap);
        System.out.println("Bot with high 61 and low 17 is: " + botMap.values().stream()
                .filter(bot -> bot.getLowValue() == 17 && bot.getHighValue() == 61)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No bot found with low 17 and high 61")));
        int outputValue = calculateOutputValue(outputMap);
        System.out.println("Product of outputs 0, 1 and 2: " + outputValue);
    }

    private static void processBots(Map<Integer, Bot> botMap, Map<Integer, Output> outputMap) {
        boolean unProcessed;
        do {
            unProcessed = botMap.values().stream().filter(Bot::isUnProcessed).findAny().isPresent();
            for (Bot bot : botMap.values()) {
                if (bot.isUnProcessed()) {
                    bot.process(botMap, outputMap);
                }
            }
        } while (unProcessed);
    }

    private static int calculateOutputValue(Map<Integer, Output> outputMap) {
        return outputMap.values().stream()
                .filter(output -> output.getId() == 0 || output.getId() == 1 || output.getId() == 2)
                .mapToInt(Output::getValue)
                .reduce(1, (a, b) -> a * b);
    }


}