package com.dalhousie.foodnculture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePage extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }


    HomeFragment sf = new HomeFragment();
    HostFragment ff = new HostFragment();
    third_frgmt tf =  new third_frgmt();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, sf).commit();
                return true;
            case R.id.community:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, ff).commit();
                return true;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, tf).commit();
                return true;
        }
        return false;
    }
}