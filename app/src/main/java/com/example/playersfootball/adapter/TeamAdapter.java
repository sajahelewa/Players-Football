package com.example.playersfootball.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.playersfootball.R;
import com.example.playersfootball.data.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamHolder> {
    private List<Team> teams = new ArrayList<>();
    private OnItemClickListener listener;
    private OnDeleteClickListener deleteListener;
    private OnEditClickListener editListener;

    public TeamAdapter(List<Team> teams, OnItemClickListener listener, OnDeleteClickListener deleteListener, OnEditClickListener editListener) {
        this.teams = teams;
        this.listener = listener;
        this.deleteListener = deleteListener;
        this.editListener = editListener;
    }

    public TeamAdapter(List<Team> teams) {
        this.teams = teams;
    }

    @NonNull
    @Override
    public TeamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_item, parent, false);
        return new TeamHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamHolder holder, int position) {
        Team currentTeam = teams.get(position);
        holder.textViewTeamName.setText(currentTeam.getName());
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
        notifyDataSetChanged();
    }

    class TeamHolder extends RecyclerView.ViewHolder {
        private TextView textViewTeamName;
        private ImageView buttonDelete;
        private ImageView buttonEdit;

        public TeamHolder(View itemView) {
            super(itemView);
            textViewTeamName = itemView.findViewById(R.id.text_view_team_name);
            buttonDelete = itemView.findViewById(R.id.button_delete);
            buttonEdit = itemView.findViewById(R.id.button_edit);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(teams.get(position));
                }
            });

            buttonDelete.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (deleteListener != null && position != RecyclerView.NO_POSITION) {
                    deleteListener.onDeleteClick(teams.get(position));
                }
            });

            buttonEdit.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (editListener != null && position != RecyclerView.NO_POSITION) {
                    editListener.onEditClick(teams.get(position));
                }
            });

        }
    }


    public interface OnItemClickListener {
        void onItemClick(Team team);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Team team);
    }

    public interface OnEditClickListener {
        void onEditClick(Team team);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteListener = listener;
    }

    public void setOnEditClickListener(OnEditClickListener listener) {
        this.editListener = listener;
    }
}
