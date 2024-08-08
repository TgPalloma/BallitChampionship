package br.com.palloma.ballitchampionship.model;

public class Match {

    private Team teamA;
    private Team teamB;
    private Integer teamAPoints;
    private Integer teamBPoints;
    private Boolean activeStatus;
    private Integer stage;

    //As equipes começam uma partida com 50 pontos cada uma
    // - Blot: Objetivo do jogo +5 pontos
    // - Plif: Falta, da +1 para a equipe que sofreu a falta

    public Match(Team teamA, Team teamB, int stage) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.stage = stage;
        this.teamAPoints = 0;
        this.teamBPoints = 0;
        activeStatus = true;
    }

    /**true para "Ativa" e false para "Finalizada"**/
    public Boolean getActiveStatus() {
        return activeStatus;
    }

    /**true para "Ativa" e false para "Finalizada"**/
    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        stage = stage;
    }

    public Team getTeamA() {
        return teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setStartMatch () {
        teamAPoints = 50;
        teamBPoints = 50;
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

    /**Coloque no argumento true para A ou false para B **/
    public void resolveGrusht (boolean i) {
        if (i) {
            teamAPoints +=3;
        } else {
            teamBPoints +=3;
        }
    }

    public Team getWinner () {
        if(teamA.getPoints() > teamB.getPoints()) {
            return teamA;
        } return teamB;
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
