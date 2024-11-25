package com.example.playersfootball.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "teams")
public class Team {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    // Constructor
    public Team(String name) {
        this.name = name;
    }

    public Team() {

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
}




