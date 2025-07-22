package de.twisssow.adventofcode.mmxvi.day8;

public class Light {

    public enum State {
        ON, OFF
    }


    State state;


    public Light() {
        this.state = State.OFF;
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
