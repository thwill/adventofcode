package de.twisssow.adventofcode.mmxvi.day14;

import java.util.Objects;

public class MD5HashChecker implements Comparable<MD5HashChecker> {
    private Character characterToCheck;
    private String candidateHash;
    private String fullFillmentHash;
    private int startIndex;
    private int endIndex;
    private int fullFillmentIndex;


    public MD5HashChecker(Character characterToCheck, int startIndex) {
        this.characterToCheck = characterToCheck;
        this.startIndex = startIndex;
        this.endIndex = startIndex + 1000;
    }

    public Character getCharacterToCheck() {
        return characterToCheck;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public String getCandidateHash() {
        return candidateHash;
    }

    public void setCandidateHash(String candidateHash) {
        this.candidateHash = candidateHash;
    }

    public String getFullFillmentHash() {
        return fullFillmentHash;
    }

    public void setFullFillmentHash(String fullFillmentHash) {
        this.fullFillmentHash = fullFillmentHash;
    }

    public int getFullFillmentIndex() {
        return fullFillmentIndex;
    }

    public void setFullFillmentIndex(int fullFillmentIndex) {
        this.fullFillmentIndex = fullFillmentIndex;
    }

    @Override
    public String toString() {
        return "MD5HashChecker{" +
                "keyToCheck=" + characterToCheck +
                ", candidateHash='" + candidateHash + '\'' +
                ", fullFillmentHash='" + fullFillmentHash + '\'' +
                ", startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        MD5HashChecker checker = (MD5HashChecker) o;
        return startIndex == checker.startIndex && endIndex == checker.endIndex && characterToCheck.equals(checker.characterToCheck) && candidateHash.equals(checker.candidateHash);
    }

    @Override
    public int hashCode() {
        int result = characterToCheck.hashCode();
        result = 31 * result + candidateHash.hashCode();
        result = 31 * result + startIndex;
        result = 31 * result + endIndex;
        return result;
    }

    @Override
    public int compareTo(MD5HashChecker o) {
        return Integer.compare(this.startIndex, o.startIndex);
    }

}
