package com.example.reviewer.RoomDb;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.reviewer.RoomDb.Daos.GameDao;
import com.example.reviewer.RoomDb.Models.Game;

@Database(entities = {Game.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract GameDao gameDao();
}
