package com.example.lovestou.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lovestou.R;
import com.example.lovestou.adapter.CollectionAdapter;
import com.example.lovestou.bean.DataBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class CollectionActivity extends AppCompatActivity {
    private ImageButton ib_return;
    private RecyclerView recyclerView;
    private CollectionAdapter collectionAdapter;
    private ImageView iv_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        initView();
    }

    private List<DataBean.ItemsBean> getShoucang() {
        SharedPreferences sp = getSharedPreferences("xxx", MODE_PRIVATE);
        String json = sp.getString("sc", "");
        List<DataBean.ItemsBean> list = new Gson().fromJson(json, new TypeToken<List<DataBean.ItemsBean>>() {
        }.getType());
        return list;
    }

    private void initView() {
        ib_return = findViewById(R.id.ib_return);
        ib_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.rv_collection);
        List<DataBean.ItemsBean> collectionList = getShoucang();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        collectionAdapter = new CollectionAdapter(this, collectionList);
        if (collectionList != null) {
            recyclerView.setAdapter(collectionAdapter);
        } else {
            Toast.makeText(this, "没有收藏内容！", Toast.LENGTH_SHORT).show();
        }

        iv_delete = findViewById(R.id.iv_delete);
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectionList.clear();
                collectionAdapter.notifyDataSetChanged();
                Toast.makeText(CollectionActivity.this, "清空完成！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
