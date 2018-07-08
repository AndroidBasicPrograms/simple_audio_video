package com.example.jayhind.audiovideoapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class fromServerWithControlsActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {
    VideoView vtag;
    int p=0;
    String url="http://192.168.43.55/Meditation/video/Waterfall.mp4";
    MediaController controls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_server_with_controls);
        vtag=findViewById(R.id.video);

        if (controls==null)
        {
        controls=new MediaController(this);
        }

        Uri uri= Uri.parse(url);
        vtag.setVideoURI(uri);
        vtag.setMediaController(controls);
        vtag.setOnPreparedListener(this);

    }

    @Override
    public void onPrepared(MediaPlayer vt) {
        if(p==0)
        {
            vtag.start();
        }
        else
        {
            vtag.pause();
        }
    }
}
