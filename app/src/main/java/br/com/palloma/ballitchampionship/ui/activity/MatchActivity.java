package br.com.palloma.ballitchampionship.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.palloma.ballitchampionship.R;
import br.com.palloma.ballitchampionship.dao.ChanpionshipDAO;

public class MatchActivity extends AppCompatActivity {

    private Button btnBlotTeamA;
    private Button btnBlotTeamB;

    private Button btnPlifTeamA;
    private Button btnPlifTeamB;

    private TextView tvPointsTeamA;
    private TextView tvPointsTeamB;

    private TextView tvNameTeamA;
    private TextView tvNameTeamB;

    private Integer pontosA = 50;
    private Integer pontosB = 50;

    private int position;

    ChanpionshipDAO daoChampionship = new ChanpionshipDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_match);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extras = getIntent().getExtras();
        position = extras.getInt("position");
        setupViews();
        setupTeams();
        setupClicsBlots();
        setupCliscsPlifts();
    }

    public void setupViews () {
        tvNameTeamA = findViewById(R.id.tv_match_activity_name_team_a);
        tvNameTeamB = findViewById(R.id.tv_match_activity_name_team_b);

        btnBlotTeamA = findViewById(R.id.bt_matche_blot_team_a);
        btnBlotTeamB = findViewById(R.id.bt_matche_blot_team_b);

        btnPlifTeamA = findViewById(R.id.bt_matche_plif_team_a);
        btnPlifTeamB = findViewById(R.id.bt_matche_plif_team_b);

        tvPointsTeamA = findViewById(R.id.tv_point_team_a);
        tvPointsTeamB = findViewById(R.id.tv_point_team_b);
    }

    public void setupTeams () {
        tvNameTeamA.setText(daoChampionship.getList().get(position).getTeamA().getName().toString());
        tvNameTeamB.setText(daoChampionship.getList().get(position).getTeamB().getName().toString());
        tvPointsTeamB.setText(pontosB.toString());
        tvPointsTeamA.setText(pontosA.toString());
    }

    public void setupClicsBlots () {

        btnBlotTeamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pontosA += 5;
                tvPointsTeamA.setText(pontosA.toString());
            }
        });

        btnBlotTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pontosB += 5;
                tvPointsTeamB.setText(pontosB.toString());
            }
        });
    }

    public void setupCliscsPlifts () {

        btnPlifTeamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pontosB += 1;
                tvPointsTeamB.setText(pontosB.toString());
            }
        });

        btnPlifTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pontosA += 1;
                tvPointsTeamA.setText(pontosA.toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Não faz nada
        super.onBackPressed();
    }
}