package lesson1.maraphone;

public interface Competitor {
    boolean run(int dist);
    boolean swim(int dist);
    boolean jump(int dist);
    String info();
    boolean isOnDistance();
    String getType();
    String getName();

}
