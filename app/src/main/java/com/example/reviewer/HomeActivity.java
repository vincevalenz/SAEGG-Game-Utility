package com.example.reviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.reviewer.GameLibrary.ManageUserGamesActivity;
<<<<<<< HEAD

import com.example.reviewer.RoomDb.AppDatabase;

import com.example.reviewer.UserProfile.UserProfileActivity;
import com.example.reviewer.Models.ObjectModelgetSelfInfo;
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

public class HomeActivity extends AppCompatActivity {



    private INodeJS myAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private String email, password;

    AppDatabase gameDb,
                userDb;
    User user;
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


        appDb = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,
                "user")
                .allowMainThreadQueries()
                .build();

       String name = appDb.userDao().getUserName();
       System.out.println(name);
       String email = appDb.userDao().getUserEmail();
        System.out.println(email);
       String pass = appDb.userDao().getUserPass();
        System.out.println(pass);
        Bundle extras = getIntent().getExtras();
        email = extras.getString("email");
        password = extras.getString("password");

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);


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
                loadUserInfoToDb(email, password);

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
                openGameInfo(appDb, "Destiny 2");
            }
        });

        runescape_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameInfo(appDb, "Runescape");
            }
        });

        death_stranding_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameInfo(appDb, "Death Stranding");
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

    private void loadUserInfoToDb(final String email, final String password) {

        compositeDisposable.add(myAPI.getSelfInfo(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("TESTING S VALUE", s);
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<ObjectModelgetSelfInfo>>(){}.getType();
                        List<ObjectModelgetSelfInfo> postsSelf = gson.fromJson(s, listType);


                        user = new User(1,
                                email,
                                password,
                                postsSelf.get(0).getName(),
                                postsSelf.get(0).getUnique_id());
                        userDb = Room.databaseBuilder(getApplicationContext(),
                                AppDatabase.class,
                                "user")
                                .allowMainThreadQueries()
                                .build();
                        userDb.userDao().addUserInfo(user);
                        openReviewPage();
                        Log.d("Please God work", postsSelf.get(0).getName());
                    }
                }));

    }
    // ... more navigation



}
