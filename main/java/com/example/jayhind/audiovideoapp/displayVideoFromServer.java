package com.example.jayhind.audiovideoapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class displayVideoFromServer extends AppCompatActivity {
    VideoView vtag;
    String url="http://192.168.43.55/Meditation/video/Waterfall.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vtag=findViewById(R.id.video);
        //autoplayed
        Uri uri=Uri.parse(url);
        vtag.setVideoURI(uri);
        vtag.start();
    }
}
