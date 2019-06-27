package com.example.lovestou.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.lovestou.R;
import com.example.lovestou.activity.HmstVideoActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavFragment extends Fragment {
    private View navView;
    private ImageView iv_hmst;
    private WebView webView;

    public NavFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        navView = inflater.inflate(R.layout.fragment_nav, container, false);

        initView();
        return navView;
    }

    private void initView() {
        iv_hmst = navView.findViewById(R.id.iv_hmst);
        iv_hmst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HmstVideoActivity.class);
                startActivity(intent);
            }
        });

        webView = navView.findViewById(R.id.webview_nav);
        String url = "https://map.51240.com/shantou__map/";
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
                view.loadUrl("javascript:function setTop(){document.querySelector('#dituxianshi_td_zuo').style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.querySelector('#api_map_k_j').style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.querySelector('#dao_hang_tiao_lian_jie').style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.querySelector('#top').style.display=\"none\";}setTop();");
            }
        });
        webView.loadUrl(url);

    }
}
