package com.bwie.test.app;

import android.app.Application;

import com.dash.zxinglibrary.activity.ZXingLibrary;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Fresco
        Fresco.initialize(this);
        //初始化Zxing库
        ZXingLibrary.initDisplayOpinion(this);
    }
}
