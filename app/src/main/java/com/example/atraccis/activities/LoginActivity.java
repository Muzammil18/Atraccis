package com.example.atraccis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.atraccis.R;

public class LoginActivity extends AppCompatActivity {
    TextView btnLogin;
    CheckBox checkBox;
    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=findViewById(R.id.btnlogin);
        checkBox=findViewById(R.id.checkBox);
        constraintLayout=findViewById(R.id.constraintLayout);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                finish();
                startActivity(intent);

            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    constraintLayout.setVisibility(View.VISIBLE);
                }
                else {
                    constraintLayout.setVisibility(View.GONE);
                }
            }
        });
    }
}