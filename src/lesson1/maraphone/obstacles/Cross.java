package lesson1.maraphone.obstacles;

import lesson1.maraphone.Competitor;

public class Cross extends Obstacles {

    private int distance;

    public Cross(int distance) {
        this.distance = distance;
    }

    @Override
    public String getName() {
        return "Cross";
    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public boolean doit(Competitor competitor) {
        return competitor.run(distance);
    }
}
