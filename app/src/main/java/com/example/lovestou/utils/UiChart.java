package com.example.lovestou.utils;

import android.app.Activity;
import android.os.Handler;

import com.example.lovestou.bean.MsgItem;

import java.io.IOException;

public class UiChart {
    ChartRespong chartRespong;
    Handler handler = new Handler();
    Activity activity;

    public UiChart(Activity activity) {
        this.activity = activity;
    }

    public ChartRespong getChartRespong() {
        return chartRespong;
    }

    public void setChartRespong(ChartRespong chartRespong) {
        this.chartRespong = chartRespong;
    }

    public void SendMsg(String msg) throws IOException {
        if (msg.equals("")) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    chartRespong.SendNull();
                }
            });
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        MsgItem msgItem = Okhttpnew.NothreadenqueueGetrequest(ChartRespong.url + msg, MsgItem.class);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (msgItem != null) {
                                    chartRespong.SendSuccess(msgItem);
                                } else {
                                    chartRespong.RequesrNull();
                                }
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();

        }
    }
}
