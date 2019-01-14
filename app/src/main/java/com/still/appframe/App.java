package com.still.appframe;

import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import com.igexin.sdk.PushManager;
import com.still.common.app.Application;
import com.still.factory.Factory;

public class App extends Application {

    private MessageReceiver messageReceiver;
    private IntentFilter filter;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            PushManager.getInstance().initialize(getApplicationContext(), DemoPushService.class);
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        if(Build.VERSION.SDK_INT>=26){
            filter = new IntentFilter("com.igexin.sdk.action.oVDaupDlg97N97zO3bmDn4");
            messageReceiver = new MessageReceiver();
            //注册广播接收者的对象
            registerReceiver(messageReceiver, filter);
        }

        // 调用Factory进行初始化
        Factory.setup();
        // 推送进行初始化
//        PushManager.getInstance().initialize(this);
//        handler.sendEmptyMessageDelayed(0,1500);
        PushManager.getInstance().initialize(getApplicationContext(), DemoPushService.class);
    }


}

