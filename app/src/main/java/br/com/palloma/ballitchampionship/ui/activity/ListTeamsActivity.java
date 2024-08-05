package br.com.palloma.ballitchampionship.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.palloma.ballitchampionship.R;
import br.com.palloma.ballitchampionship.dao.ChanpionshipDAO;
import br.com.palloma.ballitchampionship.dao.TeamDAO;
import br.com.palloma.ballitchampionship.data.Teams;

public class ListTeamsActivity extends AppCompatActivity {

    ListView listTeam;
    FloatingActionButton fabAddTeam;
    FloatingActionButton fabHelp;
    Button btnStarChampionship;

    private final TeamDAO teamDao = new TeamDAO();
    private final ChanpionshipDAO csDao = new ChanpionshipDAO();
    private final Teams teamsXP = new Teams();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_teams);
        if(!csDao.getProgressStatus()){
            teamsXP.addTeamXp();
        }
        setupViews();
        setupListView();
        checkTeamsNumber();
        addTeam();
        help();
        editTeam();
        startChanpionShip();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupListView();
        checkTeamsNumber();
        startChanpionShip();
    }

    public void setupViews() {
        listTeam = findViewById(R.id.rv_team_list);
        fabAddTeam = findViewById(R.id.fab_add_team);
        fabHelp = findViewById(R.id.fab_help);
        btnStarChampionship = findViewById(R.id.bt_star_championship);
    }

    public void setupListView () {
        listTeam.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, teamDao.getTeamsList()));
    }

    public void addTeam() {
        fabAddTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListTeamsActivity.this, TeamRegisterActivity.class));
            }
        });
    }

    public void help() {
        fabHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListTeamsActivity.this, "Ainda não implementado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkTeamsNumber () {
        int listSize = teamDao.getTeamsList().size();
        fabAddTeam.setEnabled(false);

        if(listSize < 16) {
            fabAddTeam.setEnabled(true);
        }
        if (!(listSize%2 == 0 && listSize >= 8)) {
            btnStarChampionship.setEnabled(false);
        } else {
            btnStarChampionship.setEnabled(true);
        }
    }

    public void editTeam () {
        listTeam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(ListTeamsActivity.this, TeamRegisterActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });
    }

    public void startChanpionShip () {
        if(!csDao.getProgressStatus()) {
            btnStarChampionship.setText("INICIAR CAMPEONATO");
        } else {
            btnStarChampionship.setText("ACESSAR CAMPEONATO EM ANDAMENTO");
            fabAddTeam.setEnabled(false);
        }

        btnStarChampionship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!csDao.getProgressStatus()) {
                    csDao.shuffleTeams(teamDao.getTeamsList());
                }

                startActivity(new Intent(ListTeamsActivity.this, ChampionshipMatchesActivity.class));
            }
        });
    }
}