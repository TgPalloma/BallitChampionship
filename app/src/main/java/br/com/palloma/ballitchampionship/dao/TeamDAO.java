package br.com.palloma.ballitchampionship.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.palloma.ballitchampionship.model.Team;

public class TeamDAO {

    private static List<Team> teamsList = new ArrayList<>();

    public void save(String name, int year, String warCry, int position) {

        if (position == 20) {
            teamsList.add(new Team(name, year, warCry));
        } else {
            teamsList.set(position, new Team(name, year, warCry));
        }
    }

    public ArrayList<Team> getTeamsList() {
        return new ArrayList<>(teamsList);
    }

    public Team getTeam (int position) {
        return teamsList.get(position);
    }

    public void deleteTeam(int id) {
        teamsList.remove(id);
    }
}
