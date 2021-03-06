package com.example.reviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.reviewer.Models.ObjectModelgetGameInfo;
import com.example.reviewer.Retrofit.INodeJS;
import com.example.reviewer.Retrofit.RetrofitClient;
import com.example.reviewer.RoomDb.AppDatabase;
import com.example.reviewer.RoomDb.Models.Game;
import com.example.reviewer.RoomDb.Models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class ReviewActivity extends AppCompatActivity implements PreviewDialog.PreviewDialogListener {

    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    private ImageButton exitButton, postReviewButton;

    private RatingBar ratingBar;

    private TextView gameTitle;

    private EditText reviewBody;

    private int gameRating = 0;
    private Game game = new Game();

    AppDatabase appDb;


    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("game_id");


        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);


        postReviewButton = findViewById(R.id.post_review_button);
        exitButton = findViewById(R.id.exit_button);

        ratingBar = findViewById(R.id.rating_bar);
        gameTitle = findViewById(R.id.game_title);      // get game title from callee
        reviewBody = findViewById(R.id.review_text);

        gameTitle.setText(name);


        appDb = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,
                "Game")
                .allowMainThreadQueries()
                .build();
        game = appDb.gameDao().getGame(name);


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        postReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameRating == 0) {
                    Toast toast = Toast.makeText(ReviewActivity.this, "Please rate game", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else if (reviewBody.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(ReviewActivity.this, "Please write a review", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else {
                    openPreviewDialog(gameTitle.getText().toString(), reviewBody.getText().toString(), gameRating, game.getGame_id());
                }
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                gameRating = (int)rating;
            }
        });


    }

    public void openPreviewDialog(String gameTitle, String reviewBody, int rating, int game_id) {
        PreviewDialog preview = PreviewDialog.newInstance(gameTitle, reviewBody, rating, game_id);
        preview.show(getSupportFragmentManager(), "preview dialog");
    }

    @Override
    public void applyTexts(int gameID, int rating, String review) {
        appDb = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,
                "user")
                .allowMainThreadQueries()
                .build();
        if(gameID != 0 && rating != 0 && !review.isEmpty()) {
            compositeDisposable.add(myAPI.postReview(gameID,
                                    appDb.userDao().getUserEmail(),
                                    appDb.userDao().getUserPass(),
                                    rating,
                                    review,
                                    appDb.userDao().getUserUid(),
                                    appDb.userDao().getUserName())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            Toast toast = Toast.makeText(ReviewActivity.this, s, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }));
        }
        finish();
    }

}
