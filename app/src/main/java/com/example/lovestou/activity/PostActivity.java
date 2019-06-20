package com.example.lovestou.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovestou.R;
import com.example.lovestou.bean.PhoneBean;
import com.example.lovestou.bean.PostBean;
import com.example.lovestou.utils.NetworkListining;
import com.example.lovestou.utils.OkhttpUntil;

public class PostActivity extends AppCompatActivity {
    private EditText ed_input;
    private Button btn_send;
    private TextView tv_province,tv_city,tv_town;
    private ImageButton ib_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        initView();

    }
    private void initView() {
        ed_input = findViewById(R.id.ed_input);
        btn_send = findViewById(R.id.btn_send);
        tv_province = findViewById(R.id.tv_province);
        tv_city = findViewById(R.id.tv_city);
        tv_town = findViewById(R.id.tv_town);
        ib_return = findViewById(R.id.ib_return);

        ib_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postNumber = "https://api.jisuapi.com/zipcode/query?appkey=96df8b4b4f2d3890&zipcode=" + ed_input.getText().toString().trim();
                GetPostInfo(postNumber);
            }
        });
    }
    public void GetPostInfo(final String url) {
        OkhttpUntil.enqueueGetrequest(url, PostBean.class, new NetworkListining<PostBean>() {
            @Override
            public void BackResultSuccess(PostBean bean, int code) {
                try{
                    if(code==200){
                        tv_province.setText(bean.getResult().get(0).getProvince());
                        tv_city.setText(bean.getResult().get(0).getCity());
                        tv_town.setText(bean.getResult().get(0).getTown());
                    }
                }catch (Exception e){
                    Toast.makeText(PostActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void BackResultFail(Exception errow) {
                Toast.makeText(PostActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
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
