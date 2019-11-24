package com.example.reviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.reviewer.models.GameInfoModel;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameInfoActivity extends AppCompatActivity {

    CarouselView carouselView;
    GameInfoModel game = new GameInfoModel();
    public String[] images = new String[5];
    public int imgListSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);

        images = game.getImageUrls();

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