package com.example.reviewer.RoomDb.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "reviews")
public class Review {

    @PrimaryKey(autoGenerate = true)
    private int review_id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "unique_id")
    private String uid;

    @ColumnInfo(name = "rating")
    private int rating;

    @ColumnInfo(name = "review")
    private String review;

    @ColumnInfo(name = "game_id")
    private int game_id;

    public Review(){}

    // Game Review Constructor
    @Ignore
    public Review(int review_id, String uid, String name, String review, int rating) {
        this.review_id = review_id;
        this.uid = uid;
        this.name = name;
        this.review = review;
        this.rating = rating;
        game_id = 0;
    }

    // Profile Review Constructor
    @Ignore
    public Review(int review_id, int game_id, String name, String review, int rating) {
        this.review_id = review_id;
        this.game_id = game_id;
        this.name = name;
        this.review = review;
        this.rating = rating;
        uid = "";
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }
}
