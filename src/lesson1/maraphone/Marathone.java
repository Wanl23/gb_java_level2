package lesson1.maraphone;

import lesson1.maraphone.members.Cat;
import lesson1.maraphone.members.Dog;
import lesson1.maraphone.members.Man;
import lesson1.maraphone.obstacles.Cross;
import lesson1.maraphone.obstacles.Wall;
import lesson1.maraphone.obstacles.Water;

public class Marathone {
    public static void main(String[] args) {
        Cat cat1 = new Cat("cat", "c1", 100, 1, 1);
        Cat cat2 = new Cat("cat", "c2", 200, 2, 2);
        Cat cat3 = new Cat("cat", "c3", 300, 3, 3);

        Dog dog1 = new Dog("dog", "d1", 400, 4, 4);
        Dog dog2 = new Dog("dog", "d3", 500, 5, 5);
        Dog dog3 = new Dog("dog", "d3", 600, 6, 6);

        Man man1 = new Man("man", "m1", 1000, 100, 2);
        Man man2 = new Man("man", "m2", 2000, 200, 4);
        Man man3 = new Man("man", "m3", 2000, 300, 6);

        Course courseDifferent = new Course("course1", new Wall(3), new Cross(300), new Water(3));
        Course courseWalls = new Course("course2", new Wall(3), new Wall(1), new Wall(4));
        Course courseWater = new Course("course3", new Water(2), new Water(4), new Water(5));
        Course courseCross = new Course("course4", new Cross(400), new Cross(300), new Cross(1000));

        Team cats = new Team("cats", cat1, cat2, cat3);
        Team dogs = new Team("dogs", dog1, dog2, dog3);
        Team mens = new Team("mens", man1, man2, man3);
        Team teamAll = new Team("all", cat3, dog3, man3);

        cats.start(courseDifferent);
        cats.teamInfo();
        cats.successfulTeamInfo();

//        dogs.start(courseWater);
//        dogs.teamInfo();
//        dogs.successfulTeamInfo();
//
//        mens.start(courseCross);
//        mens.teamInfo();
//        mens.successfulTeamInfo();
//        teamAll.start(courseDifferent);


    }
}
