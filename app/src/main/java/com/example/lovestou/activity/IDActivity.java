package com.example.lovestou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovestou.R;
import com.example.lovestou.bean.IDBean;
import com.example.lovestou.utils.NetworkListining;
import com.example.lovestou.utils.OkhttpUntil;

import me.james.biuedittext.BiuEditText;

public class IDActivity extends AppCompatActivity {
    private BiuEditText ed_input;
    private Button btn_send;
    private TextView tv_province,tv_city,tv_town,tv_sex,tv_birth;
    private ImageButton ib_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        initView();
    }

    private void initView() {
        ed_input = findViewById(R.id.ed_input);
        tv_province = findViewById(R.id.tv_province);
        tv_city = findViewById(R.id.tv_city);
        tv_town = findViewById(R.id.tv_town);
        tv_sex = findViewById(R.id.tv_sex);
        tv_birth = findViewById(R.id.tv_birth);

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
                String sfzNumber = "https://api.jisuapi.com/idcard/query?appkey=c3a90638dc954cb9&idcard=" + ed_input.getText().toString().trim();
                GetSfzInfo(sfzNumber);
            }
        });
    }
    public void GetSfzInfo(final String url) {
        OkhttpUntil.enqueueGetrequest(url, IDBean.class, new NetworkListining<IDBean>() {
            @Override
            public void BackResultSuccess(IDBean bean, int code) {
                try{
                    if(code==200){
                        tv_province.setText(bean.getResult().getProvince());
                        tv_city.setText(bean.getResult().getCity());
                        tv_town.setText(bean.getResult().getTown());
                        tv_sex.setText(bean.getResult().getSex());
                        tv_birth.setText(bean.getResult().getBirth());
                    }
                }catch (Exception e){
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
