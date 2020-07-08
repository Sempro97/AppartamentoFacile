package com.example.appartamentofacile.Database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.appartamentofacile.User;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class UserRepository {
    private UserDAO userDAO;
    private LiveData<List<User>> user;

    public UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        userDAO = db.userDAO();
        user = userDAO.getUser();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<User>> getUsers(){
        return user;
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

    public User getUsers(final User user) {
        Future<User> future;
        User userSelected = null;
        try {
            future = UserRoomDatabase.databaseWriteExecutor.submit(new Callable<User>() {
                @Override
                public User call() {
                    return userDAO.findByID(user.getId());
                }
            });
            userSelected = future.get();
        } catch (Exception e) {
            Log.d("LAB", e.toString());
        }
        return userSelected;
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
}
