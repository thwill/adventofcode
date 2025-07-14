package de.twisssow.adventofcode.mmxv.helper.day17;

public class Container {


    String name;
    int volume;


    public Container(String name, int volume) {
        this.name = name;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Container container = (Container) o;
        return volume == container.volume && name.equals(container.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + volume;
        return result;
    }
}
