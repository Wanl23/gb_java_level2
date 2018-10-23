package lesson1.maraphone.obstacles;

import lesson1.maraphone.Competitor;

public abstract class Obstacles {
    private String name;
    private int distance;

    public abstract boolean doit(Competitor competitor);

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }
}
