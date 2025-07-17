package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.common.LineReader;
import de.twisssow.adventofcode.common.Permutator;
import de.twisssow.adventofcode.mmxv.day9.Route;
import de.twisssow.adventofcode.mmxv.day9.Strecke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Day9 {

    public static void main(String[] args) {
        Set<String> locations = new HashSet<>();
        Set<Route> routes = new TreeSet<>();
        Map<Strecke, Integer> distances = new HashMap<Strecke, Integer>();
        List<String> lines = LineReader.readInputFile("mmxv/distances.txt");

        for (String line : lines) {
            String[] parts = line.split("=");
            String loacationPart = parts[0].trim();
            String distancePart = parts[1].trim();

            String[] locationsParts = loacationPart.split("to");
            String startLocation = locationsParts[0].trim();
            String endLocation = locationsParts[1].trim();
            locations.add(startLocation);
            locations.add(endLocation);
            Strecke strecke = new Strecke(startLocation, endLocation);
            distances.put(strecke, Integer.valueOf(distancePart));
        }
        List<List<String>> permutierteLocations =Permutator.permutiere(new ArrayList(locations));
        for (List<String> permutierteLocation : permutierteLocations) {
            Route route = new Route(permutierteLocation);
            route.calculateDistance(distances);
            routes.add(route);
        }
        System.out.println("Eine kürzeste Route ist " + ((TreeSet<Route>) routes).first());
        System.out.println("Eine längste Route ist " + ((TreeSet<Route>) routes).last());
    }

}


