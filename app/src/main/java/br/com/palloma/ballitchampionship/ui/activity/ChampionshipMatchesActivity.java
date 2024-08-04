package br.com.palloma.ballitchampionship.ui.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.palloma.ballitchampionship.R;
import br.com.palloma.ballitchampionship.dao.ChanpionshipDAO;
import br.com.palloma.ballitchampionship.model.Team;

public class ChampionshipMatchesActivity extends AppCompatActivity {

    private ListView listGames;

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
        matchesListSetup ();
    }

    public void setupViews () {
        listGames = findViewById(R.id.lv_championship_matches_list);
    }

    public void matchesListSetup () {
        listGames.setAdapter(new ArrayAdapter<Team>(this, android.R.layout.simple_list_item_1, dao.getList()));
    }


}