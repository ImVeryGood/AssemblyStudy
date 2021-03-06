package com.m.assemblystudy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.m.common.router.RouterMapUtil;
import com.m.personal.PersonalMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("NonConstantResourceId")
    public void jump(View view) {
        switch (view.getId()) {
            case R.id.order:
                ARouter.getInstance().build(RouterMapUtil.order).navigation();
                break;
            case R.id.personal:
               ARouter.getInstance().build(RouterMapUtil.personal).navigation();
                break;

        }
    }
}