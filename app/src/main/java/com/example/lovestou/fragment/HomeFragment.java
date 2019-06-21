package com.example.lovestou.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lovestou.R;
import com.example.lovestou.activity.BannerWebActivity;
import com.example.lovestou.activity.HomeWebActivity;
import com.example.lovestou.activity.NoticeActivity;
import com.example.lovestou.activity.TodayActivity;
import com.example.lovestou.activity.stNewsActivity;
import com.example.lovestou.adapter.CarsAdapter;
import com.example.lovestou.adapter.EducationAdapter;
import com.example.lovestou.adapter.FoodAdapter;
import com.example.lovestou.adapter.HouseAdapter;
import com.example.lovestou.adapter.LookAdapter;
import com.example.lovestou.adapter.NewsAdapter;
import com.example.lovestou.adapter.TravelAdapter;
import com.example.lovestou.bean.CarsBean;
import com.example.lovestou.bean.EducationBean;
import com.example.lovestou.bean.FoodBean;
import com.example.lovestou.bean.HouseBean;
import com.example.lovestou.bean.LookBean;
import com.example.lovestou.bean.TravelBean;
import com.example.lovestou.utils.NewsInterface;
import com.example.lovestou.utils.OkhttpUntil;
import com.example.lovestou.utils.TostringNetworkListining;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sunfusheng.marqueeview.MarqueeView;
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
    private MarqueeView marqueeView;
    private List<Integer> imgs;

    private ImageView stNews_img1,stNews_img2,today_img1,today_img2;
    private TextView stNews_title1,stNews_title2,today_title1,today_title2;

    private LinearLayout ll_fuwu,ll_gongkai,ll_tousu,ll_daohang,ll_stNews,ll_today;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeView = inflater.inflate(R.layout.fragment_home, container, false);
        initBanner();
//        initBannerView();
        initNotice();
        initVideo();
        initllView();
        return homeView;
    }

    private void initllView() {
        ll_fuwu = homeView.findViewById(R.id.ll_fuwu);
        ll_gongkai = homeView.findViewById(R.id.ll_gongkai);
        ll_tousu = homeView.findViewById(R.id.ll_tousu);
        ll_daohang = homeView.findViewById(R.id.ll_daohang);
        ll_stNews = homeView.findViewById(R.id.ll_stNews);
        ll_today = homeView.findViewById(R.id.ll_today);

        ll_fuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.gdzwfw.gov.cn/portal/index?region=440500";
                String name = "政务服务";
                Intent intent = new Intent(getActivity(), HomeWebActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        ll_tousu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://admin.st.gov.cn/u/consult/index/cnst/zfpy";
                String name = "作风投诉";
                Intent intent = new Intent(getActivity(), HomeWebActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        ll_gongkai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.shantou.gov.cn/cnst/index.shtml?s=1";
                String name = "政务公开";
                Intent intent = new Intent(getActivity(), BannerWebActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        ll_daohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.shantou.gov.cn/cnst/index.shtml?s=7";
                String name = "部门导航";
                Intent intent = new Intent(getActivity(), BannerWebActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        ll_stNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), stNewsActivity.class);
                startActivity(intent);
            }
        });

        ll_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TodayActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initVideo() {
        stNews_img1 = homeView.findViewById(R.id.stNews_img1);
        stNews_img2 = homeView.findViewById(R.id.stNews_img2);
        today_img1 = homeView.findViewById(R.id.today_img1);
        today_img2 = homeView.findViewById(R.id.today_img2);

        stNews_title1 = homeView.findViewById(R.id.stNews_title1);
        stNews_title2 = homeView.findViewById(R.id.stNews_title2);
        today_title1 = homeView.findViewById(R.id.today_title1);
        today_title2 = homeView.findViewById(R.id.today_title2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc_stNews = Jsoup.connect("http://st.cutv.com/t/c/index.shtml").timeout(3000).get();
                    Document doc_today = Jsoup.connect("http://st.cutv.com/e/d/index.shtml").timeout(3000).get();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Elements eles_stNews = doc_stNews.select("div.lp_line").select("ul").select("li");
                            Elements eles_today = doc_today.select("div.lp_line").select("ul").select("li");
                            List<String> stNews_titles = new ArrayList<>();
                            List<String> stNews_imgs = new ArrayList<>();
                            List<String> today_titles = new ArrayList<>();
                            List<String> today_imgs = new ArrayList<>();
                            for (int i = 0; i < 2;i ++) {
                                String stNews_title = eles_stNews.get(i).select("a").attr("title");
                                String stNews_img = eles_stNews.get(i).select("img").attr("src");
                                String today_title = eles_today.get(i).select("a").attr("title");
                                String today_img = eles_today.get(i).select("img").attr("src");

                                stNews_titles.add(stNews_title);
                                stNews_imgs.add(stNews_img);
                                today_titles.add(today_title);
                                today_imgs.add(today_img);
                            }
                            stNews_title1.setText(stNews_titles.get(0));
                            stNews_title2.setText(stNews_titles.get(1));
                            Glide.with(getActivity()).load(stNews_imgs.get(0)).placeholder(R.drawable.error).into(stNews_img1);
                            Glide.with(getActivity()).load(stNews_imgs.get(1)).placeholder(R.drawable.error).into(stNews_img2);
                            today_title1.setText(today_titles.get(0));
                            today_title2.setText(today_titles.get(1));
                            Glide.with(getActivity()).load(today_imgs.get(0)).placeholder(R.drawable.error).into(today_img1);
                            Glide.with(getActivity()).load(today_imgs.get(1)).placeholder(R.drawable.error).into(today_img2);
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void initNotice() {
        marqueeView = homeView.findViewById(R.id.marqueeView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("http://www.shantou.gov.cn/cnst/bmzx/wzllist.shtml").get();
                    Elements elements = doc.select("div.wzlm_right").select("ul").select("li");
                    List<String> notice = new ArrayList<>();
                    List<String> noHref = new ArrayList<>();
                    for (int i = 0 ;i < 10 ;i ++) {
                        String no = elements.get(i).select("a").text();
                        String hr = "http://www.shantou.gov.cn"+elements.get(i).select("a").attr("href");
                        notice.add(no);
                        noHref.add(hr);
                    }
                    marqueeView.startWithList(notice);
                    marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, TextView textView) {
                            Intent intent = new Intent(getActivity(), NoticeActivity.class);
                            startActivity(intent);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void initBanner() {
        imgs = new ArrayList<>();
        imgs.add(R.mipmap.banner1);
        imgs.add(R.mipmap.banner2);
        imgs.add(R.mipmap.banner3);
        imgs.add(R.mipmap.banner4);
        imgs.add(R.mipmap.banner5);
        banner = homeView.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
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
        banner.setImages(imgs);
        banner.start();
    }
//    private void initBannerView() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    final Document doc = Jsoup.connect("http://st.cutv.com/").get();
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Elements elements = doc.select("div.focus_img").select("ul").select("li").select("a");
//                            List<String> titles = new ArrayList<>();
//                            List<String> imgs = new ArrayList<>();
//                            for (int i = 0; i < 5; i++) {
//                                String img = elements.get(i).select("img").attr("src");
//                                String title = elements.get(i).select("img").attr("title");
////                                String href = elements.get(i).attr("href");
//                                titles.add(title);
//                                imgs.add(img);
//                            }
//                            banner.setImages(imgs);
//                            banner.start();
//                        }
//                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

}
