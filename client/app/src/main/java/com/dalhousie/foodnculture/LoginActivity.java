package com.dalhousie.foodnculture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageButton back_button = findViewById(R.id.btnArrowleft);
        TextView forget_password = findViewById(R.id.txtForgotPassword);
        Button loginButton = findViewById(R.id.btnLogin);

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
            Intent errorIntent = new Intent(view.getContext(), ErrorActivity.class);
            startActivity(errorIntent);
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