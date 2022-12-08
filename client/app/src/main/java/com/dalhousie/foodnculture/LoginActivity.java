package com.dalhousie.foodnculture;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageButton back_button = findViewById(R.id.btnArrowleft);
        TextView forget_password = findViewById(R.id.txtForgotPassword);

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

    }

    private void showBottomSheetDialog(){
        final BottomSheetDialog bsd = new BottomSheetDialog(this);
        bsd.setContentView(R.layout.bottomsheet_password);
        bsd.show();


        final BottomSheetDialog dg = new BottomSheetDialog(this);
        dg.setContentView(R.layout.bottomsheet_otp_validation);

        Button sendEmail = bsd.findViewById(R.id.btnSendEmail);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bsd.cancel();
                dg.show();
            }
        });




    }

}