package com.dalhousie.foodnculture.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.dalhousie.foodnculture.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageButton backButton = findViewById(R.id.btnArrowleft);
        Button registerButton = findViewById(R.id.btnRegister);

        backButton.setOnClickListener(view -> finish());

        registerButton.setOnClickListener(view -> {
            Intent intent1 = new Intent(view.getContext(), HomePage.class);
            startActivity(intent1);
        });

    }
}