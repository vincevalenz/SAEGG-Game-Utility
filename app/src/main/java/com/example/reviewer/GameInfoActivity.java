package com.example.reviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.reviewer.RoomDb.AppDatabase;
import com.example.reviewer.RoomDb.Models.Game;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class GameInfoActivity extends AppCompatActivity{

    CarouselView carouselView;

    public String[] images = new String[5];
    public TextView gameTitle;
    public TextView gameDesc;

    AppDatabase gameDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);

        Bundle bundle = getIntent().getExtras();
        String gameToLoad = bundle.getString("game_name");

        gameDb = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,
                "Game")
                .allowMainThreadQueries()
                .build();

        Game game;
        game = gameDb.gameDao().getGame(gameToLoad);

        carouselView = findViewById(R.id.game_screenshots);
        gameTitle = findViewById(R.id.game_title);
        gameDesc = findViewById(R.id.game_summary);

        images = game.getImage_urls();
        gameTitle.setText(game.getName());
        gameDesc.setText(game.getDescription());

        carouselView.setPageCount(images.length);
        carouselView.setImageListener(imageListener);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Glide.with(imageView)
                    .load(images[position])
                    .placeholder(R.drawable.placeholder)
                    .into(imageView);
        }
    };


}