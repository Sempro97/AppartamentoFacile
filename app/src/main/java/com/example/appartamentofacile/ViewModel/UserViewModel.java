package com.example.appartamentofacile.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.appartamentofacile.Database.Repository;
import com.example.appartamentofacile.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<User>> users;
    private User user;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        users = repository.getUsers();
    }

    LiveData<List<User>> getUsers() {
        return users;
    }

    public User getUser(String username){
        this.user = repository.getUserbyUsername(username);
        return this.user;
    }

    public User getUserLogged(){
        return user;
    }


    public void addUser(User user){
        this.user = user;
        repository.addUser(user);
    }

}
