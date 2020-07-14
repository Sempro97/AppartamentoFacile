package com.example.appartamentofacile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appartamentofacile.ViewModel.UserViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.appartamentofacile.MainActivity.USERNAME_FILE_lOG;
import static com.example.appartamentofacile.MainActivity.USERNAME_NAME_lOG;

public class RegisterFragment extends Fragment {

    private UserViewModel userViewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.af_register_fragment, container, false);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        final TextInputLayout passwordRepeatTextInput = view.findViewById(R.id.password_repeat_text_input);
        final TextInputEditText passwordRepeatEditText = view.findViewById(R.id.password_repeat_edit_text);
        final TextInputLayout usernameTextInput = view.findViewById(R.id.username_text_input);
        final TextInputEditText usernameEditText = view.findViewById(R.id.username_edit_text);
        MaterialButton nextButton = view.findViewById(R.id.next_button);
        MaterialButton cancelButton = view.findViewById(R.id.cancel_button);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPasswordValid(passwordEditText.getText())) {
                    //Not secure password
                    passwordTextInput.setError(getString(R.string.af_error_password));
                }else if(!(passwordEditText.getText().toString().equals(passwordRepeatEditText.getText().toString()))){
                    //Check integrity password failed
                    passwordRepeatTextInput.setError(getString(R.string.af_error_password_notEqual));
                }else if(userViewModel.getUser(usernameEditText.getText().toString()) != null){//check if account is already present
                    usernameTextInput.setError("Username already exist!");
                }else{
                    //Ok password correct
                    passwordTextInput.setError(null); // Clear the error
                    passwordRepeatTextInput.setError(null); // Clear the error
                    //Track that user log in successfully
                    SharedPreferences sharedPref = getActivity().getSharedPreferences(USERNAME_FILE_lOG,Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(USERNAME_NAME_lOG,usernameEditText.getText().toString());
                    editor.apply();
                    userViewModel.addUser(new User(usernameEditText.getText().toString(),passwordEditText.getText().toString()));
                    getActivity().getSupportFragmentManager().popBackStack();
                    //((NavigationHost) getActivity()).navigateTo(getActivity().getSupportFragmentManager().getFragments().get(0), false); // Navigate to the next Fragment
                }
            }
        });

        // Clear the error once more than 8 characters are typed.
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });

        cancelButton.setOnClickListener(v -> {
            passwordEditText.getText().clear();
            usernameEditText.getText().clear();
            passwordRepeatEditText.getText().clear();
        });

        return view;
    }

    /*
        Low security check
    */
    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }
}
