package com.example.atraccis.views.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.atraccis.R;

public class SplashActivity extends AppCompatActivity {
    //Get api Version.
    @RequiresApi(api = Build.VERSION_CODES.P)
    //Override onCreate method.
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Connect splash_activity.xml with that java class.
        setContentView(R.layout.activity_splash);
        //useing handler for delay time to move on new activity.
        new Handler().postDelayed(new Runnable() {
            //Override run method.
            @Override
            public void run() {
                //create intent for start  LoginActivity
                Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        },3000);
    }
}

