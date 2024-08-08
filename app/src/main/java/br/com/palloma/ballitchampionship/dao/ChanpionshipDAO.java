package br.com.palloma.ballitchampionship.dao;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.palloma.ballitchampionship.model.Match;
import br.com.palloma.ballitchampionship.model.Team;

public class ChanpionshipDAO {

    TeamDAO teamDAO = new TeamDAO();

    private static boolean championshipInProgress = false;

    private static List<Match> matchList = new ArrayList<>();

    private static List<Team> freeList = new ArrayList<>();

    //Método que embaralha os times e monta as duplas que deverão competir
    //Foi usado o "shuffle" do Colections para fazer isso.
    public void shuffleTeams (List<Team> listTeams, Context context) {

        championshipInProgress = true;
        Collections.shuffle(listTeams);

        if (listTeams.size() == 8 || listTeams.size() == 16){

            for (int i = 0; i < listTeams.size(); i += 2) {
                matchList.add(new Match(listTeams.get(i), listTeams.get(i + 1), 1));
            }
            Toast.makeText(context, "Campeonato criado com sucesso!", Toast.LENGTH_LONG).show();

        } else {

            int free = 16 - listTeams.size();
            int counter = 0;

            for (int i = 0; i < listTeams.size() - free; i += 2) {
                matchList.add(new Match(listTeams.get(i), listTeams.get(i + 1), 1));
                counter  = i;
            }
            for (int i = counter; i < listTeams.size(); i++) {

                freeList.add(new Team(
                        listTeams.get(i).getName(),
                        listTeams.get(i).getYear(),
                        listTeams.get(i).getWarCry(),
                        listTeams.get(i).getId()
                ));
            }
            Toast.makeText(context, freeList.size() + " Ficaram isentos da primeira fase", Toast.LENGTH_LONG).show();
        }

    }

    public void saveEndOfMatch (@NonNull Match match) {

        teamDAO.teamPoints(match.getTeamA().getId(), match.getTeamAPoints());
        teamDAO.teamPoints(match.getTeamB().getId(), match.getTeamBPoints());
        match.setActiveStatus(false);

        for (int i=0; i < matchList.size(); i++) {
            if (match.getTeamA().getId() == matchList.get(i).getTeamA().getId()) {
                if (match.getStage() == matchList.get(i).getStage()) {
                    matchList.set(i,match);
                }
            }
        }
    }

    public List<Match> getList () {

        List<Match> listTemp = new ArrayList<>();

        for (int i = matchList.size() - 1; i >= 0; i--) {
            listTemp.add(matchList.get(i));
        }

        return listTemp;
    }

    public void evaluateNewStage (Context context) {
        int counter = 0;
        int currentStage = 0;
        for (Match match:matchList) {
            if (!match.getActiveStatus()) {
                counter += 1;
                currentStage = match.getStage();
            }
        }
        if (matchList.size() == counter) {
            if (currentStage == 1) {
                buildSecondStage();
                Toast.makeText(context, freeList.size() + " equipes isentas da primeira etapa foram incluidas.", Toast.LENGTH_LONG).show();
            } else {
                buildStageAfterSecond(currentStage);
                Toast.makeText(context, (currentStage + 1) + " inciada com sucesso" , Toast.LENGTH_LONG).show();
            }
        }
    }

    public void buildStageAfterSecond (int currentStage) {

        int size = matchList.size();

        for (int i=0; i < size; i+=2) {
            if (matchList.get(i).getStage() == currentStage) {
                matchList.add(new Match(
                        matchList.get(i).getWinner(),
                        matchList.get(i+1).getWinner(),
                        currentStage + 1
                ));
            }
        }
    }

    public void buildSecondStage () {

        int size = matchList.size();

        for (int i=0;i < size; i+=2) {
            matchList.add(new Match(
                    matchList.get(i).getWinner(),
                    matchList.get(i+1).getWinner(),
                    2
            ));
        }

        for (int i=0; i < freeList.size(); i+=2) {
            matchList.add(new Match(
                    freeList.get(i),
                    freeList.get(i+1),
                    2
            ));
        }
    }

    public boolean getProgressStatus () {
        return championshipInProgress;
    }
}
