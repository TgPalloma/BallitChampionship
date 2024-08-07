package br.com.palloma.ballitchampionship.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Time;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import br.com.palloma.ballitchampionship.R;
import br.com.palloma.ballitchampionship.dao.ChanpionshipDAO;
import br.com.palloma.ballitchampionship.dao.TeamDAO;
import br.com.palloma.ballitchampionship.model.Match;

public class MatchActivity extends AppCompatActivity {



    //Variáveis de campos e botões
    private Button btnBlotTeamA;
    private Button btnBlotTeamB;

    private Button btnPlifTeamA;
    private Button btnPlifTeamB;
    private Button btnAdvrungh;
    private Button btnEndMatch;

    private TextView tvPointsTeamA;
    private TextView tvPointsTeamB;

    private TextView tvNameTeamA;
    private TextView tvNameTeamB;

    private TextView countDown;

    Match match;
    CountDownTimer timer;

    //Variável para armazenar a posição da partida na lista
    private int position;

    ChanpionshipDAO daoChampionship = new ChanpionshipDAO();
    TeamDAO daoTeam = new TeamDAO();

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

        //Ao criar a Activity, o método pega o valor que recebe e aplica na posição, pegando os valores dessa posição.
        Bundle extras = getIntent().getExtras();
        position = extras.getInt("position");
        match = new Match(
                daoChampionship.getList().get(position).getTeamA(),
                daoChampionship.getList().get(position).getTeamA());
        setupViews();
        setupTeams();
        setTextPointsAndMatchEnd();
        setupClicsBlots();
        setupCliscsPlifts();
        setupEndMatch();
    }

    //Relacionando as views nos layouts
    public void setupViews () {
        tvNameTeamA = findViewById(R.id.tv_match_activity_name_team_a);
        tvNameTeamB = findViewById(R.id.tv_match_activity_name_team_b);

        btnBlotTeamA = findViewById(R.id.bt_matche_blot_team_a);
        btnBlotTeamB = findViewById(R.id.bt_matche_blot_team_b);

        btnPlifTeamA = findViewById(R.id.bt_matche_plif_team_a);
        btnPlifTeamB = findViewById(R.id.bt_matche_plif_team_b);

        tvPointsTeamA = findViewById(R.id.tv_point_team_a);
        tvPointsTeamB = findViewById(R.id.tv_point_team_b);

        btnAdvrungh = findViewById(R.id.bt_match_advrungh);
        btnEndMatch = findViewById(R.id.bt_match_end_match);

        countDown = findViewById(R.id.tv_timer);
    }

    //Aplica os nomes e pontos das equipes aos respectivos textviews
    public void setupTeams () {
        tvNameTeamA.setText(daoChampionship.getList().get(position).getTeamA().getName().toString());
        tvNameTeamB.setText(daoChampionship.getList().get(position).getTeamB().getName().toString());
        tvPointsTeamA.setText(match.getTeamAPoints().toString());
        tvPointsTeamB.setText(match.getTeamAPoints().toString());
    }

    public void setupClicsBlots () {

        btnBlotTeamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match.addBlotA();
                setTextPointsAndMatchEnd();

            }
        });

        btnBlotTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match.addBlotB();
                setTextPointsAndMatchEnd();
            }
        });
    }

    public void setupCliscsPlifts () {

        btnPlifTeamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match.addPlifA();
                setTextPointsAndMatchEnd();
            }
        });

        btnPlifTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match.addPlifB();
                setTextPointsAndMatchEnd();
                startTimer();
            }
        });
    }

    public void setTextPointsAndMatchEnd () {
        tvPointsTeamA.setText(match.getTeamAPoints().toString());
        tvPointsTeamB.setText(match.getTeamBPoints().toString());

        if (match.getTeamBPoints()==match.getTeamAPoints()) {
            btnEndMatch.setText("DESEMPATE!");
        } else {
            btnEndMatch.setText("FINALIZAR PARTIDA!");
        }
    }

    public void setupEndMatch () {
        btnEndMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void startTimer() {

        timer = new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Long hours = (millisUntilFinished / 1000) /3600;
                Long minutes = ((millisUntilFinished / 1000) % 3600) / 60;
                Long seconds = (millisUntilFinished / 1000) % 60;


                String timeFormated = String.format(Locale.getDefault(),"%02d:%02d:%02d", hours, minutes, seconds);
                countDown.setText(timeFormated);
            }



            @Override
            public void onFinish() {

            }
        };
    }

}