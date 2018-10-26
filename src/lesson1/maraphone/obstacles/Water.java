package lesson1.maraphone.obstacles;

import lesson1.maraphone.Competitor;

public class Water extends Obstacles {

    private int distance;

    public Water(int distance) {
        this.distance = distance;
    }

    @Override
    public String getName() {
        return "Water";
    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public boolean doit(Competitor competitor) {
        return competitor.swim(distance);
    }
}
