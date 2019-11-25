package com.baw.day1120_ymn_demo1.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.baw.day1120_ymn_demo1.Contract;

/**
 * Copyright (C)
 * <p>
 * FileName: BaseActivity
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/20 11:58
 * <p>
 * Description:
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements Contract.IView {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Layout() != 0) {
            setContentView(Layout());
            initView();
            mPresenter = initPresenter();
            if (mPresenter != null) {
                mPresenter.onAttch(this);
            }
            startCoding();
        }
    }

    protected abstract void startCoding();

    protected abstract P initPresenter();

    protected abstract void initView();

    protected abstract int Layout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onEnd();
        }
    }
}
