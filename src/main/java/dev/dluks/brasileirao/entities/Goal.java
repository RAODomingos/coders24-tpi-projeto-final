package dev.dluks.brasileirao.entities;

public class Goal {

    private final int matchId;
    private final String round;
    private final String team;
    private final String athlete;
    private final String minute;
    private final String typeOfGoal;

    public Goal(String[] data) {
        this.matchId = Integer.parseInt(data[0].replace("\"", ""));
        this.round = data[1].replace("\"", "");
        this.team = data[2].replace("\"", "");
        this.athlete = data[3].replace("\"", "");
        this.minute = data[4].replace("\"", "");
        this.typeOfGoal = data[5].replace("\"", "");
    }

    public int getMatchId() {
        return matchId;
    }

    public String getRound() {
        return round;
    }

    public String getTeam() {
        return team;
    }

    public String getAthlete() {
        return athlete;
    }

    public String getMinute() {
        return minute;
    }

    public String getTypeGoal() {
        return typeOfGoal;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "matchId=" + matchId +
                ", round='" + round + '\'' +
                ", team='" + team + '\'' +
                ", athlete='" + athlete + '\'' +
                ", minute='" + minute + '\'' +
                ", typeOfGoal='" + typeOfGoal + '\'' +
                '}';
    }
}
