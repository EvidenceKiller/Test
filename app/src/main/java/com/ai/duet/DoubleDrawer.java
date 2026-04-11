package com.ai.duet;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.adl.base.AdlApp;
import com.adl.mount.core.TsTaskDrawer;
import com.adl.mount.sport.TsSportFrame;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/6/14
 * Describe   : 类描述
 */
public class DoubleDrawer extends TsTaskDrawer {

    private Paint mPaint;
    private int mCenterLineWidth;

    public DoubleDrawer() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeWidth(AdlApp.px2Dp(10));
        mPaint.setTextSize(AdlApp.sp2px(16));

        mCenterLineWidth = AdlApp.px2Dp(10);
    }

    @Override
    public synchronized void updateFrame(TsSportFrame frame) {
        // TODO 帧处理
    }

    @Override
    protected void drawScene(Canvas canvas) {
        // 中线
        mPaint.setStrokeWidth(mCenterLineWidth);
        mPaint.setTypeface(Typeface.DEFAULT);
        mPaint.setStyle(Paint.Style.FILL);
        int cx = viewWidth / 2;
        int startX = cx - mCenterLineWidth / 2;
        canvas.drawLine(startX, 0, startX, viewHeight, mPaint);
    }
}
