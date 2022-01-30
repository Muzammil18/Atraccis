package com.example.atracc.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.atracc.R;
import com.example.atracc.coustomViews.ProgressDialogue;
import com.example.atracc.helpers.DateDifference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private ArrayList<String> companisList=new ArrayList<String>();
    SharedPreferences sharedpreferences;
    TextView btnLogin;
    CheckBox checkBox;
    EditText email,userPassword;
   Spinner selectCompany;
    ConstraintLayout constraintLayout;
    DatabaseReference my_Ref,my_RefCompany;
    ProgressDialogue progressDialogue;
    DateDifference dateDifference;
    private int counterForUser=0;
    private int counterForIncharge=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialogue=new ProgressDialogue(LoginActivity.this);
        getDataFromDatabase();
        find_View_By_Id();
        onClickListener();

    }


    private void find_View_By_Id() {
        btnLogin=findViewById(R.id.btnlogin);
        checkBox=findViewById(R.id.checkBox);
        constraintLayout=findViewById(R.id.constraintLayout);
        email=findViewById(R.id.txtuseremail);
        userPassword=findViewById(R.id.txt_user_password);
        selectCompany=findViewById(R.id.txtselect_company);
        sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        dateDifference=new DateDifference();
    }
    private void onClickListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()) {
                    if(validation_For_User()){
                        verify_Incharge();
                    }
                }
                else{
                    if(validation_For_User()){
                    verify_User();}
                }

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
    private void getDataFromDatabase() {

        progressDialogue.show();
        my_Ref= FirebaseDatabase.getInstance().getReference("Companies");
        my_Ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                   companisList.add(npsnapshot.child("name").getValue(String.class));
                }
                progressDialogue.dismiss();
                setSppinner();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, "Cann't be in database ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insert_in_database() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        HashMap<String, Object> map = new HashMap<>();
        map.put("email",email.getText().toString());
        map.put("company",selectCompany.getSelectedItem().toString());
        map.put("date",sdf.format(new Date()));
        FirebaseDatabase.getInstance().getReference().child("Registration_User").push()
                .setValue(map);
    }
    private void setSppinner(){
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, companisList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectCompany.setAdapter(adapter);
    }
    private void verify_Incharge(){
        final String[] password = new String[1];
        my_RefCompany= FirebaseDatabase.getInstance().getReference("Companies");

        my_RefCompany.orderByChild("email").equalTo(email.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                counterForIncharge = (int) snapshot.getChildrenCount();
                Log.e("Password",snapshot.getChildrenCount()+"");

                if (counterForIncharge == 1) {
                        for (DataSnapshot npsnapshot : snapshot.getChildren()){
                            password[0] =npsnapshot.child("password").getValue(String.class).toString();
                        }
                        if(password[0].equals(userPassword.getText().toString())){
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("email", email.getText().toString());
                            editor.putString("company", selectCompany.getSelectedItem().toString());
                            editor.putBoolean("incharge", Boolean.TRUE);
                            editor.putBoolean("keeplogin", Boolean.TRUE);
                            editor.putLong("confirmDate",1);
                            editor.putBoolean("insertvideo",true);
                            editor.putBoolean("insertsong",true);
                            editor.putBoolean("inserthyponosis",true);
                            editor.putInt("counter",sharedpreferences.getInt("counter",0)+1);
                            editor.commit();
                            Intent intent = new Intent(LoginActivity.this, TermsActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Password Is Incorrect", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Your Email is Incorrect", Toast.LENGTH_SHORT).show();
                    }

                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
    private void verify_User() {
        final String[] name = new String[1];

        my_RefCompany= FirebaseDatabase.getInstance().getReference("Companies");

        my_RefCompany.orderByChild("email").equalTo(email.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                counterForIncharge = (int) snapshot.getChildrenCount();
                if (counterForIncharge == 1) {
                    Toast.makeText(LoginActivity.this, "You are Incharge Please enter password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        my_Ref= FirebaseDatabase.getInstance().getReference("Registration_User");

        my_Ref.orderByChild("email").equalTo(email.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    counterForUser = (int) snapshot.getChildrenCount();
                    if (counterForUser == 1) {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("email", email.getText().toString());
                        editor.putString("company", selectCompany.getSelectedItem().toString());
                        editor.putBoolean("keeplogin", Boolean.TRUE);
                        editor.putLong("confirmDate",1);
                        editor.putBoolean("insertvideo",true);
                        editor.putBoolean("insertsong",true);
                        editor.putBoolean("inserthyponosis",true);
                        editor.putInt("counter",sharedpreferences.getInt("counter",0)+1);
                        for (DataSnapshot npsnapshot : snapshot.getChildren()){
                            try {
                                editor.putLong("dateDiffrence",dateDifference.getDifference(npsnapshot.child("date").getValue(String.class)));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this,TermsActivity.class);
                        finish();
                        startActivity(intent);
                    }
                    else if (counterForIncharge==0) {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("email", email.getText().toString());
                        editor.putString("company", selectCompany.getSelectedItem().toString());
                        editor.putBoolean("keeplogin", Boolean.TRUE);
                        editor.putLong("confirmDate",1);
                        editor.putBoolean("insertvideo",true);
                        editor.putBoolean("insertsong",true);
                        editor.putBoolean("inserthyponosis",true);
                        editor.putInt("counter",sharedpreferences.getInt("counter",0)+1);
                        editor.commit();
                        insert_in_database();
                        Intent intent = new Intent(LoginActivity.this, TermsActivity.class);
                        finish();
                        startActivity(intent);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });




    }
private Boolean validation_For_User(){
        if(email.getText().toString().isEmpty()){
            email.setError("Please Enter Valid Email");
            return false;
        }
    if(checkBox.isChecked()) {
        if(userPassword.getText().toString().isEmpty()){
            userPassword.setError("Please Enter Valid Password");
            return false;
        }
    }
        return true;
}
}