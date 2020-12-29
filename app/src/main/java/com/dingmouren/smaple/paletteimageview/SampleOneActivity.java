package com.dingmouren.smaple.paletteimageview;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by dingmouren on 2017/7/6.
 * email:naildingmouren@gmail.com
 */

public class SampleOneActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private SampleOneAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_one);
        initView();
        initData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void initData(){
        mAdapter = new SampleOneAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

    }



}
