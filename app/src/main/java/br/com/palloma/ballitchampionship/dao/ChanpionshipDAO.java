package br.com.palloma.ballitchampionship.dao;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.palloma.ballitchampionship.model.Match;
import br.com.palloma.ballitchampionship.model.Team;

public class ChanpionshipDAO {

    private static boolean championshipInProgress = false;
    private static List<Match> matches = new ArrayList<>();

    //Método que embaralha os times e monta as duplas que deverão competir
    //Foi usado o "shuffle" do Colections para fazer isso.
    public void shuffleTeams (ArrayList<Team> listTeams) {
        Collections.shuffle(listTeams);
        championshipInProgress = true;
        for (int i=0; i<listTeams.size(); i+=2) {
            matches.add(new Match(listTeams.get(i), listTeams.get(i + 1)));
        }
    }

    public List<Match> getList () {
        return matches;
    }

    public boolean getProgressStatus () {
        return championshipInProgress;
    }
}
