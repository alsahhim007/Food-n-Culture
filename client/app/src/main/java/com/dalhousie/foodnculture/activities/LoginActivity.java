package com.dalhousie.foodnculture.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.apifacade.ApiFacade;
import com.dalhousie.foodnculture.models.Authentication;
import com.dalhousie.foodnculture.models.User;
import com.dalhousie.foodnculture.utilities.AESSecurity;
import com.dalhousie.foodnculture.utilities.ISecurity;
import com.dalhousie.foodnculture.utilities.ValidatorHelper;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;
import java.util.Optional;

public class LoginActivity extends AppCompatActivity {

    ISecurity security = new AESSecurity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageButton back_button = findViewById(R.id.btnArrowleft);
        TextView forget_password = findViewById(R.id.txtForgotPassword);
        Button loginButton = findViewById(R.id.btnLogin);
        EditText etUserEmail = findViewById(R.id.etEnteryouremail);
        EditText etUserPassword = findViewById(R.id.etEnteryourpass);
        TextView dontHaveAnAccount = findViewById(R.id.dont_have_an_account_text);

        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        back_button.setOnClickListener(view -> finish());
        forget_password.setOnClickListener(view -> showForgotPasswordDialog());

        loginButton.setOnClickListener(view -> {
            if (validateField(etUserEmail) && validateField(etUserPassword)) {
                Optional<User> user = checkUser(etUserEmail.getText().toString());
                if (user.isPresent()) {
                    if (Objects.equals(security.decrypt(user.get().getPassword()), etUserPassword.getText().toString())) {
                        Intent homeIntent = new Intent(view.getContext(), HomePage.class);
                        startActivity(homeIntent);
                        finish();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("logged", true);
                        editor.putString("email", user.get().getEmail());
                        editor.apply();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "User not found with provided Email/Username", Toast.LENGTH_SHORT).show();
                }
            } else {
                Snackbar.make(view, "Oops, Some field are incorrect.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(registerIntent);
                finish();
            }
        });
    }

    private void showForgotPasswordDialog() {
        final BottomSheetDialog emailDialog = new BottomSheetDialog(this);
        emailDialog.setContentView(R.layout.bottomsheet_password);
        emailDialog.show();

        final BottomSheetDialog otpDialog = new BottomSheetDialog(this);
        otpDialog.setContentView(R.layout.bottomsheet_otp_validation);

        Button sendEmail = emailDialog.findViewById(R.id.btnSendEmail);
        EditText forgetEmail = emailDialog.findViewById(R.id.etEmail);

        if (sendEmail != null && forgetEmail != null) {
            sendEmail.setOnClickListener(view -> {
                if (validateField(forgetEmail) && validateEmail(forgetEmail)) {
                    Optional<User> user = checkUser(forgetEmail.getText().toString());
                    if (user.isPresent()) {
                        Authentication auth = new Authentication();
                        auth.setUserId(user.get().getId());
                        try {
                            if (ApiFacade.getInstance().getAuthenticationApi().save(auth) == 1) {
                                emailDialog.cancel();
                                otpDialog.show();
                                showOTPDialog(emailDialog, otpDialog, user.get());
                            } else {
                                emailDialog.cancel();
                                Toast.makeText(getApplicationContext(), "There is an error generating OTP", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "User not found with provided Email", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(view, "Email is incorrect", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }

    private void showOTPDialog(BottomSheetDialog emailDialog, BottomSheetDialog otpDialog, User user) {
        Button sendOtp = otpDialog.findViewById(R.id.btnValidate);
        PinView pinView = otpDialog.findViewById(R.id.firstPinView);
        if (sendOtp != null && pinView != null) {
            sendOtp.setOnClickListener(view -> {
                try {
                    Authentication authentication = ApiFacade.getInstance().getAuthenticationApi().getOTPByUserId(user.getId());
                    if (authentication.getOtp().equals(pinView.getText().toString())) {
                        Intent homeIntent = new Intent(view.getContext(), HomePage.class);
                        startActivity(homeIntent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "There is an error generating OTP", Toast.LENGTH_SHORT).show();
                    otpDialog.cancel();
                    emailDialog.show();
                    e.getStackTrace();
                }
            });
        }
    }

    private boolean validateField(EditText checkField) {
        if (checkField.length() == 0) {
            checkField.setError("Required field");
            return false;
        }
        return true;
    }

    private boolean validateEmail(EditText email) {
        if (!ValidatorHelper.isValidEmail(email.getText().toString())) {
            email.setError("Enter valid email");
            return false;
        }
        return true;
    }

    private Optional<User> checkUser(String email) {
        if (ValidatorHelper.isValidEmail(email)) {
            return ApiFacade.getInstance().getUserApi().getByEmail(email);
        } else {
            return ApiFacade.getInstance().getUserApi().getByUserName(email);
        }
    }
}