package de.twisssow.adventofcode.mmxv.helper.day9;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Route implements Comparable<Route> {

    List<String> locations;

    int distance = 0;

    public Route(List<String> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "Route{" +
                "locations=" + locations +
                ", distance=" + distance +
                '}';
    }

    public int getDistance() {
        return distance;
    }

    public void calculateDistance(Map<Strecke, Integer> distances) {
        List<String> locationsList = new ArrayList<>(locations);
        for (int i = 0; i < locationsList.size() - 1; i++) {
            String startLocation = locationsList.get(i);
            String endLocation = locationsList.get(i + 1);
            Strecke strecke = new Strecke(startLocation, endLocation);
            if (distances.containsKey(strecke)) {
                this.distance += distances.get(strecke);
            } else {
                strecke = new Strecke(endLocation, startLocation);
                if (distances.containsKey(strecke)) {
                    this.distance += distances.get(strecke);
                } else {
                    throw new IllegalArgumentException("No distance found for " + startLocation + " to " + endLocation);
                }
            }

        }
    }

    @Override
    public int compareTo(Route o) {
        return Integer.compare(this.distance, o.distance);
    }
}
