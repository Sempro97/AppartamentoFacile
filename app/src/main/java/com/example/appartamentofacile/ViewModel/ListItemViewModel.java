package com.example.appartamentofacile.ViewModel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.appartamentofacile.CardItem;
import com.example.appartamentofacile.Database.UserRepository;
import com.example.appartamentofacile.User;
import com.example.appartamentofacile.UserWithCard;

import java.util.List;

public class ListItemViewModel extends AndroidViewModel {

    UserRepository repository;
    private LiveData<List<CardItem>> item_list;


    public ListItemViewModel(@NonNull Application application) {
        super(application);
        this.repository = new UserRepository(application);
        item_list = repository.getItems();
    }

    public LiveData<List<CardItem>> getItems() {
        return item_list;
    }
}
