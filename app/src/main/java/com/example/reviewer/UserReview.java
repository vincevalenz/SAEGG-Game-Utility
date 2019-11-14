package com.example.reviewer;

import java.io.Serializable;

public class UserReview implements Serializable {
    private String reviewText;
    private String gameTitle;
    private String reviewTitle;
    private Double gameRating;

    public UserReview(String reviewText, String reviewTitle, String gameTitle, double gameRating) {
        this.reviewText = reviewText;
        this.reviewTitle = reviewTitle;
        this.gameTitle = gameTitle;
        this.gameRating = gameRating;
    }

    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }

    public String getReviewTitle() { return reviewTitle; }
    public void setReviewTitle(String reviewTitle) { this.reviewTitle = reviewTitle; }


    public String getGameTitle() { return gameTitle; }
    public void setGameTitle(String gameTitle) { this.gameTitle = gameTitle; }

    public Double getGameRating() { return gameRating; }
    public void setGameRating(double gameRating) { this.gameRating = gameRating; }
}
