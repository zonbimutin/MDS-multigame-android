package com.example.mds_multigame.dao;

import android.content.Context;

import com.example.mds_multigame.model.Player;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = { Player.class}, version = AppDatabase.DATABASE_VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myDbb";

    public abstract PlayerDao appDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
