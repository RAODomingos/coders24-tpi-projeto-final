package dev.dluks.brasileirao.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Match {
    // Header do csv de entrada
    // "ID","rodata","data","hora","mandante","visitante","formacao_mandante","formacao_visitante","tecnico_mandante","tecnico_visitante","vencedor","arena","mandante_Placar","visitante_Placar","mandante_Estado","visitante_Estado"

    private final int id;
    private final String round;
    private final LocalDate date;
    private final LocalTime time;
    private final String homeTeam;
    private final String visitorTeam;
    private final String homeTeamFormation;
    private final String visitorTeamFormation;
    private final String homeTeamCoach;
    private final String visitorTeamCoach;
    private final String winner;
    private final String stadium;
    private final int homeTeamScore;
    private final int visitorTeamScore;
    private final String homeTeamState;
    private final String visitorTeamState;
    private final int totalScore;

    public Match(String[] data) {
        this.id = Integer.parseInt(data[0]);
        this.round = data[1];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.date = LocalDate.parse(data[2], formatter);
        this.time = LocalTime.parse(data[3]);
        this.homeTeam = data[4];
        this.visitorTeam = data[5];
        this.homeTeamFormation = data[6];
        this.visitorTeamFormation = data[7];
        this.homeTeamCoach = data[8];
        this.visitorTeamCoach = data[9];
        this.winner = data[10];
        this.stadium = data[11];
        this.homeTeamScore = Integer.parseInt(data[12]);
        this.visitorTeamScore = Integer.parseInt(data[13]);
        this.homeTeamState = data[14];
        this.visitorTeamState = data[15];

        this.totalScore = this.homeTeamScore + this.visitorTeamScore;
    }

    public int getId() {
        return id;
    }

    public String getRound() {
        return round;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getVisitorTeam() {
        return visitorTeam;
    }

    public String getHomeTeamFormation() {
        return homeTeamFormation;
    }

    public String getVisitorTeamFormation() {
        return visitorTeamFormation;
    }

    public String getHomeTeamCoach() {
        return homeTeamCoach;
    }

    public String getVisitorTeamCoach() {
        return visitorTeamCoach;
    }

    public String getWinner() {
        return winner;
    }

    public String getStadium() {
        return stadium;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getVisitorTeamScore() {
        return visitorTeamScore;
    }

    public String getHomeTeamState() {
        return homeTeamState;
    }

    public String getVisitorTeamState() {
        return visitorTeamState;
    }

    public int getTotalScore() {
        return totalScore;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", round='" + round + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", visitorTeam='" + visitorTeam + '\'' +
                ", homeTeamFormation='" + homeTeamFormation + '\'' +
                ", visitorTeamFormation='" + visitorTeamFormation + '\'' +
                ", homeTeamCoach='" + homeTeamCoach + '\'' +
                ", visitorTeamCoach='" + visitorTeamCoach + '\'' +
                ", winner='" + winner + '\'' +
                ", stadium='" + stadium + '\'' +
                ", homeTeamScore=" + homeTeamScore +
                ", visitorTeamScore=" + visitorTeamScore +
                ", homeTeamState='" + homeTeamState + '\'' +
                ", visitorTeamState='" + visitorTeamState + '\'' +
                '}';
    }
}
