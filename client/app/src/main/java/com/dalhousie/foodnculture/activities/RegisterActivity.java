package com.dalhousie.foodnculture.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.apifacade.ApiFacade;
import com.dalhousie.foodnculture.exceptions.UserAlreadyExist;
import com.dalhousie.foodnculture.models.User;
import com.dalhousie.foodnculture.utilities.AESSecurity;
import com.dalhousie.foodnculture.utilities.ValidatorHelper;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {

    EditText etFirstNameInput;
    EditText etLastNameInput;
    EditText etUsernameInput;
    EditText etEmailInput;
    EditText etPasswordInput;
    EditText etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageButton backButton = findViewById(R.id.btnArrowleft);
        Button registerButton = findViewById(R.id.btnRegister);
        TextView alreadyHaveAnAccount = findViewById(R.id.alreadyHaveAnAccount);

        etFirstNameInput = findViewById(R.id.etFirstNameInput);
        etLastNameInput = findViewById(R.id.etLastNameInput);
        etUsernameInput = findViewById(R.id.etUsernameInput);
        etEmailInput = findViewById(R.id.etEmailInput);
        etPasswordInput = findViewById(R.id.etPasswordInput);
        etConfirmPassword = findViewById(R.id.etConfirmpasswor);

        backButton.setOnClickListener(view -> finish());

        registerButton.setOnClickListener(view -> {
            Intent intent1 = new Intent(view.getContext(), HomePage.class);
            startActivity(intent1);
        });

        alreadyHaveAnAccount.setOnClickListener(view -> {
            Intent loginIntent = new Intent(view.getContext(), LoginActivity.class);
            startActivity(loginIntent);
            finish();
        });

        registerButton.setOnClickListener(view -> {
            if (validateField(etFirstNameInput) && validateField(etLastNameInput) && validateField(etUsernameInput)
                    && validateField(etEmailInput) && validateEmail() && validateField(etPasswordInput)
                    && validateField(etConfirmPassword) && validatePassword()) {
                try {
                    if (saveUser() == 1) {
                        Toast.makeText(getApplicationContext(), "User has been created successfully", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(view.getContext(), LoginActivity.class);
                        startActivity(intent1);
                    } else {
                        Toast.makeText(getApplicationContext(), "There is an error creating user", Toast.LENGTH_SHORT).show();
                    }
                } catch (UserAlreadyExist e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Snackbar.make(view, "Oops, Some field are incorrect.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private boolean validateField(EditText checkField) {
        if (checkField.length() == 0) {
            checkField.setError("Required field");
            return false;
        }
        return true;
    }

    private boolean validateEmail() {
        if (!ValidatorHelper.isValidEmail(etEmailInput.getText().toString())) {
            etEmailInput.setError("Enter valid email");
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        if (!etPasswordInput.getText().toString().equals(etConfirmPassword.getText().toString())) {
            etConfirmPassword.setError("Confirm password is not matching with Password");
            return false;
        }
        return true;
    }

    int saveUser() throws Exception {
        User user = new User();
        user.setFirstName(etFirstNameInput.getText().toString());
        user.setLastName(etLastNameInput.getText().toString());
        user.setUserName(etUsernameInput.getText().toString());
        user.setEmail(etEmailInput.getText().toString());
        user.setPassword(AESSecurity.encrypt(etPasswordInput.getText().toString()));
        user.setVerified(false);
        user.setStatus("Created");
        return ApiFacade.getInstance().getUserApi().save(user);
    }
}