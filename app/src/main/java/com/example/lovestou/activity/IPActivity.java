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
import com.example.lovestou.bean.IpBean;
import com.example.lovestou.utils.NetworkListining;
import com.example.lovestou.utils.OkhttpUntil;

public class IPActivity extends AppCompatActivity {
    private EditText ed_input;
    private Button btn_send;
    private TextView tv_country,tv_province,tv_city,tv_type;
    private ImageButton ib_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        initView();
    }
    private void initView() {
        ed_input = findViewById(R.id.ed_input);
        tv_country = findViewById(R.id.tv_country);
        tv_province = findViewById(R.id.tv_province);
        tv_city = findViewById(R.id.tv_city);
        tv_type = findViewById(R.id.tv_type);

        ib_return = findViewById(R.id.ib_return);
        ib_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = "https://api.jisuapi.com/ip/location?appkey=c3a90638dc954cb9&ip=" + ed_input.getText().toString().trim();
                GetIpInfo(ip);
            }
        });
    }
    public void GetIpInfo(final String url) {
        OkhttpUntil.enqueueGetrequest(url, IpBean.class, new NetworkListining<IpBean>() {
            @Override
            public void BackResultSuccess(IpBean bean, int code) {
                try{
                    if(code == 200){
                        tv_country.setText(bean.getResult().getCountry());
                        tv_province.setText(bean.getResult().getProvince());
                        tv_city.setText(bean.getResult().getCity());
                        tv_type.setText(bean.getResult().getType());
                    }
                }catch (Exception e){
                    Toast.makeText(IPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void BackResultFail(Exception errow) {
                Toast.makeText(IPActivity.this, "请输入正确的IP地址", Toast.LENGTH_SHORT).show();
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
