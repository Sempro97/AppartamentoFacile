package com.example.appartamentofacile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class Utils {

    static final int ACTIVITY_ADD_TRIP = 1;
    static final String LOGGED_IN = "log";

    static void insertFragment(AppCompatActivity activity, Fragment fragment, String tag) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment, tag)
                .commit();
    }

    static void setUpToolbar(AppCompatActivity activity, String title) {
        Toolbar toolbar = activity.findViewById(R.id.app_bar);
        toolbar.setTitle(title);
        //Set a Toolbar to act as the ActionBar for the Activity
        activity.setSupportActionBar(toolbar);
    }
}
