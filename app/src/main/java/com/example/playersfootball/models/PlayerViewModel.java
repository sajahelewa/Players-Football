package com.example.playersfootball.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.playersfootball.data.Player;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel {
    private PlayerRepository repository;
    private LiveData<List<Player>> playersByTeam;

    public PlayerViewModel(@NonNull Application application, int teamId) {
        super(application);
        repository = new PlayerRepository(application, teamId);
        playersByTeam = repository.getPlayersByTeam();
    }

    public void insert(Player player) {
        repository.insert(player);
    }

    public void update(Player player) {
        repository.update(player);
    }

    public void delete(Player player) {
        repository.delete(player);
    }

    public LiveData<List<Player>> getPlayersByTeam() {
        return playersByTeam;
    }
}

