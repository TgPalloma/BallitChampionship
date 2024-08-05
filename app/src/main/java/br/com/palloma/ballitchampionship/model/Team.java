package br.com.palloma.ballitchampionship.model;

import java.util.Random;

public class Team {

    private String name;
    private Integer year;
    private String WarCry;
    private Integer points = 0;

    public Team(String name, Integer year, String warCry) {

        this.name = name;
        this.year = year;
        WarCry = warCry;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public String getWarCry() {
        return WarCry;
    }

    public Integer getPoints() {
        return points;
    }

    public void addPointsBeforematche (int ponts) {
        this.points += points;
    }

    public void advrungh () {
        this.points -= 10;
    }

    @Override
    public String toString() {
        return name + "\n"
                + year;
    }
}
