package com.m.assemblystudy;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;


/**
 * createDate:2021/6/17
 *
 * @author:spc
 * @describe：
 */
public class App extends Application {
    private boolean isDebug=true;
    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
           ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(App.this);
    }
}