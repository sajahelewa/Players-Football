package com.example.playersfootball.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.playersfootball.data.Team;

import java.util.List;

// TeamDao.java
@Dao
public interface TeamDao {
    @Insert
    void insert(Team team);

    @Update
    void update(Team team);

    @Delete
    void delete(Team team);

    @Query("SELECT * FROM teams")
    LiveData<List<Team>> getAllTeams();
}




