package com.dalhousie.foodnculture;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup_screen);

        Button signUpButton = (Button) findViewById(R.id.signup_button);
        signUpButton.setOnClickListener(this);

        Button loginButton = (Button) findViewById(R.id.signin_button);
        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signup_button:
                Intent signup_i = new Intent(this, RegisterActivity.class);
                startActivity(signup_i);
                break;
            case R.id.signin_button:
                Intent signin_i = new Intent(this, LoginActivity.class);
                startActivity(signin_i);
                break;
        }
    }
}
