package com.example.lovestou.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class UpdateUserInfoReceiver extends BroadcastReceiver {
    public interface ACTION {
        String UPDATE_USERINFO = "update_userinfo";
    }
    //广播intent类型
    public interface INTENT_TYPE {
        String TYPE_NAME = "intent_name";
        String UPDATE_HEAD = "update_head";//更新头像
    }
    private BaseOnReceiveMsgListener onReceiveMsgListener;
    public UpdateUserInfoReceiver(BaseOnReceiveMsgListener onReceiveMsgListener) {
        this.onReceiveMsgListener = onReceiveMsgListener;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        onReceiveMsgListener.onReceiveMsg(context, intent);
    }
    public interface BaseOnReceiveMsgListener {
        void onReceiveMsg(Context context, Intent intent);
    }
}
