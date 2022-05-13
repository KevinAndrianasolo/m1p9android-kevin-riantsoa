package com.kevinandrianasolo.m1p9android.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.kevinandrianasolo.m1p9android.R;
import com.kevinandrianasolo.m1p9android.models.Course;

import java.util.List;

public class CourseAdapter extends BaseAdapter {

    private Context context;
    private List<Course> courseList;
    private LayoutInflater inflater;

    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Course getItem(int i) {
        return courseList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Course currentCourse = getItem(i);

        view = inflater.inflate(R.layout.course_adapter, null);
        TextView titleTextView = view.findViewById(R.id.course_title);
        TextView descriptionTextView = view.findViewById(R.id.course_description);

        titleTextView.setText(currentCourse.getTitle());
        descriptionTextView.setText(currentCourse.getDescription());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("course", currentCourse);

                NavController navController = Navigation.findNavController((Activity) view.getContext(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_course, bundle); // Pass the categoryId to fragment
                //Toast.makeText(view.getContext(), "Click on "+currentCourse.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
