package com.example.lovestou.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovestou.R;
import com.example.lovestou.adapter.SearchItemAdapter;
import com.example.lovestou.bean.HistoryBean;
import com.example.lovestou.bean.IDBean;
import com.example.lovestou.utils.NetworkListining;
import com.example.lovestou.utils.OkhttpUntil;

import java.util.ArrayList;
import java.util.List;

import me.james.biuedittext.BiuEditText;

import static android.view.View.VISIBLE;

public class IDActivity extends AppCompatActivity {
    private BiuEditText ed_input;
    private Button btn_send;
    private TextView tv_province, tv_city, tv_town, tv_sex, tv_birth;
    private ImageButton ib_return;

    private List<HistoryBean> historyList = new ArrayList<>();
    private ListView listView;
    private View header_view;
    private SearchItemAdapter searchItemAdapter;
    private TextView tv_deleteAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        initView();
    }

    private void initView() {
        ed_input = findViewById(R.id.et_search);
        tv_province = findViewById(R.id.tv_province);
        tv_city = findViewById(R.id.tv_city);
        tv_town = findViewById(R.id.tv_town);
        tv_sex = findViewById(R.id.tv_sex);
        tv_birth = findViewById(R.id.tv_birth);

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
            }
        });

        ib_return = findViewById(R.id.ib_return);
        ib_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_send = findViewById(R.id.btn_serarch);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = ed_input.getText().toString().trim();
                String sfzNumber = "https://api.jisuapi.com/idcard/query?appkey=c3a90638dc954cb9&idcard=" + input;
                GetSfzInfo(sfzNumber);

                HistoryBean bean = new HistoryBean(input);
                historyList.add(bean);
                searchItemAdapter = new SearchItemAdapter(historyList, IDActivity.this);
                listView.setAdapter(searchItemAdapter);
                listView.setVisibility(View.GONE);
            }
        });
    }

    public void GetSfzInfo(final String url) {
        OkhttpUntil.enqueueGetrequest(url, IDBean.class, new NetworkListining<IDBean>() {
            @Override
            public void BackResultSuccess(IDBean bean, int code) {
                try {
                    if (code == 200) {
                        tv_province.setText(bean.getResult().getProvince());
                        tv_city.setText(bean.getResult().getCity());
                        tv_town.setText(bean.getResult().getTown());
                        tv_sex.setText(bean.getResult().getSex());
                        tv_birth.setText(bean.getResult().getBirth());
                    }
                } catch (Exception e) {
                    Toast.makeText(IDActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void BackResultFail(Exception errow) {
                Toast.makeText(IDActivity.this, "请输入正确的身份证号码", Toast.LENGTH_SHORT).show();

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
