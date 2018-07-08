package com.example.jayhind.audiovideoapp;
import android.app.Activity;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import java.io.IOException;
import java.util.HashMap;

public class surfaceViewActivity extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {
    SurfaceView sfv;
    SurfaceHolder sfh;
    MediaPlayer mp;
    ImageView img;
    //http://192.168.43.55/Meditation/video/Waterfall.mp4
    String url="http://192.168.43.55/Meditation/admin/upload/Video/Emerge+YOUR+WISDOM+BKShivani.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        sfv=findViewById(R.id.sv);
        img=findViewById(R.id.img);
        sfh=sfv.getHolder();
        sfh.addCallback(this);


        Bitmap bitmap;
        bitmap = retriveVideoFrameFromVideo(url);
        if (bitmap != null) {
            bitmap = Bitmap.createScaledBitmap(bitmap, 240, 240, false);
            img.setImageBitmap(bitmap);
        }

    }

    private Bitmap retriveVideoFrameFromVideo(String url) {

        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            if (Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(url, new HashMap<String, String>());
            else
                mediaMetadataRetriever.setDataSource(url);

            bitmap = mediaMetadataRetriever.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mp = new MediaPlayer();
        mp.setDisplay(sfh);
        try {
            mp.setDataSource(url);
            mp.prepare();
            mp.setOnPreparedListener(surfaceViewActivity.this);
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mp.start();
    }
}
