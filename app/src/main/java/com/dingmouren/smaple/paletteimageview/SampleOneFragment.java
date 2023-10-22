package com.dingmouren.smaple.paletteimageview;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dingmouren.paletteimageview.PaletteImageView;

/**
 * Created by dingmouren on 2017/7/7.
 * email:naildingmouren@gmail.com
 */

public class SampleOneFragment extends Fragment {
    private static final String IMG_ID = "img_id";
    public PaletteImageView paletteImageView;
    private LinearLayout mContainer;


    public static SampleOneFragment newInstance(int imgId){
        SampleOneFragment fragment = new SampleOneFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(IMG_ID,imgId);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_sample_one,container,false);
        mContainer  = (LinearLayout) view.findViewById(R.id.container);
        paletteImageView = (PaletteImageView) view.findViewById(R.id.palette);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null){
            int imgId = getArguments().getInt(IMG_ID);
            paletteImageView.setBitmap(BitmapFactory.decodeResource(getResources(),imgId));
        }
    }

    @Override
    public void onPause() {
        mContainer.removeAllViews();
        super.onPause();
    }
}
