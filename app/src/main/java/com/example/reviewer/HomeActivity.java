package com.example.reviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.reviewer.GameLibrary.ManageUserGamesActivity;
import com.example.reviewer.RoomDb.AppDatabase;
import com.example.reviewer.RoomDb.Models.Game;

public class HomeActivity extends AppCompatActivity {

    AppDatabase gameDb;

    Button rec_page_button,
            usr_profile_button,
            rev_game_button,
            admin_add_game_button,
            destiny_2_button,
            runescape_button,
            death_stranding_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rec_page_button = (Button)findViewById(R.id.rec_page_button);
        usr_profile_button = findViewById(R.id.user_profile_page_button);
        destiny_2_button = findViewById(R.id.destiny_2_button);
        runescape_button = findViewById(R.id.runescape_button);
        death_stranding_button = findViewById(R.id.death_stranding_button);
        rev_game_button = findViewById(R.id.review_game_button);
        admin_add_game_button = findViewById(R.id.admin_add_game_button);

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

        rev_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReviewPage();
            }
        });

        admin_add_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdminAddGamePage();
            }
        });

        destiny_2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameInfo(gameDb, "Destiny 2");
            }
        });

        runescape_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameInfo(gameDb, "Runescape");
            }
        });

        death_stranding_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameInfo(gameDb, "Death Stranding");
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

    public void openGameInfo(AppDatabase appDatabase, String name) {
        Intent intent = new Intent(this, GameInfoActivity.class);
        intent.putExtra("game_name", name);

        startActivity(intent);
    }

    public void openReviewPage() {
        Intent intent = new Intent(this, ReviewActivity.class);
        startActivity(intent);
    }

    public void openAdminAddGamePage() {
        Intent intent = new Intent(this, ManageUserGamesActivity.class);
        startActivity(intent);
    }
    // ... more navigation


}
