package com.still.appframe;


import com.still.appframe.activitys.MainActivity;
import com.still.appframe.frags.assist.PermissionsFragment;
import com.still.common.app.Activity;

public class LaunchActivity extends Activity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launch;
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (PermissionsFragment.haveAll(this, getSupportFragmentManager())) {
            MainActivity.show(this);
            finish();
        }

    }
}
