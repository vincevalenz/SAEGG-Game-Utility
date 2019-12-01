package com.example.reviewer.Models;

import com.google.gson.annotations.SerializedName;

public class ObjectModelgetGamesList {

    @SerializedName("game_id")
    private int game_id;

    @SerializedName("game_name")
    private String game_name;

    public ObjectModelgetGamesList(){}

    public int getGame_id() { return game_id; }

    public String getGame_name(){
        return game_name;
    }

}
