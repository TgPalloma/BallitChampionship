package br.com.palloma.ballitchampionship.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.palloma.ballitchampionship.R;
import br.com.palloma.ballitchampionship.model.Team;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private final List<Team> teams;
    private final Intent intent;
    private final Context contex;

    public TeamAdapter(List<Team> teamList, Context context, Intent intent) {
        this.teams = teamList;
        this.intent = intent;
        contex = context;
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {

        TextView teamName;
        TextView teamYear;
        TextView teamWarcry;
        TextView teamPosition;
        CardView cardView;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.tv_team_item_name_team);
            teamYear = itemView.findViewById(R.id.tv_team_item_team_year);
            teamWarcry = itemView.findViewById(R.id.tv_team_item_team_warcry);
            teamPosition = itemView.findViewById(R.id.tv_team_item_position_list);
            cardView = itemView.findViewById(R.id.cv_item_team);
        }
    }

    @NonNull
    @Override
    public TeamAdapter.TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_item, parent, false);
        TeamViewHolder tvh = new TeamViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.TeamViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Integer posi = position +1;
        holder.teamPosition.setText(posi.toString());
        holder.teamName.setText(teams.get(position).getName());
        holder.teamYear.setText(teams.get(position).getYear().toString());
        holder.teamWarcry.setText(teams.get(position).getWarCry());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id", teams.get(position).getId());
                contex.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }
}
