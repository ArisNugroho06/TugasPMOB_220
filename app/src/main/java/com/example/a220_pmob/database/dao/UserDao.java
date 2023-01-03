package com.example.a220_pmob.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.a220_pmob.database.entity.User;

@Dao
public interface UserDao {

    @Insert
    void registerUser(User user);

    @Query("SELECT * FROM users WHERE nim=:nim AND password=:password")
    User login(String nim, String password);
}
