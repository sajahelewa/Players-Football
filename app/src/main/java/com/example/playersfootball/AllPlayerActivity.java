package com.example.playersfootball;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
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

public class AllPlayerActivity extends AppCompatActivity {
    private PlayerViewModel playerViewModel;
    public static final String EXTRA_TEAM_ID = "com.example.EXTRA_TEAM_ID";
    private RecyclerView recyclerViewPlayers;
    private FloatingActionButton fab;
    private PlayerAdapter playerAdapter;
    public static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_player);
        int teamId = getIntent().getIntExtra(EXTRA_TEAM_ID, -1);


        recyclerViewPlayers = findViewById(R.id.recyclerViewPlayers);
        recyclerViewPlayers.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPlayers.setHasFixedSize(true);

        playerAdapter = new PlayerAdapter();
        recyclerViewPlayers.setAdapter(playerAdapter);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mahasiswa").allowMainThreadQueries().build();

        playerViewModel = new ViewModelProvider(this, new PlayerViewModelFactory(getApplication(), -1)).get(PlayerViewModel.class);
        playerViewModel.getAllPlayers().observe(this, players -> playerAdapter.setPlayers(players));

        playerAdapter.setOnDeleteClickListener(player -> {
            db.playerDao().delete(player);
//            playerAdapter.setPlayers(db.playerDao().getAllPlayers());
        });

        playerAdapter.setOnEditClickListener(player -> {
            Intent intent = new Intent(AllPlayerActivity.this, AddEditPlayerActivity.class);
            intent.putExtra(AddEditPlayerActivity.EXTRA_ID, player.getId());
            intent.putExtra(AddEditPlayerActivity.EXTRA_NAME, player.getName());
            intent.putExtra(AddEditPlayerActivity.EXTRA_TEAM_ID, teamId);
            startActivity(intent);
        });

        playerViewModel = new ViewModelProvider(this, new PlayerViewModelFactory(getApplication(), teamId)).get(PlayerViewModel.class);
        playerViewModel.getPlayersByTeam().observe(this, players -> playerAdapter.setPlayers(players));

        fab = findViewById(R.id.fab_add_player);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(AllPlayerActivity.this, AddEditPlayerActivity.class);
            intent.putExtra("origin", "AllPlayerActivity");
            startActivity(intent);

        });

    }

}
