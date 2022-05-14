package com.kevinandrianasolo.m1p9android.ui.course;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.media.MediaPlayer;
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
import android.widget.Toast;
import android.widget.VideoView;

import com.kevinandrianasolo.m1p9android.R;
import com.kevinandrianasolo.m1p9android.models.Category;
import com.kevinandrianasolo.m1p9android.models.Course;
import com.kevinandrianasolo.m1p9android.utils.MediaUtils;

public class CourseFragment extends Fragment {

    private CourseViewModel mViewModel;

    public static CourseFragment newInstance() {
        return new CourseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        /**
         * Inflate the layout of Course Fragment and initialize the corresponding view
         */
        View view =  inflater.inflate(R.layout.course_fragment, container, false);
        this.init(view);
        return view;
    }

    public void init(View view){
        /**
         * Getting fragment parameters : "Course"
         */
        Bundle bundle = this.getArguments();
        Course currentCourse = (Course) bundle.get("course");

        /**
         * Show a progress dialog until the video loads
         */
        ProgressDialog progDialog = MediaUtils.dialog(view.getContext(), "Chargement de la vidéo", "Récupération des données nécessaires...");
        progDialog.show();

        /**
         * Instantiate videoView with the current URL, and dismiss progress dialog when the video is loaded
         */
        VideoView videoView = view.findViewById(R.id.course_video);
        MediaUtils.loadVideo(view.getContext(), videoView, currentCourse.getSrc(), true, progDialog);

        /**
         * Setting views variable Course.title and Course.description whith the fragment parameters
         */
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