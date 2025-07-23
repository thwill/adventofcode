package de.twisssow.adventofcode.mmxvi.day10;

import java.util.Map;

public class Bot {

    private final static int UNKNOWN_ID = -1;

    private int id = UNKNOWN_ID;

    private Integer lowValue;
    private Integer highValue;

    private int lowBotId = UNKNOWN_ID;
    private int highBotId = UNKNOWN_ID;
    private int lowOutputId = UNKNOWN_ID;
    private int highOutputId = UNKNOWN_ID;

    private boolean processed = false;


    public Bot(int id) {
        this.id = id;
    }

    public boolean isUnProcessed() {
        return !processed;
    }

    public Integer getLowValue() {
        return lowValue;
    }

    public Integer getHighValue() {
        return highValue;
    }

    private boolean isCompletedWithValues() {
        return lowValue != null && highValue != null;
    }

    private boolean isReadyToProcess() {
        return isCompletedWithValues() && (lowBotId != UNKNOWN_ID || lowOutputId != UNKNOWN_ID)
                && (highBotId != UNKNOWN_ID || highOutputId != UNKNOWN_ID);
    }


    public void addValue(int value) {
        if (lowValue == null) {
            lowValue = value;
        } else if (highValue == null) {
            highValue = value;
        } else {
            throw new IllegalStateException("Bot already has two values assigned.");
        }
        if (isCompletedWithValues()) {
            if (lowValue > highValue) {
                int temp = lowValue;
                lowValue = highValue;
                highValue = temp;
            }
        }
    }

    public void addLowTarget(String type, int targetId) {
        if ("bot".equals(type)) {
            addLowBot(targetId);
        } else if ("output".equals(type)) {
            addLowOutput(targetId);
        } else {
            throw new IllegalArgumentException("Unknown target type: " + type);
        }
    }

    public void addHighTarget(String type, int targetId) {
        if ("bot".equals(type)) {
            addHighBot(targetId);
        } else if ("output".equals(type)) {
            addHighOutput(targetId);
        } else {
            throw new IllegalArgumentException("Unknown target type: " + type);
        }
    }

    private void addLowOutput(int outputId) {
        if (lowOutputId == UNKNOWN_ID) {
            lowOutputId = outputId;
        } else {
            throw new IllegalStateException("Low output already set for bot " + id);
        }
    }

    private void addHighOutput(int outputId) {
        if (highOutputId == UNKNOWN_ID) {
            highOutputId = outputId;
        } else {
            throw new IllegalStateException("High output already set for bot " + id);
        }
    }

    private void addLowBot(int botId) {
        if (lowBotId == UNKNOWN_ID) {
            lowBotId = botId;
        } else {
            throw new IllegalStateException("Low bot already set for bot " + id);
        }
    }

    private void addHighBot(int botId) {
        if (highBotId == UNKNOWN_ID) {
            highBotId = botId;
        } else {
            throw new IllegalStateException("High bot already set for bot " + id);
        }
    }

    public void process(Map<Integer, Bot> botMap, Map<Integer, Output> outputMap) {
        if (isReadyToProcess()) {
            if (lowBotId != UNKNOWN_ID) {
                botMap.computeIfAbsent(lowBotId, Bot::new).addValue(lowValue);
            } else if (lowOutputId != UNKNOWN_ID) {
                outputMap.computeIfAbsent(lowOutputId, Output::new).addValue(lowValue);
            }
            if (highBotId != UNKNOWN_ID) {
                botMap.computeIfAbsent(highBotId, Bot::new).addValue(highValue);
            } else if (highOutputId != UNKNOWN_ID) {
                outputMap.computeIfAbsent(highOutputId, Output::new).addValue(highValue);
            }
            processed = true;
        }

    }

    @Override
    public String toString() {
        return "Bot{id=" + id +
                ", lowValue=" + lowValue +
                ", highValue=" + highValue +
                ", lowBotId=" + lowBotId +
                ", highBotId=" + highBotId +
                ", lowOutputId=" + lowOutputId +
                ", highOutputId=" + highOutputId +
                ", processed=" + processed +
                '}';
    }
}
