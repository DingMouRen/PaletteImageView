package com.dingmouren.paletteimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by dingmouren on 2017/4/25.
 */

public class CustomImageView extends ImageView {
    private static final String TAG = CustomImageView.class.getName();
    private Paint mPaintPath;
    private Paint mPaintBitmap;
    private int mHorizontalRadius;
    private int mVerticalRadius;
    private Bitmap mBitmap;
    private Canvas mCanvasPath;
    private PorterDuffXfermode mPorterDuffXfermode;
    private Path mPathLeftTop,mPathLeftBottom,mPathRightTop,mPathRightBottom;
    public CustomImageView(Context context) {
        this(context,null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public void draw(Canvas canvas) {
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(), Bitmap.Config.ARGB_4444);
        mCanvasPath = new Canvas(mBitmap);
        super.draw(mCanvasPath);
        drawRound();
        canvas.drawBitmap(mBitmap,0,0,mPaintBitmap);
    }


    private void init() {

        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        mPaintPath = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintPath.setXfermode(mPorterDuffXfermode);

        mPaintBitmap = new Paint(Paint.ANTI_ALIAS_FLAG);

    }



    public void setCornerRadius(int radius){
        if (radius > getWidth() / 2 || radius > getHeight() / 2){
            if (getWidth() > getHeight()){
                radius = getHeight() / 2;
            }else {
                radius = getWidth() / 2;
            }
        }
        this.mHorizontalRadius = radius;
        this.mVerticalRadius = radius;
        invalidate((int)getX(),(int)getY(),getMeasuredWidth(),getMeasuredHeight());
    }


    private void drawRound() {

        mPathLeftTop = new Path();
        mPathLeftTop.moveTo(0, mVerticalRadius);
        mPathLeftTop.lineTo(0, 0);
        mPathLeftTop.lineTo(mHorizontalRadius, 0);
        mPathLeftTop.arcTo(new RectF(0, 0, mHorizontalRadius * 2, mVerticalRadius * 2), -90, -90);
        mPathLeftTop.close();
        mCanvasPath.drawPath(mPathLeftTop, mPaintPath);

        mPathLeftBottom = new Path();
        mPathLeftBottom.moveTo(0, getHeight() - mVerticalRadius);
        mPathLeftBottom.lineTo(0, getHeight());
        mPathLeftBottom.lineTo(mHorizontalRadius, getHeight());
        mPathLeftBottom.arcTo(new RectF(0, getHeight() - mVerticalRadius * 2, mHorizontalRadius * 2, getHeight()), 90, 90);
        mPathLeftBottom.close();
        mCanvasPath.drawPath(mPathLeftBottom, mPaintPath);

        mPathRightBottom = new Path();
        mPathRightBottom.moveTo(getWidth() - mHorizontalRadius, getHeight());
        mPathRightBottom.lineTo(getWidth(), getHeight());
        mPathRightBottom.lineTo(getWidth(), getHeight() - mVerticalRadius);
        mPathRightBottom.arcTo(new RectF(getWidth() - mHorizontalRadius * 2, getHeight() - mVerticalRadius * 2, getWidth(), getHeight()), -0, 90);
        mPathRightBottom.close();
        mCanvasPath.drawPath(mPathRightBottom, mPaintPath);

        mPathRightTop = new Path();
        mPathRightTop.moveTo(getWidth(), mVerticalRadius);
        mPathRightTop.lineTo(getWidth(), 0);
        mPathRightTop.lineTo(getWidth() - mHorizontalRadius, 0);
        mPathRightTop.arcTo(new RectF(getWidth() - mHorizontalRadius * 2, 0, getWidth(), 0 + mVerticalRadius * 2), -90, 90);
        mPathRightTop.close();
        mCanvasPath.drawPath(mPathRightTop, mPaintPath);
    }

}
