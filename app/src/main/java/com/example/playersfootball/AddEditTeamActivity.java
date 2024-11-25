package com.example.playersfootball;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.playersfootball.data.Player;
import com.example.playersfootball.data.Team;
import com.example.playersfootball.models.AppDatabase;



public class AddEditTeamActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.EXTRA_ID";
    public static final String EXTRA_NAME = "com.example.EXTRA_NAME";

    private EditText editTextName;
    private int teamId;
    public static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_team);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mahasiswa").allowMainThreadQueries().build();

        editTextName = findViewById(R.id.edit_text_name);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Team");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            teamId = intent.getIntExtra(EXTRA_ID, -1);
        } else {
            setTitle("Add Team");
        }

        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTeam();
            }
        });
    }

    private void saveTeam() {
        String name = editTextName.getText().toString();

        if (name.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a name", Toast.LENGTH_SHORT).show();
            return;
        }

        Team team = new Team(name);
        if (teamId != -1) {
            team.setId(teamId);
            db.teamDao().update(team);
        } else {
            db.teamDao().insert(team);
        }

        Intent intent = new Intent(AddEditTeamActivity.this, MainActivity.class);
        startActivity(intent);
        finish();



    }
}
