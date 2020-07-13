package com.example.appartamentofacile.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.appartamentofacile.CardItem;
import com.example.appartamentofacile.User;
import com.example.appartamentofacile.UserWithCard;

import java.util.List;

@Dao
interface UserDAO {
    // The selected on conflict strategy ignores a new person
    // if it's exactly the same as one already in the list.
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void addUser(User user);

    @Query("SELECT * from user_saved ORDER BY user_id DESC")
    LiveData<List<User>> getUser();

    @Query("SELECT * FROM user_saved WHERE user_id LIKE :id")
    User findByID(int id);

    @Transaction
    @Query("SELECT * FROM user_saved WHERE username LIKE :username")
    User findByUsername(String username);

    @Transaction
    @Query("SELECT * FROM user_saved")
    LiveData<List<UserWithCard>> getUsersWithCards();


}

@Dao
interface CardItemDAO {
    // The selected on conflict strategy ignores a new CardItem
    // if it's exactly the same as one already in the list.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addCardItem(CardItem CardItem);

    @Transaction
    @Query("SELECT * from item ORDER BY item_id DESC")
    LiveData<List<CardItem>> getItems();
}


