package com.example.lovestou;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lovestou.activity.ChatActivity;
import com.example.lovestou.activity.LoginActivity;
import com.example.lovestou.fragment.FindFragment;
import com.example.lovestou.fragment.HomeFragment;
import com.example.lovestou.fragment.NavFragment;
import com.example.lovestou.fragment.NewsFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eu.long1.spacetablayout.SpaceTabLayout;

public class MainActivity extends AppCompatActivity {
    private SpaceTabLayout spaceTabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private RelativeLayout rl_login,rl_robot;
    private TextView tv_login,tv_weather,tv_temp;
    private ImageView iv_weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spaceTabLayout = findViewById(R.id.spaceTabLayout);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(4);
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new NewsFragment());
        fragmentList.add(new NavFragment());
        fragmentList.add(new FindFragment());

        spaceTabLayout.initialize(viewPager,getSupportFragmentManager(),fragmentList,savedInstanceState);
        spaceTabLayout.setTabFourIcon(R.mipmap.find);

        initWeather();
        initView();
        initLogin();
    }

    private void initWeather() {
        tv_weather = findViewById(R.id.tv_weather);
        tv_temp = findViewById(R.id.tv_temp);
        iv_weather = findViewById(R.id.iv_weather);

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Document document = Jsoup.connect("https://www.tianqi.com/shantou/").timeout(100000).get();
                            Elements elements = document.select("dl.weather_info").select("dd.weather");
                            String href = elements.select("img").attr("src");
                            String temp = elements.select("span").get(0).ownText();
                            String weather = elements.select("span").select("b").text();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv_temp.setText(temp);
                                    tv_weather.setText(weather);
                                    Glide.with(MainActivity.this).load("http:" + href).into(iv_weather);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }

    private void initLogin(){
        tv_login = findViewById(R.id.tv_login);
        rl_login = findViewById(R.id.rl_login);
        rl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        tv_login.setText(name);
//        return meview;
    }


    private void initView() {
        rl_robot = findViewById(R.id.rl_robot);
        rl_robot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        spaceTabLayout.saveState(outState);
        super.onSaveInstanceState(outState);
    }
}
