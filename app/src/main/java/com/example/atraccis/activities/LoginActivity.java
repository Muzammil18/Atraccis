package com.example.atraccis.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.atraccis.R;
import com.example.atraccis.coustomViews.ProgressDialogue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private int counterForUser=0;
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
    }
    private void onClickListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()) {
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    finish();
                    startActivity(intent);
                }
                else{
                    verify_User();
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
        HashMap<String, Object> map = new HashMap<>();
        map.put("email",email.getText().toString());
        map.put("company",selectCompany.getSelectedItem().toString());
        FirebaseDatabase.getInstance().getReference().child("Registration_User").push()
                .setValue(map);
    }
    private void setSppinner(){
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, companisList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectCompany.setAdapter(adapter);
    }

    private void verify_User() {
        my_Ref= FirebaseDatabase.getInstance().getReference("Registration_User");

        my_Ref.orderByChild("email").equalTo(email.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    counterForUser = (int) snapshot.getChildrenCount();
                    if (counterForUser == 1) {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("email", email.getText().toString());
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        finish();
                        startActivity(intent);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });

        my_RefCompany= FirebaseDatabase.getInstance().getReference("Companies");

        my_RefCompany.orderByChild("email").equalTo(email.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                counterForUser = (int) snapshot.getChildrenCount();
                if (counterForUser == 1) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("email", email.getText().toString());
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        if(counterForUser==0){
            insert_in_database();
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            finish();
            startActivity(intent);
        }


    }

}