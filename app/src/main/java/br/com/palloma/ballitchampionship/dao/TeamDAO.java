package br.com.palloma.ballitchampionship.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import br.com.palloma.ballitchampionship.model.Team;

public class TeamDAO {

    private static HashMap<Integer,Team> teamsHash = new HashMap<>();

    public void save(String name, int year, String warCry, Integer id) {
        Random r = new Random();

        if (id==51) {
            do{
                id = r.nextInt(50);
            } while(containsIdTeam(id));

            teamsHash.replace(id, new Team(name, year, warCry,id));
        } else {
            teamsHash.put(id, new Team(name, year, warCry,id));
        }
    }

    public boolean containsIdTeam (Integer id) {
        if (teamsHash.containsKey(id)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void teamPoints (int cod, String teamName) {

    }

    public ArrayList<Team> getTeamsList() {
        return new ArrayList<>(teamsHash.values());
    }

    public Team getTeam (int position) {
        return teamsHash.get(position);
    }

    public void deleteTeam(int id) {
        teamsHash.remove(id);
    }
}
