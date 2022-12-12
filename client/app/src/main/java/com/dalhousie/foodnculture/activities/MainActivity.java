package com.dalhousie.foodnculture.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.utilities.ConfigProvider;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return super.getSharedPreferences(name, mode);
    }

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // set app policy to ensure HTTP requests are not blocked by the main thread
        StrictMode.ThreadPolicy appPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(appPolicy);

        // initialize application configuration
        try {
            new ConfigProvider().loadConfiguration(getAssets().open("application.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        sp = getSharedPreferences("login", MODE_PRIVATE);
        if (sp.getBoolean("logged", false)) {
            Intent homeIntent = new Intent(this, HomePage.class);
            startActivity(homeIntent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup_screen);

        Button signUpButton = (Button) findViewById(R.id.signup_button);
        signUpButton.setOnClickListener(this);

        Button loginButton = (Button) findViewById(R.id.signin_button);
        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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

    @Override
    public void onBackPressed() {
        if (sp.getBoolean("logged", false)) {
            System.out.println("Hello");
        } else {
            super.onBackPressed();
            finish();
        }
    }
}
