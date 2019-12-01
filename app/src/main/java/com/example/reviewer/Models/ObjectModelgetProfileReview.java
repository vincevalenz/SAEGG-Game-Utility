package com.example.reviewer.Models;

import com.google.gson.annotations.SerializedName;

public class ObjectModelgetProfileReview {

    @SerializedName("game_id")
    private int game_id;

    @SerializedName("name")
    private String name;

    @SerializedName("review_rating")
    private int review_rating;

    @SerializedName("written_review")
    private String written_review;

    public ObjectModelgetProfileReview(){}

    public int getGame_id() { return game_id; }

    public String getUserName() { return name; }

    public int getReview_rating() { return review_rating; }

    public String getWritten_review() { return written_review; }

}
