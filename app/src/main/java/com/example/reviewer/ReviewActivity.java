package com.example.reviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ReviewActivity extends AppCompatActivity {
    private ImageButton exitButton,
            oneStar,
            twoStar,
            threeStar,
            fourStar,
            fiveStar;
    private Button submitButton;
    private TextView gameTitle;
    private EditText reviewText,
            reviewTitle;
    private Double rating = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.submit_button);
        exitButton = findViewById(R.id.exit_button);
        oneStar = findViewById(R.id.one_star_button);
        twoStar = findViewById(R.id.two_star_button);
        threeStar = findViewById(R.id.three_star_button);
        fourStar = findViewById(R.id.four_star_button);
        fiveStar = findViewById(R.id.five_star_button);
        gameTitle = findViewById(R.id.game_title);      // get game title from callee
        reviewText = findViewById(R.id.review_text);
        reviewTitle = findViewById(R.id.review_title_text);

//        oneStar.setOnClickListener(this);
//        twoStar.setOnClickListener(this);
//        threeStar.setOnClickListener(this);
//        fourStar.setOnClickListener(this);
//        fiveStar.setOnClickListener(this);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewActivity.this, PostReviewActivity.class);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
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

//    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_star_button:
                rating = 1.0;
                break;
            case R.id.two_star_button:
                rating = 2.0;
                break;
            case R.id.three_star_button:
                rating = 3.0;
                break;
            case R.id.four_star_button:
                rating = 4.0;
                break;
            case R.id.five_star_button:
                rating = 5.0;
                break;
        }

    }
}
