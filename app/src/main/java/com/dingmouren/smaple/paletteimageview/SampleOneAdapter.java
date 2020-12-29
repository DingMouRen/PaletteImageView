package com.dingmouren.smaple.paletteimageview;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

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
