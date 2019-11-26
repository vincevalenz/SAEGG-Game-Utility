package com.example.reviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reviewer.Retrofit.INodeJS;
import com.example.reviewer.Retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import java.util.Locale;

public class ReviewActivity extends AppCompatActivity implements View.OnClickListener, PreviewDialog.PreviewDialogListener {

    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    private ImageButton exitButton,
                        oneStar,
                        twoStar,
                        threeStar,
                        fourStar,
                        fiveStar;
    private Button postReviewButton;

    private TextView gameTitle,
                     ratingDisplay;
    private EditText reviewBody;
    private int rating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);

        postReviewButton = findViewById(R.id.post_review_button);
        exitButton = findViewById(R.id.exit_button);
        oneStar = findViewById(R.id.one_star_button);
        twoStar = findViewById(R.id.two_star_button);
        threeStar = findViewById(R.id.three_star_button);
        fourStar = findViewById(R.id.four_star_button);
        fiveStar = findViewById(R.id.five_star_button);
        gameTitle = findViewById(R.id.game_title);      // get game title from callee
        reviewBody = findViewById(R.id.review_text);
        ratingDisplay = findViewById(R.id.rating_display_text);

        oneStar.setOnClickListener(this);
        twoStar.setOnClickListener(this);
        threeStar.setOnClickListener(this);
        fourStar.setOnClickListener(this);
        fiveStar.setOnClickListener(this);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(ReviewActivity.this, HomeActivity.class);
                //startActivity(intent);
                finish();
            }
        });

        postReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rating != 0)
                    openPreviewDialog(gameTitle.getText().toString(), reviewBody.getText().toString(), rating);
                else {
                    Toast toast = Toast.makeText(ReviewActivity.this, "Please rate game", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_star_button:
                if (rating == 1) rating = 0;
                else rating = 1;
                break;
            case R.id.two_star_button:
                if (rating == 2) rating = 0;
                else rating = 2;
                break;
            case R.id.three_star_button:
                if (rating == 3) rating = 0;
                else rating = 3;
                break;
            case R.id.four_star_button:
                if (rating == 4) rating = 0;
                else rating = 4;
                break;
            case R.id.five_star_button:
                if (rating == 5) rating = 0;
                else rating = 5;
                break;
        }
        if (rating == 0) {
            ratingDisplay.setText("");
            return;
        }
        ratingDisplay.setText(String.format(Locale.ENGLISH, "%d", rating));
    }

    public void openPreviewDialog(String gameTitle, String reviewBody, int rating) {
        PreviewDialog preview = PreviewDialog.newInstance(gameTitle, reviewBody, rating);
        preview.show(getSupportFragmentManager(), "preview dialog");
    }

    @Override
    public void applyTexts(int gameID, String email, String password, int rating, String review) {
        // set params
        if(gameID != 0 && email != null && password != null && rating != 0 && review != null) {
            postReview(gameID, email, password, rating, review);
        }
    }

    public void postReview(int gameID, String email, String password, int rating, String review) {
        compositeDisposable.add(myAPI.postReview(gameID, email, password, rating, review)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(ReviewActivity.this, "" + s, Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}
