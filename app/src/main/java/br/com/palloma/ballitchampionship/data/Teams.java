package br.com.palloma.ballitchampionship.data;


import br.com.palloma.ballitchampionship.dao.TeamDAO;

public class Teams {

    TeamDAO dao = new TeamDAO();

    public void teamXP () {
        dao.save("Enterprise A", 1976, "Khaaaaaan!!!", 101);
        dao.save("Enterprise D", 1994, "Acionar...", 101);
        dao.save("Millenium Falcon", 1979, "RRRAAAAAAGGHHH",101);
        dao.save("Jedi's", 1981, "A Força está com ele", 101);
        dao.save("Siths", 2004, "Morte aos Jedi's!!", 101);
        dao.save("Falf Life", 1997, "Unforseen Consequences", 101);
        dao.save("Tomb Raider", 1996, "AAAAAAAH!! ploft", 101);
        dao.save("Borgs", 1995, "Resistir é inútil...", 101);
        dao.save("Homeworld", 1999, "No one leaves", 101);
        dao.save("Piratas do Caribe", 2000, "Parolar", 101);
        dao.save("Rebeldes", 1977, "Estou com um mau pessentimento",101);
        dao.save("O Exterminador", 1993, "I will back", 101);
    }

}
