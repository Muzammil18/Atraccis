package com.example.atraccis.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atraccis.R;
import com.example.atraccis.activities.listOfUsers.ListOfUsersActivity;
import com.example.atraccis.fragment.HypnosisFragment;
import com.example.atraccis.fragment.SongFragment;
import com.example.atraccis.fragment.VideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingActivity extends AppCompatActivity  {
        SharedPreferences sharedpreferences;
        TextView txtAlaram,txtAnalysis;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        find_View_By_Id();



}

        private void find_View_By_Id() {
        sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        txtAlaram=findViewById(R.id.txtalarm);
        txtAnalysis=findViewById(R.id.txtanalysis);
                if (!sharedpreferences.getBoolean("incharge", false)) {
                        txtAnalysis.setVisibility(View.GONE);
                }
                txtAnalysis.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                startActivity(new Intent(SettingActivity.this, ListOfUsersActivity.class));
                        }
                });

        }

}