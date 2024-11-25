package com.example.playersfootball;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playersfootball.data.Team;
import com.example.playersfootball.models.AppDatabase;


import androidx.room.Room;


public class AddTeamActivity extends AppCompatActivity {
    Team team;
    private Button insertData;
    private EditText etNama;
    public static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_team);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mahasiswa").allowMainThreadQueries().build();

        insertData = findViewById(R.id.btInsert);
        etNama = findViewById(R.id.etName);

        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etNama.getText().toString().isEmpty()) {
                    team = new Team();
                    team.setName(etNama.getText().toString());
                    //Insert data in database
                    db.teamDao().insert(team);
                    startActivity(new Intent(AddTeamActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a valid name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
