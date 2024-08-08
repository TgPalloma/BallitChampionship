package br.com.palloma.ballitchampionship.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

    private TextView tvCountDown;
    private TextView tvTittleTierbreaker;

    private CardView cvTierbreakerTeamA;
    private CardView cvTierbreakerTeamB;
    private CardView cvWinnerTeam;

    private TextView tvTierbreakerTeamAName;
    private TextView tvTierbreakerTeamBName;

    private TextView tvTierbreakerTeamADb;
    private TextView tvTierbreakerTeamBDb;

    private TextView tvWinnerWarcry;
    private TextView tvWinnerTeamName;

    private Match match;

    private boolean matchFinish;

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
                daoChampionship.getList().get(position).getTeamB(),
                daoChampionship.getList().get(position).getStage());

        match.setStartMatch();

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

        tvCountDown = findViewById(R.id.tv_countdown_timer);

        cvWinnerTeam = findViewById(R.id.cv_match_activity_winner_team);
        tvWinnerTeamName = findViewById(R.id.tv_match_activity_name_team_win);
        tvWinnerWarcry = findViewById(R.id.tv_match_activity_warcry_team_win);

    }

    //Aplica os nomes e pontos das equipes aos respectivos textviews
    public void setupTeams () {
        tvNameTeamA.setText(match.getTeamA().getName());
        tvNameTeamB.setText(match.getTeamB().getName());
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
            }
        });
    }

    public boolean tierbreakerVerify (Match match) {
        if (match.getTeamBPoints() == match.getTeamAPoints()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTextPointsAndMatchEnd () {
        tvPointsTeamA.setText(match.getTeamAPoints().toString());
        tvPointsTeamB.setText(match.getTeamBPoints().toString());

        if (tierbreakerVerify(match)) {
            btnEndMatch.setText("GRUSHT!");
        } else {
            btnEndMatch.setText("DEFINIR VENCEDOR");
        }
    }

    public void setupEndMatch () {
        btnEndMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!matchFinish) {
                    if(tierbreakerVerify(match)) {
                        setupTierbreakerViews();
                        setupTierbreakerTexts(match);
                        cvTierbreakerTeamA.setVisibility(View.VISIBLE);
                        cvTierbreakerTeamB.setVisibility(View.VISIBLE);
                        tvTittleTierbreaker.setVisibility(View.VISIBLE);
                        tvCountDown.setVisibility(View.VISIBLE);
                        tierbreaker(match);
                    } else {
                        setWinner(match);
                    }
                } else {
                    btnEndMatch.setText("ENCERRAR PARTIDA");
                    finish();

                }
            }
        });
    }

    public void setupTierbreakerViews () {
        cvTierbreakerTeamA = findViewById(R.id.cvnu_tierbreaker_team_a);
        cvTierbreakerTeamB = findViewById(R.id.cvnu_tierbreaker_team_b);

        tvTittleTierbreaker = findViewById(R.id.tv_match_activity_title_tiebreaker);
        tvCountDown = findViewById(R.id.tv_countdown_timer);

        tvTierbreakerTeamAName = findViewById(R.id.tv_match_activity_tierbreaker_name_team_a);
        tvTierbreakerTeamBName = findViewById(R.id.tv_match_activity_tierbreaker_name_team_b);

        tvTierbreakerTeamADb = findViewById(R.id.tv_match_activity_tierbreaker_name_team_a_db);
        tvTierbreakerTeamBDb = findViewById(R.id.tv_match_activity_tierbreaker_name_team_b_db);
    }

    public void setupTierbreakerTexts (Match match) {
        tvTierbreakerTeamAName.setText(match.getTeamA().getName());
        tvTierbreakerTeamBName.setText(match.getTeamB().getName());
    }

    public void tierbreaker (Match match) {

        disablePointsButtons();

        final Double[] randomDbA = new Double[1];
        final Double[] randomDbB = new Double[1];

        CountDownTimer timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                long minutes = ((millisUntilFinished / 1000) % 3600) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;

                String timeFomatted = String.format("%02d:%02d", minutes, seconds);
                tvCountDown.setText(timeFomatted);

                randomDbA[0] = 70.0 + (Math.random() * 10);
                randomDbB[0] = 70.0 + (Math.random() * 10);

                tvTierbreakerTeamADb.setText(String.format("%.2fdB", randomDbA[0]));
                tvTierbreakerTeamBDb.setText(String.format("%.2fdB", randomDbB[0]));
                btnEndMatch.setEnabled(false);

            }

            @Override
            public void onFinish() {
                tvCountDown.setText("00:00");
                if(randomDbA[0] > randomDbB[0]) {
                    match.resolveGrusht(true);
                } else {
                    match.resolveGrusht(false);
                }
                setTextPointsAndMatchEnd ();
                btnEndMatch.setEnabled(true);
                setWinner(match);
            }
        }.start();
    }

    public void disablePointsButtons () {

        btnAdvrungh.setEnabled(false);

        btnBlotTeamA.setEnabled(false);
        btnBlotTeamB.setEnabled(false);

        btnPlifTeamA.setEnabled(false);
        btnPlifTeamB.setEnabled(false);
    }

    public void setWinner (Match match) {

        disablePointsButtons();

        cvWinnerTeam.setVisibility(View.VISIBLE);

        if (match.getTeamAPoints() > match.getTeamBPoints()) {

            tvWinnerTeamName.setText(match.getTeamA().getName());
            tvWinnerWarcry.setText("\"" + match.getTeamA().getWarCry() + "\"");
        } else {
            tvWinnerTeamName.setText(match.getTeamB().getName());
            tvWinnerWarcry.setText("\"" + match.getTeamB().getWarCry() + "\"");
        }

        matchFinish = true;
        daoChampionship.saveEndOfMatch(match);
    }


}