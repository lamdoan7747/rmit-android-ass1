package com.example.rmit_android_ass1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BookingDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);


        TextView courseName = (TextView) findViewById(R.id.courseName);
        TextView courseCode = (TextView) findViewById(R.id.courseCode);

        Intent i = getIntent();
        Course course = (Course) i.getSerializableExtra("course");
        courseName.setText(String.format("%s",course.getName()));
        courseCode.setText(String.format("%s",course.getCode()));

        // Declare all compononts
        EditText studentName = (EditText) findViewById(R.id.studentName);
        EditText studentID = (EditText) findViewById(R.id.studentID);
        EditText datePicker = (EditText) findViewById(R.id.editTextDate);
        EditText timePicker = (EditText) findViewById(R.id.editTextTime);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.optionList);


        Button bookEvent = (Button) findViewById(R.id.bookEvent);
        bookEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(studentName.length()==0) {
                    studentName.setError("Required!");
                } else if (studentID.length() == 0) {
                    studentID.setError("Required!");
                } else if (datePicker.length() == 0) {
                     datePicker.setError("Required!");
                } else if (timePicker.length() == 0){
                    timePicker.setError("Required!");
                } else {
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) findViewById(radioId);

                    Intent i = new Intent(BookingDetail.this,ConfirmBooking.class);
                    i.putExtra("courseName", String.format("%s",course.getName()));
                    i.putExtra("studentName", studentName.getText().toString());
                    i.putExtra("studentID", studentID.getText().toString());
                    i.putExtra("date", datePicker.getText().toString());
                    i.putExtra("time",timePicker.getText().toString());
                    i.putExtra("option",radioButton.getText().toString());

                    i.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                    startActivity(i);
                    finish();

                }
            }
        });

    }
}