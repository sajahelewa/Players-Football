package com.example.playersfootball.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.playersfootball.data.Player;

import java.util.List;

@Dao
public interface PlayerDao {
    @Insert
    void insert(Player player);

    @Update
    void update(Player player);

    @Delete
    void delete(Player player);

    @Query("SELECT * FROM players WHERE teamId = :teamId")
    LiveData<List<Player>> getPlayersByTeam(int teamId);

    @Query("SELECT * FROM players")
    LiveData<List<Player>> getAllPlayers();
}
