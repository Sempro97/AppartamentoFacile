package com.example.appartamentofacile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.appartamentofacile.Database.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository repository;

    private LiveData<List<User>> users;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        users = repository.getUsers();
    }

    LiveData<List<User>> getUsers() {
        return users;
    }

    public User getUser(String username){
        return repository.getUserbyUsername(username);
    }


    void addUser(User user){
        repository.addUser(user);
    }

}
