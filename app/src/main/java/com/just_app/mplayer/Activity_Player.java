package com.just_app.mplayer;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.IOException;

public class Activity_Player extends Activity implements
        MediaPlayer.OnBufferingUpdateListener {
    private Button mButtonStart, mButtonPause, mButtonStop;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private ImageView mImageView;
    private int pos = 0;
    private Uri uri;
    public static final String EXTRA_IMAGE_URL =
            "com.just_app.mplayer_img";
    public static final String EXTRA_SOUND_URL =
            "com.just_app.mplayer_sound";
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);
        findView();
        getIntentData();
        initViews();
    }

    private void initViews() {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(Activity_Player.this, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findView() {
        mImageView = (ImageView) findViewById(R.id.imageArtist);
        mButtonStart = (Button) findViewById(R.id.buttonStart);
        mButtonPause = (Button) findViewById(R.id.buttonPause);
        mButtonStop = (Button) findViewById(R.id.buttonStop);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
    }

    public void startAudio(View v) {
        try {
            if (pos == 0) {
                mediaPlayer.prepare();
            }
            mediaPlayer.start();
            mediaPlayer.setOnBufferingUpdateListener(this);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        mButtonStart.setEnabled(false);
        mButtonPause.setEnabled(true);
        mButtonStop.setEnabled(true);
    }

    public void pauseAudio(View v) {
        mediaPlayer.pause();
        pos = 1;
        mButtonStart.setEnabled(true);
        mButtonPause.setEnabled(false);
        mButtonStop.setEnabled(false);
    }

    public void stopAudio(View v) {
        stop();
    }

    private void stop() {
        if (mediaPlayer != null) {
            pos = 0;
            mediaPlayer.seekTo(0);
            mediaPlayer.stop();
            mButtonPause.setEnabled(false);
            mButtonStop.setEnabled(false);
            mButtonStart.setEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mButtonStop.isEnabled()) {
            stop();
        }
    }

    public void getIntentData() {
        DisplayImageOptions op = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        Intent intent = getIntent();
        String position_image = intent.getStringExtra(EXTRA_IMAGE_URL);
        String position_sound = intent.getStringExtra(EXTRA_SOUND_URL);
        uri = Uri.parse(position_sound);
        imageLoader.displayImage(position_image, mImageView, op, null);
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        seekBar.setMax(mediaPlayer.getDuration() / 100 * percent);
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
    }
}