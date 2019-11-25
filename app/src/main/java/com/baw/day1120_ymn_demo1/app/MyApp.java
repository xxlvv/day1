package com.baw.day1120_ymn_demo1.app;

import android.app.Application;
import android.content.Context;

import com.baw.day1120_ymn_demo1.CrashHandler;

/**
 * Copyright (C)
 * <p>
 * FileName: MyApp
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/20 11:44
 * <p>
 * Description:
 */
public class MyApp extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        CrashHandler.getInstance().init(this);
    }
}
