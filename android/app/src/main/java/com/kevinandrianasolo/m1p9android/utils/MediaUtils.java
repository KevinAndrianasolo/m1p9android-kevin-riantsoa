package com.kevinandrianasolo.m1p9android.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.kevinandrianasolo.m1p9android.R;
import com.squareup.picasso.Picasso;

public class MediaUtils {
    public static ProgressDialog dialog(Context context, String title, String message){
        ProgressDialog progDialog = new ProgressDialog(context);
        progDialog.setTitle("Chargement de la vidéo");
        progDialog.setMessage("Récupération des données nécessaires...");
        progDialog.setIndeterminate(true);
        progDialog.setIcon(R.drawable.ic_baseline_video_library_24);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return progDialog;
    }

    public static void loadVideo(Context context, VideoView videoView, MediaController mediaController, String url, Boolean autostart, ProgressDialog progDialog){
        Uri uri = Uri.parse(url);


        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {

                Toast.makeText(context, "Une erreur est survenue lors du chargement du média", Toast.LENGTH_SHORT).show();
                if(progDialog!=null) progDialog.dismiss();
                progDialog.setMessage("Error: "+ what);
                return false;
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mediaPlayer){
                mediaPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                mediaPlayer.setScreenOnWhilePlaying(false);

                if(autostart) videoView.start();
                if(progDialog!=null) progDialog.dismiss();

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


    }

    public static void loadImage(ImageView imageView, String url){
        Picasso.get().load(url).into(imageView);
    }
}
