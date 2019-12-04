package com.example.reviewer.GameLibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.example.reviewer.GameInfoActivity;
import com.example.reviewer.R;
import com.example.reviewer.RoomDb.AppDatabase;
import com.example.reviewer.RoomDb.Models.Game;

import java.util.List;

public class GameListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static AppDatabase gameDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        gameDb = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,
                "Game")
                .allowMainThreadQueries()
                .build();

        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);

        List<Game> gameModels = gameDb.gameDao().getAllGames();
        GameViewAdapter gameViewAdapter;

        OnRecyclerItemClickListener listener = new OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(Game game) {
                System.out.println("GAME PRESSED: " + game.getName());
                openGameInfo(game.getGame_id(),
                        game.getName(),
                        game.getDescription(),
                        game.getImage_urls()[0],
                        game.getImage_urls()[1],
                        game.getImage_urls()[2],
                        game.getImage_urls()[3],
                        game.getImage_urls()[4],
                        true); // location 1 is remote
                return;
            }
        };

        gameViewAdapter = new GameViewAdapter(gameModels, listener);
        recyclerView.setAdapter(gameViewAdapter);
    }

    private void openGameInfo(int id, String name, String description, String img1, String img2, String img3, String img4, String img5, boolean fromRemote) {
        Intent intent = new Intent(this, GameInfoActivity.class);
        intent.putExtra("game_id", id);
        intent.putExtra("game_name", name);
        intent.putExtra("game_desc", description);
        intent.putExtra("game_img_1", img1);
        intent.putExtra("game_img_2", img2);
        intent.putExtra("game_img_3", img3);
        intent.putExtra("game_img_4", img4);
        intent.putExtra("game_img_5", img5);
        intent.putExtra("from_remote", fromRemote);// 0 for local, 1 for remote
        startActivity(intent);
    }

}
