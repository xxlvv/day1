package com.baw.day1120_ymn_demo1;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;


/**
 * Copyright (C)
 * <p>
 * FileName: CrashHandler
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/20 20:42
 * <p>
 * Description:
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private CrashHandler(){

    }

    private static  class CrashHolder{
        private static final CrashHandler crash = new CrashHandler();
    }

    public static CrashHandler getInstance(){
        return CrashHolder.crash;
    }

    //系统默认的uncaughtException处理类
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    private Context mContext;



    public void init(Context context) {
        this.mContext = context;
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!handleException(e) && uncaughtExceptionHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            uncaughtExceptionHandler.uncaughtException(t, e);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ee) {
                Log.e("CrashHandler", "error : " + ee.getMessage());
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();

        return true;
    }
}
