package com.example.lovestou.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovestou.R;
import com.example.lovestou.bean.VideoBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends AppCompatActivity implements UniversalVideoView.VideoViewCallback {
    private static final String TAG = "MainActivity";
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
//    private static final String VIDEO_URL = "http://videofile1.cutv.com/originfileg/010061_t/2019/06/20/G19/G19jkjjpkkopkjmljjjgct_cug.mp4";

    UniversalVideoView mVideoView;
    UniversalMediaController mMediaController;

    View mVideoLayout;
    private ImageView iv_hmst;
    private WebView webView;
    private TextView tv_title, tv_time;
    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullscreen;

    public static VideoBean videoBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        tv_title = findViewById(R.id.tv_title);
        tv_time = findViewById(R.id.tv_time);
        tv_title.setText(videoBean.getTitle().substring(0, videoBean.getTitle().length()-10));
        tv_time.setText(videoBean.getTitle().substring(videoBean.getTitle().length()-10));
        webView = findViewById(R.id.webview_video);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        webView.loadUrl(videoBean.getHref());
        webView.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                String s = request.getUrl().toString();
                if (s.endsWith(".mp4")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            videoView.setVideoURI(Uri.parse(s));
                            mVideoView.setVideoPath(s);//设置视频播放地址

                        }
                    });
                }
                return super.shouldInterceptRequest(view, request);
            }
        });


        iv_hmst = findViewById(R.id.iv_hmst);
        iv_hmst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(VideoActivity.this, HmstVideoActivity.class);
                startActivity(intent1);
            }
        });

        mVideoLayout = findViewById(R.id.video_layout);
        mVideoView = (UniversalVideoView) findViewById(R.id.videoView);
        mMediaController = (UniversalMediaController) findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);
        setVideoAreaSize();//设置屏幕大小和播放地址
        mVideoView.setVideoViewCallback(this);//设置屏幕状态和播放状态的监听
        mVideoView.start();
        //设置播放完成监听
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG, "onCompletion ");
            }
        });



        SharedPreferences hsp = getSharedPreferences("xxVideo", MODE_PRIVATE);
        String json = hsp.getString("ls", "");
        if (json.equals("")) {
            List<VideoBean> list = new ArrayList<>();
            list.add(videoBean);
            hsp.edit().putString("ls", new Gson().toJson(list)).apply();
        } else {
            //从数据库取出来的
            List<VideoBean> list = new Gson().fromJson(json, new TypeToken<List<VideoBean>>() {
            }.getType());
            if (!list.contains(videoBean)){
                list.add(videoBean);
            }else {

            }
            hsp.edit().putString("sc", new Gson().toJson(list)).apply();
        }
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

        } else {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.cachedHeight;
            mVideoLayout.setLayoutParams(layoutParams);
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