package com.example.lovestou.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.example.lovestou.R;

public class NewsWebActivity extends AppCompatActivity {
    private WebView webView;
    private ImageButton ib_return;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        webView = findViewById(R.id.webView);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        webView.getSettings().setJavaScriptEnabled(true);//是否允许JavaScript脚本运行，默认为false。设置true时，会提醒可能造成XSS漏洞
        webView.getSettings().setSupportZoom(true);//是否可以缩放，默认true
        webView.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webView.getSettings().setAppCacheEnabled(true);//是否使用缓存
        webView.getSettings().setDomStorageEnabled(true);//开启本地DOM存储
        webView.getSettings().setLoadsImagesAutomatically(true); // 加载图片
        webView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        webView.getSettings().setBlockNetworkImage(false) ;
        webView.getSettings().setBlockNetworkLoads(false);
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);//播放音频，多媒体需要用户手动？设置为false为可自动播放
        webView.getSettings().setAppCacheEnabled(true);//是否使用缓存
        webView.getSettings().setDomStorageEnabled(true);//DOM Storage
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                showProgress("页面加载中");//开始加载动画
            }

            @Override
            public void onPageFinished(WebView view, String url1) {
                super.onPageFinished(view, url1);
                view.loadUrl("javascript:function setTop(){document.querySelector('#appDiv').style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.querySelector('#header').style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.querySelector('#footer').style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.querySelector('#news_bottom').style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.querySelector('.app_download').style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.querySelector('.news_title').querySelector('p').style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.querySelector('.vote').style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.querySelector('.r_nav').style.display=\"none\";}setTop();");
//                view.loadUrl("javascript:function setTop(){document.getElementsByClassName('main')[0].style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.getElementsByClassName('main')[1].style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.getElementsByClassName('main')[2].style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.querySelector('.adv').style.display=\"none\";}setTop();");


            }
        });
        webView.loadUrl(url);

        ib_return = findViewById(R.id.ib_return);
        ib_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
