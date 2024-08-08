package br.com.palloma.ballitchampionship.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.palloma.ballitchampionship.R;
import br.com.palloma.ballitchampionship.dao.ChanpionshipDAO;
import br.com.palloma.ballitchampionship.dao.TeamDAO;
import br.com.palloma.ballitchampionship.ui.adapter.MatchAdapter;

public class ChampionshipMatchesActivity extends AppCompatActivity {

    private RecyclerView listGames;
    private Button btNewStage;

    private final ChanpionshipDAO dao = new ChanpionshipDAO();
    private final TeamDAO teamDao = new TeamDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chanpionship_matches);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extras = getIntent().getExtras();
        boolean status = extras.getBoolean("status");
        if (status) {
            dao.shuffleTeams(teamDao.getTeamsList(), ChampionshipMatchesActivity.this);
        }
        dao.evaluateNewStage(ChampionshipMatchesActivity.this);
        setupViews();
        setupMatchesList ();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dao.evaluateNewStage(ChampionshipMatchesActivity.this);
        setupMatchesList();
    }

    public void setupViews () {
        listGames = findViewById(R.id.rv_championship_matches_list);
        btNewStage = findViewById(R.id.bt_championship_new_stage);
    }

    public void setupBtNewStage () {


        btNewStage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void setupMatchesList () {

        Intent intent = new Intent(ChampionshipMatchesActivity.this, MatchActivity.class);
        MatchAdapter adapter = new MatchAdapter(dao.getList(),ChampionshipMatchesActivity.this, intent);
        listGames.setLayoutManager(new LinearLayoutManager(this));
        listGames.setAdapter(adapter);

    }
}