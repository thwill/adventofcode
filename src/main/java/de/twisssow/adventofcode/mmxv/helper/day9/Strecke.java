package de.twisssow.adventofcode.mmxv.helper.day9;

public class Strecke {
    private String startLocation;
    private String endLocation;


    public Strecke(String startLocation, String endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Strecke strecke = (Strecke) o;
        return startLocation.equals(strecke.startLocation) && endLocation.equals(strecke.endLocation);
    }

    @Override
    public int hashCode() {
        int result = startLocation.hashCode();
        result = 31 * result + endLocation.hashCode();
        return result;
    }
}
