package com.dalhousie.foodnculture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageButton back_button = findViewById(R.id.btnArrowleft);
        Button registerButton = findViewById(R.id.btnRegister);
        TextView alreadyHaveAnAccount = findViewById(R.id.alreadyHaveAnAccount);
//
        back_button.setOnClickListener(view -> finish());

        registerButton.setOnClickListener(view -> {
            Intent intent1 = new Intent(view.getContext(), HomePage.class);
            startActivity(intent1);
        });

        alreadyHaveAnAccount.setOnClickListener(view -> {
            Intent loginIntent = new Intent(view.getContext(), LoginActivity.class);
            startActivity(loginIntent);
            finish();
        });

    }
}