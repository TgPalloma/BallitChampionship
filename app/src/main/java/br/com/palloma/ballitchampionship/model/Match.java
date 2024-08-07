package br.com.palloma.ballitchampionship.model;

public class Match {

    private Team teamA;
    private Team teamB;
    private Integer teamAPoints;
    private Integer teamBPoints;
    private Boolean status;
    private int Stage;

    //As equipes começam uma partida com 50 pontos cada uma
    // - Blot: Objetivo do jogo +5 pontos
    // - Plif: Falta, da +1 para a equipe que sofreu a falta

    public Match(Team teamA, Team teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.teamAPoints = 50;
        this.teamBPoints = 50;
    }

    public int getStage() {
        return Stage;
    }

    public void setStage(int stage) {
        Stage = stage;
    }

    public Team getTeamA() {
        return teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void addBlotA () {
        teamAPoints += 5;
    }

    public void addBlotB () {
        teamBPoints += 5;
    }

    public void addPlifA () {
        teamBPoints += 1;
    }

    public void addPlifB () {
        teamAPoints += 1;
    }

    public Integer getTeamAPoints() {
        return teamAPoints;
    }

    public Integer getTeamBPoints() {
        return teamBPoints;
    }

    @Override
    public String toString() {
        return teamA.toString() + "\n" +
                teamB.toString();
    }
}
