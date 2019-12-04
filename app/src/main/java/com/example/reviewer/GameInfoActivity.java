package com.example.reviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.reviewer.GameLibrary.ManageUserGamesActivity;
import com.example.reviewer.RoomDb.AppDatabase;
import com.example.reviewer.RoomDb.Models.Game;
import com.example.reviewer.UserProfile.UserProfileActivity;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

public class GameInfoActivity extends AppCompatActivity{

    CarouselView carouselView;

    public String[] images = new String[5];
    public TextView gameTitle;
    public TextView gameDesc;

    Button userProfileBtn, addGameToListBtn;

    AppDatabase gameDb;
    Game game = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);

        Bundle bundle = getIntent().getExtras();
        final int id = bundle.getInt("game_id");
        final String name = bundle.getString("game_name"),
                desc = bundle.getString("game_desc") ,
                img_1 = bundle.getString("game_img_1"),
                img_2 = bundle.getString("game_img_2"),
                img_3 = bundle.getString("game_img_3"),
                img_4 = bundle.getString("game_img_4"),
                img_5 = bundle.getString("game_img_5");
        boolean fromRemote = bundle.getBoolean("from_remote");

        carouselView = findViewById(R.id.game_screenshots);
        gameTitle = findViewById(R.id.game_title);
        gameDesc = findViewById(R.id.game_summary);
        userProfileBtn = findViewById(R.id.info_page_user_profile_button);
        addGameToListBtn = findViewById(R.id.add_game_to_list_button);

        gameDb = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,
                "Game")
                .allowMainThreadQueries()
                .build();

        if(fromRemote) {
            images[0] = img_1;
            images[1] = img_2;
            images[2] = img_3;
            images[3] = img_4;
            images[4] = img_5;

            game.setGame_id(id);
            game.setName(name);
            game.setDescription(desc);
            game.setImage_urls(images);

            images = game.getImage_urls();
            gameTitle.setText(game.getName());
            gameDesc.setText(game.getDescription());

            carouselView.setPageCount(images.length);
            carouselView.setImageListener(imageListener);

        } else {
            game = gameDb.gameDao().getGame(name);

            if (images.length != 0) {
                images = game.getImage_urls();
            }
            gameTitle.setText(game.getName());
            gameDesc.setText(game.getDescription());

            carouselView.setPageCount(images.length);
            carouselView.setImageListener(imageListener);
        }

        userProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open user profile
                openUserProfile("Placeholder");
            }
        });

        addGameToListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add game to local list

                String gameName = name, gameDesc = desc;
                List<Game> currentGames = gameDb.gameDao().getAllGames();
                boolean exists = false;
                for(int i = 0; i < currentGames.size(); i++) {
                    //for each game already in list, check if duplicate exists
                    if(gameName.equals(currentGames.get(i).getName())
                            && gameDesc.equals(currentGames.get(i).getDescription())) {
                        exists = true;
                    }
                }

                if(exists) {
                    Toast.makeText(GameInfoActivity.this, "ERROR: " + name + " is already in your list!.", Toast.LENGTH_SHORT).show();
                } else {
                    gameDb.gameDao().addGame(game);
                    Toast.makeText(GameInfoActivity.this, name + " added.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Glide.with(imageView)
                    .load(images[position])
                    .placeholder(R.drawable.placeholder)
                    .into(imageView);
        }
    };

    private void openUserProfile(String userName) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra("user_name", userName);
        startActivity(intent);
    }


}