package com.baw.day1120_ymn_demo1.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.baw.day1120_ymn_demo1.Contract;

/**
 * Copyright (C)
 * <p>
 * FileName: BaseFragment
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/20 14:55
 * <p>
 * Description:
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements Contract.IView {

    protected P mPResenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(Layout(), container, false);
        initView(inflate);
        mPResenter = initPresenter();
        if (mPResenter != null) {
            mPResenter.onAttch(this);
        }
        startCoding();
        return inflate;
    }

    protected abstract void startCoding();

    protected abstract P initPresenter();

    protected abstract void initView(View inflate);

    protected abstract int Layout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPResenter != null) {
            mPResenter.onEnd();
        }
    }
}
