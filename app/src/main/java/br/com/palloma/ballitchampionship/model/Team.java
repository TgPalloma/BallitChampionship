package br.com.palloma.ballitchampionship.model;

import java.util.Random;

public class Team {

    private Integer id;
    private String name;
    private Integer year;
    private String WarCry;

    public Team(String name, Integer year, String warCry) {

        Random random = new Random();
        this.id = random.nextInt(20);

        this.name = name;
        this.year = year;
        WarCry = warCry;
    }

    public Integer getId() {
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

    @Override
    public String toString() {
        return name + "\n"
                + year;
    }
}
