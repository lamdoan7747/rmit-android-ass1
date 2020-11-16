package com.example.rmit_android_ass1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseListViewAdapter extends BaseAdapter {
    final ArrayList<Course> courses;

    CourseListViewAdapter(ArrayList<Course> courses) {
        this.courses = courses;
    }


    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public Object getItem(int position) {
        return courses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View viewCourse;
        if (view == null) {
            viewCourse = View.inflate(viewGroup.getContext(), R.layout.course_view, null);

        } else viewCourse = view;

        Course course = (Course) getItem(position);
        ((TextView) viewCourse.findViewById(R.id.nameCourse)).setText(String.format("%s",course.getName()));
        ((TextView) viewCourse.findViewById(R.id.codeCourse)).setText(String.format("%s",course.getCode()));

        return viewCourse;
    }
}
