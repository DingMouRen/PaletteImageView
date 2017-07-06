package com.dingmouren.smaple.paletteimageview;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArraySet;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
    private MyAdapter mAdapter;
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
        mAdapter = new MyAdapter(this);
        mViewPager.setAdapter(mAdapter);

    }

    private class MyAdapter extends PagerAdapter{
        private List<View> mList = new ArrayList<>();
        private LayoutInflater mLayoutInflater;
        public MyAdapter(Context cotext) {
            mLayoutInflater = LayoutInflater.from(cotext);
        }

        @Override
        public int getCount() {
            return Constant.mImgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mLayoutInflater.inflate(R.layout.item_sample_one,container,false);
            PaletteImageView palette = (PaletteImageView) view.findViewById(R.id.palette);
            palette.setBitmap(BitmapFactory.decodeResource(getResources(),Constant.mImgs[position]));
            container.addView(view);
            if (!mList.contains(view))  mList.add(view);
            return view;
        }
    }
}
