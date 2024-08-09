package br.com.palloma.ballitchampionship.utils;

import java.util.Comparator;

import br.com.palloma.ballitchampionship.model.Team;

public class SortList implements Comparator<Team> {

    @Override
    public int compare(Team o2, Team o1) {
        return o1.getPoints() - o2.getPoints();
    }
}
