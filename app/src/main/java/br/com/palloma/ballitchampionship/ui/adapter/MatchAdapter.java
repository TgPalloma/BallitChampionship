package br.com.palloma.ballitchampionship.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {

    private final List<Match> matches;
    private final Intent intent;
    private final Context contex;


    public MatchAdapter(List<Match> matchesList, Context context, Intent intent) {
        this.matches = matchesList;
        this.intent = intent;
        contex = context;
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
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_item, parent, false);
        MatchViewHolder mvh = new MatchViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.teamNameA.setText(matches.get(position).getTeamA().getName());
        holder.teamNameB.setText(matches.get(position).getTeamB().getName());

        holder.btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("position", position);
                contex.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }
}
