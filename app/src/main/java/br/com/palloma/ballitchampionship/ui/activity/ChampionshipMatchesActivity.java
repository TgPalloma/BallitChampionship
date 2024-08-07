package br.com.palloma.ballitchampionship.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.palloma.ballitchampionship.R;
import br.com.palloma.ballitchampionship.dao.ChanpionshipDAO;
import br.com.palloma.ballitchampionship.model.Match;
import br.com.palloma.ballitchampionship.ui.adapter.MatchAdapter;

public class ChampionshipMatchesActivity extends AppCompatActivity {

    private RecyclerView listGames;

    private final ChanpionshipDAO dao = new ChanpionshipDAO();

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

        setupViews();
        setupMatchesList ();
    }

    public void setupViews () {
        listGames = findViewById(R.id.lv_championship_matches_list);
    }

    public void setupMatchesList () {
        Intent intent = new Intent(ChampionshipMatchesActivity.this, MatchActivity.class);
        MatchAdapter adapter = new MatchAdapter(dao.getList(),ChampionshipMatchesActivity.this, intent);
        listGames.setLayoutManager(new LinearLayoutManager(this));
        listGames.setAdapter(adapter);

    }
}