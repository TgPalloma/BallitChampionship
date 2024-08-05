package br.com.palloma.ballitchampionship.ui.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.palloma.ballitchampionship.R;
import br.com.palloma.ballitchampionship.dao.TeamDAO;
import br.com.palloma.ballitchampionship.model.Team;

public class TeamRegisterActivity extends AppCompatActivity {

    //Declaração de Variáveis dos campos
    EditText etName;
    EditText etYear;
    EditText etWarCry;
    Button btRegister;
    Button btDelete;

    //Classe responsável por acessar o banco de dados (ainda in memoria)
    TeamDAO dao = new TeamDAO();

    int position = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_register);

        setupViews();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("position");
            getDataTeam();
            deleteTeam();
        }
        registerTeam();
    }

    //Método de definição dos campos do formulario com variáveis.
    public void setupViews () {
        etName = findViewById(R.id.et_team_name);
        etYear = findViewById(R.id.et_team_year);
        etWarCry = findViewById(R.id.et_team_warcry);
        btRegister = findViewById(R.id.bt_register_team);
        btDelete = findViewById(R.id.bt_delete_team);

    }

    public void getDataTeam () {
        btRegister.setText("Salvar Alterações");
        btDelete.setVisibility(View.VISIBLE);
        etName.setText(dao.getTeam(position).getName());
        etYear.setText(dao.getTeam(position).getYear().toString());
        etWarCry.setText(dao.getTeam(position).getWarCry());
    }

    //Método onClick do botão de cadastro
    public void registerTeam () {
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                Integer year = Integer.parseInt(etYear.getText().toString());
                String warCry = etWarCry.getText().toString();

                dao.save(name, year, warCry,position);
                finish();
            }
        });
    }

    public void deleteTeam() {
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.deleteTeam(position);
                finish();
            }
        });
    }
}