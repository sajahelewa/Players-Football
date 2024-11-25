package com.example.playersfootball.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PlayerViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private int teamId;

    public PlayerViewModelFactory(Application application, int teamId) {
        this.application = application;
        this.teamId = teamId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PlayerViewModel.class)) {
            return (T) new PlayerViewModel(application, teamId);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

