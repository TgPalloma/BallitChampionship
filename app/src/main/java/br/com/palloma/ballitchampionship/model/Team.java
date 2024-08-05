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

    public void setupWinPoints (int cod) {

        /*Código de cada tipo de pontuação
        100 - Inicio de pártida = 50
        200 - Blot = 5
        250 - Plift = 1
        300 - Advrungh! = -10
         */

        switch (cod) {
            case 100: points += 50;
                break;

            case 200: points += 5;
                break;

            case 250: points += 1;
                break;

            case 300: points -= 10;
                break;
        }
    }

    @Override
    public String toString() {
        return name + "\n"
                + year;
    }
}
