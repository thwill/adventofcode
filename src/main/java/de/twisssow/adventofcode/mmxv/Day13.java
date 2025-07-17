package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.common.Permutator;
import de.twisssow.adventofcode.mmxv.day13.GuestArrangement;
import de.twisssow.adventofcode.mmxv.day13.GuestRelations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Day13 {

    public static void main(String[] args) {

        List<String> lines = LineReader.readInputFile("mmxv/relations.txt");
        Map<String, GuestRelations> guestRelationsMap = new HashMap<>();

        for (String line : lines) {
            String[] parts = line.split(" ");
            String guestName = parts[0];
            guestRelationsMap.computeIfAbsent(guestName, GuestRelations::new);
            // Assuming the format is "Alice would gain 54 happiness units by sitting next to Bob."
            int value = Integer.parseInt(parts[3]);
            if ("lose".equals(parts[2])) {
                value = -value; // If it says "lose", we negate the value
            }
            String neighbourName = parts[10].replace(".", ""); // Remove the trailing dot
            guestRelationsMap.get(guestName).addRelation(neighbourName, value);
        }
        List<String> guests = guestRelationsMap.keySet().stream().toList();
        List<List<String>> arrangementsList = Permutator.permutiere(guests);
        TreeSet<GuestArrangement> set = new TreeSet<>();
        for (List<String> arrangement : arrangementsList) {
            GuestArrangement guestArrangement = new GuestArrangement(arrangement);
            guestArrangement.calculateHappiness(guestRelationsMap);
            set.add(guestArrangement);
        }
        GuestArrangement bestArrangement = set.last();
        System.out.println("Best arrangement: " + bestArrangement);
        // Adding myself to the arrangement
        String mySelf = "Me";
        GuestRelations myRelations = new GuestRelations(mySelf);
        for (String guest : guests) {
            myRelations.addRelation(guest, 0); // Assuming 0 happiness with myself
        }
        for (String guest : guests) {
            guestRelationsMap.get(guest).addRelation(mySelf, 0); // Assuming 0 happiness from others}
        }
        guestRelationsMap.put(mySelf, myRelations);
        guests = guestRelationsMap.keySet().stream().toList();
        List<List<String>> arrangementsListWithMyself = Permutator.permutiere(guests);
        TreeSet<GuestArrangement> setWithMySelf = new TreeSet<>();
        for (List<String> arrangement : arrangementsListWithMyself) {
            GuestArrangement guestArrangement = new GuestArrangement(arrangement);
            guestArrangement.calculateHappiness(guestRelationsMap);
            setWithMySelf.add(guestArrangement);
        }
        bestArrangement = setWithMySelf.last();
        System.out.println("Best arrangement with myself: " + bestArrangement);



    }


}
