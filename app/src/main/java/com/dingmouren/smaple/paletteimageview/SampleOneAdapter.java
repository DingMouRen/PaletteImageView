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
 */

public class SampleOneAdapter extends FragmentStatePagerAdapter {
    private Set<SampleOneFragment> mSet = new LinkedHashSet<>();
    private  List<SampleOneFragment> mList = new ArrayList<>();
    public SampleOneAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        SampleOneFragment fragment = SampleOneFragment.newInstance(Constant.mHeros[position]);
        mSet.add(fragment);
        return fragment;
    }
    public List<SampleOneFragment> getList(){
        mList.clear();
        Iterator iterator = mSet.iterator();
        while (iterator.hasNext()){
            mList.add((SampleOneFragment) iterator.next());
        }
        return  mList;
    }
    @Override
    public int getCount() {
        return Constant.mHeros.length;
    }

}
