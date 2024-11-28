package com.example.playersfootball;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.playersfootball.adapter.TeamAdapter;
import com.example.playersfootball.data.Team;
import com.example.playersfootball.models.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView myRecyclerview;
    FloatingActionButton myFab,allFab;
    TeamAdapter teamAdapter;
    public static AppDatabase db;
    List<Team> listTeam = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        myRecyclerview = findViewById(R.id.myRecyclerview);

        myFab = findViewById(R.id.myFab);
        allFab = findViewById(R.id.allFab);


        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTeamActivity.class);
                startActivity(intent);
            }
        });

        allFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllPlayerActivity.class);
                startActivity(intent);
            }
        });

        initRecyclerView();
        fetchDataFromRoom();
    }

    private void fetchDataFromRoom() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mahasiswa").allowMainThreadQueries().build();
        db.teamDao().getAllTeams().observe(this, teams -> {
            listTeam = teams;
            teamAdapter.setTeams(listTeam);

            //just checking data from db
            for (int i = 0; i < listTeam.size(); i++) {
                Log.e("Aplikasi", listTeam.get(i).getName() + i);
            }
            Log.e("cek list", "" + listTeam.size());
        });
    }

    private void initRecyclerView() {
        myRecyclerview.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerview.setLayoutManager(llm);
        teamAdapter = new TeamAdapter(new ArrayList<>());
        myRecyclerview.setAdapter(teamAdapter);

        teamAdapter.setOnItemClickListener(team -> {
            Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
            intent.putExtra(PlayerActivity.EXTRA_TEAM_ID, team.getId());
            startActivity(intent);
        });

        teamAdapter.setOnDeleteClickListener(team -> {
            db.teamDao().delete(team);
        });

        teamAdapter.setOnEditClickListener(team -> {
            Intent intent = new Intent(MainActivity.this, AddEditTeamActivity.class);
            intent.putExtra(AddEditTeamActivity.EXTRA_ID, team.getId());
            intent.putExtra(AddEditTeamActivity.EXTRA_NAME, team.getName());
            startActivity(intent);
        });
    }
}
