package com.example.reviewer.RoomDb.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "games")
public class Game {

    @PrimaryKey
    private int game_id;

    @ColumnInfo(name = "game_name")
    private String name;

    @ColumnInfo(name = "game_description")
    private String description;

    @ColumnInfo(name = "images")
    private String[] image_urls = new String[5];

    public Game(){}

    @Ignore
    public Game(int game_id, String name, String description, String[] image_urls) {
        this.game_id = game_id;
        this.name = name;
        this.description = description;
        this.image_urls = image_urls;
    }

    @Ignore
    public Game(int game_id, String name, String image) {
        this.game_id = game_id;
        this.name = name;
        this.image_urls[0] = image;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getImage_urls() {
        return image_urls;
    }

    public void setImage_urls(String[] image_urls) {
        this.image_urls = image_urls;
    }
}
