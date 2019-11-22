package com.example.reviewer.RoomDb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "games")
public class Game {

    @PrimaryKey
    private int game_id;

    @ColumnInfo(name = "game_name")
    private String name;

    @ColumnInfo(name = "game_description")
    private String description;

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String game_name) {
        this.name = game_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
