package com.example.appartamentofacile.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.appartamentofacile.CardItem;
import com.example.appartamentofacile.Database.Repository;

import java.util.List;

public class ListItemViewModel extends AndroidViewModel {

    Repository repository;
    private LiveData<List<CardItem>> item_list;


    public ListItemViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        item_list = repository.getItems();
    }

    public LiveData<List<CardItem>> getItems() {
        return item_list;
    }
}
