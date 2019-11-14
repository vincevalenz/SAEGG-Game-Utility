package com.example.reviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PostReviewActivity extends AppCompatActivity {

    private TextView gameTitle,
            reviewTitle,
            reviewText,
            gameRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_review);

        gameTitle = findViewById(R.id.game_title);
        reviewText = findViewById(R.id.review_text);
        reviewTitle = findViewById(R.id.review_title_text);
        gameRating = findViewById(R.id.game_rating);

        UserReview review = (UserReview)getIntent().getSerializableExtra("user_review");

        if (review != null) {
            gameTitle.setText(review.getGameTitle());
            gameRating.setText(String.valueOf(review.getGameRating()));
            reviewTitle.setText(review.getReviewTitle());
            reviewText.setText(review.getReviewText());
        }
        else {
            gameTitle.setText("Exit page");
        }
    }
}
