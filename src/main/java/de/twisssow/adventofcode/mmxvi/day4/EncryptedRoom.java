package de.twisssow.adventofcode.mmxvi.day4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncryptedRoom {

    private final String name;
    private final int sectorId;
    private final String checksum;

    public EncryptedRoom(String input) {
        String[] parts = input.split("\\[|\\]");
        String[] nameAndSector = parts[0].split("-");
        this.sectorId = Integer.parseInt(nameAndSector[nameAndSector.length - 1]);
        this.checksum = parts[1];
        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < nameAndSector.length - 1; i++) {
            nameBuilder.append(nameAndSector[i]).append(i +1 < nameAndSector.length -1 ? "-" : "");
        }
        this.name = nameBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public int getSectorId() {
        return sectorId;
    }

    public String getChecksum() {
        return checksum;
    }

    public boolean isValid() {
        String calculatedChecksum = calculateChecksum();
        return calculatedChecksum.equals(checksum);
    }

    private String calculateChecksum() {

        Map<Character, Integer> counts = new HashMap<>();
        String compactName = getName().replace("-", ""); // Entferne Bindestriche
        for (char c : compactName.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        // Erstelle eine Liste der Buchstaben
        List<Character> buchstaben = new ArrayList<>(counts.keySet());

        // Sortiere: erst nach Häufigkeit absteigend, dann nach Buchstabe aufsteigend
        buchstaben.sort((a, b) -> {
            int cmp = Integer.compare(counts.get(b), counts.get(a)); // Häufigkeit absteigend
            if (cmp != 0) return cmp;
            return Character.compare(a, b); // alphabetisch aufsteigend
        });

        // Baue das Ergebnis mit max. 5 Buchstaben
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Math.min(5, buchstaben.size()); i++) {
            result.append(buchstaben.get(i));
        }

        return result.toString();
    }

    public String decryptName() {
        StringBuilder decrypted = new StringBuilder();
        int shift = sectorId % 26; // Verschiebung für den Buchstaben
        for (char c : name.toCharArray()) {
            if (c == '-') {
                decrypted.append(' '); // Bindestrich wird zu Leerzeichen
            } else {
                char shiftedChar = (char) ((c - 'a' + shift) % 26 + 'a');
                decrypted.append(shiftedChar);
            }
        }
        return decrypted.toString();
    }


    @Override
    public String toString() {
        return "EncryptedRoom{" +
                "name='" + name + '\'' +
                ", sectorId=" + sectorId +
                ", checksum='" + checksum + '\'' +
                '}';
    }

}
