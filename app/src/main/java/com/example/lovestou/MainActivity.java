package com.example.lovestou;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lovestou.fragment.FindFragment;
import com.example.lovestou.fragment.HomeFragment;
import com.example.lovestou.fragment.NavFragment;
import com.example.lovestou.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import eu.long1.spacetablayout.SpaceTabLayout;

public class MainActivity extends AppCompatActivity {
    private SpaceTabLayout spaceTabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
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
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        spaceTabLayout.saveState(outState);
        super.onSaveInstanceState(outState);
    }
}
