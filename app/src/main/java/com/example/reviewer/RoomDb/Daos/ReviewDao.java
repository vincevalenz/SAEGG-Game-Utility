package com.example.reviewer.RoomDb.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.reviewer.RoomDb.Models.Review;

import java.util.List;

@Dao
public interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addReview(Review review);

    @Query("SELECT * FROM reviews WHERE NOT unique_id=''")
    List<Review> getProfileReviews();

    @Query("SELECT * FROM reviews WHERE unique_id LIKE :uid")
    List<Review> getProfileReviews(String uid);

    @Query("SELECT * FROM reviews WHERE NOT game_id=0")
    List<Review> getGameReviews();

    @Query("SELECT * FROM reviews WHERE game_id LIKE :game_id")
    List<Review> getGameReviews(int game_id);

    @Query("DELETE FROM reviews")
    void deleteAll();
}
