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

public class MatcheActivity extends AppCompatActivity {

    Button btnBlotTeamA;
    Button btnBlotTeamB;

    Button btnPlifTeamA;
    Button btnPlifTeamB;

    TextView tvPointsTeamA;
    TextView tvPointsTeamB;

    Integer pontosA = 0;
    Integer pontosB = 0;

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
        setTitle("Partida");
        setupViews();
        setupClicsBlots();
        setupCliscsPlifts();
    }

    public void setupViews () {
        btnBlotTeamA = findViewById(R.id.bt_matche_blot_team_a);
        btnBlotTeamB = findViewById(R.id.bt_matche_blot_team_b);

        btnPlifTeamA = findViewById(R.id.bt_matche_plif_team_a);
        btnPlifTeamB = findViewById(R.id.bt_matche_plif_team_b);

        tvPointsTeamA = findViewById(R.id.tv_point_team_a);
        tvPointsTeamB = findViewById(R.id.tv_point_team_b);
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
}