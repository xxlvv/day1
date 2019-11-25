package com.baw.day1120_ymn_demo1.base;

import com.baw.day1120_ymn_demo1.Contract;

import java.lang.ref.WeakReference;

/**
 * Copyright (C)
 * <p>
 * FileName: BasePresenter
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/20 11:55
 * <p>
 * Description:
 */
public abstract class BasePresenter<V extends Contract.IView> implements Contract.IPresenter {
    //虚引用
    protected WeakReference<V> weakReference;

    public BasePresenter() {
        initModel();
    }

    //初始化model的方法
    protected abstract void initModel();

    //绑定View
    protected void onAttch(V v) {
        weakReference = new WeakReference<>(v);
    }

    //解绑
    protected void onEnd() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }

    //获取View的方法
    protected V getView() {
        return weakReference.get();
    }

}
