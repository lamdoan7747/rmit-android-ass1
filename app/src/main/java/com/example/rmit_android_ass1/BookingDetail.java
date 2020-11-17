package com.example.rmit_android_ass1;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

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
        RadioButton lastRadioButton = (RadioButton) findViewById(R.id.option2);

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
                } else if (radioGroup.getCheckedRadioButtonId() == -1){
                    lastRadioButton.setError("Required!");
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

                    startActivityForResult(i,200);


                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                String response = (String) data.getExtras().get("msg");
                Toast.makeText(BookingDetail.this, response, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BookingDetail.this,MainActivity.class);
                intent.putExtra("msg",response);
                setResult(RESULT_OK,intent);
                finish();
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onDateClick(View view){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.show();
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#FF018786"));
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#FF018786"));
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                EditText date = findViewById(R.id.editTextDate);
                date.setText(String.format("%d-%d-%d", dayOfMonth, month, year));
            }
        });
    }



    public void onTimeClick(View view){
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                ((EditText) findViewById(R.id.editTextTime)).setText(hourOfDay + ":" + minute);
            }
        },hour,minute,android.text.format.DateFormat.is24HourFormat(this));
        timePickerDialog.show();
        timePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#FF018786"));
        timePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#FF018786"));
    }

}