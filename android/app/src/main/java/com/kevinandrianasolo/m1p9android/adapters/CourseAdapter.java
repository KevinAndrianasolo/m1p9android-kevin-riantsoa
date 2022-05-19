package com.kevinandrianasolo.m1p9android.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.kevinandrianasolo.m1p9android.utils.MediaUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;
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

        /**
         * Setting course thumbnail, an image wich represent the entire course
         */
        //ImageView thumbnailView = view.findViewById(R.id.course_thumbnail);
        //MediaUtils.loadImage(thumbnailView, currentCourse.getThumbnail());

        /**
         * Fill Course.title and Course.description
         */
        TextView titleTextView = view.findViewById(R.id.course_title);
        TextView descriptionTextView = view.findViewById(R.id.course_description);
        titleTextView.setText(currentCourse.getTitle());
        descriptionTextView.setText(currentCourse.getDescription());

        /**
         * Setting listener to view, on click on a course navigate to the Course details fragment
         */
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Passing parameters to course fragment
                 */
                Bundle bundle = new Bundle();
                bundle.putSerializable("course", currentCourse);

                /**
                 * Navigate to course fragment with the course parameter, which contains courseId, ...
                 */
                NavController navController = Navigation.findNavController((Activity) view.getContext(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_course, bundle); // Pass the categoryId to fragment
            }
        });
        return view;
    }
}
