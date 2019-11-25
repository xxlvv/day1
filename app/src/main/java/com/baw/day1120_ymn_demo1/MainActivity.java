package com.baw.day1120_ymn_demo1;


import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.baw.day1120_ymn_demo1.base.BaseActivity;
import com.baw.day1120_ymn_demo1.base.BasePresenter;
import com.baw.day1120_ymn_demo1.fragment.MyFragment;
import com.baw.day1120_ymn_demo1.fragment.ShouFragment;
import com.baw.day1120_ymn_demo1.mvp.Presenter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {


    @Override
    protected void startCoding() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return new Presenter();
    }

    private ViewPager vp;
    private RadioGroup rg;
    private RadioButton rb1;
    private RadioButton rb2;

    @Override
    protected void initView() {

        vp = findViewById(R.id.vp);
        rg = findViewById(R.id.rg);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);

        final List<Fragment> list = new ArrayList<>();
        list.add(new ShouFragment());
        list.add(new MyFragment());

        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        //vp.setOffscreenPageLimit(1);
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        //vp.setOffscreenPageLimit(2);
                        vp.setCurrentItem(1);
                        break;
                }
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg.check(rg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setOffscreenPageLimit(2);

    }

    @Override
    protected int Layout() {
        return R.layout.activity_main;
    }

    @Override
    public void Success(String json) {

    }

    @Override
    public void Filed(String error) {

    }
}
