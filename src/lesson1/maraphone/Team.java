package lesson1.maraphone;

import lesson1.maraphone.obstacles.Obstacles;

public class Team {

    private String teamName;
    private Competitor[] competitors;

    public Team(String teamName, Competitor...competitors) {
        this.teamName = teamName;
        this.competitors = competitors;
    }

    protected void start(Course course){
        for (Competitor c : this.competitors){
            for (Obstacles obstacles : course.obstacles) {
                if(obstacles.doit(c))
                    System.out.println(c.getType() + " " + c.getName() + " successfully done the "
                            + obstacles.getName() + " " + obstacles.getDistance());
                else{
                    System.out.println(c.getType() + " " + c.getName() +
                        " can't do the " + obstacles.getName() + " " + obstacles.getDistance());
                    break;
                }
            }
        }
    }

    protected void teamInfo(){
        String info = "Team " + teamName + " info: \n";
        for (Competitor c: this.competitors) {
            info += c.info() + "\n";
        }
        System.out.println(info);
    }

    protected void successfulTeamInfo(){
        String info = "Successful team " + teamName + " info: \n";
        for (Competitor c: this.competitors) {
            if(c.isOnDistance())
                info += c.info() + "\n";
        }
        System.out.println(info);
    }
}
