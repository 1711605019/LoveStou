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
import com.example.lovestou.bean.OilBean;
import com.example.lovestou.utils.NetworkListining;
import com.example.lovestou.utils.OkhttpUntil;

import java.util.ArrayList;
import java.util.List;

import me.james.biuedittext.BiuEditText;

import static android.view.View.VISIBLE;

public class OilActivity extends AppCompatActivity {
    private BiuEditText ed_input;
    private Button btn_send;
    private TextView tv_time,tv_89,tv_90,tv_92,tv_93,tv_95,tv_97,tv_00;
    private ImageButton ib_return;

    private List<HistoryBean> historyList = new ArrayList<>();
    private ListView listView;
    private View header_view;
    private SearchItemAdapter searchItemAdapter;
    private TextView tv_deleteAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        initView();
    }

    private void initView() {
        ed_input = findViewById(R.id.et_search);
        tv_time = findViewById(R.id.tv_time);
        tv_00 = findViewById(R.id.tv_00);
        tv_89 = findViewById(R.id.tv_89);
        tv_90 = findViewById(R.id.tv_90);
        tv_92 = findViewById(R.id.tv_92);
        tv_93 = findViewById(R.id.tv_93);
        tv_95 = findViewById(R.id.tv_95);
        tv_97 = findViewById(R.id.tv_97);

        listView = findViewById(R.id.mRecyclerView);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        header_view = inflater.inflate(R.layout.history_header,null);
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
                String info = "https://api.jisuapi.com/oil/query?appkey=c3a90638dc954cb9&province=" + input;
                GetOilInfo(info);

                HistoryBean bean = new HistoryBean(input);
                historyList.add(bean);
                searchItemAdapter = new SearchItemAdapter(historyList,OilActivity.this);
                listView.setAdapter(searchItemAdapter);
                listView.setVisibility(View.GONE);
            }
        });
    }
    public void GetOilInfo(final String url) {
        OkhttpUntil.enqueueGetrequest(url, OilBean.class, new NetworkListining<OilBean>() {
            @Override
            public void BackResultSuccess(OilBean bean, int code) {
                try{
                    if(code==200){
                        tv_time.setText(bean.getResult().getUpdatetime());
                        tv_00.setText(bean.getResult().getOil0()+"元/升");
                        tv_89.setText(bean.getResult().getOil89()+"元/升");
                        tv_90.setText(bean.getResult().getOil90()+"元/升");
                        tv_92.setText(bean.getResult().getOil92()+"元/升");
                        tv_93.setText(bean.getResult().getOil93()+"元/升");
                        tv_95.setText(bean.getResult().getOil95()+"元/升");
                        tv_97.setText(bean.getResult().getOil97()+"元/升");
                    }
                }catch (Exception e){
                    Toast.makeText(OilActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void BackResultFail(Exception errow) {
                Toast.makeText(OilActivity.this, "请输入正确的信息", Toast.LENGTH_SHORT).show();

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