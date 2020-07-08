package com.example.appartamentofacile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel<T> extends ViewModel {
    private final MutableLiveData<T> selected = new MutableLiveData<T>();

    public void select(T data){
        selected.setValue(data);
    }

    public LiveData<T> getSelected(){
        return selected;
    }

}
