package com.shiva.ananta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    Context context;
    Fragment selectedFragment = null;
    BottomNavigationView navigationView;
    VideoView video;
    VideoView video1;
    ImageView imageView_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.framelayout);
        context = getApplicationContext();

        navigationView  = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(fpage_navListener);
        navigationView.setSelectedItemId(R.id.home_menu);

        Intent intent = getIntent();
        if (intent.hasExtra("check")) {
            String data = intent.getStringExtra("check");
            if(data.equals("true")){
                LibraryFragment fragment = new LibraryFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
               fragmentManager.beginTransaction().replace(R.id.framelayout,fragment).commit();
            }
        }

        if (getIntent().getBooleanExtra("OPEN_USER_FRAGMENT", false)) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            LibraryBook_DetailsFragment fragment = new LibraryBook_DetailsFragment();
            fragmentTransaction.replace(R.id.framelayout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
    @SuppressLint("NonConstantResourceId")
    private BottomNavigationView.OnNavigationItemSelectedListener fpage_navListener = item -> {


        switch (item.getItemId()) {
            case R.id.home_menu:
                selectedFragment =  new NewHomeFramgent();
                item.setChecked(true);
                break;

            case R.id.library_menu:
                selectedFragment = new LibraryFragment();
                item.setChecked(true);
                break;

            case R.id.ananta_menu:
                selectedFragment = new AnantaFragment();
                item.setChecked(true);
                break;
            case R.id.reels_menu:
                selectedFragment = new ReelsFragment();
                item.setChecked(true);
                break;
            case R.id.profile_menu:
                selectedFragment = new ProfileFragment();
                item.setChecked(true);
                break;

        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout, selectedFragment).commit();

        return false;
    };
}