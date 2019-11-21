package com.example.reviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.reviewer.models.GameInfoModel;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;



public class GameInfoActivity extends AppCompatActivity {

    CarouselView carouselView;
    GameInfoModel gameInfoModel;
    private TextView gameSummaryText;

    public int[] temp = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5};

//    public String[] images = new String[5];
//
//    gameInfoModel.setImageArray(images);

    String[] imageUrls = {"https://specials-images.forbesimg.com/imageserve/5dd3f75ee0af7b0006b1afe6/960x0.jpg?fit=scale",
            "https://cdn.vox-cdn.com/thumbor/KTeBCvpJ9zogaT9IHfyVpkqd5pU=/0x0:1920x1080/1200x675/filters:focal(859x0:1165x306)/cdn.vox-cdn.com/uploads/chorus_image/image/61407537/Hammer_Titan.0.jpg",
            "https://steamcdn-a.akamaihd.net/steam/apps/1085660/ss_01ba13f9c57e39fe11e5ec5dd3b13313af310f0e.1920x1080.jpg?t=1570039639",
            "https://specials-images.forbesimg.com/imageserve/5dcde98a2c886a0007ec286b/960x0.jpg?fit=scale",
            "https://specials-images.forbesimg.com/imageserve/5da07ab06763cb0006087f16/960x0.jpg?fit=scale" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);

        carouselView = (CarouselView) findViewById(R.id.game_screenshots);
        carouselView.setPageCount(temp.length);

        carouselView.setImageListener(imageListener);

//        gameSummaryText.setText(R.string.game_summary);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(temp[position]);
            Glide.with(imageView).
                    load(imageUrls[position]).
                    into(imageView);
        }
    };
}