package com.example.reviewer.GameLibrary;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.reviewer.R;
import com.example.reviewer.RoomDb.Models.Game;

public class GameViewAdapter extends RecyclerView.Adapter<GameViewHolder> {
    private String TAG = "GameViewAdapter";

    private List<Game> gameCollection;

    public GameViewAdapter(List<Game> collection) {
        gameCollection = collection;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list_game_item, parent, false);
        return new GameViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder gameViewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        Game model = gameCollection.get(position);
        gameViewHolder.bindView(model);
    }

    @Override
    public int getItemCount() { return gameCollection.size(); }

}
