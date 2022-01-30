package com.example.atracc.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atracc.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FollowUpTestActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    TextView testName,question1,question2,question3,question4,question5,question6,question7,btnSubmit;
    EditText question7Ans7;
    CheckBox question1Ans1,question1Ans2,question1Ans3,question1Ans4,
            question2Ans1,question2Ans2,question2Ans3,question2Ans4,question2Ans5,
            question3Ans1,question3Ans2,question3Ans3,question3Ans4,question3Ans5,
            question4Ans1,question4Ans2,question4Ans3,question4Ans4,question4Ans5,question4Ans6,question4Ans7,
            question5Ans1,question5Ans2,question5Ans3,question5Ans4,question5Ans5,question5Ans6,question5Ans7,question5Ans8,question5Ans9,
            question6Ans1,question6Ans2,question6Ans3,question6Ans4,question6Ans5,question6Ans6,question6Ans7,question6Ans8,question6Ans9,question6Ans10;
    String strQuestion1Ans1="",strQuestion1Ans2="",strQuestion1Ans3="",strQuestion1Ans4="",
            strQuestion2Ans1="",strQuestion2Ans2="",strQuestion2Ans3="",strQuestion2Ans4="",strQuestion2Ans5="",
            strQuestion3Ans1="",strQuestion3Ans2="",strQuestion3Ans3="",strQuestion3Ans4="",strQuestion3Ans5="",
            strQuestion4Ans1="",strQuestion4Ans2="",strQuestion4Ans3="",strQuestion4Ans4="",strQuestion4Ans5="",strQuestion4Ans6="",strQuestion4Ans7="",
            strQuestion5Ans1="",strQuestion5Ans2="",strQuestion5Ans3="",strQuestion5Ans4="",strQuestion5Ans5="",strQuestion5Ans6="",strQuestion5Ans7="",strQuestion5Ans8="",strQuestion5Ans9="",
            strQuestion6Ans1="",strQuestion6Ans2="",strQuestion6Ans3="",strQuestion6Ans4="",strQuestion6Ans5="",strQuestion6Ans6="",strQuestion6Ans7="",strQuestion6Ans8="",strQuestion6Ans9="",strQuestion6Ans10="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_up_test);
        find_View_By_Id();
        onClickListener();

    }

    private void find_View_By_Id() {
        sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
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
        question1Ans3=findViewById(R.id.question1checkBox3);
        question1Ans4=findViewById(R.id.question1checkBox4);
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
                    insertFollowTestDetail();
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean("FollowTest", true);
                    editor.commit();
                    Intent intent=new Intent(FollowUpTestActivity.this,DashboardActivity.class);
                    finish();
                    startActivity(intent);                }


            }
        });

    }
    private boolean validation(){
        if((!question1Ans1.isChecked())&&(!question1Ans2.isChecked())&&(!question1Ans3.isChecked())&&(!question1Ans4.isChecked())){
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
    private void getAnswers(){
        if(question1Ans1.isChecked()){
            strQuestion1Ans1=question1Ans1.getText().toString();
        }
        if(question1Ans2.isChecked()){
            strQuestion1Ans2=question1Ans2.getText().toString();
        }
        if(question1Ans3.isChecked()){
            strQuestion1Ans3=question1Ans3.getText().toString();
        }
        if(question1Ans4.isChecked()){
            strQuestion1Ans4=question1Ans4.getText().toString();
        }
        if(question2Ans1.isChecked()){
            strQuestion2Ans1=question2Ans1.getText().toString();
        }
        if(question2Ans2.isChecked()){
            strQuestion2Ans2=question3Ans2.getText().toString();
        }
        if(question2Ans3.isChecked()){
            strQuestion2Ans3=question2Ans3.getText().toString();
        }
        if(question2Ans4.isChecked()){
            strQuestion2Ans4=question2Ans4.getText().toString();
        }
        if(question2Ans5.isChecked()){
            strQuestion2Ans5=question2Ans5.getText().toString();
        }
        if(question3Ans1.isChecked()){
            strQuestion3Ans1=question3Ans1.getText().toString();
        }
        if(question3Ans2.isChecked()){
            strQuestion3Ans2=question3Ans2.getText().toString();
        }
        if(question3Ans3.isChecked()){
            strQuestion3Ans3=question3Ans3.getText().toString();
        }
        if(question3Ans4.isChecked()){
            strQuestion3Ans4=question3Ans4.getText().toString();
        }
        if(question3Ans5.isChecked()){
            strQuestion3Ans5=question3Ans5.getText().toString();
        }

        if(question4Ans1.isChecked()){
            strQuestion4Ans1=question4Ans1.getText().toString();
        }
        if(question4Ans2.isChecked()){
            strQuestion4Ans2=question4Ans2.getText().toString();
        }
        if(question4Ans3.isChecked()){
            strQuestion4Ans3=question4Ans3.getText().toString();
        }
        if(question4Ans4.isChecked()){
            strQuestion4Ans4=question4Ans4.getText().toString();
        }
        if(question4Ans5.isChecked()){
            strQuestion4Ans5=question4Ans5.getText().toString();
        }
        if(question4Ans6.isChecked()){
            strQuestion4Ans6=question4Ans6.getText().toString();
        }
        if(question4Ans7.isChecked()){
            strQuestion4Ans7=question4Ans7.getText().toString();
        }

        if(question5Ans1.isChecked()){
            strQuestion5Ans1=question5Ans1.getText().toString();
        }
        if(question5Ans2.isChecked()){
            strQuestion5Ans2=question5Ans2.getText().toString();
        }
        if(question5Ans3.isChecked()){
            strQuestion5Ans3=question5Ans3.getText().toString();
        }
        if(question5Ans4.isChecked()){
            strQuestion5Ans4=question5Ans4.getText().toString();
        }
        if(question5Ans5.isChecked()){
            strQuestion5Ans5=question5Ans5.getText().toString();
        }
        if(question5Ans6.isChecked()){
            strQuestion5Ans6=question5Ans6.getText().toString();
        }
        if(question5Ans7.isChecked()){
            strQuestion5Ans7=question5Ans7.getText().toString();
        }
        if(question5Ans8.isChecked()){
            strQuestion5Ans8=question5Ans8.getText().toString();
        }
        if(question5Ans9.isChecked()){
            strQuestion5Ans9=question5Ans9.getText().toString();
        }

        if(question6Ans1.isChecked()){
            strQuestion6Ans1=question6Ans1.getText().toString();
        }
        if(question6Ans2.isChecked()){
            strQuestion6Ans2=question6Ans2.getText().toString();
        }
        if(question6Ans3.isChecked()){
            strQuestion6Ans3=question6Ans3.getText().toString();
        }
        if(question6Ans4.isChecked()){
            strQuestion6Ans4=question6Ans4.getText().toString();
        }
        if(question6Ans5.isChecked()){
            strQuestion6Ans5=question6Ans5.getText().toString();
        }
        if(question6Ans6.isChecked()){
            strQuestion6Ans6=question6Ans6.getText().toString();
        }
        if(question6Ans7.isChecked()){
            strQuestion6Ans7=question6Ans7.getText().toString();
        }
        if(question6Ans8.isChecked()){
            strQuestion6Ans8=question6Ans8.getText().toString();
        }
        if(question6Ans9.isChecked()){
            strQuestion6Ans9=question6Ans9.getText().toString();
        }
        if(question6Ans10.isChecked()){
            strQuestion6Ans10=question6Ans10.getText().toString();
        }
    }
    private void insertFollowTestDetail(){
        getAnswers();
        HashMap<String, Object> map = new HashMap<>();
        map.put("email",sharedpreferences.getString("email",""));
        map.put("Question1",question1.getText().toString());
        map.put("Answer1",strQuestion1Ans1+" "+strQuestion1Ans2);
        map.put("Question2",question2.getText().toString());
        map.put("Answer2",strQuestion2Ans1+" "+strQuestion2Ans2+" "+strQuestion2Ans3+" "+strQuestion2Ans4+" "+strQuestion2Ans5);
        map.put("Question3",question3.getText().toString());
        map.put("Answer3",strQuestion3Ans1+" "+strQuestion3Ans2+" "+strQuestion3Ans3+" "+strQuestion3Ans4+" "+strQuestion3Ans5);
        map.put("Question4",question4.getText().toString());
        map.put("Answer4",strQuestion4Ans1+" "+strQuestion4Ans2+" "+strQuestion4Ans3+" "+strQuestion4Ans4+" "+strQuestion4Ans5+" "+strQuestion4Ans6+" "+strQuestion4Ans7);
        map.put("Question5",question5.getText().toString());
        map.put("Answer5",strQuestion5Ans1+" "+strQuestion5Ans2+" "+strQuestion5Ans3+" "+strQuestion5Ans4+" "+strQuestion5Ans5+" "+strQuestion5Ans6+" "+strQuestion5Ans7+" "+strQuestion5Ans8+" "+strQuestion5Ans9);
        map.put("Question6",question6.getText().toString());
        map.put("Answer6",strQuestion6Ans1+" "+strQuestion6Ans2+" "+strQuestion6Ans3+" "+strQuestion6Ans4+" "+strQuestion6Ans5+" "+strQuestion6Ans6+" "+strQuestion6Ans7+" "+strQuestion6Ans8+" "+strQuestion6Ans9+" "+strQuestion6Ans10);
       map.put("Question7",question7.getText().toString());
        map.put("Answer7",question7Ans7.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("Initial_Test").push()
                .setValue(map);
    }
}
