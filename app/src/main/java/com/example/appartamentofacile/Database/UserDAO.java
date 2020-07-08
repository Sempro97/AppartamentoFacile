package com.example.appartamentofacile.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.appartamentofacile.User;

import java.util.List;

@Dao
public interface UserDAO {
    // The selected on conflict strategy ignores a new person
    // if it's exactly the same as one already in the list.
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void addUser(User user);

    @Query("SELECT * from user_saved ORDER BY user_id DESC")
    LiveData<List<User>> getUser();

    @Query("SELECT * FROM user_saved WHERE user_id LIKE :id")
    User findByID(int id);

    @Query("SELECT * FROM user_saved WHERE username LIKE :username")
    User findByUsername(String username);
}
