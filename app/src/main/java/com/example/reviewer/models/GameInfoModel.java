package com.example.reviewer.models;

import com.example.reviewer.R;

public class GameInfoModel {
//    private int gameId;
    private String gameName;
    private String gameDescription;

//    public void setGameId(int id) {gameId = id;}
//    public int getGameId() {return gameId;}

    public void setGameName(String name) {gameName = name;}
    public String getGameName() {return gameName;}

    public void setGameDescription(String description) {gameDescription = description;}
    public String getGameDescription() {return gameDescription;}

    private String[] imageUrls = {"https://specials-images.forbesimg.com/imageserve/5dd3f75ee0af7b0006b1afe6/960x0.jpg?fit=scale",
    "https://cdn.vox-cdn.com/thumbor/KTeBCvpJ9zogaT9IHfyVpkqd5pU=/0x0:1920x1080/1200x675/filters:focal(859x0:1165x306)/cdn.vox-cdn.com/uploads/chorus_image/image/61407537/Hammer_Titan.0.jpg",
    "https://steamcdn-a.akamaihd.net/steam/apps/1085660/ss_01ba13f9c57e39fe11e5ec5dd3b13313af310f0e.1920x1080.jpg?t=1570039639",
    "https://specials-images.forbesimg.com/imageserve/5dcde98a2c886a0007ec286b/960x0.jpg?fit=scale",
    "https://specials-images.forbesimg.com/imageserve/5da07ab06763cb0006087f16/960x0.jpg?fit=scale"};

    public void setImageArray (String[] imageArray) {
        System.arraycopy(imageUrls, 0,imageArray, 0, 5);
    }

    public GameInfoModel getGameInfo(int id) {
        GameInfoModel temp = new GameInfoModel();
//        temp.setGameName(/*SOME STRING*/);
//        temp.setGameDescription(/*SOME STRING*/);
        return temp;
    }
}
