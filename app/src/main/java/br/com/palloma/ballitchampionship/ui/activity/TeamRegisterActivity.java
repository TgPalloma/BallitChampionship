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

    /*A Activity de cadastro de equipes só pode ser acessada caso o numero de equipes já cadastrados
    *seja menor que 16 ou que o campeonato ainda não tenha iniciado. uma vez iniciado não
    *não há mais acessar essa activity pois o botão ficará bloqueado.*/

    //Declaração de Variáveis dos campos
    EditText etName;
    EditText etYear;
    EditText etWarCry;
    Button btRegister;
    Button btDelete;

    //Classe responsável por acessar o banco de dados (ainda in memoria)
    TeamDAO dao = new TeamDAO();

    /*Variável usada para pegar a posição na lista. O padrão 20 vem configurado para novos cadastros
    * caso seja diferente disso, a edição ou delete da equipe é habilitada e essa variável é reatribuida
    * para buscar a equipe cadastrada na lista*/
    int id = 51;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_register);

        setupViews();

        /*Recebe e avalia se alguma informação chegou da activity pai para habilitar edição ou
        *cadastrar um numero novo */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
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

    //Método que atribui os valores de uma equipe já existente aos campos de preenchimento para alteração.
    public void getDataTeam () {
        btRegister.setText("Salvar Alterações");
        btDelete.setVisibility(View.VISIBLE);
        etName.setText(dao.getTeam(id).getName());
        etYear.setText(dao.getTeam(id).getYear().toString());
        etWarCry.setText(dao.getTeam(id).getWarCry());
    }

    //Método onClick do botão de cadastro ou atualização
    public void registerTeam () {
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                Integer year = Integer.parseInt(etYear.getText().toString());
                String warCry = etWarCry.getText().toString();

                dao.save(name, year, warCry,id);
                finish();
            }
        });
    }

    //Método para deletar uma equipe.
    public void deleteTeam() {
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.deleteTeam(id);
                finish();
            }
        });
    }
}