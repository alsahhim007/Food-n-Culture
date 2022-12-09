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

        StrictMode.ThreadPolicy appPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(appPolicy);
//        TODO? remove be, below code only for reference
//        List<User> users = ApiFacade.getInstance().getUserApi().findAll();
//        for(User user : users) {
//            System.out.println("Email = " + user.getEmail());
//        }
//        User user = new User();
//        user.setUserName("rajsoni");
//        user.setEmail("rajsoni@gmail.com");
//        user.setPassword("edwbduwbdwid");
//        user.setFirstName("Raj");
//        user.setLastName("Soni");
//        user.setVerified(false);
//        user.setStatus("created");
//        user.setUpdatedAt("2022-11-17 00:00:00");
//        user.setCreatedAt("2022-11-17 00:00:00");
//        ApiFacade.getInstance().getUserApi().save(user);

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
