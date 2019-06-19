package com.example.lovestou.utils;

import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUntil {
    public static OkHttpClient okHttpClient = new OkHttpClient();
    public static Handler handler = new Handler();

    /*  public static void synGetrequest(String url,Class myclass,NetworkListining listining) throws Exception {
          new Thread(new Runnable() {
              @Override
              public void run() {
                  try {
                      listining.BackResultSuccess(toObject(myclass, responseTostring(Toresponse(Tocall(Torequest(url))))));
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }).start();


      }*/
    public static <T> void enqueueGetrequest(String url, Class<T> myclass, NetworkListining<T> listining) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Tocall(Torequest(url)).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listining.BackResultFail(e);
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            String string = response.body().string();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                  try{
                                      listining.BackResultSuccess(toObject(myclass, string), response.code());
                                  }catch (Exception e){
                                      listining.BackResultFail(e);
                                  }finally {
                                      if(string!=null){
                                          listining.tostring(string);
                                      }else{
                                          listining.tostring("404！！！！");
                                      }

                                  }

                                }
                            });

                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    public static <T> void enqueueGetrequest(String url, TostringNetworkListining listining) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Tocall(Torequest(url)).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listining.BackResultFail(e);
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                           if(response.code()==200){
                               String string = response.body().string();
                               handler.post(new Runnable() {
                                   @Override
                                   public void run() {
                                       listining.tostring(string);
                                   }
                               });
                           }

                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public static <T> T toObject(Class<T> myclass, String string) {
        return new Gson().fromJson(string, myclass);
    }

    public static String responseTostring(Response toresponse) throws IOException {
        String string = toresponse.body().string();
        return string;
    }

    public static Response Toresponse(Call tocall) throws IOException {
        return tocall.execute();
    }

    public static Call Tocall(Request torequest) {
        Call call = okHttpClient.newCall(torequest);

        return call;
    }

    public static Request Torequest(String url) {
        return new Request.Builder().url(url).get().build();
    }
}
