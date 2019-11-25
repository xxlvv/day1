package com.baw.day1120_ymn_demo1.mvp;

import com.baw.day1120_ymn_demo1.Contract;
import com.baw.day1120_ymn_demo1.NetUtil;

import java.util.Map;

/**
 * Copyright (C)
 * <p>
 * FileName: Model
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/20 11:54
 * <p>
 * Description:
 */
public class Model implements Contract.ModelInter {
    @Override
    public void doGET(String url, final Contract.ModelShared modelShared) {
        NetUtil.getInstance().doGET(url, new NetUtil.Shared() {
            @Override
            public void Success(String json) {
                modelShared.Success(json);
            }

            @Override
            public void Filed(String error) {
                modelShared.Filed(error);
            }
        });
    }

    @Override
    public void doPOST(String url, final Map<String, String> map, final Contract.ModelShared modelShared) {
        NetUtil.getInstance().doPOST(url, map, new NetUtil.Shared() {
            @Override
            public void Success(String json) {
                modelShared.Success(json);
            }

            @Override
            public void Filed(String error) {
                modelShared.Filed(error);
            }
        });
    }
}
