package br.com.palloma.ballitchampionship.ui.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.palloma.ballitchampionship.R;
import br.com.palloma.ballitchampionship.dao.TeamDAO;

public class TeamRegisterActivity extends AppCompatActivity {

    //Declaração de Variáveis dos campos
    EditText edName;
    EditText edYear;
    EditText edWarCry;
    Button btRegister;

    TeamDAO dao = new TeamDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_register);

        setupViews();
    }

    //Método de definição dos campos do formulario com variáveis.
    public void setupViews () {
        edName = findViewById(R.id.et_team_name);
        edYear = findViewById(R.id.et_team_year);
        edWarCry = findViewById(R.id.et_team_warcry);
        btRegister = findViewById(R.id.bt_register_team);
    }

    public void registerTeam () {

    }

    //Método onClick do botão de cadastro
    public void onClickBtn(View view) {

        String name = edName.getText().toString();
        int year = Integer.parseInt(edYear.getText().toString());
        String warCry = edWarCry.getText().toString();

        dao.save(name, year, warCry);

        startActivity(new Intent(TeamRegisterActivity.this, ListTeamsActivity.class));
    }
}