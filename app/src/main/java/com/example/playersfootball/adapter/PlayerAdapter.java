package com.example.playersfootball.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playersfootball.R;
import com.example.playersfootball.data.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerHolder> {
    private List<Player> players = new ArrayList<>();
    private OnItemClickListener listener;
    private OnDeleteClickListener deleteListener;
    private OnEditClickListener editListener;

    public PlayerAdapter() {
        // Constructor
    }

    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_item, parent, false);
        return new PlayerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder holder, int position) {
        Player currentPlayer = players.get(position);
        holder.textViewPlayerName.setText(currentPlayer.getName());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    class PlayerHolder extends RecyclerView.ViewHolder {
        private TextView textViewPlayerName;
        private ImageView buttonDelete;
        private ImageView buttonEdit;

        public PlayerHolder(View itemView) {
            super(itemView);
            textViewPlayerName = itemView.findViewById(R.id.text_view_team_name);
            buttonDelete = itemView.findViewById(R.id.button_delete);
            buttonEdit = itemView.findViewById(R.id.button_edit);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(players.get(position));
                }
            });

            buttonDelete.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (deleteListener != null && position != RecyclerView.NO_POSITION) {
                    deleteListener.onDeleteClick(players.get(position));
                }
            });

            buttonEdit.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (editListener != null && position != RecyclerView.NO_POSITION) {
                    editListener.onEditClick(players.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Player player);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Player player);
    }

    public interface OnEditClickListener {
        void onEditClick(Player player);
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
