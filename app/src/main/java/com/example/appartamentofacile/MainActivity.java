package com.example.appartamentofacile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.appartamentofacile.ViewModel.UserViewModel;

import static com.example.appartamentofacile.Utils.LOGGED_IN;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    private static final String TAG = "MainActivity - LAB";
    private UserViewModel userViewModel;
    public static final String FRAGMENT_TAG = "homeFragment";

    public static final String USERNAME_FILE_lOG = "username";
    public static final String USERNAME_NAME_lOG = "name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.af_main_activity);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        if (savedInstanceState == null) {
            Utils.insertFragment(this, new LoginFragment(), TAG);
        }
        if (userViewModel.getUserLogged() != null) {
            Utils.insertFragment(this, new ApartamentGridFragment(), FRAGMENT_TAG);
        }
    }

    /**
     * Navigate to the given fragment.
     *
     * @param fragment       Fragment to navigate to.
     * @param addToBackstack Whether or not the current fragment should be added to the backstack.
     */
    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack, String tag) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment,tag);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    /**
     * Method call after the   activity.setResult(RESULT_OK); e activity.finish(); in AddFragment.
     *
     * @param requestCode requestCode of the intent (ACTIVITY_ADD_TRIP in this case)
     * @param resultCode the result of the intent (RESULT_OK)
     * @param data the optional data in the intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult()");
        if (requestCode == Utils.ACTIVITY_ADD_TRIP && resultCode == RESULT_OK) {
            Log.d(TAG, "RESULT_OK");
            FragmentManager manager = getSupportFragmentManager();
            ApartamentGridFragment apartamentGridFragment = (ApartamentGridFragment) manager.findFragmentByTag(FRAGMENT_TAG);
            if (apartamentGridFragment != null) {
                Log.d(TAG, "updateList()");
                apartamentGridFragment.updateList();
            }
        }
    }
}
