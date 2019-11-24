package com.example.reviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.reviewer.RoomDb.Game;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class GameInfoActivity extends AppCompatActivity {

    CarouselView carouselView;
    Game game = new Game();
    public String[] images = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);

        images = game.getImage_urls();

        carouselView = (CarouselView) findViewById(R.id.game_screenshots);
        carouselView.setPageCount(images.length);

        carouselView.setImageListener(imageListener);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Glide.with(imageView).
                    load(images[position]).
                    into(imageView);
        }
    };
}