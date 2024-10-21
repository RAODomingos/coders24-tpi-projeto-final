package dev.dluks.brasileirao.entities;

public class Card {

    private final int matchId;
    private final String round;
    private final String team;
    private final String card;
    private final String athlete;
    private final String shirtNumber;
    private final String position;
    private final String minute;

    public Card(String[] data) {
        this.matchId = Integer.parseInt(data[0]);
        this.round = data[1];
        this.team = data[2];
        this.card = data[3];
        this.athlete = data[4];
        this.shirtNumber = data[5];
        this.position = data[6];
        this.minute = data[7];
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

    public String getCard() {
        return card;
    }

    public String getAthlete() {
        return athlete;
    }

    public String getShirtNumber() {
        return shirtNumber;
    }

    public String getPosition() {
        return position;
    }

    public String getMinute() {
        return minute;
    }

    @Override
    public String toString() {
        return "Card{" +
                "matchId=" + matchId +
                ", round='" + round + '\'' +
                ", team='" + team + '\'' +
                ", card='" + card + '\'' +
                ", athlete='" + athlete + '\'' +
                ", shirtNumber='" + shirtNumber + '\'' +
                ", position='" + position + '\'' +
                ", minute='" + minute + '\'' +
                '}';
    }
}
