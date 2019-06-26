package com.example.lovestou.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lovestou.R;
import com.example.lovestou.adapter.VideoNewsAdapter;
import com.example.lovestou.bean.VideoBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class VideoHistoryActivity extends AppCompatActivity {
    private ImageButton ib_return;
    private RecyclerView recyclerView;
    private VideoNewsAdapter videoNewsAdapter;
    private ImageView iv_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_history);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);


        initView();
    }

    private List<VideoBean> getHistory() {
        SharedPreferences sp = getSharedPreferences("xxVideo", MODE_PRIVATE);
        String json = sp.getString("ls", "");
        List<VideoBean> list = new Gson().fromJson(json, new TypeToken<List<VideoBean>>() {
        }.getType());
        return list;
    }

    private void initView() {
        recyclerView = findViewById(R.id.rv_video);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        List<VideoBean> videoList = getHistory();
        videoNewsAdapter = new VideoNewsAdapter(this, videoList);
        if (videoList != null) {
            recyclerView.setAdapter(videoNewsAdapter);
        } else {
            Toast.makeText(this, "没有观看记录！", Toast.LENGTH_SHORT).show();
        }

        iv_delete = findViewById(R.id.iv_delete);
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoList.clear();
                videoNewsAdapter.notifyDataSetChanged();
                Toast.makeText(VideoHistoryActivity.this, "清空完成！", Toast.LENGTH_SHORT).show();
            }
        });

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
