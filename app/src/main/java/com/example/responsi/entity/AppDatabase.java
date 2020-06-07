package com.example.responsi.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {DataNews.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataNewsDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase iniDb(Context context){
        if (appDatabase == null)
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class, "newsdb")
                    .allowMainThreadQueries().build();

        return appDatabase;
    }

    public static void destroyInstance(){appDatabase = null;}
}
