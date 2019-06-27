package com.example.lovestou.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
import com.example.lovestou.bean.OilBean;
import com.example.lovestou.utils.NetworkListining;
import com.example.lovestou.utils.OkhttpUntil;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

import me.james.biuedittext.BiuEditText;

import static android.view.View.VISIBLE;

public class OilActivity extends AppCompatActivity {
    private BiuEditText ed_input;
    private Button btn_send;
    private ImageButton ib_return;

    private List<HistoryBean> historyList = new ArrayList<>();
    private ListView listView;
    private View header_view;
    private SearchItemAdapter searchItemAdapter;
    private TextView tv_deleteAll;

    private LineChart lineChart;
    private TextView tv_sf;
    private LinearLayout ll_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        initView();
    }

    private void initView() {
        lineChart = findViewById(R.id.chart);
        ed_input = findViewById(R.id.et_search);
        listView = findViewById(R.id.mRecyclerView);
        ll_content = findViewById(R.id.ll_content);
        tv_sf = findViewById(R.id.tv_sf);
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
                ll_content.setVisibility(View.GONE);
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

                if (!input.equals("")) {
                    HistoryBean bean = new HistoryBean(input);
                    historyList.add(bean);
                    searchItemAdapter = new SearchItemAdapter(historyList, OilActivity.this);
                    listView.setAdapter(searchItemAdapter);
                    listView.setVisibility(View.GONE);
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(ed_input.getWindowToken(), 0);
            }
        });
    }

    private void initLineChart() {
        Description description = new Description();
        description.setText("单价：元/升");
        description.setTextSize(16);
        lineChart.setDescription(description);
        String[] xData = {"#00", "#90", "#92", "#95", "#97"};//预设的x轴的数据
        String[] yData = {"4", "5", "6", "7", "8"};//预设的y轴的数据


        lineChart.setAutoScaleMinMaxEnabled(false);
        lineChart.setBorderWidth(1f);//设置边框的宽度（粗细）
        lineChart.setBorderColor(Color.WHITE);
        lineChart.setDrawBorders(true);//显示图形的边框（边界）
        lineChart.setDragXEnabled(false);//在放大的情况下，能否拖动x轴
        lineChart.setDragYEnabled(true);
        lineChart.setTouchEnabled(true);//设置为false的话，界面就像是一个图片
//        lineChart.setBackgroundResource(R.drawable.loading);
        lineChart.setDrawMarkers(true);//设置是否显示
        lineChart.setMarker(new IMarker() {//设置imarker可以设置点击数据的时候出现的图形。
            @Override
            public MPPointF getOffset() {
                return null;
            }

            @Override
            public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
                return null;
            }

            @Override
            public void refreshContent(Entry e, Highlight highlight) {

            }

            @Override
            public void draw(Canvas canvas, float posX, float posY) {
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setColor(Color.GREEN);
                paint.setTextSize(22f);
                canvas.drawText("here", posX, posY, paint);
            }
        });

        lineChart.animateX(500);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xData));
        xAxis.setLabelCount(xData.length - 1);
        xAxis.setAxisMaximum(xData.length - 1);
        xAxis.setAxisMinimum(0);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        lineChart.getAxisRight().setEnabled(false);
    }

    private synchronized LineData getData(OilBean bean) {
        if (bean != null) {
            //节点
            ArrayList<Entry> nodeData = new ArrayList<>();
            nodeData.add(new Entry(0, Float.parseFloat(bean.getResult().getOil0())));
            nodeData.add(new Entry(1, Float.parseFloat(bean.getResult().getOil90())));
            nodeData.add(new Entry(2, Float.parseFloat(bean.getResult().getOil92())));
            nodeData.add(new Entry(3, Float.parseFloat(bean.getResult().getOil95())));
            nodeData.add(new Entry(4, Float.parseFloat(bean.getResult().getOil97())));

            LineDataSet lineDataSet = new LineDataSet(nodeData, "更新于" + bean.getResult().getUpdatetime());
            lineDataSet.setDrawFilled(true);//就是折线图下面是否有阴影填充，这样看起来就像是起伏的山脉
            lineDataSet.setFillColor(Color.GREEN);
            lineDataSet.setDrawCircles(true);//数据是否用圆圈显示
            lineDataSet.setCircleColor(Color.BLACK);//显示数据的圆的颜色
            lineDataSet.setCircleRadius(5f);//显示数据的圆的半径
            lineDataSet.setCircleColors(Color.BLACK, Color.GRAY, Color.BLUE, Color.GREEN, Color.RED);//显示的几个数据的园的颜色
            lineDataSet.setDrawCircleHole(false);//数据是否用环形圆圈（同心圆）显示
            lineDataSet.setCircleHoleColor(Color.YELLOW);//同心圆内圆的颜色，即圆孔的颜色
            lineDataSet.setCircleHoleRadius(3f);//内圆的半径
            lineDataSet.setColor(Color.GRAY);//折线的颜色，以及label前面图形的颜色
            lineDataSet.setValueTextSize(10f);//数据的字体大小
            lineDataSet.setHighlightEnabled(true);//设置是否显示十字架的凸显样式
            lineDataSet.setHighLightColor(Color.RED);//设置图形样式的颜色
            lineDataSet.setDrawHorizontalHighlightIndicator(true);//设置凸显样式水平图形显隐
            lineDataSet.setDrawVerticalHighlightIndicator(true);//设置凸显样式垂直图形显隐
            lineDataSet.setHighlightLineWidth(0.5f);//点击数据会出现十字架形状的定位显示，设置该十字架的宽度
            lineDataSet.setLineWidth(2f);//设置折线的宽度
            lineDataSet.setFormSize(15f);//label前面的图形的大小
            lineDataSet.setForm(Legend.LegendForm.CIRCLE);//设置图例的图形的形状
            lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);//设置折线的形状：直线、梯形、贝塞尔曲线
            lineDataSet.setCubicIntensity(0.5f);//修改CUBIC_BEZIER模式下面的曲线的一个参数，会让曲线变的很奇怪
            return new LineData(lineDataSet);
        }
        return null;

    }

    public void GetOilInfo(final String url) {
        OkhttpUntil.enqueueGetrequest(url, OilBean.class, new NetworkListining<OilBean>() {
            @Override
            public void BackResultSuccess(OilBean bean, int code) {
                try {
                    if (code == 200) {
                        initLineChart();
                        LineData lineData = getData(bean);
                        lineChart.setData(lineData);
                        tv_sf.setText(ed_input.getText().toString().trim() + "今日油价");
                        ll_content.setVisibility(VISIBLE);
                    }
                } catch (Exception e) {
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