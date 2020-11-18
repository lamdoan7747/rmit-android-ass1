package com.example.rmit_android_ass1;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BookingDetail extends AppCompatActivity{

    private EditText studentName, studentID, datePicker, timePicker;
    private TextView courseName, courseCode;
    private RadioGroup radioGroup;
    private RadioButton lastRadioButton, selectedRadioButton;
    private Button bookEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);

        Intent i = getIntent();
        // Get course object from confirm booking activity
        Course course = (Course) i.getSerializableExtra("course");

        // Set text view of course name
        courseName = findViewById(R.id.courseName);
        courseName.setText(String.format("%s",course.getName()));

        // Set text view of course code
        courseCode = findViewById(R.id.courseCode);
        courseCode.setText(String.format("%s",course.getCode()));

        // Declare all edit text view
        studentName = findViewById(R.id.studentName);
        studentID = findViewById(R.id.studentID);
        datePicker = findViewById(R.id.editTextDate);
        timePicker = findViewById(R.id.editTextTime);

        // Set up option radio button
        radioGroup = findViewById(R.id.optionList);
        lastRadioButton = findViewById(R.id.option2);

        bookEvent = findViewById(R.id.bookEvent);
        bookEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(studentName.length()==0) {
                    studentName.setError(getString(R.string.errorOfView));
                } else if (studentID.length() == 0) {
                    studentID.setError(getString(R.string.errorOfView));
                } else if (datePicker.length() == 0) {
                     datePicker.setError(getString(R.string.errorOfView));
                } else if (timePicker.length() == 0){
                    timePicker.setError(getString(R.string.errorOfView));
                } else if (radioGroup.getCheckedRadioButtonId() == -1){
                    lastRadioButton.setError(getString(R.string.errorOfView));
                } else {
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    selectedRadioButton = findViewById(radioId);

                    Intent i = new Intent(BookingDetail.this,ConfirmBooking.class);
                    i.putExtra("courseName", String.format("%s", course.getName()));
                    i.putExtra("studentName", studentName.getText().toString().toUpperCase());
                    i.putExtra("studentID", studentID.getText().toString());
                    i.putExtra("date", datePicker.getText().toString());
                    i.putExtra("time", timePicker.getText().toString());
                    i.putExtra("option", selectedRadioButton.getText().toString());

                    startActivityForResult(i,200);
                }
            }
        });

    }

    // Access Date Dialog when click the EditText
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onDateClick(View view){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.show();
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                EditText date = findViewById(R.id.editTextDate);
                date.setText(String.format("%d-%d-%d", dayOfMonth, month, year));
            }
        });
    }


    // Access Time Dialog when click the EditText
    public void onTimeClick(View view){
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                ((EditText) findViewById(R.id.editTextTime)).setText(String.format("%d:%d", hourOfDay, minute));
            }
        },hour,minute,android.text.format.DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                if (data != null){
                    String response = (String) data.getExtras().get("msg");
                    Intent intent = new Intent(BookingDetail.this,MainActivity.class);
                    intent.putExtra("msg",response);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        }
    }
}