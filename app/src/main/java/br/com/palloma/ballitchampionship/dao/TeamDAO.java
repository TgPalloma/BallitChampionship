package br.com.palloma.ballitchampionship.dao;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.palloma.ballitchampionship.model.Team;

public class TeamDAO {

    private static Integer id;
    private HashMap<Integer,Team> teamsList = new HashMap<>();

    public void save(String name, int year, String warCry) {

        Team team = new Team(name, year, warCry);
        teamsList.put(team.getId(), team);
    }

    public ArrayList<Team> getTeamsList() {
        return new ArrayList<>(teamsList.values());
    }

    public Team getTeam (int id) {
        return teamsList.get(id);
    }

    public void deleteTeam(int id) {
        teamsList.remove(id);
    }
}
