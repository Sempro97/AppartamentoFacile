package com.example.appartamentofacile;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.af_register_fragment, container, false);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        final TextInputLayout passwordRepeatTextInput = view.findViewById(R.id.password_repeat_text_input);
        final TextInputEditText passwordRepeatEditText = view.findViewById(R.id.password_repeat_edit_text);
        final TextInputEditText usernameEditText = view.findViewById(R.id.username_edit_text);
        MaterialButton nextButton = view.findViewById(R.id.next_button);
        MaterialButton cancelButton = view.findViewById(R.id.cancel_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPasswordValid(passwordEditText.getText())) {
                    //Not secure password
                    passwordTextInput.setError(getString(R.string.shr_error_password));
                }else if(!(passwordEditText.getText().toString().equals(passwordRepeatEditText.getText().toString()))){
                    //Check same password failed
                    passwordRepeatTextInput.setError(getString(R.string.af_error_password_notEqual));
                }else{
                    //Ok new account created
                    passwordTextInput.setError(null); // Clear the error
                    passwordRepeatTextInput.setError(null); // Clear the error
                    getActivity().getSupportFragmentManager().popBackStack();
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
        In reality, this will have more complex logic including, but not limited to, actual
        authentication of the username and password.
    */
    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }
}
