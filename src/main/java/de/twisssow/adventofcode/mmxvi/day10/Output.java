package de.twisssow.adventofcode.mmxvi.day10;

public class Output {

    private final int id;
    private Integer value;

    public Output(int id) {
        this.id = id;
        this.value = null;
    }

    public int getId() {
        return id;
    }

    public Integer getValue() {
        return value;
    }

    public void addValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Output{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }

}
