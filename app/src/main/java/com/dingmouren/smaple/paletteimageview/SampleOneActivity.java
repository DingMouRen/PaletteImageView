package com.dingmouren.smaple.paletteimageview;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArraySet;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dingmouren.paletteimageview.PaletteImageView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dingmouren on 2017/7/6.
 */

public class SampleOneActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private SampleOneAdapter mAdapter;
    private RelativeLayout mActionBar;
    private Window window;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_one);
        window = SampleOneActivity.this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.md_teal_A400));
        }
        initView();
        initData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mActionBar = (RelativeLayout) findViewById(R.id.action_bar);
    }

    private void initData(){
        mAdapter = new SampleOneAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int color = mAdapter.getList().get(position).paletteImageView.mMainColor;
                mActionBar.setBackgroundColor(color);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(color);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }



}
