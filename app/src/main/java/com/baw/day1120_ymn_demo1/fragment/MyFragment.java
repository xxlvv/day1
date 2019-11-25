package com.baw.day1120_ymn_demo1.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.baw.day1120_ymn_demo1.R;
import com.baw.day1120_ymn_demo1.base.BaseFragment;
import com.baw.day1120_ymn_demo1.base.BasePresenter;
import com.baw.day1120_ymn_demo1.mvp.Presenter;

public class MyFragment extends BaseFragment {

    @Override
    protected void startCoding() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return new Presenter();
    }

    private WebView wrap;
    private Button ChangeColor;
    private Button alertTitle;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView(View inflate) {


        ChangeColor = inflate.findViewById(R.id.ChangeColor);
        alertTitle = inflate.findViewById(R.id.alertTitle);
        wrap = inflate.findViewById(R.id.wrap);
        //设置网络地址
        wrap.loadUrl("https://abnerming8.github.io/abnerming.html");
        //获取设置
        WebSettings settings = wrap.getSettings();
        //设置启用js脚本
        settings.setJavaScriptEnabled(true);
        //设置可以弹出对话框
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置在本应用内游览网址
        wrap.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        //设置可以弹出对话框
        wrap.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        //添加javaScript接口
        wrap.addJavascriptInterface(new Demo(), "android");

        //点击更换颜色
        ChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrap.loadUrl("javascript:changeColor('#FF0202')");
            }
        });
        //点击弹出对话框
        alertTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrap.loadUrl("javascript:toast()");
            }
        });
    }

    @Override
    protected int Layout() {
        return R.layout.fragment_my;
    }

    @Override
    public void Success(String json) {

    }

    @Override
    public void Filed(String error) {

    }

    private class Demo {
        @JavascriptInterface
        public void show() {
            Toast.makeText(getActivity(), "潘世松手速真的快", Toast.LENGTH_SHORT).show();
        }
    }
}
