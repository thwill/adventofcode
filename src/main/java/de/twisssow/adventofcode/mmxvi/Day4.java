package de.twisssow.adventofcode.mmxvi;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.mmxvi.day4.EncryptedRoom;

import java.util.ArrayList;
import java.util.List;

public class Day4 {

    public static void main(String[] args) {

        List<String> inputLines = LineReader.readInputFile("mmxvi/room_chiffres.txt");
        List<EncryptedRoom> rooms = new ArrayList<>();

        for (String line : inputLines) {
            rooms.add(new EncryptedRoom(line));

        }
        System.out.println("Summe der Sektornummern: " + rooms.stream()
                .filter(EncryptedRoom::isValid)
                .mapToInt(EncryptedRoom::getSectorId)
                .sum());

        for (EncryptedRoom room : rooms) {
            if (room.isValid()) {
                String decryptedName = room.decryptName();
                if (decryptedName.contains("north")) {
                    System.out.println("Raum mit 'north': " + decryptedName + " (Sektor: " + room.getSectorId() + ")");
                }
            }
        }

    }
}
