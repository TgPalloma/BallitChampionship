package br.com.palloma.ballitchampionship.dao;

import android.content.Context;
import android.util.Log;
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
    private static boolean championshipNonFinished = false;
    private static Integer maxStages = 0;
    private static Integer currentStage = 1;

    private static List<Match> matchList = new ArrayList<>();

    private static List<Team> freeList = new ArrayList<>();

    /**Método que embaralha os times e monta as duplas que deverão competir
     *Foi usado o "shuffle" do Colections para fazer isso. O método também avalia quais times serão isentos da primeira fase*/
    public void shuffleTeams (List<Team> listTeams, Context context) {

        championshipInProgress = true;
        Collections.shuffle(listTeams);

        if (listTeams.size() == 8)
            maxStages = 3;
        else maxStages = 4;

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

    // Método que salva o fim de cada partida
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

    //Método que pega a lista de partidas e a refaz na ordem inversa usando um laço de repetição
    public List<Match> getList () {

        List<Match> listTemp = new ArrayList<>();

        for (int i = matchList.size() - 1; i >= 0; i--) {
            listTemp.add(matchList.get(i));
        }
        return listTemp;
    }

    //Método que avalia se uma nova etapa acabou e se é possível começar uma nova.
    public void evaluateNewStage (Context context) {

        Integer winners = 0;
        for (int i=0; i< matchList.size(); i++) {
            if (!matchList.get(i).getActiveStatus())
                winners += 1;
        }
        if (winners == matchList.size()) {

            if (currentStage == 1) {
                currentStage += 1;
                Log.d("currentStage", currentStage.toString());
                buildSecondStage();
                return;
            }
            if (maxStages == 4) {
                if (currentStage == 2) {
                    currentStage += 1;
                    buildSemiFinals();
                } else
                if (currentStage == 3) {
                    currentStage += 1;
                    buildFinals();
                }
            }
            if (maxStages == 3) {
                buildFinals();
            }
        }
    }

    //Métodos das segunda e das etapas finais.
    public void buildSemiFinals () {

        Integer size = matchList.size();

        matchList.add(new Match(
                matchList.get(size-1).getWinner(),
                matchList.get(size-2).getWinner(),
                currentStage
        ));
        matchList.add(new Match(
                matchList.get(size-3).getWinner(),
                matchList.get(size-4).getWinner(),
                currentStage
        ));
    }

    public void buildFinals () {

        matchList.add(new Match(
                matchList.get(matchList.size()-1).getWinner(),
                matchList.get(matchList.size()-2).getWinner(),
                currentStage));
    }

    /**Importante notar que a segunda etapa utiliza a lista de isentos da primeira etapa
     * aticionando-a na proxima para participação de jogos. Esse método foi campeonato e isenção
     * foi determinado após ampla pesquisa de funcionamento de campeonatos.
     */
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

    //Verifica se o campeonato já está em andamento
    public boolean getProgressStatus () {
        return championshipInProgress;
    }
}