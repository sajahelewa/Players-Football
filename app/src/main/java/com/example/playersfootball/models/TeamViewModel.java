package com.example.playersfootball.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.playersfootball.data.Team;

import java.util.List;

public class TeamViewModel extends AndroidViewModel {
    private TeamRepository repository;
    private LiveData<List<Team>> allTeams;

    public TeamViewModel(@NonNull Application application) {
        super(application);
        repository = new TeamRepository(application);
        allTeams = repository.getAllTeams();
    }

    public void insert(Team team) {
        repository.insert(team);
    }

    public void update(Team team) {
        repository.update(team);
    }

    public void delete(Team team) {
        repository.delete(team);
    }

    public LiveData<List<Team>> getAllTeams() {
        return allTeams;
    }
}

