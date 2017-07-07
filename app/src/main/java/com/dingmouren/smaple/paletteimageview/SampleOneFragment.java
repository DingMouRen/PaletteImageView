package com.dingmouren.smaple.paletteimageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dingmouren.paletteimageview.PaletteImageView;

/**
 * Created by dingmouren on 2017/7/7.
 */

public class SampleOneFragment extends Fragment {
    private static final String IMG_ID = "img_id";
    public PaletteImageView paletteImageView;

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

    private Bitmap zipBitmap(int imgId){
        Bitmap bitmap = null;
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        BitmapFactory.decodeResource(getResources(),imgId,options);
//        options.inJustDecodeBounds = true;
//        options.inSampleSize = 8;
//        options.inJustDecodeBounds = false;
//        bitmap = BitmapFactory.decodeResource(getResources(),imgId,options);
        Matrix matrix = new Matrix();
//        matrix.setScale(2.0f,2.0f);
        bitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(),imgId),0,0,324,594,matrix,true);
        return bitmap;
    }
}
