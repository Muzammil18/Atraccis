package com.example.atraccis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atraccis.R;

public class TestActivity extends AppCompatActivity {
    TextView testName,question1,question2,question3,question4,question5,question6,question7,btnSubmit;
    EditText question7Ans7;
    CheckBox question1Ans1,question1Ans2,
            question2Ans1,question2Ans2,question2Ans3,question2Ans4,question2Ans5,
            question3Ans1,question3Ans2,question3Ans3,question3Ans4,question3Ans5,
            question4Ans1,question4Ans2,question4Ans3,question4Ans4,question4Ans5,question4Ans6,question4Ans7,
            question5Ans1,question5Ans2,question5Ans3,question5Ans4,question5Ans5,question5Ans6,question5Ans7,question5Ans8,question5Ans9,
            question6Ans1,question6Ans2,question6Ans3,question6Ans4,question6Ans5,question6Ans6,question6Ans7,question6Ans8,question6Ans9,question6Ans10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        find_View_By_Id();
        onClickListener();

    }

    private void find_View_By_Id() {
        testName=findViewById(R.id.textView2);
        question1=findViewById(R.id.textView2);
        question2=findViewById(R.id.textView2);
        question3=findViewById(R.id.textView2);
        question4=findViewById(R.id.textView2);
        question5=findViewById(R.id.textView2);
        question6=findViewById(R.id.textView2);
        question7=findViewById(R.id.textView2);
        question1Ans1=findViewById(R.id.question1checkBox1);
        question1Ans2=findViewById(R.id.question1checkBox2);
        question2Ans1=findViewById(R.id.question2checkBox1);
        question2Ans2=findViewById(R.id.question2checkBox2);
        question2Ans3=findViewById(R.id.question2checkBox3);
        question2Ans4=findViewById(R.id.question2checkBox4);
        question2Ans5=findViewById(R.id.question2checkBox5);
        question3Ans1=findViewById(R.id.question3checkBox1);
        question3Ans2=findViewById(R.id.question3checkBox2);
        question3Ans3=findViewById(R.id.question3checkBox3);
        question3Ans4=findViewById(R.id.question3checkBox4);
        question3Ans5=findViewById(R.id.question3checkBox5);
        question4Ans1=findViewById(R.id.question4checkBox1);
        question4Ans2=findViewById(R.id.question4checkBox2);
        question4Ans3=findViewById(R.id.question4checkBox3);
        question4Ans4=findViewById(R.id.question4checkBox4);
        question4Ans5=findViewById(R.id.question4checkBox5);
        question4Ans6=findViewById(R.id.question4checkBox6);
        question4Ans7=findViewById(R.id.question4checkBox7);
        question5Ans1=findViewById(R.id.question5checkBox1);
        question5Ans2=findViewById(R.id.question5checkBox2);
        question5Ans3=findViewById(R.id.question5checkBox3);
        question5Ans4=findViewById(R.id.question5checkBox4);
        question5Ans5=findViewById(R.id.question5checkBox5);
        question5Ans6=findViewById(R.id.question5checkBox6);
        question5Ans7=findViewById(R.id.question5checkBox7);
        question5Ans8=findViewById(R.id.question5checkBox8);
        question5Ans9=findViewById(R.id.question5checkBox9);
        question6Ans1=findViewById(R.id.question6checkBox1);
        question6Ans2=findViewById(R.id.question6checkBox2);
        question6Ans3=findViewById(R.id.question6checkBox3);
        question6Ans4=findViewById(R.id.question6checkBox4);
        question6Ans5=findViewById(R.id.question6checkBox5);
        question6Ans6=findViewById(R.id.question6checkBox6);
        question6Ans7=findViewById(R.id.question6checkBox7);
        question6Ans8=findViewById(R.id.question6checkBox8);
        question6Ans9=findViewById(R.id.question6checkBox9);
        question6Ans10=findViewById(R.id.question6checkBox10);
        question7Ans7=findViewById(R.id.question7ans);
        btnSubmit=findViewById(R.id.btnsubmit);
    }
    private void onClickListener() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()) {
                    Toast.makeText(TestActivity.this, "ready ti insert in databasse", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    private boolean validation(){
        if((!question1Ans1.isChecked())&&(!question1Ans2.isChecked())){
            Toast.makeText(this, "Please Select at lest one answer from question 1", Toast.LENGTH_SHORT).show();
        return false;
        }
        if(!question2Ans1.isChecked()&&!question2Ans2.isChecked()&&!question2Ans3.isChecked()&&!question2Ans4.isChecked()&&!question2Ans5.isChecked()){
            Toast.makeText(this, "Please Select at lest one answer from question 2", Toast.LENGTH_SHORT).show();
        return false;
        }
        if(!question3Ans1.isChecked()&&!question3Ans2.isChecked()&&!question3Ans3.isChecked()&&!question3Ans4.isChecked()&&!question3Ans5.isChecked()){
            Toast.makeText(this, "Please Select at lest one answer from question 3", Toast.LENGTH_SHORT).show();
        return false;
        }
        if(!question4Ans1.isChecked()&&!question4Ans2.isChecked()&&!question4Ans3.isChecked()&&!question4Ans4.isChecked()&&!question4Ans5.isChecked()&&!question4Ans6.isChecked()&&!question4Ans7.isChecked()){
            Toast.makeText(this, "Please Select at lest one answer from question 4", Toast.LENGTH_SHORT).show();
        return false;
        }
        if(!question5Ans1.isChecked()&&!question5Ans2.isChecked()&&!question5Ans3.isChecked()&&!question5Ans4.isChecked()&&!question5Ans5.isChecked()&&!question5Ans6.isChecked()&&!question5Ans7.isChecked()&&!question5Ans8.isChecked()&&!question5Ans9.isChecked()){
            Toast.makeText(this, "Please Select at lest one answer from question 5", Toast.LENGTH_SHORT).show();
        return false;
        }
        if(!question6Ans1.isChecked()&&!question6Ans2.isChecked()&&!question6Ans3.isChecked()&&!question6Ans4.isChecked()&&!question6Ans5.isChecked()&&!question6Ans6.isChecked()&&!question6Ans7.isChecked()&&!question6Ans8.isChecked()&&!question6Ans9.isChecked()&&!question6Ans10.isChecked()){
            Toast.makeText(this, "Please Select at lest one answer from question 6", Toast.LENGTH_SHORT).show();
        return false;
        }
       if(question7Ans7.getText().toString().isEmpty()){
           Toast.makeText(this, "Please write answer of question 7", Toast.LENGTH_SHORT).show();
           return false;
       }
        return true;
    }
}
