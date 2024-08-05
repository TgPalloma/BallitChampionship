package br.com.palloma.ballitchampionship.ui.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.palloma.ballitchampionship.R;
import br.com.palloma.ballitchampionship.model.Match;
import br.com.palloma.ballitchampionship.ui.activity.ChampionshipMatchesActivity;
import br.com.palloma.ballitchampionship.ui.activity.ListTeamsActivity;
import br.com.palloma.ballitchampionship.ui.activity.TeamRegisterActivity;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {

    List<Match> matches;

    public MatchAdapter(List<Match> matchesList) {
        this.matches = matchesList;
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder {

        TextView teamNameA;
        TextView teamNameB;
        Button btStart;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            teamNameA = itemView.findViewById(R.id.tv_match_item_name_team_a);
            teamNameB = itemView.findViewById(R.id.tv_match_item_name_team_b);
            btStart = itemView.findViewById(R.id.bt_start_match);
        }
    }

    @NonNull
    @Override
    public MatchAdapter.MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_item, parent, false);
        MatchViewHolder mvh = new MatchViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchAdapter.MatchViewHolder holder, int position) {
        holder.teamNameA.setText(matches.get(position).getTeamA().getName());
        holder.teamNameB.setText(matches.get(position).getTeamB().getName());
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }
}
