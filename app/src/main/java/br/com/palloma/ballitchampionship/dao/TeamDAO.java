package br.com.palloma.ballitchampionship.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.palloma.ballitchampionship.data.Teams;
import br.com.palloma.ballitchampionship.model.Team;

public class TeamDAO {

    private static List<Team> teamsList = new ArrayList<>();
    private static Random r = new Random();

    public void save(String name, int year, String warCry, int id) {

        if (id == 101) {
            teamsList.add(new Team(name, year, warCry, r.nextInt(100)));
        } else {
            for (int i=0; i<16; i++) {
                if (teamsList.get(i).getId() == id) {
                    teamsList.set(i, new Team(name, year, warCry, id));
                    break;
                }
            }
        }
    }

    public void teamPoints (int id, int points) {
        for (int i=0; i<16; i++) {
            if (teamsList.get(i).getId() == id) {
                teamsList.get(i).setPoints(points);
                break;
            }
        }
    }

    public List<Team> getTeamsList() {
        return teamsList;
    }

    /**Buscar pelo id e retorna o time**/
    public Team getTeam (int id) {
        for (Team team: teamsList) {
            if (team.getId() == id)
                return team;
        }
        return null;
    }

    public void deleteTeam(int id) {
        for (int i=0; i<16; i++) {
            if (teamsList.get(i).getId() == id) {
                teamsList.remove(i);
                break;
            }
        }
    }
}
