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
import com.example.playersfootball.models.AppDatabase;

public class AddEditPlayerActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.EXTRA_ID";
    public static final String EXTRA_NAME = "com.example.EXTRA_NAME";
    public static final String EXTRA_TEAM_ID = "com.example.EXTRA_TEAM_ID";

    private EditText editTextName;
    private int teamId;
    private int playerId = -1;
    public static AppDatabase db;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_player);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mahasiswa").allowMainThreadQueries().build();

        editTextName = findViewById(R.id.edit_text_name);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Player");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            playerId = intent.getIntExtra(EXTRA_ID, -1);
        } else {
            setTitle("Add Player");
        }

        teamId = intent.getIntExtra(EXTRA_TEAM_ID, -1);

        buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePlayer();
            }
        });
    }

    private void savePlayer() {
        String name = editTextName.getText().toString();

        if (name.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a name", Toast.LENGTH_SHORT).show();
            return;
        }

        Player player = new Player(name, teamId);
        if (playerId != -1) {
            player.setId(playerId);
            db.playerDao().update(player);
        } else {
            db.playerDao().insert(player);
        }

        Intent intent = new Intent(AddEditPlayerActivity.this, PlayerActivity.class);
        intent.putExtra(PlayerActivity.EXTRA_TEAM_ID, teamId);
        startActivity(intent);
        finish();
    }
}
