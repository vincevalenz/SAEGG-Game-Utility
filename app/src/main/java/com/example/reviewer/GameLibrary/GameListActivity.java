package com.example.reviewer.GameLibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.reviewer.R;
import com.example.reviewer.RoomDb.AppDatabase;
import com.example.reviewer.RoomDb.Game;
import com.example.reviewer.models.GameInfoModel;

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

        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);


        List<Game> gameModels = gameDb.gameDao().getAllGames();

        System.out.println("DEBUG: GameModel[0] name is: " + gameModels.get(0).getName());

        GameViewAdapter gameViewAdapter = new GameViewAdapter(gameModels);
        recyclerView.setAdapter(gameViewAdapter);
    }
}
