package com.dingmouren.paletteimageview.listener;

import com.dingmouren.paletteimageview.PaletteImageView;

/**
 * Created by dingmouren on 2017/8/15.
 * email:naildingmouren@gmail.com
 */

public interface OnParseColorListener {
    void onComplete(PaletteImageView paletteImageView);
    void onFail();
}
