package com.dalhousie.foodnculture.activities;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.fragments.CommunityList;
import com.dalhousie.foodnculture.fragments.FriendsFragment;
import com.dalhousie.foodnculture.fragments.HomeFragment;
import com.dalhousie.foodnculture.fragments.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class HomePage extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    SharedPreferences sharedPreferences;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar actionBar = findViewById(R.id.main_app_bar);
        ImageView chatIcon = findViewById(R.id.chat_icon);
        actionBar.setTitle("");
        actionBar.setBackgroundColor(Color.WHITE);
        setSupportActionBar(actionBar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener((NavigationBarView.OnItemSelectedListener) this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        chatIcon.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new FriendsFragment()).commit();
        });
    }

    HomeFragment homeFragment = new HomeFragment();
    UserProfileFragment userProfileFragment = new UserProfileFragment();
    CommunityList communityList = new CommunityList();

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                return true;
            case R.id.community:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, communityList).commit();
                return true;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, userProfileFragment).commit();
                return true;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (sharedPreferences.getBoolean("logged", false)) {
            getSupportFragmentManager().popBackStack();

            System.out.println(getClass().getSimpleName());

            System.out.println(getSupportFragmentManager().getBackStackEntryCount());

            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment, "HOME_FRAGMENT").commit();
                count += 1;
                Toast.makeText(this, "Press back twice to exit", Toast.LENGTH_SHORT).show();

            }
            String current_fragment = String.valueOf(getSupportFragmentManager().findFragmentByTag("HOME_FRAGMENT"));
            if (!current_fragment.equals("null")) {
                if (Objects.equals(Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("HOME_FRAGMENT")).getTag(), "HOME_FRAGMENT") && count >= 2) {
                    finishAffinity();
                }
            }
        } else {
            super.onBackPressed();
        }
    }
}