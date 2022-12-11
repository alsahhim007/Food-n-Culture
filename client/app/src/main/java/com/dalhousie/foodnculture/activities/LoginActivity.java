package com.dalhousie.foodnculture.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.apifacade.ApiFacade;
import com.dalhousie.foodnculture.models.User;
import com.dalhousie.foodnculture.utilities.AESSecurity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Optional;

public class LoginActivity extends AppCompatActivity {

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


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });

        loginButton.setOnClickListener(view -> {
            Optional<User> user = ApiFacade.getInstance().getUserApi().getByEmail(etUserEmail.getText().toString());
            if(user.isPresent() && AESSecurity.decrypt(user.get().getPassword()).equals(etUserPassword.getText().toString())){
                Intent homeIntent = new Intent(view.getContext(), HomePage.class);
                startActivity(homeIntent);
            }else{
                Toast.makeText(getApplicationContext(),"User Not Found With Provided Email",Toast.LENGTH_LONG).show();
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

    private void showBottomSheetDialog(){
        final BottomSheetDialog bsd = new BottomSheetDialog(this);
        bsd.setContentView(R.layout.bottomsheet_password);
        bsd.show();

        final BottomSheetDialog dg = new BottomSheetDialog(this);
        dg.setContentView(R.layout.bottomsheet_otp_validation);

        Button sendEmail = bsd.findViewById(R.id.btnSendEmail);
        sendEmail.setOnClickListener(view -> {
            bsd.cancel();
            dg.show();
        });
    }
}