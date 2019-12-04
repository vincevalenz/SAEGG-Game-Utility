package com.example.reviewer.RoomDb;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.reviewer.RoomDb.Daos.*;
import com.example.reviewer.RoomDb.Models.*;

@Database(entities = {Game.class, User.class, Review.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract GameDao gameDao();
    public abstract UserDao userDao();
    public abstract ReviewDao reviewDao();
}
