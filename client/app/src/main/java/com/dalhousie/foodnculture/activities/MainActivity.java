package com.dalhousie.foodnculture.activities;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.apifacade.ApiFacade;
import com.dalhousie.foodnculture.models.User;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //set app policy to ensure HTTP requests are not blocked by the main thread
        StrictMode.ThreadPolicy appPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(appPolicy);

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
