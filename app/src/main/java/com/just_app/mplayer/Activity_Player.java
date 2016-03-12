package com.just_app.mplayer;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class Activity_Player extends Activity {
    private Button mButtonStart, mButtonPause, mButtonStop;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    public static final String EXTRA_IMAGE_URL =
            "com.just_app.mplayer_img";
    public static final String EXTRA_SOUND_URL =
            "com.just_app.mplayer_sound";
    private DisplayImageOptions op;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);
        ImageView mImageView = (ImageView) findViewById(R.id.imageArtist);
        mButtonStart = (Button) findViewById(R.id.buttonStart);
        mButtonPause = (Button) findViewById(R.id.buttonPause);
        mButtonStop = (Button) findViewById(R.id.buttonStop);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        op = new DisplayImageOptions.Builder()
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
        imageLoader.displayImage(position_image, mImageView, op, null);

    }
}