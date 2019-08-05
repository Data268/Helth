package com.wd.doctor.open_login.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.utils.Constant;
import com.wd.doctor.open_login.R;
@Route(path = Constant.ACTIVITY_URL_WELCOME)
public class WelcomeActivity extends BaseActivity {

    private final int ACTION = 1;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ACTION :
                    intentByRouter(Constant.ACTIVITY_URL_LOGIN);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        Message message = handler.obtainMessage();
        message.what = ACTION;
        handler.sendMessageDelayed(message,2000);
    }

    @Override
    protected void destoryData() {

    }
}
