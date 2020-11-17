package com.example.rmit_android_ass1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmBooking extends AppCompatActivity {

    TextView confirmStudentName,confirmStudentId,
            confirmCourseName,confirmDate,
            confirmTime,confirmOption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_booking);



        Intent i = getIntent();
        String studentName = (String) i.getExtras().get("studentName");
        String studentId = (String) i.getExtras().get("studentID");
        String courseName = (String) i.getExtras().get("courseName");
        String date = (String) i.getExtras().get("date");
        String time = (String) i.getExtras().get("time");
        String option = (String) i.getExtras().get("option");

        confirmStudentName = (TextView) findViewById(R.id.confirmStudentName);
        confirmStudentName.setText(studentName);

        confirmStudentId = (TextView) findViewById(R.id.confirmStudentId);
        confirmStudentId.setText(studentId);

        confirmCourseName = (TextView) findViewById(R.id.confirmCourseName);
        confirmCourseName.setText(courseName);

        confirmDate = (TextView) findViewById(R.id.confirmDate);
        confirmDate.setText(date);

        confirmTime = (TextView) findViewById(R.id.confirmTime);
        confirmTime.setText(time);

        confirmOption = (TextView) findViewById(R.id.confirmOption);
        confirmOption.setText(option);

        Button buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ConfirmBooking.this,MainActivity.class);
                i.putExtra("msg","Booking successfully!");
                setResult(RESULT_OK,i);
                finish();
            }
        });

    }
}