package com.example.atracc.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.atracc.R;
import com.example.atracc.helpers.DateDifference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;

public class SplashActivity extends AppCompatActivity {
    //Get api Version.
    DatabaseReference my_Ref;
    SharedPreferences sharedpreferences;
    DateDifference dateDifference;
    int counter=0;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onResume() {
        super.onResume();


    }
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
                //Set Sharepreferenc for first time running app.
                sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                if (sharedpreferences.getBoolean("keeplogin", false)) {
                    verify_User();
                    if(sharedpreferences.getLong("confirmDate",0)==sharedpreferences.getLong("dateDiffrence",0)){
                        Intent intent;
                        if(!sharedpreferences.getBoolean("terms",false)){
                            intent= new Intent(SplashActivity.this,
                                    TermsActivity.class);
                        }
                        else if(!sharedpreferences.getBoolean("InitialTest",false)){
                            intent= new Intent(SplashActivity.this,
                                    TestActivity.class);
                        } else if(!sharedpreferences.getBoolean("FollowTest",false)&&(sharedpreferences.getLong("dateDiffrence",0)==30||sharedpreferences.getLong("dateDiffrence",0)==60||sharedpreferences.getLong("dateDiffrence",0)==90)){
                            intent= new Intent(SplashActivity.this,
                                    FollowUpTestActivity.class);
                        }
                        else{
                            intent= new Intent(SplashActivity.this,
                                    DashboardActivity.class);}
                        finish();
                        startActivity(intent);

                    }
                    else{
                        Log.e("DateDifference", String.valueOf(sharedpreferences.getLong("dateDiffrence",0)));
                        editor.putBoolean("insertvideo",true);
                        editor.putBoolean("insertsong",true);
                        editor.putBoolean("inserthyponosis",true);
                        editor.putLong("confirmDate",sharedpreferences.getLong("dateDiffrence",0));
                        if(sharedpreferences.getInt("counter",0)==10){
                            editor.putInt("counter",0);
                        }
                        editor.putInt("counter",sharedpreferences.getInt("counter",0)+1);
                       editor.commit();
                        Intent intent;
                        if(!sharedpreferences.getBoolean("terms",false)){
                            intent= new Intent(SplashActivity.this,
                                    TermsActivity.class);
                        }
                        else if(!sharedpreferences.getBoolean("InitialTest",false)){
                            intent= new Intent(SplashActivity.this,
                                    TestActivity.class);
                        }
                        else{
                        intent= new Intent(SplashActivity.this,
                                DashboardActivity.class);}
                        finish();
                        startActivity(intent);
                    }

                }
                else{
                //create intent for start  LoginActivity
                Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);}
            }
        },3000);
    }
    private void verify_User() {
        dateDifference=new DateDifference();
        my_Ref= FirebaseDatabase.getInstance().getReference("Registration_User");
        sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        my_Ref.orderByChild("email").equalTo(sharedpreferences.getString("email","")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    for (DataSnapshot npsnapshot : snapshot.getChildren()){
                        try {
                            editor.putLong("dateDiffrence",dateDifference.getDifference(npsnapshot.child("date").getValue(String.class)));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    editor.commit();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });




    }
}

