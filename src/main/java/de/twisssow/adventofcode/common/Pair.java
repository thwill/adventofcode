package de.twisssow.adventofcode.common;

public class Pair<T> {

    private T first;
    private T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (first == null ? 0 : first.hashCode());
        result = 31 * result + (second == null ? 0 : second.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pair<?> other)) return false;
        return (first == null ? other.first == null : first.equals(other.first)) &&
                (second == null ? other.second == null : second.equals(other.second));
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public static <T> Pair<T> of(T first, T second) {
        return new Pair<>(first, second);
    }
}
