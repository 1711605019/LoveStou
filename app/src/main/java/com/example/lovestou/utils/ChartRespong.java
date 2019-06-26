package com.example.lovestou.utils;

import com.example.lovestou.bean.MsgItem;

public interface ChartRespong {
    final String url = "https://api.jisuapi.com/iqa/query?appkey=c3a90638dc954cb9&question=";

    public void SendNull();

    public void SendSuccess(MsgItem msg);

    public void RequesrNull();
}
