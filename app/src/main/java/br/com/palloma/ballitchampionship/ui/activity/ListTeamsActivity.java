package br.com.palloma.ballitchampionship.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.palloma.ballitchampionship.R;
import br.com.palloma.ballitchampionship.dao.TeamDAO;
import br.com.palloma.ballitchampionship.model.Team;

public class ListTeamsActivity extends AppCompatActivity {

    ListView listTeam;
    FloatingActionButton fabAddTeam;

    private final TeamDAO dao = new TeamDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_teams);
        setupViews();
        setupListView();
        addTeam();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupListView();
    }

    public void setupViews() {
        listTeam = findViewById(R.id.rv_team_list);
        fabAddTeam = findViewById(R.id.fab_add_team);
    }

    public void setupListView () {
        listTeam.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.getTeamsList()));
    }

    public void addTeam() {
        fabAddTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListTeamsActivity.this, TeamRegisterActivity.class));
            }
        });
    }
}