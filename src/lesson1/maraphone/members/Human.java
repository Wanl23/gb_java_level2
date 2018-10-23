package lesson1.maraphone.members;

import lesson1.maraphone.Competitor;

public class Human implements Competitor {

    private String type;
    private String name;
    private int runRestriction;
    private int swimRestriction;
    private int jumpRestriction;
    private boolean onDistance;

    public Human(String type, String name, int runRestriction, int swimRestriction, int jumpRestriction) {
        this.type = type;
        this.name = name;
        this.runRestriction = runRestriction;
        this.swimRestriction = swimRestriction;
        this.jumpRestriction = jumpRestriction;
        this.onDistance = true;
    }

    @Override
    public boolean run(int dist) {
        if(dist <= runRestriction) return true;
        else{
            onDistance = false;
            return false;
        }
    }

    @Override
    public boolean swim(int dist) {
        if(dist <= runRestriction) return true;
        else{
            onDistance = false;
            return false;
        }
    }

    @Override
    public boolean jump(int dist) {
        if(dist <= runRestriction)
            return true;
        else{
            onDistance = false;
            return false;
        }
    }

    @Override
    public String info() {
        return "type: " + type + ", name: " + name + ", on distance: " + onDistance;
    }

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }
}
