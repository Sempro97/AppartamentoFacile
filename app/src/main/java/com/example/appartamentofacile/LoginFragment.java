package com.example.appartamentofacile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.appartamentofacile.ViewModel.UserViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.appartamentofacile.MainActivity.FRAGMENT_TAG;
import static com.example.appartamentofacile.MainActivity.USERNAME_FILE_lOG;
import static com.example.appartamentofacile.MainActivity.USERNAME_NAME_lOG;

public class LoginFragment extends Fragment {

    private SharedPreferences sharedPref;
    private UserViewModel userViewModel;
    private TextInputEditText usernameEditText;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.af_login_fragment, container, false);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        usernameEditText = view.findViewById(R.id.username_edit_text);
        MaterialButton nextButton = view.findViewById(R.id.next_button);
        MaterialButton registerButton = view.findViewById(R.id.register_button);
        MaterialButton cancelButton = view.findViewById(R.id.cancel_button);
        // Set an error if the password is less than 8 characters.
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(getString(R.string.shr_error_password));
                } else {
                    passwordTextInput.setError(null); // Clear the error
                    if(isUsernameStored(usernameEditText.getText().toString(), passwordEditText.getText().toString())){
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(USERNAME_NAME_lOG,usernameEditText.getText().toString());
                        //apply to prevent memory leak
                        editor.apply();
                        ((NavigationHost) getActivity()).navigateTo(new ApartamentGridFragment(), true,FRAGMENT_TAG); // Navigate to the next Fragment
                    }
                    else{
                        passwordTextInput.setError(getString(R.string.af_error_password_wrong));
                    }


                }
            }
        });
        // Start new fragment.
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameEditText.setText("");
                passwordTextInput.setError(null);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new RegisterFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        cancelButton.setOnClickListener(v -> {
            passwordEditText.getText().clear();
            usernameEditText.getText().clear();
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

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(usernameEditText.getText().toString().isEmpty()) {
            sharedPref = getActivity().getSharedPreferences(USERNAME_FILE_lOG,Context.MODE_PRIVATE);
            usernameEditText.setText(sharedPref.getString(USERNAME_NAME_lOG, ""));
        }
    }

    private boolean isUsernameStored(String username, String password) {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        if(userViewModel.getUser(username) != null) {
            User user = userViewModel.getUser(username);
            if (password.hashCode() == user.getPassword()) {
                return true;
            }
            //pasword not equal
            Log.e("LogIn","Password Wrong!");
            return false;
        }
        else{
            Log.e("LogIn","Username not found!");
            //username is null, not found
            return false;
        }

    }

    /*
        In reality, this will have more complex logic including, but not limited to, actual
        authentication of the username and password.
    */
    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }
}
