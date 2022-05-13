package com.kevinandrianasolo.m1p9android.ui.course;

import android.arch.lifecycle.ViewModelProvider;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.kevinandrianasolo.m1p9android.R;
import com.kevinandrianasolo.m1p9android.models.Category;
import com.kevinandrianasolo.m1p9android.models.Course;

public class CourseFragment extends Fragment {

    private CourseViewModel mViewModel;

    public static CourseFragment newInstance() {
        return new CourseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.course_fragment, container, false);

        this.init(view);

        return view;
    }

    public void init(View view){
        Bundle bundle = this.getArguments();
        Course currentCourse = (Course) bundle.get("course");

        int resId = view.getContext().getResources().getIdentifier(currentCourse.getSrc(), "raw", view.getContext().getPackageName());


        VideoView videoView = view.findViewById(R.id.course_video);
        String videoPath = "android.resource://"+view.getContext().getPackageName()+"/"+resId;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(view.getContext());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();

        TextView courseTitle = view.findViewById(R.id.course_details_title);
        TextView courseDescription = view.findViewById(R.id.course_details_description);

        courseTitle.setText(currentCourse.getTitle());
        courseDescription.setText(currentCourse.getDescription());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(CourseViewModel.class);
        // TODO: Use the ViewModel
    }

}