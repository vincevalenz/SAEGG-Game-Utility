package com.example.reviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.reviewer.GameLibrary.GameViewAdapter;
import com.example.reviewer.GameLibrary.ManageUserGamesActivity;
import com.example.reviewer.Models.ObjectModelgetGameInfo;
import com.example.reviewer.Models.ObjectModelgetGamesList;
import com.example.reviewer.RoomDb.Models.Game;
import com.example.reviewer.UserProfile.UserProfileActivity;
import com.example.reviewer.Models.ObjectModelgetSelfInfo;
import com.example.reviewer.Retrofit.INodeJS;
import com.example.reviewer.Retrofit.RetrofitClient;
import com.example.reviewer.RoomDb.AppDatabase;

import com.example.reviewer.RoomDb.Models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity {

    private INodeJS myAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public static AppDatabase appDb;
    private User user;
    private String email, password;
    public List<Game> games = new ArrayList<>();

    int game_ids_0 = -9999,
        game_ids_1 = -9999,
        game_ids_2 = -9999,
        game_ids_3 = -9999,
        game_ids_4 = -9999,
        game_ids_5 = -9999,
        game_ids_6 = -9999;

    public Button openGameBtn, addGamesBtn, displayGamesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);

        //init API
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);

        Bundle extras = getIntent().getExtras();
        email = extras.getString("email");
        password = extras.getString("password");

        openGameBtn = findViewById(R.id.load_games_button);
        addGamesBtn = findViewById(R.id.add_loaded_games_button);
        displayGamesBtn = findViewById(R.id.display_games_button);


        openGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGamesList(7,1);
            }
        });

        addGamesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGameInfo(game_ids_0);
                getGameInfo(game_ids_1);
                getGameInfo(game_ids_2);
                getGameInfo(game_ids_3);
                getGameInfo(game_ids_4);
                getGameInfo(game_ids_5);
                getGameInfo(game_ids_6);
            }
        });

        displayGamesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Game> gameModels = games;
                for(int i = 0; i < gameModels.size(); i++) {
                    System.out.println("DEBUG: GameModel[" + i + "] name is: " + gameModels.get(i).getName());
                }

                GameViewAdapter gameViewAdapter = new GameViewAdapter(gameModels);
                recyclerView.setAdapter(gameViewAdapter);
            }
        });
    }



    private void openGameInfo(String name, String description, String img1, String img2, String img3, String img4, String img5, int location) {
        Intent intent = new Intent(this, GameInfoActivity.class);
        intent.putExtra("game_name", name);
        intent.putExtra("game_desc", name);
        intent.putExtra("game_img_1", name);
        intent.putExtra("game_img_2", name);
        intent.putExtra("game_img_3", name);
        intent.putExtra("game_img_4", name);
        intent.putExtra("game_img_5", name);
        intent.putExtra("location", location);// 0 for local, 1 for remote
        startActivity(intent);
    }



    private void getGamesList(int amount, int page){
        compositeDisposable.add(myAPI.getGamesList(amount, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("TESTING S VALUE", s);
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<ObjectModelgetGamesList>>(){}.getType();
                        List<ObjectModelgetGamesList> posts = gson.fromJson(s, listType);
                        Log.d("Please God work", posts.toString());

                        game_ids_0 = posts.get(0).getGame_id();
                        game_ids_1 = posts.get(1).getGame_id();
                        game_ids_2 = posts.get(2).getGame_id();
                        game_ids_3 = posts.get(3).getGame_id();
                        game_ids_4 = posts.get(4).getGame_id();
                        game_ids_5 = posts.get(5).getGame_id();
                        game_ids_6 = posts.get(6).getGame_id();
                    }
                }));
    }

    private void getGameInfo(final int game_id){
        compositeDisposable.add(myAPI.getGameInfo(game_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("TESTING S VALUE", s);
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<ObjectModelgetGameInfo>>(){}.getType();
                        List<ObjectModelgetGameInfo> posts = gson.fromJson(s, listType);
                        Log.d("Please God work", posts.toString());

                        String[] images = {posts.get(0).getPic1(),
                                posts.get(0).getPic2(),
                                posts.get(0).getPic3(),
                                posts.get(0).getPic4(),
                                posts.get(0).getPic5()};

                        Game game = new Game(game_id,
                                posts.get(0).getGame_name(),
                                posts.get(0).getGame_description(),
                                images);
                        games.add(game);
                    }
                }));
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
                        appDb = Room.databaseBuilder(getApplicationContext(),
                                AppDatabase.class,
                                "user")
                                .allowMainThreadQueries()
                                .build();
                        appDb.userDao().addUserInfo(user);
//                        openReviewPage();
                        Log.d("Please God work", postsSelf.get(0).getName());
                    }
                }));

    }
    // ... more navigation


}

/*private INodeJS myAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private String email, password;

    AppDatabase gameDb, userDb;
    User user;

//    Button rec_page_button;
//            usr_profile_button,
        Button rev_game_button;
//            admin_add_game_button,
//            destiny_2_button,
//            runescape_button,
//            death_stranding_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle extras = getIntent().getExtras();
        email = extras.getString("email");
        password = extras.getString("password");

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);

//        rec_page_button = (Button)findViewById(R.id.rec_page_button);
//        usr_profile_button = findViewById(R.id.user_profile_page_button);
//        destiny_2_button = findViewById(R.id.destiny_2_button);
//        runescape_button = findViewById(R.id.runescape_button);
//        death_stranding_button = findViewById(R.id.death_stranding_button);
        rev_game_button = findViewById(R.id.review_game_button);
//        admin_add_game_button = findViewById(R.id.admin_add_game_button);

//        rec_page_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openRecPage();
//            }
//        });
//
//        usr_profile_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openUserProfile();
//            }
//        });
//
        rev_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUserInfoToDb(email, password);

            }
        });
//
//        admin_add_game_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openAdminAddGamePage();
//            }
//        });
//
//        destiny_2_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openGameInfo(gameDb, "Destiny 2");
//            }
//        });
//
//        runescape_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openGameInfo(gameDb, "Runescape");
//            }
//        });
//
//        death_stranding_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openGameInfo(gameDb, "Death Stranding");
//            }
//        });


    }

//    public void openRecPage() {
//        Intent intent = new Intent(this, RecommendsActivity.class);
//        startActivity(intent);
//    }
//
//    public void openUserProfile() {
//        Intent intent = new Intent(this, UserProfileActivity.class);
//        startActivity(intent);
//    }
//
//    public void openGameInfo(AppDatabase appDatabase, String name) {
//        Intent intent = new Intent(this, GameInfoActivity.class);
//        intent.putExtra("game_name", name);
//
//        startActivity(intent);
//    }

    public void openReviewPage() {
        Intent intent = new Intent(this, ReviewActivity.class);
        startActivity(intent);
    }
//
//    public void openAdminAddGamePage() {
//        Intent intent = new Intent(this, ManageUserGamesActivity.class);
//        startActivity(intent);
//    }*/