package com.example.reviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button rec_page_button, usr_profile_button, game_info_button, rev_game_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rec_page_button = (Button)findViewById(R.id.rec_page_button);
        usr_profile_button = findViewById(R.id.user_profile_page_button);
        game_info_button = findViewById(R.id.game_info_page_button);
        rev_game_button = findViewById(R.id.review_game_button);

        rec_page_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecPage();
            }
        });

        usr_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserProfile();
            }
        });

        game_info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameInfo();
            }
        });

        rev_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReviewPage();
            }
        });
    }

    public void openRecPage() {
        Intent intent = new Intent(this, RecommendsActivity.class);
        startActivity(intent);
    }

    public void openUserProfile() {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    public void openGameInfo() {
        Intent intent = new Intent(this, GameInfoActivity.class);
        startActivity(intent);
    }

    public void openReviewPage() {
        Intent intent = new Intent(this, ReviewActivity.class);
        startActivity(intent);
    }
    // ... more navigation


}
