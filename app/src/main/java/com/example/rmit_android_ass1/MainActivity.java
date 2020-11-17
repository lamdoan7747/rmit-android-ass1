package com.example.rmit_android_ass1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Course> courses;
    CourseListViewAdapter courseListViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        courses = new ArrayList<>();
        courses.add(new Course(1,"Android Development","COSC1111"));
        courses.add(new Course(2,"IOS Development","COSC1112"));
        courses.add(new Course(3,"Practical Data Science","COSC1113"));
        courses.add(new Course(4,"Web Programming","COSC1114"));
        courses.add(new Course(5,"Algorithm & Analysis","COSC1124"));


        courseListViewAdapter = new CourseListViewAdapter(courses);

        ListView listViewCourse = (ListView) findViewById(R.id.listCourse);
        listViewCourse.setAdapter(courseListViewAdapter);

        listViewCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Course course = (Course) courseListViewAdapter.getItem(position);
                Toast.makeText(MainActivity.this,course.getName(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this,BookingDetail.class);
                i.putExtra("course",course);
                startActivityForResult(i,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                String response = (String) data.getExtras().get("msg");
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }
    }
}