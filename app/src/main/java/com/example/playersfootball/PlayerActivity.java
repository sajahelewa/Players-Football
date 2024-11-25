package com.example.playersfootball;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.playersfootball.adapter.PlayerAdapter;
import com.example.playersfootball.models.AppDatabase;
import com.example.playersfootball.models.PlayerViewModel;
import com.example.playersfootball.models.PlayerViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlayerActivity extends AppCompatActivity {
    public static final String EXTRA_TEAM_ID = "com.example.EXTRA_TEAM_ID";

    private PlayerViewModel playerViewModel;
    FloatingActionButton fab;
    public static AppDatabase db;
    PlayerAdapter playerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        int teamId = getIntent().getIntExtra(EXTRA_TEAM_ID, -1);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_players);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        playerAdapter = new PlayerAdapter();
        recyclerView.setAdapter(playerAdapter);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mahasiswa").allowMainThreadQueries().build();

        playerAdapter.setOnDeleteClickListener(player -> {
            db.playerDao().delete(player);
            updatePlayerList(teamId);
        });

        playerAdapter.setOnEditClickListener(player -> {
            Intent intent = new Intent(PlayerActivity.this, AddEditPlayerActivity.class);
            intent.putExtra(AddEditPlayerActivity.EXTRA_ID, player.getId());
            intent.putExtra(AddEditPlayerActivity.EXTRA_NAME, player.getName());
            intent.putExtra(AddEditPlayerActivity.EXTRA_TEAM_ID, teamId);
            startActivity(intent);
        });

        playerViewModel = new ViewModelProvider(this, new PlayerViewModelFactory(getApplication(), teamId)).get(PlayerViewModel.class);
        playerViewModel.getPlayersByTeam().observe(this, players -> playerAdapter.setPlayers(players));

        fab = findViewById(R.id.fab_add_player);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(PlayerActivity.this, AddEditPlayerActivity.class);
            intent.putExtra(AddEditPlayerActivity.EXTRA_TEAM_ID, teamId);
            startActivity(intent);
        });
    }

    private void updatePlayerList(int teamId) {
        playerViewModel.getPlayersByTeam().observe(this, players -> playerAdapter.setPlayers(players));
    }
}
