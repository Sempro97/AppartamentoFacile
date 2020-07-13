package com.example.appartamentofacile.Database;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.appartamentofacile.CardItem;
import com.example.appartamentofacile.User;
import com.example.appartamentofacile.UserWithCard;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class UserRepository {
    private UserDAO userDAO;
    private LiveData<List<User>> user;
    private CardItemDAO cardItemDAO;
    private LiveData<List<CardItem>> item_list;
    private LiveData<List<UserWithCard>> userWithCard;

    public UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        userDAO = db.userDAO();
        user = userDAO.getUser();
        cardItemDAO = db.cardItemDAO();
        userWithCard = userDAO.getUsersWithCards();
        item_list = cardItemDAO.getItems();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<User>> getUsers(){
        return user;
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<CardItem>> getItems(){
        return item_list;
    }


    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void addUser(final User user) {
        UserRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDAO.addUser(user);
            }
        });
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void addCardItem(final CardItem CardItem) {
        UserRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cardItemDAO.addCardItem(CardItem);
            }
        });
    }

    public User getUserbyUsername(final String username) {
        Future<User> future;
        User userSelected = null;
        try {
            future = UserRoomDatabase.databaseWriteExecutor.submit(new Callable<User>() {
                @Override
                public User call() {
                    return userDAO.findByUsername(username);
                }
            });
            userSelected = future.get();
        } catch (Exception e) {
            Log.d("LAB", e.toString());
        }
        return userSelected;
    }

//    public LiveData<List<CardItem>> getCardOfUser(final String username){
//        Future<User> future;
//        User userSelected = null;
//        try {
//            future = UserRoomDatabase.databaseWriteExecutor.submit(new Callable<User>() {
//                @Override
//                public User call() {
//                    return userDAO.findByUsername(username);
//                }
//            });
//            userSelected = future.get();
//            User finalUserSelected = userSelected;
//            all_card.getValue().clear();
//            all_card.getValue().addAll(userWithCard.getValue().stream().filter(
//                    (v)-> v.user.getUsername() == finalUserSelected.getUsername())
//                    .flatMap(raw -> raw.cards.stream())
//                    .collect(Collectors.toList())
//            );
//        } catch (Exception e) {
//            Log.d("LAB", e.toString());
//        }
//        return all_card;
//    }
}
