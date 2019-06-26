package com.example.lovestou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovestou.R;
import com.example.lovestou.bean.CalendarBean;
import com.example.lovestou.utils.NetworkListining;
import com.example.lovestou.utils.OkhttpUntil;
import com.example.lovestou.view.MeiZuMonthView;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

public class CalendarActivity extends AppCompatActivity implements CalendarView.OnCalendarSelectListener, CalendarView.OnYearChangeListener, CalendarView.OnMonthChangeListener {
    private TextView tv_date;
    private CalendarView mCalendarView;
    private TextView tv_yi, tv_ji, tv_caishen, tv_xishen, tv_fusehn, tv_taishen, tv_xiongshen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        initView();
        initData();
    }

    private void initData() {


    }

    private void initView() {
        tv_yi = findViewById(R.id.tv_yi);
        tv_ji = findViewById(R.id.tv_ji);
        tv_caishen = findViewById(R.id.tv_caishen);
        tv_xishen = findViewById(R.id.tv_xishen);
        tv_fusehn = findViewById(R.id.fushen);
        tv_taishen = findViewById(R.id.taishen);
        tv_xiongshen = findViewById(R.id.tv_xiongshen);
        mCalendarView = findViewById(R.id.calendarView);
        tv_date = findViewById(R.id.tv_date);
        mCalendarView.setMonthView(MeiZuMonthView.class);
        //calendarView.setWeekView(MeizuWeekView.class);
        mCalendarView.setOnCalendarSelectListener(this);
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.showYearSelectLayout(mCalendarView.getCurYear());
            }
        });

        tv_date.setText(mCalendarView.getCurYear() + "年" + mCalendarView.getCurMonth() + "月");
        mCalendarView.setOnYearChangeListener(this);
        mCalendarView.setOnMonthChangeListener(this);

        findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });

    }

    public void GetCalendarInfo(final String url) {
        OkhttpUntil.enqueueGetrequest(url, CalendarBean.class, new NetworkListining<CalendarBean>() {
            @Override
            public void BackResultSuccess(CalendarBean bean, int code) {
                try {
                    if (code == 200) {
                        tv_yi.setText(bean.getResult().getYi().toString());
                        tv_ji.setText(bean.getResult().getJi().toString());
                        tv_caishen.setText(bean.getResult().getCaishen());
                        tv_xishen.setText(bean.getResult().getXishen());
                        tv_fusehn.setText(bean.getResult().getFushen());
                        tv_taishen.setText(bean.getResult().getTaishen());
                        tv_xiongshen.setText(bean.getResult().getXiongshen());
                    }
                } catch (Exception e) {
                    Toast.makeText(CalendarActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void BackResultFail(Exception errow) {
                Toast.makeText(CalendarActivity.this, "出错啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void tostring(String responseString) {

            }
        });
    }


    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        tv_date.setText(calendar.getYear() + "年" + calendar.getMonth() + "月");
        String url = "https://api.jisuapi.com/huangli/date?appkey=c3a90638dc954cb9&year=" + calendar.getYear() + "&month=" + calendar.getMonth() + "&day=" + calendar.getDay();
        GetCalendarInfo(url);

    }

    @Override
    public void onYearChange(int year) {
        tv_date.setText(year + "年");
    }

    @Override
    public void onMonthChange(int year, int month) {
        tv_date.setText(year + "年" + month + "月");
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
