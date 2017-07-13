package com.dingmouren.smaple.paletteimageview;

import android.graphics.Matrix;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.ArraySet;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dingmouren on 2017/7/7.
 * email:naildingmouren@gmail.com
 */

public class SampleOneAdapter extends FragmentStatePagerAdapter {
    public SampleOneAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        SampleOneFragment fragment = SampleOneFragment.newInstance(Constant.mHeros[position]);
        return fragment;
    }
    @Override
    public int getCount() {
        return Constant.mHeros.length;
    }

}
