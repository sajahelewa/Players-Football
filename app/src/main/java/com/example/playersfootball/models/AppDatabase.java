package com.example.playersfootball.models;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.playersfootball.data.Player;
import com.example.playersfootball.data.Team;


// AppDatabase.java
@Database(entities = {Team.class, Player.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;


    public abstract TeamDao teamDao();
    public abstract PlayerDao playerDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "mahasiswa")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

