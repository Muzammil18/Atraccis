package com.example.atracc.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atracc.R;
import com.example.atracc.activities.listOfUsers.ListOfUsersActivity;
import com.example.atracc.alarms.Activity_AlarmsList;

public class SettingActivity extends AppCompatActivity  {
        SharedPreferences sharedpreferences;
        TextView txtAlaram,txtAnalysis;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
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
                                if(sharedpreferences.getLong("dateDiffrence",0)==30||sharedpreferences.getLong("dateDiffrence",0)==60||sharedpreferences.getLong("dateDiffrence",0)==90){
                                startActivity(new Intent(SettingActivity.this, ReviewsActivity.class));}
                                else {
                                        startActivity(new Intent(SettingActivity.this,  ListOfUsersActivity.class));
                                }
                        }
                });
                txtAlaram.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                startActivity(new Intent(SettingActivity.this, Activity_AlarmsList.class));
                        }
                });


        }

}