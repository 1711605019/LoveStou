package com.example.lovestou.utils;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Okhttpnew {
    public static <T> T NothreadenqueueGetrequest(String url, Class<T> myclass) throws IOException {
        OkHttpClient okHttpClient=new OkHttpClient();
        Call call = okHttpClient.newCall(new Request.Builder().get().url(url).build());
        Response execute = call.execute();
        return new Gson().fromJson(execute.body().string(),myclass);
    }
}
