package br.com.palloma.ballitchampionship.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.palloma.ballitchampionship.R;
import br.com.palloma.ballitchampionship.dao.ChanpionshipDAO;
import br.com.palloma.ballitchampionship.dao.TeamDAO;
import br.com.palloma.ballitchampionship.model.Team;
import br.com.palloma.ballitchampionship.ui.activity.ChampionshipMatchesActivity;


public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private final List<Team> teams;
    private final Intent intent;
    private final Context contex;
    private final Boolean advrungh;

    public TeamAdapter(List<Team> teamList, Context context, Intent intent, Boolean advrungh) {
        this.teams = teamList;
        this.intent = intent;
        this.contex = context;
        this.advrungh = advrungh;
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {

        TextView teamName;
        TextView teamYear;
        TextView teamWarcry;
        TextView teamPosition;
        TextView teamPoints;
        CardView cardView;
        FloatingActionButton fabAdvrungh;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.tv_team_item_name_team);
            teamYear = itemView.findViewById(R.id.tv_team_item_team_year);
            teamWarcry = itemView.findViewById(R.id.tv_team_item_team_warcry);
            teamPosition = itemView.findViewById(R.id.tv_team_item_position_list);
            teamPoints = itemView.findViewById(R.id.tv_team_item_team_points);
            cardView = itemView.findViewById(R.id.cv_item_team);
            fabAdvrungh = itemView.findViewById(R.id.fab_team_item_advrungh);
        }
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_item, parent, false);
        TeamViewHolder tvh = new TeamViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ChanpionshipDAO dao = new ChanpionshipDAO();
        TeamDAO teamDAO = new TeamDAO();

        Integer posi = position +1;
        holder.teamPosition.setText(posi.toString());
        holder.teamName.setText(teams.get(position).getName());

        if (dao.getProgressStatus()) {
            holder.cardView.setClickable(false);
            if (advrungh) {
                                holder.teamYear.setText(teams.get(position).getPoints().toString());
                holder.fabAdvrungh.setVisibility(View.VISIBLE);
                holder.teamPoints.setVisibility(View.GONE);
                holder.fabAdvrungh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = teams.get(position).getId();
                        teamDAO.teamPoints(id, -10);
                        Toast.makeText(contex, "10 pontos retirados da equipe " + teamDAO.getTeam(id).getName() + ". Total de pontos: " + teamDAO.getTeam(id).getPoints().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                holder.teamPoints.setVisibility(View.VISIBLE);
                holder.teamYear.setText(teams.get(position).getYear().toString());
                holder.teamPoints.setText(teams.get(position).getPoints().toString());
                holder.teamWarcry.setText(teams.get(position).getWarCry());
            }
        } else {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    intent.putExtra("id", teams.get(position).getId());
                    contex.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }
}
