package com.example.atracc.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atracc.R;

public class TermsActivity extends AppCompatActivity  {
        SharedPreferences sharedpreferences;
        TextView txtnext;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        find_View_By_Id();



}

        private void find_View_By_Id() {
                sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
                txtnext=findViewById(R.id.btnsubmit);
                txtnext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putBoolean("terms", true);
                                editor.commit();
                                Intent intent=new Intent(TermsActivity.this,TestActivity.class);
                                finish();
                                startActivity(intent);
                        }
                });
        }

}