package br.com.palloma.ballitchampionship.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.palloma.ballitchampionship.model.Match;
import br.com.palloma.ballitchampionship.model.Team;

public class ChanpionshipDAO {

    private static boolean championshipInProgress = false;

    private static List<Match> matches = new ArrayList<>();

    private static List<Match> stageOne = new ArrayList<>();
    private static List<Match> stageTwo = new ArrayList<>();
    private static List<Match> stageThree = new ArrayList<>();
    private static List<Match> stageFour = new ArrayList<>();

    private static List<Team> freeList = new ArrayList<>();

    //Método que embaralha os times e monta as duplas que deverão competir
    //Foi usado o "shuffle" do Colections para fazer isso.
    public void shuffleTeams (List<Team> listTeams) {

        championshipInProgress = true;
        Collections.shuffle(listTeams);

        if (listTeams.size() == 8 || listTeams.size() == 16){

            for (int i = 0; i < listTeams.size(); i += 2) {
                matches.add(new Match(listTeams.get(i), listTeams.get(i + 1)));
            }

        } else {

            int free = 16 - listTeams.size();
            int counter = 0;

            for (int i = 0; i < 16 - free; i += 2) {
                matches.add(new Match(listTeams.get(i), listTeams.get(i + 1)));
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
        }
    }

    public List<Match> getList () {
        return matches;
    }

    public boolean getProgressStatus () {
        return championshipInProgress;
    }
}
