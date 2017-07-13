package com.dingmouren.smaple.paletteimageview;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dingmouren.paletteimageview.PaletteImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dingmouren on 2017/7/6.
 * email:naildingmouren@gmail.com
 */

public class SampleTwoActivity extends AppCompatActivity {
    private RecyclerView mRecycler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_two);
        initView();
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new GridLayoutManager(this,2));
        mRecycler.setHasFixedSize(true);
        mRecycler.setAdapter(new MyAdapter());
    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample_two,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bindData(Constant.mImgs[position]);
        }

        @Override
        public int getItemCount() {
            return Constant.mImgs.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            PaletteImageView mPaletteImageView;
            LinearLayout mLinearLayout;
            TextView title,content;
            public ViewHolder(View itemView) {
                super(itemView);
                mPaletteImageView = (PaletteImageView) itemView.findViewById(R.id.palette);
                mLinearLayout = (LinearLayout)itemView. findViewById(R.id.linear);
                title = (TextView)itemView. findViewById(R.id.tv1);
                content = (TextView) itemView.findViewById(R.id.tv2);
                initListener();
            }

            private void initListener() {
                mPaletteImageView.setOnParseColorListener(new PaletteImageView.OnParseColorListener() {
                    @Override
                    public void onComplete(PaletteImageView paletteImageView) {
                        int[] vibrant = paletteImageView.getVibrantColor();
                        int[] vibrantDark = paletteImageView.getDarkVibrantColor();
                        int[] vibrantLight = paletteImageView.getLightVibrantColor();
                        int[] muted = paletteImageView.getMutedColor();
                        int[] mutedDark = paletteImageView.getDarkMutedColor();
                        int[] mutedLight = paletteImageView.getLightMutedColor();
                        List<int[]> list = new ArrayList<int[]>();
                        list.clear();
                        list.add(vibrant);
                        list.add(vibrantDark);
                        list.add(vibrantLight);
                        list.add(muted);
                        list.add(mutedDark);
                        list.add(mutedLight);
                        for (int i = 0; i <list.size() ; i++) {
                            int[] arry = list.get(i);
                            if (arry == null) list.remove(arry);
                        }
                        int[] arry = list.get(new Random().nextInt(list.size()-1));
                        if(arry != null) {
                            title.setTextColor(arry[1]);
                            content.setTextColor(arry[0]);
                            mLinearLayout.setBackgroundColor(arry[2]);
                        }
                    }

                    @Override
                    public void onFail() {

                    }
                });
            }

            public void bindData(int imgId){
                if (mPaletteImageView.getTag() == null || (int)mPaletteImageView.getTag() == imgId) {
                    mPaletteImageView.setTag(imgId);
                    mPaletteImageView.setBitmap(BitmapFactory.decodeResource(getResources(), imgId));
                }
            }
        }
    }
}
