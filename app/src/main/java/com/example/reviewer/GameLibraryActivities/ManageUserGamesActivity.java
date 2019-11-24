package com.example.reviewer.GameLibraryActivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
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

public class ManageUserGamesActivity extends AppCompatActivity {

    public static AppDatabase gameDb;

    private Button addGameBtn;
    private Button viewAllGamesBtn;
    private Button deleteAllBtn;
    private TextView allGameText;
    private EditText gameName;
    private EditText gameDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user_games);

        addGameBtn = findViewById(R.id.add_game_button);
        viewAllGamesBtn = findViewById(R.id.view_all_games_button);
        deleteAllBtn = findViewById(R.id.delete_all_games_button);
        gameName = findViewById(R.id.edit_game_name);
        gameDesc = findViewById(R.id.edit_game_description);
        allGameText = findViewById(R.id.game_list_text);

        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

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
                Toast.makeText(ManageUserGamesActivity.this, name + " added.", Toast.LENGTH_SHORT).show();

                gameName.setText("");
                gameDesc.setText("");
            }
        });

        viewAllGamesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Game> games = gameDb.gameDao().getAllGames();

                String info = "";
                StringBuilder infoBuilder=  new StringBuilder(info);

                for(Game game : games) {
                    int id = game.getGame_id();
                    String name = game.getName();
                    String desc = game.getDescription();

                    System.out.println("DEBUG id: " + id);
                    System.out.println("DEBUG name: " + name);
                    System.out.println("DEBUG desc: " + desc);

                    infoBuilder.append(id);
                    infoBuilder.append(",\n");
                    infoBuilder.append(name);
                    infoBuilder.append(",\n");
                    infoBuilder.append(desc);
                    infoBuilder.append("\n\n");
                }

                info = infoBuilder.toString();
                System.out.println("DEBUG: game list " + info);

                allGameText.setText(info);
            }
        });

        deleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertBuilder.setTitle("DELETE GAME DATABASE CONFIRMATION")
                        .setMessage("Are you sure you want to delete all games in the database?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ManageUserGamesActivity.this, "Yaay", Toast.LENGTH_SHORT).show();
                                gameDb.gameDao().deleteAll();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }
}