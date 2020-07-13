package com.example.appartamentofacile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appartamentofacile.RecyclerView.ApartmentCardRecyclerViewAdapter;
import com.example.appartamentofacile.ViewModel.ListItemViewModel;
import com.example.appartamentofacile.ViewModel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.appartamentofacile.MainActivity.USERNAME_FILE_lOG;
import static com.example.appartamentofacile.MainActivity.USERNAME_NAME_lOG;

public class ApartamentGridFragment extends Fragment {

    private static final String LOG = "Apartment-Fragment";
    private UserViewModel userViewModel;
    private ListItemViewModel itemViewModel;
    private ApartmentCardRecyclerViewAdapter adapter;
    private User user;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.af_product_grid_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final FragmentActivity activity = getActivity();
        user = getUserLogged();

        // Set up the RecyclerView
        recyclerView = activity.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        adapter = new ApartmentCardRecyclerViewAdapter(activity);
        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        itemViewModel = new ViewModelProvider(activity).get(ListItemViewModel.class);
        //when the list of the items changed, the adapter gets the new list.
        itemViewModel.getItems().observe(activity, new Observer<List<CardItem>>() {
                    @Override
                    public void onChanged(List<CardItem> cardItems) {
                        adapter.setData(cardItems.stream().filter((v) ->
                                v.getUserCreatorID() == user.getId())
                                .collect(Collectors.toList()));
                    }
                });

        FloatingActionButton floatingActionButton = activity.findViewById(R.id.fab_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivityForResult(new Intent(activity, AddActivity.class),
                        Utils.ACTIVITY_ADD_TRIP);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.af_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    private User getUserLogged() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(USERNAME_FILE_lOG, Context.MODE_PRIVATE);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        return userViewModel.getUser(sharedPref.getString(USERNAME_NAME_lOG, ""));
    }

    /**
     * Method called from the MainActivity when the user add a new card
     */
    void updateList() {
        Log.d(LOG, "updateList()");
        adapter.updateList();
    }
}


