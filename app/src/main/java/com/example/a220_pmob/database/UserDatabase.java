package com.example.a220_pmob.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.a220_pmob.database.dao.UserDao;
import com.example.a220_pmob.database.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static final String dbName = "220_user";
    private static UserDatabase userDatabase;

    public static synchronized UserDatabase getUserDatabase(Context context) {
        if(userDatabase == null) {
            userDatabase = Room.databaseBuilder(context, UserDatabase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return userDatabase;
    }

    public abstract UserDao userDao();
}
