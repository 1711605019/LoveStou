package com.example.lovestou.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovestou.R;
import com.example.lovestou.adapter.SearchItemAdapter;
import com.example.lovestou.bean.HistoryBean;
import com.example.lovestou.bean.PostBean;
import com.example.lovestou.utils.NetworkListining;
import com.example.lovestou.utils.OkhttpUntil;

import java.util.ArrayList;
import java.util.List;

import me.james.biuedittext.BiuEditText;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class PostActivity extends AppCompatActivity {
    private BiuEditText ed_input;
    private Button btn_send;
    private TextView tv_province, tv_city, tv_town;
    private ImageButton ib_return;

    private List<HistoryBean> historyList = new ArrayList<>();
    private ListView listView;
    private View header_view;
    private SearchItemAdapter searchItemAdapter;
    private TextView tv_deleteAll;
    private LinearLayout ll_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        initView();

    }

    private void initView() {
        ed_input = findViewById(R.id.et_search);
        btn_send = findViewById(R.id.btn_serarch);
        tv_province = findViewById(R.id.tv_province);
        tv_city = findViewById(R.id.tv_city);
        tv_town = findViewById(R.id.tv_town);
        ib_return = findViewById(R.id.ib_return);
        ll_content = findViewById(R.id.ll_content);

        listView = findViewById(R.id.mRecyclerView);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        header_view = inflater.inflate(R.layout.history_header, null);
        listView.addHeaderView(header_view);
        tv_deleteAll = header_view.findViewById(R.id.tv_deleteAll);
        tv_deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyList.clear();
                searchItemAdapter.notifyDataSetChanged();
            }
        });

        ed_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(VISIBLE);
                ll_content.setVisibility(GONE);
            }
        });

        ib_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = ed_input.getText().toString().trim();
                String postNumber = "https://api.jisuapi.com/zipcode/query?appkey=96df8b4b4f2d3890&zipcode=" + input;
                GetPostInfo(postNumber);

                if (!input.equals("")) {
                    HistoryBean bean = new HistoryBean(input);
                    historyList.add(bean);
                    searchItemAdapter = new SearchItemAdapter(historyList, PostActivity.this);
                    listView.setAdapter(searchItemAdapter);
                    listView.setVisibility(GONE);
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(ed_input.getWindowToken(), 0);

            }
        });
    }

    public void GetPostInfo(final String url) {
        OkhttpUntil.enqueueGetrequest(url, PostBean.class, new NetworkListining<PostBean>() {
            @Override
            public void BackResultSuccess(PostBean bean, int code) {
                try {
                    if (code == 200) {
                        tv_province.setText(bean.getResult().get(0).getProvince());
                        tv_city.setText(bean.getResult().get(0).getCity());
                        tv_town.setText(bean.getResult().get(0).getTown());
                        ll_content.setVisibility(VISIBLE);
                    }
                } catch (Exception e) {
                    Toast.makeText(PostActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void BackResultFail(Exception errow) {
                Toast.makeText(PostActivity.this, "请输入正确的邮政编码", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void tostring(String responseString) {

            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

}
