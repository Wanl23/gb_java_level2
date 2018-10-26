package lesson1.maraphone.members;

public class Cat extends Animal {

    String type;
    String name;
    int runRestriction;
    int swimRestriction;
    int jumpRestriction;
    boolean onDistance;

    public Cat(String type, String name, int runRestriction, int swimRestriction, int jumpRestriction) {
        super(type, name, runRestriction, swimRestriction, jumpRestriction);
    }

    @Override
    public boolean run(int dist) {
        return super.run(dist);
    }

    @Override
    public boolean swim(int dist) {
        return super.swim(dist);
    }

    @Override
    public boolean jump(int dist) {
        return super.jump(dist);
    }

    @Override
    public String info() {
        return super.info();
    }

    @Override
    public boolean isOnDistance() {
        return super.isOnDistance();
    }
}
