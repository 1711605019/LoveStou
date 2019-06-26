package com.example.lovestou.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lovestou.R;
import com.example.lovestou.adapter.LayoutMsgViewAdapter;
import com.example.lovestou.bean.MsgItem;
import com.example.lovestou.utils.ChartRespong;
import com.example.lovestou.utils.UiChart;

import java.util.ArrayList;
import java.util.List;

import me.james.biuedittext.BiuEditText;

public class ChatActivity extends AppCompatActivity implements ChartRespong {
    private ListView listView;
    private BiuEditText input_box;
    private Button send_btn;
    private List<MsgItem> msg_list;
    private LayoutMsgViewAdapter msgAdapter;
    private UiChart uiChart;
    private View header_view;
    private ImageButton ib_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        uiChart = new UiChart(this);
        uiChart.setChartRespong(this);

        initView();
    }

    private void initView() {
        listView = findViewById(R.id.msg_list_view);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        header_view = inflater.inflate(R.layout.chat_header, null);
        listView.addHeaderView(header_view);
        input_box = findViewById(R.id.input_box);
        send_btn = findViewById(R.id.send_btn);
        msg_list = new ArrayList<MsgItem>();
        msgAdapter = new LayoutMsgViewAdapter(msg_list, this);
        listView.setAdapter(msgAdapter);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    uiChart.SendMsg(input_box.getText().toString().trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
    public void SendNull() {
        Toast.makeText(this, "不能发送空消息！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void SendSuccess(MsgItem msg) {
        msg.setMyself(input_box.getText().toString().trim());
        msg_list.add(msg);
        msgAdapter.notifyDataSetChanged();
//        Toast.makeText(this, msg.getResult().getContent(), Toast.LENGTH_SHORT).show();
        input_box.setText("");
        listView.setSelection(msg_list.size());
    }

    @Override
    public void RequesrNull() {

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

}
