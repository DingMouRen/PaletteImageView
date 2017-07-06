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

/**
 * Created by dingmouren on 2017/7/6.
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

            }

            public void bindData(int imgId){
                if (mPaletteImageView.getTag() == null || (int)mPaletteImageView.getTag() == imgId) {
                    mPaletteImageView.setTag(imgId);
                    mPaletteImageView.setBitmap(BitmapFactory.decodeResource(getResources(), imgId));
                    mLinearLayout.setBackgroundColor(mPaletteImageView.mMainColor);
                    title.setTextColor(mPaletteImageView.getLightMutedTitleTextColor());
                    content.setTextColor(mPaletteImageView.getLightMutedContentTextColor());
                }
            }
        }
    }
}
