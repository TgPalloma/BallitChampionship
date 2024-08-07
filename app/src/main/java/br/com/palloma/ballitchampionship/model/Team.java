package br.com.palloma.ballitchampionship.model;

import java.util.Random;

public class Team {

    private int id;
    private String name;
    private Integer year;
    private String WarCry;
    private Integer points = 0;

    public Team(String name, Integer year, String warCry, int id) {

        this.id = id;
        this.name = name;
        this.year = year;
        WarCry = warCry;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public void setPoints(Integer points) {
        this.points = points;
    }
}
