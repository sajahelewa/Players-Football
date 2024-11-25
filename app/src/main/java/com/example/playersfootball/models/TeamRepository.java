package com.example.playersfootball.models;

import android.app.Application;

import androidx.lifecycle.LiveData;


import com.example.playersfootball.data.Team;

import java.util.List;

public class TeamRepository {
    private TeamDao teamDao;
    private LiveData<List<Team>> allTeams;

    public TeamRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        teamDao = database.teamDao();
        allTeams = teamDao.getAllTeams();
    }

    public void insert(Team team) {
        new Thread(() -> teamDao.insert(team)).start();
    }

    public void update(Team team) {
        new Thread(() -> teamDao.update(team)).start();
    }

    public void delete(Team team) {
        new Thread(() -> teamDao.delete(team)).start();
    }


    public LiveData<List<Team>> getAllTeams() {
        return allTeams;
    }
}
