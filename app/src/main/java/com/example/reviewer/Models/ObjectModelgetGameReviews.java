package com.example.reviewer.Models;

import com.google.gson.annotations.SerializedName;

public class ObjectModelgetGameReviews {

    @SerializedName("unique_id")
    private String unique_id;

    @SerializedName("name")
    private String name;

    @SerializedName("review_rating")
    private int review_rating;

    @SerializedName("written_review")
    private String written_review;

    public ObjectModelgetGameReviews(){}

    public String getUnique_id() { return unique_id; }

    public String getUser_name(){
        return name;
    }

    public int getReview_rating() { return review_rating; }

    public String getWritten_review() { return written_review; }

}
