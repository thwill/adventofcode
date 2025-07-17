package de.twisssow.adventofcode.mmxv.day13;

import java.util.List;
import java.util.Map;

public class GuestArrangement implements Comparable<GuestArrangement> {


    private List<String> guests;
    private Integer happiness = 0;


    public GuestArrangement(List<String> guests) {
        this.guests = guests;
    }

    public void calculateHappiness(Map<String, GuestRelations> guestRelationsMap) {
        for (int i = 0; i < guests.size(); i++) {
            String currentGuest = guests.get(i);
            String neighbour1 = null;
            String neighbour2 = null;
            if (i == 0) {
                neighbour1 = guests.get(guests.size() - 1); // Last guest
                neighbour2 = guests.get(i + 1); // Next guest
            } else if (i == guests.size() - 1) {
                neighbour1 = guests.get(i - 1); // Previous guest
                neighbour2 = guests.get(0); // First guest
            } else {
                neighbour1 = guests.get(i - 1); // Previous guest
                neighbour2 = guests.get(i + 1); // Next guest
            }
            this.happiness += guestRelationsMap.get(currentGuest)
                    .calculateHappinessForNeighbours(neighbour1, neighbour2);

        }
    }

    @Override
    public String toString() {
        return "GuestArrangement {guests=" + guests + " has total happiness of " + happiness + '}';
    }

    @Override
    public int compareTo(GuestArrangement o) {
        return this.happiness.compareTo(o.happiness);
    }
}
