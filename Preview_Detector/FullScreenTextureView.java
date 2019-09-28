package com.insta360.bvaandroid.BvaDemo.Preview_Detector;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;

public class FullScreenTextureView extends TextureView {
    private int mRatioWidth = 0;
    private int mRatioHeight = 0;

    public FullScreenTextureView(Context context) {
        this(context, null);
    }

    public FullScreenTextureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FullScreenTextureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /*private Matrix fullScreenMatrix = new Matrix();
    private Matrix defMatrix = null;

    @Override
    public void setTransform(Matrix transform) {
        if(defMatrix == null){
            defMatrix = transform;
        }
        super.setTransform(transform);
    }*/

    /**
     * 设置此视图的纵横比。 将基于从参数计算的比例来测量视图的大小。
     * mi 8上测试，这个比例是1280 / 960
     * @param width  Relative horizontal size
     * @param height Relative vertical size
     */
    public void setAspectRatio(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Size cannot be negative.");
        }
        mRatioWidth = width;
        mRatioHeight = height;
        requestLayout();
    }

    /**
     * 设置为全屏TextureView
     * @param widthMeasureSpec 当前TextureView的宽
     * @param heightMeasureSpec 当前TextureView的高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (0 == mRatioWidth || 0 == mRatioHeight) {
            setMeasuredDimension(width, height);
        } else {
            //setMeasuredDimension(1080, 1920);
            //Log.d("Camera2BasicFragment", "mRatioWidth: "+mRatioWidth+", mRatioHeight: "+mRatioHeight);
            setMeasuredDimension(mRatioWidth, mRatioHeight);
        }
    }
}
