package com.example.lovestou;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lovestou.activity.ChatActivity;
import com.example.lovestou.activity.CollectionActivity;
import com.example.lovestou.activity.LoginActivity;
import com.example.lovestou.activity.SettingActivity;
import com.example.lovestou.activity.UserInfoActivity;
import com.example.lovestou.activity.VideoHistoryActivity;
import com.example.lovestou.fragment.FindFragment;
import com.example.lovestou.fragment.HomeFragment;
import com.example.lovestou.fragment.NavFragment;
import com.example.lovestou.fragment.NewsFragment;
import com.example.lovestou.receiver.UpdateUserInfoReceiver;
import com.example.lovestou.utils.DBUtils;
import com.example.lovestou.utils.UtilsHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import eu.long1.spacetablayout.SpaceTabLayout;

public class MainActivity extends AppCompatActivity {
    private SpaceTabLayout spaceTabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private RelativeLayout rl_robot, rl_collection, rl_history, rl_me, rl_Setting;
    private TextView tv_login, tv_weather, tv_temp;
    private ImageView iv_weather;

    private boolean isLogin = false;
    private UpdateUserInfoReceiver updateUserInfoReceiver;
    private CircleImageView iv_login;
    private IntentFilter filter;

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

        spaceTabLayout.initialize(viewPager, getSupportFragmentManager(), fragmentList, savedInstanceState);
        spaceTabLayout.setTabFourIcon(R.mipmap.find);

        initWeather();
        initView();
    }

    public void setLoginParams(boolean isLogin) {
        if (isLogin) {
            String userName = UtilsHelper.readLoginUserName(this);
            tv_login.setText(userName);
            String head = DBUtils.getInstance(MainActivity.this).getUserHead(userName);
            Bitmap bt = BitmapFactory.decodeFile(head);
            if (bt != null) {
                Drawable drawable = new BitmapDrawable(bt);//转换成drawable
                iv_login.setImageDrawable(drawable);
            } else {
                iv_login.setImageResource(R.drawable.header);
            }
        } else {
            iv_login.setImageResource(R.drawable.header);
            tv_login.setText("点击登录");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            boolean isLogin = data.getBooleanExtra("isLogin", false);
            setLoginParams(isLogin);
            this.isLogin = isLogin;
        }
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

        rl_collection = findViewById(R.id.rl_collection);
        rl_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin) {
                    Intent collection = new Intent(MainActivity.this, CollectionActivity.class);
                    startActivity(collection);
                } else {
                    Toast.makeText(MainActivity.this, "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rl_history = findViewById(R.id.rl_history);
        rl_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VideoHistoryActivity.class);
                startActivity(intent);
            }
        });

        rl_Setting = findViewById(R.id.rl_setting);
        rl_Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin) {
                    //跳转到设置界面
                    Intent settingIntent = new Intent(MainActivity.this, SettingActivity.class);
                    startActivityForResult(settingIntent, 1);
                } else {
                    Toast.makeText(MainActivity.this, "您还未登录，请先登录",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        rl_me = findViewById(R.id.rl_me);
        rl_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin) {
                    //跳转到个人资料界面
                    Intent userinfo = new Intent(MainActivity.this, UserInfoActivity.class);
                    startActivity(userinfo);
                } else {
                    //跳转到登录界面
                    Intent login = new Intent(MainActivity.this, LoginActivity.class);
                    startActivityForResult(login, 1);
                }
            }
        });

        tv_login = findViewById(R.id.tv_login);
        iv_login = findViewById(R.id.iv_login);
        iv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin) {
                    //跳转到个人资料界面
                    Intent userinfo = new Intent(MainActivity.this, UserInfoActivity.class);
                    startActivity(userinfo);
                } else {
                    //跳转到登录界面
                    Intent login = new Intent(MainActivity.this, LoginActivity.class);
                    startActivityForResult(login, 1);
                }
            }
        });
        setLoginParams(isLogin);
        receiver();
    }

    private void receiver() {
        updateUserInfoReceiver = new UpdateUserInfoReceiver(
                new UpdateUserInfoReceiver.BaseOnReceiveMsgListener() {
                    @Override
                    public void onReceiveMsg(Context context, Intent intent) {
                        String action = intent.getAction();
                        if (UpdateUserInfoReceiver.ACTION.UPDATE_USERINFO
                                .equals(action)) {
                            String type = intent.getStringExtra(UpdateUserInfoReceiver.
                                    INTENT_TYPE.TYPE_NAME);
                            if (UpdateUserInfoReceiver.INTENT_TYPE.UPDATE_HEAD  //更新头像
                                    .equals(type)) {
                                String head = intent.getStringExtra("head");
                                Bitmap bt = BitmapFactory.decodeFile(head);
                                if (bt != null) {
                                    Drawable drawable = new BitmapDrawable(bt);
                                    iv_login.setImageDrawable(drawable);
                                } else {
                                    iv_login.setImageResource(R.drawable.header);
                                }
                            }
                        }
                    }
                });
        filter = new IntentFilter(UpdateUserInfoReceiver.ACTION.UPDATE_USERINFO);
        registerReceiver(updateUserInfoReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (updateUserInfoReceiver != null) {
            unregisterReceiver(updateUserInfoReceiver);
        }
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        spaceTabLayout.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    protected long exitTime; //记录第一次点击时的时间

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                MainActivity.this.finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
