package com.example.playersfootball.models;

import android.app.Application;

import androidx.lifecycle.LiveData;


import com.example.playersfootball.data.Player;

import java.util.List;

public class PlayerRepository {
    private PlayerDao playerDao;
    private LiveData<List<Player>> playersByTeam;

    public PlayerRepository(Application application, int teamId) {
        AppDatabase database = AppDatabase.getInstance(application);
        playerDao = database.playerDao();
        playersByTeam = playerDao.getPlayersByTeam(teamId);
    }

    public void insert(Player player) {
        new Thread(() -> playerDao.insert(player)).start();
    }

    public void update(Player player) {
        new Thread(() -> playerDao.update(player)).start();
    }

    public void delete(Player player) {
        new Thread(() -> playerDao.delete(player)).start();
    }

    public LiveData<List<Player>> getPlayersByTeam() {
        return playersByTeam;
    }
}

