package com.example.reviewer.GameLibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.example.reviewer.Models.ObjectModelgetGameInfo;
import com.example.reviewer.Models.ObjectModelgetGamesList;
import com.example.reviewer.R;
import com.example.reviewer.Retrofit.INodeJS;
import com.example.reviewer.Retrofit.RetrofitClient;
import com.example.reviewer.RoomDb.AppDatabase;
import com.example.reviewer.RoomDb.Models.Game;
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

public class AllGameListActivity extends AppCompatActivity {
    private INodeJS myAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private RecyclerView recyclerView;
    public static AppDatabase gameDb;

    private List<Integer> game_ids = new ArrayList<>();
    String game_name;
    String game_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);


        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);

        getGamesList(5, 0);

        List<Game> gameModels = new ArrayList<>();
        int index = 0;
        while(index < game_ids.size()) {
            getGameInfo(game_ids.get(index));
            Game game = new Game(game_ids.get(index), game_name, game_pic);
            gameModels.add(game);
        }

        for(int i = 0; i < gameModels.size(); i++) {
            System.out.println("DEBUG: GameModel[" + i + "] name is: " + gameModels.get(i).getName());
        }

        GameViewAdapter gameViewAdapter = new GameViewAdapter(gameModels);
        recyclerView.setAdapter(gameViewAdapter);
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

                        int index = 0;
                        while(index < posts.size()) {
                            game_ids.add(posts.get(index).getGame_id());
                            index++;
                        }


                    }
                }));
    }



    private void getGameInfo(int game_id){
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

                        game_name = posts.get(0).getGame_name();
                        game_pic = posts.get(0).getPic1();

                    }
                }));
    }



}




