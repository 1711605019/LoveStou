package com.example.lovestou.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageButton;

import com.example.lovestou.R;
import com.example.lovestou.adapter.VideoNewsAdapter;
import com.example.lovestou.bean.VideoBean;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class stNewsActivity extends AppCompatActivity {
    private XRecyclerView recyclerView;
    private ImageButton imageButton;
    private VideoNewsAdapter adapter;
    private List<VideoBean> videoList = new ArrayList<>();
    private int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_news);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        initThread();
        initView();
    }

    private void initThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://st.cutv.com/t/c/index" + (page > 1 ? "_" + page : "") + ".shtml").timeout(100000).get();
                    Elements elements = document.select("div.lp_line").select("ul").select("li");
                    for (int j = 0; j < elements.size(); j++) {
                        String title = elements.get(j).select("a").attr("title");
                        String img = elements.get(j).select("img").attr("src");
                        String href = "http://st.cutv.com" + elements.get(j).select("a").attr("href");

                        VideoBean bean = new VideoBean(title, img, href);
                        videoList.add(bean);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            page++;
                            adapter = new VideoNewsAdapter(getApplicationContext(), videoList);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initView() {
        imageButton = findViewById(R.id.ib_return);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.rv_stNews);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new VideoNewsAdapter(this, videoList);
        recyclerView.setAdapter(adapter);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        // 可以设置是否开启加载更多/下拉刷新
        recyclerView.setLoadingMoreEnabled(true);
        // 可以设置加载更多的样式，很多种
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
        // 如果设置上这个，下拉刷新的时候会显示上次刷新的时间
        recyclerView.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        // 添加刷新和加载更多的监听
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                // 为了看效果，加了一个等待效果，正式的时候直接写mRecyclerView.refreshComplete();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                LoadMore();
            }
        });
    }

    private void LoadMore() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://st.cutv.com/t/c/index" + (page > 1 ? "_" + page : "") + ".shtml").timeout(100000).get();
                    Elements elements = document.select("div.lp_line").select("ul").select("li");
                    for (int j = 0; j < elements.size(); j++) {
                        String title = elements.get(j).select("a").attr("title");
                        String img = elements.get(j).select("img").attr("src");
                        String href = "http://st.cutv.com" + elements.get(j).select("a").attr("href");

                        VideoBean bean = new VideoBean(title, img, href);
                        videoList.add(bean);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //  adapter = new stNewsAdapter(getApplicationContext(),stNewsList);
                            //recyclerView.setAdapter(adapter);
                            if (adapter != null) {
                                adapter.addData(videoList);
                                recyclerView.loadMoreComplete();
                                page += 1;
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
