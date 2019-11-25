package com.baw.day1120_ymn_demo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.baw.day1120_ymn_demo1.base.BaseActivity;
import com.baw.day1120_ymn_demo1.base.BasePresenter;
import com.baw.day1120_ymn_demo1.mvp.Presenter;

import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image;
    private EditText name;
    private Button bottom1;
    private MyView my_view;
    private String sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        my_view = findViewById(R.id.my_view);

        image = findViewById(R.id.image);
        name = findViewById(R.id.name);

        bottom1 = findViewById(R.id.bottom1);

        my_view.setOnClickListener(this);
        bottom1.setOnClickListener(this);
        image.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image:
                finish();
                break;
            case R.id.bottom1:
                sp = name.getText().toString().trim();
                my_view.AddTog(sp);
                break;
            case R.id.my_view:
                if (sp != null) {
                    Intent intent = getIntent();
                    intent.putExtra("name", sp);
                    setResult(100, intent);
                    finish();
                }
                break;
        }
    }
}
