package br.com.palloma.ballitchampionship.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.palloma.ballitchampionship.model.Team;

public class TeamDAO {

    private static List<Team> teamsList = new ArrayList<>();

    public void save(String name, int year, String warCry) {

        teamsList.add(new Team(name, year, warCry));
        System.out.println(teamsList);
    }

    public ArrayList<Team> getTeamsList() {
        return new ArrayList<>(teamsList);
    }

    public Team getTeam (int id) {
        return teamsList.get(id);
    }

    public void deleteTeam(int id) {
        teamsList.remove(id);
    }
}
