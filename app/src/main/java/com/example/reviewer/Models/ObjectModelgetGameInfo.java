package com.example.reviewer.Models;

import com.google.gson.annotations.SerializedName;

public class ObjectModelgetGameInfo {

    @SerializedName("game_name")
    private String game_name;

    @SerializedName("game_description")
    private String game_description;

    @SerializedName("pic1")
    private String pic1;

    @SerializedName("pic2")
    private String pic2;

    @SerializedName("pic3")
    private String pic3;

    @SerializedName("pic4")
    private String pic4;

    @SerializedName("pic5")
    private String pic5;

    public ObjectModelgetGameInfo(){}

    public String getGame_name() { return game_name; }

    public String getGame_description() { return game_description; }

    public String getPic1() { return pic1; }

    public String getPic2() { return pic2; }

    public String getPic3() { return pic3; }

    public String getPic4() { return pic4; }

    public String getPic5() { return pic5; }
}
