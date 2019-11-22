package com.example.reviewer.AdminActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reviewer.R;
import com.example.reviewer.RoomDb.AppDatabase;
import com.example.reviewer.RoomDb.Game;

import java.util.List;
import java.util.Random;

public class AddGameActivity extends AppCompatActivity {

    public static AppDatabase gameDb;

    private Button addGameBtn;
    private Button viewAllGamesBtn;
    private TextView allGameText;
    private EditText gameName;
    private EditText gameDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        addGameBtn = findViewById(R.id.add_game_button);
        viewAllGamesBtn = findViewById(R.id.view_all_games_button);
        gameName = findViewById(R.id.edit_game_name);
        gameDesc = findViewById(R.id.edit_game_description);
        allGameText = findViewById(R.id.game_list_text);

        gameDb = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,
                "Game")
                .allowMainThreadQueries()
                .build();

        addGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int gameId = rand.nextInt(100000000);
                System.out.println("Key was: " + gameId);
                String name = gameName.getText().toString();
                String desc = gameDesc.getText().toString();

                Game game = new Game();
                game.setGame_id(gameId);
                game.setName(name);
                game.setDescription(desc);

                gameDb.gameDao().addGame(game);
                Toast.makeText(AddGameActivity.this, name + " added.", Toast.LENGTH_SHORT).show();

                gameName.setText("");
                gameDesc.setText("");
            }
        });

        viewAllGamesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Game> games = gameDb.gameDao().getAllGames();

                String info = "";

                for(Game game : games) {
                    int id = game.getGame_id();
                    String name = game.getName();
                    String desc = game.getDescription();

                }

                allGameText.setText(info);
            }
        });
    }
}
