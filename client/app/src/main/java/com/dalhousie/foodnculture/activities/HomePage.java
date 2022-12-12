package com.dalhousie.foodnculture.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.fragments.CommunityList;
import com.dalhousie.foodnculture.fragments.FriendsFragment;
import com.dalhousie.foodnculture.fragments.HomeFragment;
import com.dalhousie.foodnculture.fragments.HostFragment;
import com.dalhousie.foodnculture.fragments.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePage extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp = getSharedPreferences("login", MODE_PRIVATE);
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

    HomeFragment sf = new HomeFragment();
    HostFragment ff = new HostFragment();
    UserProfileFragment tf = new UserProfileFragment();
    CommunityList cf = new CommunityList();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, sf).commit();
                return true;
            case R.id.community:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, cf).commit();
                return true;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, tf).commit();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (sp.getBoolean("logged", false)) {
            finishAffinity();
            finish();
        } else {
            super.onBackPressed();
        }
    }
}