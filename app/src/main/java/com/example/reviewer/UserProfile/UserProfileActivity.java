package com.example.reviewer.UserProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.reviewer.GameLibrary.GameListActivity;
import com.example.reviewer.R;
import com.example.reviewer.RecommendsActivity;

import org.w3c.dom.Text;

public class UserProfileActivity extends AppCompatActivity {

    TextView userNameText;
    private Button mySavedGames, myReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Bundle bundle = getIntent().getExtras();
        String userName = bundle.getString("user_name");

        mySavedGames = findViewById(R.id.mySavedGames);
        myReviews = findViewById(R.id.myReviews);
        userNameText = findViewById(R.id.user_profile_user_name_text);

        userNameText.setText(userName);

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
