package de.twisssow.adventofcode.mmxv.day6;

public class Light {

    public enum State {
        ON, OFF
    }

    int x;
    int y;
    int brightness = 0;

    private State state = State.OFF;

    public Light(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void turnOn() {
        this.state = State.ON;
    }

    public void turnOff() {
        this.state = State.OFF;
    }

    public void toggle() {
        if (this.state == State.ON) {
            this.state = State.OFF;
        } else if (this.state == State.OFF) {
            this.state = State.ON;
        }
    }


    public void toggleBrightness() {
        this.brightness+= 2;
    }

    public void turnOnBrightness() {
        this.brightness += 1;
    }

    public void turnOffBrightness() {
        if (this.brightness > 0) {
            this.brightness -= 1;
        }
    }


    public State getState() {
        return state;
    }

}




