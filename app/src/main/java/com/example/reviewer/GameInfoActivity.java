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
        int id = bundle.getInt("game_id");
        String name = bundle.getString("game_name");
        String desc = bundle.getString("game_desc");
        String img_1 = bundle.getString("game_img_1");
        String img_2 = bundle.getString("game_img_2");
        String img_3 = bundle.getString("game_img_3");
        String img_4 = bundle.getString("game_img_4");
        String img_5 = bundle.getString("game_img_5");
        boolean fromRemote = bundle.getBoolean("from_remote");

        carouselView = findViewById(R.id.game_screenshots);
        gameTitle = findViewById(R.id.game_title);
        gameDesc = findViewById(R.id.game_summary);

        if(fromRemote) {
            images[0] = img_1;
            images[1] = img_2;
            images[2] = img_3;
            images[3] = img_4;
            images[4] = img_5;
            Game game = new Game(id, name, desc, images);

            images = game.getImage_urls();
            gameTitle.setText(game.getName());
            gameDesc.setText(game.getDescription());

            carouselView.setPageCount(images.length);
            carouselView.setImageListener(imageListener);

        } else {
            gameDb = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class,
                    "Game")
                    .allowMainThreadQueries()
                    .build();

            Game game;
            game = gameDb.gameDao().getGame(name);

            if (images.length != 0) {
                images = game.getImage_urls();
            }
            gameTitle.setText(game.getName());
            gameDesc.setText(game.getDescription());

            carouselView.setPageCount(images.length);
            carouselView.setImageListener(imageListener);
        }

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