package de.twisssow.adventofcode.mmxv.day13;

import java.util.HashMap;
import java.util.Map;

public class GuestRelations {

    private String name;

    private Map<String, Integer> relations= new HashMap<>();

    public GuestRelations(String name) {
        this.name = name;
    }

    public void addRelation(String name , Integer happiness) {
        relations.put(name, happiness);
    }

    public Integer calculateHappinessForNeighbours(String neighbour1, String neighbour2) {
        Integer happiness = 0;
        if (relations.containsKey(neighbour1)) {
            happiness += relations.get(neighbour1);
        }
        if (relations.containsKey(neighbour2)) {
            happiness += relations.get(neighbour2);
        }
        return happiness;
    }




}
