package com.m.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.m.common.NetUtils;
import com.m.common.router.RouterMapUtil;

@Route(path = RouterMapUtil.personal)
public class PersonalMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_main);
    }

    public void click(View view) {
        new NetUtils().getName(this);
    }
}