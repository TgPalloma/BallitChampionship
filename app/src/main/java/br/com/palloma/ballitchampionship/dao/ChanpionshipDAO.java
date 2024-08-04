package br.com.palloma.ballitchampionship.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.palloma.ballitchampionship.model.Team;

public class ChanpionshipDAO {

    private static boolean championshipInProgress = false;
    private static List<Team> teamCSList;

    public void shuffleTeams (ArrayList<Team> listTeams) {
        Collections.shuffle(listTeams);
        championshipInProgress = true;
        teamCSList = listTeams;
    }

    public ArrayList<Team> getList () {
        return new ArrayList<>(teamCSList);
    }

    public boolean getProgressStatus () {
        return championshipInProgress;
    }
}
