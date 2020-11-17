package com.example.rmit_android_ass1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ConfirmBooking.this);

                alertDialogBuilder
                        .setTitle("Confirm")
                        .setMessage("Do you want to make a booking?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(ConfirmBooking.this,MainActivity.class);
                                intent.putExtra("msg","Booking successfully!");
                                intent.putExtra("requestCode","200");
                                setResult(RESULT_OK,intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            }
        });

    }
}