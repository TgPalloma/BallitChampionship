package br.com.palloma.ballitchampionship.data;

import br.com.palloma.ballitchampionship.dao.TeamDAO;

public class Teams {

    private TeamDAO dao = new TeamDAO();

    public void addTeamXp() {
        dao.save("Pallominha", 1986, "Pica das Galaxias");
        dao.save("Nina", 1988, "Xaropisse a vida toda!");
        dao.save("Raquel", 1986, "Em casa a qualquer custo");
        dao.save("Helenara", 1965, "Vou para a Grécia!");
        dao.save("Érico", 1938, "Sou safadenho...");
    }

}
