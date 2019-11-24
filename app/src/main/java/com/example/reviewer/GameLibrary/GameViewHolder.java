package com.example.reviewer.GameLibrary;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reviewer.R;
import com.example.reviewer.RoomDb.Game;
import com.example.reviewer.models.GameInfoModel;

public class GameViewHolder extends RecyclerView.ViewHolder {
    private TextView gameNameTextView;
    private ImageView gamePicImageView;

    public GameViewHolder(View itemView) {
        super(itemView);
        gameNameTextView = itemView.findViewById(R.id.game_name_text);
        gamePicImageView = itemView.findViewById(R.id.game_picture);
    }

    public void bindView(final Game game) {
        Glide.with(this.itemView)
                .load(game.getImage_urls()[0])
                .placeholder(R.drawable.placeholder)
                .error(new ColorDrawable(Color.RED))
                .into(gamePicImageView);

        gameNameTextView.setText(game.getName());

    }
}