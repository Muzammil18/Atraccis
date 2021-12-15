package com.example.atraccis.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atraccis.R;
import com.example.atraccis.fragment.HypnosisFragment;
import com.example.atraccis.fragment.SongFragment;
import com.example.atraccis.fragment.VideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

        BottomNavigationView bottomNavigationView;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigationView = findViewById(R.id.navigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.video_nav_graph);

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