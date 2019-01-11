package com.still.appframe;

import com.igexin.sdk.PushManager;
import com.still.common.app.Application;
import com.still.factory.Factory;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 调用Factory进行初始化
        Factory.setup();
        // 推送进行初始化
        PushManager.getInstance().initialize(this);
    }
}

