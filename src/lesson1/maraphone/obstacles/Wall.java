package lesson1.maraphone.obstacles;

import lesson1.maraphone.Competitor;

public class Wall extends Obstacles {

    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public String getName() {
        return "Wall";
    }

    @Override
    public int getDistance() {
        return height;
    }

    @Override
    public boolean doit(Competitor competitor) {
        return competitor.jump(height);
    }
}
