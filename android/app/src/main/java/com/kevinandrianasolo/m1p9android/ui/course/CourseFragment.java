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

        VideoView videoView = view.findViewById(R.id.course_video);
        Uri uri = Uri.parse(currentCourse.getSrc());

        MediaController mediaController = new MediaController(view.getContext());
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        ProgressDialog progDailog = new ProgressDialog(view.getContext());
        progDailog.setTitle("Chargement de la vidéo");
        progDailog.setMessage("Récupération des données nécessaires...");
        progDailog.setIndeterminate(true);
        progDailog.setIcon(R.drawable.ic_baseline_video_library_24);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.show();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mediaPlayer){
                videoView.start();
                progDailog.dismiss();
                Toast.makeText(view.getContext(), "Vidéo chargée", Toast.LENGTH_SHORT).show();
                // When video Screen change size.
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                        // Re-Set the videoView that acts as the anchor for the MediaController
                        mediaController.setAnchorView(videoView);
                    }
                });

            }
        });


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