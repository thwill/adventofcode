package de.twisssow.adventofcode.mmxv.day14;

public class Reindeer {

    String name;

    int speed;
    int flyTime;
    int restTime;

    int currentDistance;


    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getFlyTime() {
        return flyTime;
    }

    public int getRestTime() {
        return restTime;
    }

    public int getCurrentDistance() {
        return currentDistance;
    }

    public void setCurrentDistance(int currentDistance) {
        this.currentDistance = currentDistance;
    }

    public Reindeer(String name, int speed, int flyTime, int restTime) {
        this.name = name;
        this.speed = speed;
        this.flyTime = flyTime;
        this.restTime = restTime;
    }

    public int distanceAfterTime(int time) {
        int cycleTime = flyTime + restTime;
        int fullCycles = time / cycleTime;
        int remainingTime = time % cycleTime;

        int distance = fullCycles * speed * flyTime;
        if (remainingTime > flyTime) {
            distance += speed * flyTime;
        } else {
            distance += speed * remainingTime;
        }
        return distance;
    }

}
