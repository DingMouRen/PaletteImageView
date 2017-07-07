package com.dingmouren.paletteimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.graphics.Palette;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.support.v7.widget.AppCompatImageView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by dingmouren on 2017/4/25.
 */

public class PaletteImageView extends View implements ViewTreeObserver.OnGlobalLayoutListener {

    private static final String TAG = PaletteImageView.class.getName();
    private static final int MSG = 0x101;
    private static final int DEFAULT_PADDING = 40;
    private static final int DEFAULT_OFFSET = 20;
    private static final int DEFAULT_SHADOW_RADIUS = 20;
    private Paint mPaintShadow;
    private int mRadius;
    private int mPadding;
    private Bitmap mBitmap;
    private int mImgId;
    private AsyncTask mAsyncTask;
    public int mMainColor = -1;
    private int mOffsetX = DEFAULT_OFFSET;
    private int mOffsetY = DEFAULT_OFFSET;
    private int mShadowRadius = DEFAULT_SHADOW_RADIUS;
    private Palette mPalette;
    private RectF mRectFShadow;
    private Bitmap mRealBitmap;
    private int mOnMeasureHeightMode = -1;



    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mPaintShadow.setShadowLayer(mShadowRadius, mOffsetX, mOffsetY, mMainColor);
            invalidate();
        }
    };

    @Override
    protected void onDetachedFromWindow() {
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
        super.onDetachedFromWindow();
    }

    public PaletteImageView(Context context) {
        this(context, null);
    }

    public PaletteImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaletteImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PaletteImageView);
        mRadius = a.getDimensionPixelSize(R.styleable.PaletteImageView_paletteRadius, 0);
        mImgId = a.getResourceId(R.styleable.PaletteImageView_paletteSrc, 0);
        mPadding = a.getDimensionPixelSize(R.styleable.PaletteImageView_palettePadding, DEFAULT_PADDING);
        a.recycle();

        setPadding(mPadding, mPadding, mPadding, mPadding);

        mPaintShadow = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintShadow.setDither(true);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        mOnMeasureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (mOnMeasureHeightMode == MeasureSpec.UNSPECIFIED) {
            if (mBitmap != null) {
                height = (int) ((width -mPadding * 2)  * (mBitmap.getHeight() * 1.0f / mBitmap.getWidth())) + mPadding * 2;
            }
            if (mImgId != 0 && mRealBitmap != null){
                height = mRealBitmap.getHeight() + mPadding * 2;
            }
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
            zipBitmap(mImgId,mBitmap,mOnMeasureHeightMode);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mRealBitmap != null) {
            if (mRectFShadow == null) {
                WeakReference<RectF> weakRectF = new WeakReference<RectF>(new RectF(mPadding, mPadding, getWidth() - mPadding, getHeight() - mPadding));
                if (weakRectF.get() == null) return;
                mRectFShadow = weakRectF.get();
            }

            int id1 = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null, Canvas.ALL_SAVE_FLAG);
            canvas.drawRoundRect(mRectFShadow, mRadius, mRadius, mPaintShadow);
            canvas.restoreToCount(id1);
            canvas.drawBitmap(createRoundConerImage(mRealBitmap, mRadius), mPadding, mPadding, null);
            if (mMainColor != -1) mAsyncTask.cancel(true);
        }

    }

    @Override
    public void onGlobalLayout() {
    }

    private Bitmap createRoundConerImage(Bitmap source, int radius) {
        WeakReference<Paint> weakPaint = new WeakReference<Paint>(new Paint());
        if (weakPaint.get() == null) return null;
        Paint paint = weakPaint.get();
        paint.setAntiAlias(true);
        paint.setDither(true);
        WeakReference<Bitmap> weakBitmap = new WeakReference<Bitmap>(Bitmap.createBitmap(getWidth() - mPadding * 2, getHeight() - mPadding * 2, Bitmap.Config.ARGB_8888));
        if (weakBitmap.get() == null) return null;
        Bitmap target = weakBitmap.get();

        WeakReference<Canvas> weakCanvas = new WeakReference<Canvas>(new Canvas(target));
        if (weakCanvas.get() == null) return null;
        Canvas canvas = weakCanvas.get();
        WeakReference<RectF> weakRectF = new WeakReference<RectF>(new RectF(0, 0, source.getWidth(), source.getHeight()));
        if (weakRectF.get() == null) return null;
        RectF rect = weakRectF.get();
        canvas.drawRoundRect(rect, radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        paint.setXfermode(null);
        return target;
    }


    @Override
    protected void onAttachedToWindow() {
        getViewTreeObserver().addOnGlobalLayoutListener(this);
        super.onAttachedToWindow();
    }


    /**
     * 设置位图
     *
     * @param bitmap
     */
    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    /**
     * 初始化阴影颜色
     */
    private void initShadow(Bitmap bitmap) {
        if (bitmap != null) {
            mAsyncTask = Palette.from(bitmap).generate(paletteAsyncListener);
        }
    }



    private void zipBitmap(int imgId,Bitmap bitmap,int heightNode) {
        int rawWidth = 0;
        int rawHeight = 0;
        if (imgId !=0 && bitmap == null){
            WeakReference<BitmapFactory.Options> weakOptions = new WeakReference<BitmapFactory.Options>(new BitmapFactory.Options());
            if (weakOptions.get() == null) return;
            BitmapFactory.Options options = weakOptions.get();
            options = new BitmapFactory.Options();
            BitmapFactory.decodeResource(getResources(), imgId, options);
            rawWidth = options.outWidth;
            rawHeight = options.outHeight;
            bitmap = BitmapFactory.decodeResource(getResources(),mImgId);
        }else if (imgId == 0 && bitmap != null){
            rawWidth = bitmap.getWidth();
            rawHeight = bitmap.getHeight();
        }
        int reqWidth = getWidth() - mPadding - mPadding;
        int reqHeight = getHeight() - mPadding - mPadding;
        if (reqHeight <= 0 || reqWidth <= 0) return;
        WeakReference<Matrix> weakMatrix = new WeakReference<Matrix>(new Matrix());
        if (weakMatrix.get() == null) return;
        Matrix matrix = weakMatrix.get();
        if (heightNode == 0) {
            float scale = rawHeight * 1.0f / rawWidth;
            mRealBitmap = Bitmap.createScaledBitmap(bitmap, reqWidth, (int) (reqWidth * scale), true);
        }else {
            int dx = 0;
            int dy = 0;
            int small = Math.min(rawHeight, rawWidth);
            int big = Math.max(reqWidth, reqHeight);
            float scale = big * 1.0f / small;
            matrix.setScale(scale,scale);
            if (rawHeight > rawWidth) {
                dy = (rawHeight - rawWidth) / 2 ;
            } else if (rawHeight < rawWidth) {
                dx = (rawWidth - rawHeight) / 2 ;
            }
            mRealBitmap = Bitmap.createBitmap(bitmap,dx , dy, small, small, matrix, true);
        }
        initShadow(bitmap);

    }

    /**
     * 计算inSampleSize
     *
     * @return
     */
    private int calculateInSampleSize(int rawWidth, int rawHeight, int reqWidth, int reqHeight) {
        int inSampleSize = 0;
        try {
            inSampleSize = 1;
            if (rawHeight > reqHeight || rawWidth > reqWidth) {
                int halfHeight = rawHeight / 2;
                int halfWidth = rawWidth / 2;
                while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                    inSampleSize *= 2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inSampleSize;
    }

    /**
     * 设置半径
     *
     * @param raius
     */
    public void setPaletteRadius(int raius) {
        this.mRadius = raius;
        invalidate();
    }

    /**
     * 设置在x y方向上阴影的偏移量
     *
     * @param offsetX
     * @param offsetY
     */
    public void setPaletteShadowOffset(int offsetX, int offsetY) {
        if (offsetX >= mPadding) {
            this.mOffsetX = mPadding;
        } else {
            this.mOffsetX = offsetX;
        }
        if (offsetY > mPadding) {
            this.mOffsetX = mPadding;
        } else {
            this.mOffsetY = offsetY;
        }

        mHandler.sendEmptyMessage(MSG);
    }

    /**
     * 设置阴影的半径
     *
     * @param radius
     */
    public void setPaletteShadowRadius(int radius) {
        this.mShadowRadius = radius;
        mHandler.sendEmptyMessage(MSG);
    }


    private Palette.PaletteAsyncListener paletteAsyncListener = new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(Palette palette) {
            if (palette != null) {
                mPalette = palette;
                mMainColor = palette.getDominantSwatch().getRgb();
                mHandler.sendEmptyMessage(MSG);

            }
        }
    };

    public int getVibrantColor() {
        if (mPalette == null) return 0;
        return mPalette.getVibrantSwatch() == null ? 0 : mPalette.getVibrantSwatch().getRgb();
    }

    public int getVibrantTitleTextColor() {
        if (mPalette == null) return 0;
        return mPalette.getVibrantSwatch() == null ? 0 : mPalette.getVibrantSwatch().getTitleTextColor();
    }

    public int getVibrantContentTextColor() {
        if (mPalette == null) return 0;
        return mPalette.getVibrantSwatch() == null ? 0 : mPalette.getVibrantSwatch().getBodyTextColor();
    }

    public int getDarkVibrantColor() {
        if (mPalette == null) return 0;
        return mPalette.getDarkVibrantSwatch() == null ? 0 : mPalette.getDarkVibrantSwatch().getRgb();
    }

    public int getDarkVibrantTitleTextColor() {
        if (mPalette == null) return 0;
        return mPalette.getDarkVibrantSwatch() == null ? 0 : mPalette.getDarkVibrantSwatch().getTitleTextColor();
    }

    public int getDarkVibrantContentTextColor() {
        if (mPalette == null) return 0;
        return mPalette.getDarkVibrantSwatch() == null ? 0 : mPalette.getDarkVibrantSwatch().getBodyTextColor();
    }

    public int getLightVibrantColor() {
        if (mPalette == null) return 0;
        return mPalette.getLightVibrantSwatch() == null ? 0 : mPalette.getLightVibrantSwatch().getRgb();
    }

    public int getLightVibrantTitleTextColor() {
        if (mPalette == null) return 0;
        return mPalette.getLightVibrantSwatch() == null ? 0 : mPalette.getLightVibrantSwatch().getTitleTextColor();
    }

    public int getLightVibrantContentTextColor() {
        if (mPalette == null) return 0;
        return mPalette.getLightVibrantSwatch() == null ? 0 : mPalette.getLightVibrantSwatch().getBodyTextColor();
    }

    public int getMutedColor() {
        if (mPalette == null) return 0;
        return mPalette.getMutedSwatch() == null ? 0 : mPalette.getMutedSwatch().getRgb();
    }

    public int getMutedTitleTextColor() {
        if (mPalette == null) return 0;
        return mPalette.getMutedSwatch() == null ? 0 : mPalette.getMutedSwatch().getTitleTextColor();
    }

    public int getMutedContentTextColor() {
        if (mPalette == null) return 0;
        return mPalette.getMutedSwatch() == null ? 0 : mPalette.getMutedSwatch().getBodyTextColor();
    }

    public int getDarkMutedColor() {
        if (mPalette == null) return 0;
        return mPalette.getDarkMutedSwatch() == null ? 0 : mPalette.getDarkMutedSwatch().getRgb();
    }

    public int getDarkMutedTitleTextColor() {
        if (mPalette == null) return 0;
        return mPalette.getDarkMutedSwatch() == null ? 0 : mPalette.getDarkMutedSwatch().getTitleTextColor();
    }

    public int getDarkMutedContentTextColor() {
        if (mPalette == null) return 0;
        return mPalette.getDarkMutedSwatch() == null ? 0 : mPalette.getDarkMutedSwatch().getBodyTextColor();
    }

    public int getLightMutedColor() {
        if (mPalette == null) return 0;
        return mPalette.getLightMutedSwatch() == null ? 0 : mPalette.getLightMutedSwatch().getRgb();
    }

    public int getLightMutedTitleTextColor() {
        if (mPalette == null) return 0;
        return mPalette.getLightMutedSwatch() == null ? 0 : mPalette.getLightMutedSwatch().getTitleTextColor();
    }

    public int getLightMutedContentTextColor() {
        if (mPalette == null) return 0;
        return mPalette.getLightMutedSwatch() == null ? 0 : mPalette.getLightMutedSwatch().getBodyTextColor();
    }

    public Palette getPalette() {
        return mPalette;
    }

}
