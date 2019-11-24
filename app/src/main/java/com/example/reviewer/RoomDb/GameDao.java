package com.example.reviewer.RoomDb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addGame(Game game);

    @Query("SELECT * FROM games")
    List<Game> getAllGames();

    @Query("DELETE FROM games")
    void deleteAll();
}
