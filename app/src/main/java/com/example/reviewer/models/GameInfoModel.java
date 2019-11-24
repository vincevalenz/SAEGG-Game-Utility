package com.example.reviewer.models;

import com.example.reviewer.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameInfoModel {
    private int gameId;
    private String gameName;
    private String gameDescription;
    private String[] imageUrls = new String[5];

    public void setGameName(String name) {gameName = name;}
    public String getGameName() {return gameName;}

    public void setGameDescription(String description) {gameDescription = description;}
    public String getGameDescription() {return gameDescription;}

    public void setImageUrls (String[] urls) {
        imageUrls = urls;
    }
    public String[] getImageUrls () {
        return imageUrls;
    }
}
