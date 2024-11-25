package com.example.playersfootball.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "players")
public class Player {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int teamId;

    // Constructor
    public Player(String name, int teamId) {
        this.name = name;
        this.teamId = teamId;
    }

    public Player() {

    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}


