package com.example.atraccis.activities.listOfUsers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atraccis.R;
import com.example.atraccis.activities.LoginActivity;
import com.example.atraccis.coustomViews.ProgressDialogue;
import com.example.atraccis.helpers.DateDifference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;

public class ListOfUsersActivity extends AppCompatActivity {
    private ArrayList<String> username=new ArrayList<String>();

    //Get api Version.
    DatabaseReference my_Ref;
    SharedPreferences sharedpreferences;
    ProgressDialogue progressDialogue;
    //Override onCreate method.

    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);
        progressDialogue=new ProgressDialogue(ListOfUsersActivity.this);
        sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        getUsers();
        recyclerView=findViewById(R.id.recyclerview);

    }

    private void getUsers() {
        progressDialogue.show();
        my_Ref= FirebaseDatabase.getInstance().getReference("Registration_User");
        my_Ref.orderByChild("company").equalTo(sharedpreferences.getString("company","")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    username.add(npsnapshot.child("email").getValue(String.class));
                }
                progressDialogue.dismiss();
                ListOfUsersAdapter listOfUsersAdapter=new ListOfUsersAdapter(username);
                recyclerView.setAdapter(listOfUsersAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



    }

}
