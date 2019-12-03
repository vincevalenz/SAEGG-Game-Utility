package com.example.reviewer.UserProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.reviewer.GameLibrary.GameListActivity;
import com.example.reviewer.R;
import com.example.reviewer.RecommendsActivity;

public class UserProfileActivity extends AppCompatActivity {

    private Button mySavedGames, myReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mySavedGames = findViewById(R.id.mySavedGames);
        myReviews = findViewById(R.id.myReviews);

        mySavedGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserSavedGamesPage();
            }
        });

        myReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserReviewsPage();
            }
        });
    }

    public void openUserSavedGamesPage() {
        Intent intent = new Intent(this, GameListActivity.class);
        startActivity(intent);
    }

    public void openUserReviewsPage() {
        Intent intent = new Intent(this, UserReviews.class);
        startActivity(intent);
    }
}
