package com.example.appartamentofacile.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.appartamentofacile.CardItem;
import com.example.appartamentofacile.Database.UserRepository;

public class AddItemViewModel extends AndroidViewModel {

    private UserRepository repository;

    public AddItemViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public void addItem(CardItem item){
        repository.addCardItem(item);
    }

}
