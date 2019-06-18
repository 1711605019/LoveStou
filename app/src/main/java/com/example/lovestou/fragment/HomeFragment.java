package com.example.lovestou.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lovestou.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private View homeView;
    private Banner banner;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeView = inflater.inflate(R.layout.fragment_home, container, false);
        initBanner();
        initBannerView();
        return homeView;
    }
    private void initBanner() {
        banner = homeView.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.CubeOut);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });

    }
    private void initBannerView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Document doc = Jsoup.connect("http://st.cutv.com/").get();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Elements elements = doc.select("div.focus_img").select("ul").select("li").select("a");
                            List<String> titles = new ArrayList<>();
                            List<String> imgs = new ArrayList<>();
                            for (int i = 0; i < 5; i++) {
                                String img = elements.get(i).select("img").attr("src");
                                String title = elements.get(i).select("img").attr("title");
//                                String href = elements.get(i).attr("href");
                                titles.add(title);
                                imgs.add(img);
                            }
//                            banner.setOnBannerListener(new OnBannerListener() {
//                                @Override
//                                public void OnBannerClick(int position) {
//                                    Intent intent = new Intent(getActivity(), BannerWebActivity.class);
//                                    intent.putExtra("url",hrefs.get(position));
//                                    startActivity(intent);
//                                }
//                            });
                            //设置标题集合（当banner样式有显示title时）
                            banner.setBannerTitles(titles);
                            //设置图片集合
                            banner.setImages(imgs);
                            banner.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
