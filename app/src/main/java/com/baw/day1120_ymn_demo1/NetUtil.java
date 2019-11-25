package com.baw.day1120_ymn_demo1;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baw.day1120_ymn_demo1.app.MyApp;

import java.util.Map;

/**
 * Copyright (C)
 * <p>
 * FileName: NetUtil
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/20 11:44
 * <p>
 * Description:
 */
public class NetUtil {
    private RequestQueue mQueue;

    private NetUtil() {
        mQueue = Volley.newRequestQueue(MyApp.context);
    }

    private static class NetUtils {
        private static NetUtil netUtil = new NetUtil();
    }

    public static NetUtil getInstance() {
        return NetUtils.netUtil;
    }

    public interface Shared {
        void Success(String json);

        void Filed(String error);
    }

    public void doGET(String url, final Shared shared) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                shared.Success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                shared.Filed(error.getMessage());
            }
        });
        mQueue.add(stringRequest);
    }

    public void doPOST(String url, final Map<String, String> map, Shared shared) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (map != null) {
                    return map;
                }
                return super.getParams();
            }
        };
        mQueue.add(stringRequest);
    }
}
