package com.example.appartamentofacile;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.appartamentofacile.ViewModel.AddItemViewModel;
import com.example.appartamentofacile.ViewModel.ListItemViewModel;
import com.example.appartamentofacile.ViewModel.UserViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import static android.app.Activity.RESULT_OK;
import static com.example.appartamentofacile.MainActivity.USERNAME_FILE_lOG;
import static com.example.appartamentofacile.MainActivity.USERNAME_NAME_lOG;

public class AddFragment extends Fragment {

    private static final String LOG = "add-fragment" ;
    private DatePicker datePicker;
    private AddItemViewModel addItemViewModel;
    private UserViewModel userViewModel;
    ListItemViewModel modelItem;
    private String startDate;
    private String endDate;
    private String title;
    private String description;
    private MaterialButton startDateButton;
    private MaterialButton endDateButton;
    private TextInputEditText titleEditText;
    private TextInputEditText descriptionEditText;
    private TextInputEditText startDateEditText;
    private TextInputEditText endDateEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =  inflater.inflate(R.layout.af_add_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Activity activity = getActivity();
        if (activity != null) {
            startDateButton = activity.findViewById(R.id.button);
            endDateButton = activity.findViewById(R.id.button2);
            titleEditText = activity.findViewById(R.id.descriptionTitleTextInputEditText);
            descriptionEditText = activity.findViewById(R.id.descriptionTextInputEditText);
            startDateEditText = activity.findViewById(R.id.startDateTextInputEditText);
            endDateEditText = activity.findViewById(R.id.endDateTextInputEditText);


            startDateButton.setOnClickListener((v) -> {
                DialogFragment newFragment = new DatePickerFragment((pickerView, year, month, date) -> {
                    String inputDate = new String(date + "/" + month + "/" + year);
                    startDateEditText.setText(inputDate);
                    startDate=inputDate;
                });
                newFragment.show(((FragmentActivity) activity).getSupportFragmentManager(), "datePicker");
            });

            endDateButton.setOnClickListener((v) -> {
                DialogFragment newFragment = new DatePickerFragment((pickerView, year, month, date) -> {
                    String inputDate = new String(date + "/" + month + "/" + year);
                    endDate = inputDate;
                    endDateEditText.setText(inputDate);
                });
                newFragment.show(((FragmentActivity) activity).getSupportFragmentManager(), "datePicker");
            });

            addItemViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(AddItemViewModel.class);
            modelItem = new ViewModelProvider((ViewModelStoreOwner) activity).get(ListItemViewModel.class);
            userViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
            activity.findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(inputNotEmpty(descriptionEditText.getText())){
                    //add item to the database using the viewmodel and room
                    description = descriptionEditText.getText().toString();
                    title = titleEditText.getText().toString();
                        SharedPreferences sharedPref = getActivity().getSharedPreferences(USERNAME_FILE_lOG, Context.MODE_PRIVATE);
                    addItemViewModel.addItem(new CardItem(startDate, endDate,title, description,
                            userViewModel.getUser(sharedPref.getString(USERNAME_NAME_lOG, "")).getId()));
                    Log.e("user:",sharedPref.getString(USERNAME_NAME_lOG, ""));
                    activity.setResult(RESULT_OK);
                    activity.finish();
                    } else {
                        descriptionEditText.setError(getString(R.string.card_empty));
                   }

                }
            });
        }else{
            Log.e(LOG, "Activity is null");
        }
    }

    private boolean inputNotEmpty(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }
}
