package com.example.lovestou.activity;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.lovestou.R;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

public class HmstVideoActivity extends AppCompatActivity implements UniversalVideoView.VideoViewCallback {
    private static final String TAG = "MainActivity";
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
//    private static final String VIDEO_URL = "https://ugcbsy.qq.com/uwMROfz2r57BIaQXGdGnC2deB3dUr_IkisDvBob9yrMGODlR/szg_74135568_50001_53fe0b36d33a4069afb69ab0e580dedf.f622.mp4?sdtfrom=v1010&guid=9352e46e4f3915edfbcf01be2e4e7476&vkey=C6A1C7D4D01E157824116AA5124F0A77D098418AC1AE7ACDBF8E900BE14E30622D697BB3C646F03E55CDD2D6ADCE78378A9C43A78CA7425D657C7609D523DDFAF742D7EBABE60DAF4628364EB6429FD94FA1FADCBD84B002387A47F90A4AF67754F9410F4D3AB9236F1099EA94BC97DE12F5C351A14E9BBA7A2E5702805EEE7A";
    private static final String VIDEO_URL = "https://ugcws.video.gtimg.com/uwMROfz2r5zBIaQXGdGnC2dfDma3J1MItM3912IN4IRQvkRM/u0876ymdwaw.mp4?sdtfrom=v3010&guid=73aa75e3edf53fafefa5cfe9d9e403f6&vkey=6781226CC5394015216C07FD31CE0E4FA106D53A0306CA7B6DDBE3E20B27BBD1B1ED1A8D01CCBAA8DABC0011189C1EB28EDF4F601D040539387DB4709882ACA70297B5CC0F4E25F6E84703A6949BD54C1DD63B4619F43C913058DADFEBB1DC558EC592BF6E20F733F4393C3B6F4A2A698D40CD44C915FE05B83D558A8FAE22EA&platform=2";

    UniversalVideoView mVideoView;
    UniversalMediaController mMediaController;

    View mBottomLayout;
    View mVideoLayout;

    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullscreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hmst_video);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        mVideoLayout = findViewById(R.id.video_layout);
        mBottomLayout = findViewById(R.id.bottom_layout);
        mVideoView = (UniversalVideoView) findViewById(R.id.videoView);
        mMediaController = (UniversalMediaController) findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);
        setVideoAreaSize();//设置屏幕大小和播放地址
        mVideoView.setVideoViewCallback(this);//设置屏幕状态和播放状态的监听
        //设置播放完成监听
        mVideoView.start();
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG, "onCompletion ");
            }
        });

    }
    //    暂停
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause ");
        if (mVideoView != null && mVideoView.isPlaying()) {
            mSeekPosition = mVideoView.getCurrentPosition();
            Log.d(TAG, "onPause mSeekPosition=" + mSeekPosition);
            mVideoView.pause();
        }
    }

    /**
     * 置视频区域大小
     */
    private void setVideoAreaSize() {
        mVideoLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = mVideoLayout.getWidth();
                cachedHeight = (int) (width * 405f / 720f);
//                cachedHeight = (int) (width * 3f / 4f);
//                cachedHeight = (int) (width * 9f / 16f);
                ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cachedHeight;
                mVideoLayout.setLayoutParams(videoLayoutParams);//设置视频播放视图的大小
                mVideoView.setVideoPath(VIDEO_URL);//设置视频播放地址
                mVideoView.requestFocus();//获取焦点
            }
        });
    }

    //当保存情况状态
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState Position=" + mVideoView.getCurrentPosition());
        outState.putInt(SEEK_POSITION_KEY, mSeekPosition);
    }
    //当恢复情况状态
    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        mSeekPosition = outState.getInt(SEEK_POSITION_KEY);
        Log.d(TAG, "onRestoreInstanceState Position=" + mSeekPosition);
    }

    //全屏和默认的切换

    public void onScaleChange(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;//是否是全屏
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoLayout.setLayoutParams(layoutParams);
            mBottomLayout.setVisibility(View.GONE);

        } else {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.cachedHeight;
            mVideoLayout.setLayoutParams(layoutParams);
            mBottomLayout.setVisibility(View.VISIBLE);
        }

        switchTitleBar(!isFullscreen);
    }

    private void switchTitleBar(boolean show) {
        android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (show) {
                supportActionBar.show();
            } else {
                supportActionBar.hide();
            }
        }
    }


    public void onPause(MediaPlayer mediaPlayer) { // 视频暂停
        Log.d(TAG, "onPause UniversalVideoView callback");
    }


    public void onStart(MediaPlayer mediaPlayer) { // 视频开始播放或恢复播放
        Log.d(TAG, "onStart UniversalVideoView callback");
    }


    public void onBufferingStart(MediaPlayer mediaPlayer) {// 视频开始缓冲
        Log.d(TAG, "onBufferingStart UniversalVideoView callback");
    }


    public void onBufferingEnd(MediaPlayer mediaPlayer) {// 视频结束缓冲
        Log.d(TAG, "onBufferingEnd UniversalVideoView callback");
    }

    @Override
    public void onBackPressed() {
        if (this.isFullscreen) {
            mVideoView.setFullscreen(false);//不设置全屏
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}