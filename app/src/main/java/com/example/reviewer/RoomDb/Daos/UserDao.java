package com.example.reviewer.RoomDb.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.reviewer.RoomDb.Models.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUserInfo(User user);

    @Query("SELECT * FROM user")
    User getUserInfo();

    @Query("SELECT name FROM user WHERE user_id=1")
    String getUserName();

    @Query("SELECT unique_id FROM user WHERE user_id=1")
    String getUserUid();

    @Query("SELECT email FROM user WHERE user_id=1")
    String getUserEmail();

    @Query("SELECT password FROM user WHERE user_id=1")
    String getUserPass();

    @Query("DELETE FROM user")
    void deleteAll();

}
