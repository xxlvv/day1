package com.baw.day1120_ymn_demo1.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baw.day1120_ymn_demo1.Main2Activity;
import com.baw.day1120_ymn_demo1.MyBaseAdapter;
import com.baw.day1120_ymn_demo1.R;
import com.baw.day1120_ymn_demo1.bean.StudentBean;
import com.baw.day1120_ymn_demo1.base.BaseFragment;
import com.baw.day1120_ymn_demo1.base.BasePresenter;
import com.baw.day1120_ymn_demo1.mvp.Presenter;
import com.google.gson.Gson;

import java.net.URLEncoder;
import java.util.List;

public class ShouFragment extends BaseFragment implements View.OnClickListener {

    private String name;

    @Override
    protected void startCoding() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return new Presenter();
    }

    private RecyclerView radio;
    private EditText name1;
    private Button bottom;

    @Override
    protected void initView(View inflate) {


        name1 = inflate.findViewById(R.id.name);
        bottom = inflate.findViewById(R.id.bottom);

        radio = inflate.findViewById(R.id.radio);
        radio.setLayoutManager(new LinearLayoutManager(getActivity()));
        //添加简易分割线
        radio.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        name1.setOnClickListener(this);
        bottom.setOnClickListener(this);
    }

    @Override
    protected int Layout() {
        return R.layout.fragment_shou;
    }

    @Override
    public void Success(String json) {
        Gson gson = new Gson();
        StudentBean studentBean = gson.fromJson(json, StudentBean.class);
        final List<StudentBean.ResultBean> result = studentBean.getResult();
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter(getActivity(), result);
        radio.setAdapter(myBaseAdapter);
        //RecyclerView的点击事件
        myBaseAdapter.setItemCilck(new MyBaseAdapter.Cilck() {
            @Override
            public void onSetCilck(View v, int p) {
                Toast.makeText(getActivity(), result.get(p).getCommodityName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void Filed(String error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.name:
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.bottom:
                String url = "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=" + URLEncoder.encode(name) + "&page=1&count=5";
                mPResenter.GETonstart(url);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 100) {
            name = data.getStringExtra("name");
        }
        name1.setText(name);
        name1.setSelection(name.length());
    }
}
