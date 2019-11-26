package com.example.reviewer.GameLibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reviewer.R;
import com.example.reviewer.RoomDb.AppDatabase;
import com.example.reviewer.RoomDb.Models.Game;

import java.util.List;
import java.util.Random;

public class ManageUserGamesActivity extends AppCompatActivity {

    public static AppDatabase gameDb;

    private Button addGameBtn;
    private Button viewAllGamesBtn;
    private Button deleteAllBtn;
    private Button viewListBtn;
    private Button populateDbBtn;
    private TextView allGameText;
    private EditText gameName;
    private EditText gameDesc;
    private EditText gameImgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user_games);

        addGameBtn = findViewById(R.id.add_game_button);
        viewAllGamesBtn = findViewById(R.id.view_all_games_button);
        deleteAllBtn = findViewById(R.id.delete_all_games_button);
        viewListBtn = findViewById(R.id.view_list_button);
        populateDbBtn = findViewById(R.id.populate_db_button);

        gameName = findViewById(R.id.edit_game_name);
        gameDesc = findViewById(R.id.edit_game_description);
        gameImgs = findViewById(R.id.edit_game_img_urls);
        allGameText = findViewById(R.id.game_list_text);

        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        gameDb = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,
                "Game")
                .allowMainThreadQueries()
                .build();

        populateDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int gameId_1 = rand.nextInt(100000000),
                        gameId_2 = rand.nextInt(100000000),
                        gameId_3 = rand.nextInt(100000000),
                        gameId_4 = rand.nextInt(100000000),
                        gameId_5 = rand.nextInt(100000000),
                        gameId_6 = rand.nextInt(100000000),
                        gameId_7 = rand.nextInt(100000000),
                        gameId_8 = rand.nextInt(100000000),
                        gameId_9 = rand.nextInt(100000000),
                        gameId_10 = rand.nextInt(100000000);

                String name1 = getResources().getString(R.string.test_game_name_1),
                        name2 = getResources().getString(R.string.test_game_name_2),
                        name3 = getResources().getString(R.string.test_game_name_3),
                        name4 = getResources().getString(R.string.test_game_name_4),
                        name5 = getResources().getString(R.string.test_game_name_5),
                        name6 = getResources().getString(R.string.test_game_name_6),
                        name7 = getResources().getString(R.string.test_game_name_7),
                        name8 = getResources().getString(R.string.test_game_name_8),
                        name9 = getResources().getString(R.string.test_game_name_9),
                        name10 = getResources().getString(R.string.test_game_name_10);

                String desc1 = getResources().getString(R.string.test_game_desc_1),
                        desc2 = getResources().getString(R.string.test_game_desc_2),
                        desc3 = getResources().getString(R.string.test_game_desc_3),
                        desc4 = getResources().getString(R.string.test_game_desc_4),
                        desc5 = getResources().getString(R.string.test_game_desc_5),
                        desc6 = getResources().getString(R.string.test_game_desc_6),
                        desc7 = getResources().getString(R.string.test_game_desc_7),
                        desc8 = getResources().getString(R.string.test_game_desc_8),
                        desc9 = getResources().getString(R.string.test_game_desc_9),
                        desc10 = getResources().getString(R.string.test_game_desc_10);

                String[] imgs1 = {getResources().getString(R.string.game_1_img_1),
                        getResources().getString(R.string.game_1_img_2),
                        getResources().getString(R.string.game_1_img_3),
                        getResources().getString(R.string.game_1_img_4),
                        getResources().getString(R.string.game_1_img_5)};
                String[] imgs2 = {getResources().getString(R.string.game_2_img_1),
                        getResources().getString(R.string.game_2_img_2),
                        getResources().getString(R.string.game_2_img_3),
                        getResources().getString(R.string.game_2_img_4),
                        getResources().getString(R.string.game_2_img_5)};
                String[] imgs3 = {getResources().getString(R.string.game_3_img_1),
                        getResources().getString(R.string.game_3_img_2),
                        getResources().getString(R.string.game_3_img_3),
                        getResources().getString(R.string.game_3_img_4),
                        getResources().getString(R.string.game_3_img_5)};
                String[] imgs4 = {getResources().getString(R.string.game_4_img_1),
                        getResources().getString(R.string.game_4_img_2),
                        getResources().getString(R.string.game_4_img_3),
                        getResources().getString(R.string.game_4_img_4),
                        getResources().getString(R.string.game_4_img_5)};
                String[] imgs5 = {getResources().getString(R.string.game_5_img_1),
                        getResources().getString(R.string.game_5_img_2),
                        getResources().getString(R.string.game_5_img_3),
                        getResources().getString(R.string.game_5_img_4),
                        getResources().getString(R.string.game_5_img_5)};
                String[] imgs6 = {getResources().getString(R.string.game_6_img_1),
                        getResources().getString(R.string.game_6_img_2),
                        getResources().getString(R.string.game_6_img_3),
                        getResources().getString(R.string.game_6_img_4),
                        getResources().getString(R.string.game_6_img_5)};
                String[] imgs7 = {getResources().getString(R.string.game_7_img_1),
                        getResources().getString(R.string.game_7_img_2),
                        getResources().getString(R.string.game_7_img_3),
                        getResources().getString(R.string.game_7_img_4),
                        getResources().getString(R.string.game_7_img_5)};
                String[] imgs8 = {getResources().getString(R.string.game_8_img_1),
                        getResources().getString(R.string.game_8_img_2),
                        getResources().getString(R.string.game_8_img_3),
                        getResources().getString(R.string.game_8_img_4),
                        getResources().getString(R.string.game_8_img_5)};
                String[] imgs9 = {getResources().getString(R.string.game_9_img_1),
                        getResources().getString(R.string.game_9_img_2),
                        getResources().getString(R.string.game_9_img_3),
                        getResources().getString(R.string.game_9_img_4),
                        getResources().getString(R.string.game_9_img_5)};
                String[] imgs10 = {getResources().getString(R.string.game_10_img_1),
                        getResources().getString(R.string.game_10_img_2),
                        getResources().getString(R.string.game_10_img_3),
                        getResources().getString(R.string.game_10_img_4),
                        getResources().getString(R.string.game_10_img_5)};

                Game game_1 = new Game(gameId_1, name1, desc1, imgs1);
                Game game_2 = new Game(gameId_2, name2, desc2, imgs2);
                Game game_3 = new Game(gameId_3, name3, desc3, imgs3);
                Game game_4 = new Game(gameId_4, name4, desc4, imgs4);
                Game game_5 = new Game(gameId_5, name5, desc5, imgs5);
                Game game_6 = new Game(gameId_6, name6, desc6, imgs6);
                Game game_7 = new Game(gameId_7, name7, desc7, imgs7);
                Game game_8 = new Game(gameId_8, name8, desc8, imgs8);
                Game game_9 = new Game(gameId_9, name9, desc9, imgs9);
                Game game_10 = new Game(gameId_10, name10, desc10, imgs10);

                gameDb.gameDao().addGame(game_1);
                gameDb.gameDao().addGame(game_2);
                gameDb.gameDao().addGame(game_3);
                gameDb.gameDao().addGame(game_4);
                gameDb.gameDao().addGame(game_5);
                gameDb.gameDao().addGame(game_6);
                gameDb.gameDao().addGame(game_7);
                gameDb.gameDao().addGame(game_8);
                gameDb.gameDao().addGame(game_9);
                gameDb.gameDao().addGame(game_10);
            }
        });

        addGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int gameId = rand.nextInt(100000000);
                System.out.println("Key was: " + gameId);
                String name = gameName.getText().toString();
                String desc = gameDesc.getText().toString();

                String urlsInput = gameImgs.getText().toString();
                String[] urls = urlsInput.split(",");

                Game game = new Game(gameId, name, desc, urls);

                List<Game> currentGames = gameDb.gameDao().getAllGames();
                boolean exists = false;
                for(int i = 0; i < currentGames.size(); i++) {
                    //for each game already in list, check if duplicate exists
                    if(name.equals(currentGames.get(i).getName())
                    && desc.equals(currentGames.get(i).getDescription())) {
                        exists = true;
                    }
                }

                if(exists) {
                    Toast.makeText(ManageUserGamesActivity.this, "ERROR: " + name + " is already in your list!.", Toast.LENGTH_SHORT).show();
                } else {
                    gameDb.gameDao().addGame(game);
                    Toast.makeText(ManageUserGamesActivity.this, name + " added.", Toast.LENGTH_SHORT).show();
                }

                gameName.setText("");
                gameDesc.setText("");
                gameImgs.setText("");
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
                    String[] imgUrls = game.getImage_urls();

                    System.out.println("DEBUG id: " + id);
                    System.out.println("DEBUG name: " + name);
                    System.out.println("DEBUG desc: " + desc);

                    String urlString = "";
                    StringBuilder urlBuilder = new StringBuilder(urlString);

                    for(int i = 0; i < imgUrls.length; i++) {
                        System.out.println("DEBUG imgs: " + imgUrls[i]);
                        urlBuilder.append("Image ");
                        urlBuilder.append(i);
                        urlBuilder.append(":\n");
                        urlBuilder.append(imgUrls[i]);
                        urlBuilder.append("\n");
                        System.out.println("DEBUG URL STRING: " + urlString);
                    }

                    urlString = urlBuilder.toString();
                    System.out.println("DEBUG imgs after append loop: " + urlString);

                    infoBuilder.append(id);
                    infoBuilder.append(",\n");
                    infoBuilder.append(name);
                    infoBuilder.append(",\n");
                    infoBuilder.append(desc);
                    infoBuilder.append(",\n");
                    infoBuilder.append(urlString);
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
                                Toast.makeText(ManageUserGamesActivity.this, "All games deleted", Toast.LENGTH_SHORT).show();
                                gameDb.gameDao().deleteAll();

                                allGameText.setText("");
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        viewListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameList();
            }
        });
    }

    public void openGameList() {
        Intent intent = new Intent(this, GameListActivity.class);
        startActivity(intent);
    }
}
