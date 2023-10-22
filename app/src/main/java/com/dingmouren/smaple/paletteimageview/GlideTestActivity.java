package com.dingmouren.smaple.paletteimageview;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.dingmouren.paletteimageview.PaletteImageView;

/**
 * Created by dingmouren on 2017/8/15.
 * email:naildingmouren@gmail.com
 */

public class GlideTestActivity extends AppCompatActivity {
    private String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502771282999&di=5d754e201281f5bd9f1095436a058ba0&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3D75aaa91fa444ad342eea8f83e59220c2%2F0bd162d9f2d3572cf556972e8f13632763d0c388.jpg";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        final PaletteImageView paletteImageView = (PaletteImageView) findViewById(R.id.img);
        Glide.with(this).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                if (resource != null){
                    paletteImageView.setBitmap(resource);
                }
            }
        });
    }
}
