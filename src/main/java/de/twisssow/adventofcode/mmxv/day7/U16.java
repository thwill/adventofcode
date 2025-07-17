package de.twisssow.adventofcode.mmxv.day7;

public final class U16 {
    private static final int MASK = 0xFFFF;
    private final int value;

    public U16(int value) {
        this.value = value & MASK;
    }

    public int toInt() {
        return value;
    }

    public String toBinaryString() {
        return String.format("%16s", Integer.toBinaryString(value)).replace(' ', '0');
    }

    public U16 and(U16 other) {
        return new U16(this.value & other.value);
    }

    public U16 or(U16 other) {
        return new U16(this.value | other.value);
    }

    public U16 xor(U16 other) {
        return new U16(this.value ^ other.value);
    }

    public U16 not() {
        return new U16(~this.value);
    }

    public U16 shiftLeft(int n) {
        return new U16(this.value << n);
    }

    public U16 shiftRight(int n) {
        return new U16(this.value >>> n);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}