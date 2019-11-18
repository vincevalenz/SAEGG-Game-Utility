package com.example.reviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;

public class ReviewActivity extends AppCompatActivity implements View.OnClickListener, Serializable {

    private ImageButton exitButton,
            oneStar,
            twoStar,
            threeStar,
            fourStar,
            fiveStar;
    private Button postReviewButton;

    private TextView gameTitle,
                    ratingDisplay;
    private EditText reviewText,
            reviewTitle;
    private Double rating = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        postReviewButton = findViewById(R.id.post_review_button);
        exitButton = findViewById(R.id.exit_button);
                    oneStar = findViewById(R.id.one_star_button);
                    twoStar = findViewById(R.id.two_star_button);
                    threeStar = findViewById(R.id.three_star_button);
                    fourStar = findViewById(R.id.four_star_button);
                    fiveStar = findViewById(R.id.five_star_button);
        gameTitle = findViewById(R.id.game_title);      // get game title from callee
        reviewText = findViewById(R.id.review_text);
        reviewTitle = findViewById(R.id.review_title_text);
        ratingDisplay = findViewById(R.id.rating_display_text);

        oneStar.setOnClickListener(this);
        twoStar.setOnClickListener(this);
        threeStar.setOnClickListener(this);
        fourStar.setOnClickListener(this);
        fiveStar.setOnClickListener(this);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        postReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserReview review = new UserReview(reviewText.getText().toString(),
                        reviewTitle.getText().toString(),
                        gameTitle.getText().toString(),
                        rating);

                Intent intent = new Intent(ReviewActivity.this, PostReviewActivity.class);

                intent.putExtra("user_review", review);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_star_button:
                if (rating == 1.0) rating = 1.0;
                else rating = 1.0;
                break;
            case R.id.two_star_button:
                if (rating == 2.0) rating = 1.0;
                else rating = 2.0;
                break;
            case R.id.three_star_button:
                if (rating == 3.0) rating = 1.0;
                else rating = 3.0;
                break;
            case R.id.four_star_button:
                if (rating == 4.0) rating = 1.0;
                else rating = 4.0;
                break;
            case R.id.five_star_button:
                if (rating == 5.0) rating = 1.0;
                else rating = 5.0;
                break;
        }
        ratingDisplay.setText(rating.toString());

    }
}
