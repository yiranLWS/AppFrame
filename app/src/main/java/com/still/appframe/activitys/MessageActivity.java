package com.still.appframe.activitys;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.still.appframe.R;
import com.still.common.app.Activity;
import com.still.factory.model.Author;

public class MessageActivity extends Activity {

    /**
     * 显示人的聊天界面
     *
     * @param context 上下文
     * @param author  人的信息
     */
    public static void show(Context context, Author author) {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_message;
    }

}
