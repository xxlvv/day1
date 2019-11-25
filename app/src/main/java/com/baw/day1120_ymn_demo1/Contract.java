package com.baw.day1120_ymn_demo1;

import java.util.Map;

/**
 * Copyright (C)
 * <p>
 * FileName: Contract
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/20 11:52
 * <p>
 * Description:
 */
public interface Contract {
    interface ModelInter {
        void doGET(String url, ModelShared modelShared);

        void doPOST(String url, Map<String, String> map, ModelShared modelShared);
    }

    interface ModelShared {
        void Success(String json);

        void Filed(String error);
    }

    interface IPresenter {
        void GETonstart(String url);

        void POSTonstart(String url, Map<String, String> map);
    }

    interface IView {
        void Success(String json);

        void Filed(String error);
    }
}
