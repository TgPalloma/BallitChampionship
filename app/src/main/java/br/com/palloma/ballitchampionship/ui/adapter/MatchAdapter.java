package br.com.palloma.ballitchampionship.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
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
        TextView tvTeamPointsA;
        TextView tvTeamPointsB;
        TextView tvMatchStage;
        Button btStart;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTeamPointsA = itemView.findViewById(R.id.tv_match_item_points_team_a);
            tvTeamPointsB = itemView.findViewById(R.id.tv_match_item_points_team_b);
            teamNameA = itemView.findViewById(R.id.tv_match_item_name_team_a);
            teamNameB = itemView.findViewById(R.id.tv_match_item_name_team_b);
            tvMatchStage = itemView.findViewById(R.id.tv_match_item_stage);
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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String stage = matches.get(position).getStage() + "ª ETAPA";

        holder.teamNameA.setText(matches.get(position).getTeamA().getName());
        holder.teamNameB.setText(matches.get(position).getTeamB().getName());
        holder.tvTeamPointsA.setText(matches.get(position).getTeamAPoints().toString());
        holder.tvTeamPointsB.setText(matches.get(position).getTeamBPoints().toString());
        holder.tvMatchStage.setText(stage);

        if (!matches.get(position).getActiveStatus()) {
//            holder.btStart.setText("FINALIZADA");
//            holder.btStart.setEnabled(false);
            holder.btStart.setVisibility(View.INVISIBLE);
        }

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
