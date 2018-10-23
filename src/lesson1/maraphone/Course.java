package lesson1.maraphone;

import lesson1.maraphone.obstacles.Obstacles;

public class Course {
    String courseName;
    Obstacles[] obstacles;

    public Course(String courseName, Obstacles...obstacles) {
        this.courseName = courseName;
        this.obstacles = obstacles;
    }
}
