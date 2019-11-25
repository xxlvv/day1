package com.baw.day1120_ymn_demo1.mvp;

import com.baw.day1120_ymn_demo1.Contract;
import com.baw.day1120_ymn_demo1.base.BasePresenter;

import java.util.Map;

/**
 * Copyright (C)
 * <p>
 * FileName: Presenter
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/20 11:57
 * <p>
 * Description:
 */
public class Presenter extends BasePresenter {

    private Model model;

    @Override
    protected void initModel() {
        model = new Model();
    }

    @Override
    public void GETonstart(String url) {
        model.doGET(url, new Contract.ModelShared() {
            @Override
            public void Success(String json) {
                getView().Success(json);
            }

            @Override
            public void Filed(String error) {
                getView().Filed(error);
            }
        });
    }

    @Override
    public void POSTonstart(String url, Map<String, String> map) {
        model.doPOST(url, map, new Contract.ModelShared() {
            @Override
            public void Success(String json) {
                getView().Success(json);
            }

            @Override
            public void Filed(String error) {
                getView().Filed(error);
            }
        });
    }
}
