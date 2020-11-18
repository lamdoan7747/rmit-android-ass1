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

    private TextView confirmStudentName,confirmStudentId,
            confirmCourseName,confirmDate,
            confirmTime,confirmOption;
    private Button buttonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_booking);
        Intent i = getIntent();

        // set view of student name
        String studentName = (String) i.getExtras().get("studentName");
        confirmStudentName = findViewById(R.id.confirmStudentName);
        confirmStudentName.setText(studentName);

        // set view of student ID
        String studentId = (String) i.getExtras().get("studentID");
        confirmStudentId = findViewById(R.id.confirmStudentId);
        confirmStudentId.setText(studentId);

        // set view of course name
        String courseName = (String) i.getExtras().get("courseName");
        confirmCourseName = findViewById(R.id.confirmCourseName);
        confirmCourseName.setText(courseName);

        // set view of date
        String date = (String) i.getExtras().get("date");
        confirmDate = findViewById(R.id.confirmDate);
        confirmDate.setText(date);

        // set view of time
        String time = (String) i.getExtras().get("time");
        confirmTime = findViewById(R.id.confirmTime);
        confirmTime.setText(time);

        // set view of option
        String option = (String) i.getExtras().get("option");
        confirmOption = findViewById(R.id.confirmOption);
        confirmOption.setText(option);

        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ConfirmBooking.this);
                alertDialogBuilder
                        .setTitle(R.string.confirmTitle)
                        .setMessage(R.string.messageDialog)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(ConfirmBooking.this,MainActivity.class);
                                intent.putExtra("msg",getString(R.string.bookingStatus));
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