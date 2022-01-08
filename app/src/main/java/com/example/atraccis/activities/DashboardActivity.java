package com.example.atraccis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atraccis.R;
import com.example.atraccis.fragment.HypnosisFragment;
import com.example.atraccis.fragment.SongFragment;
import com.example.atraccis.fragment.VideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

        BottomNavigationView bottomNavigationView;
        ImageView btnSettings;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigationView = findViewById(R.id.navigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.video_nav_graph);
        btnSettings=findViewById(R.id.btnsettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        startActivity(new Intent( DashboardActivity.this, SettingActivity.class));
                }
        });

        }
        VideoFragment videoFragment = new VideoFragment();
        SongFragment songFragment = new SongFragment();
        HypnosisFragment hypnosisFragment = new HypnosisFragment();

@Override
public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
                case R.id.video_nav_graph:
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, videoFragment).commit();
        return true;

                case R.id.song_nav_graph:
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, songFragment).commit();
        return true;

                case R.id.hypnosis_nav_graph:
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, hypnosisFragment).commit();
        return true;
        }
        return false;
        }
        }