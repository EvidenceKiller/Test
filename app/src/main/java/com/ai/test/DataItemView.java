package com.ai.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: AdlMountGeneral
 * Author     : 南山
 * Date       : 2025/7/23
 * Describe   : 类描述
 */
public class DataItemView extends View {

    private List<ContentDataItem> items = new ArrayList<>();
    private Paint paint;
    private Paint paint2;
    private int totalTime = 2 * 60 * 1000; // 总时间
    private float totalAngle = 180f;
    private int stepLen = 2;
    private int paddingV = 96;
    private int paddingH = 72;
    private int minAngle = 50;
    private int maxAngle = 100;

    private Path path;
    private float[] dashPathEffect = new float[]{2, 2};

    public DataItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setColor(Color.YELLOW);
        paint.setTextSize(24f);
        paint.setFakeBoldText(true);

        paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(2);
        paint2.setColor(Color.BLACK);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setPathEffect(new DashPathEffect(dashPathEffect, 0));
        path = new Path();
    }

    public void setData(int tTime, List<ContentDataItem> list, int min, int max, float vAngle) {
        totalTime = tTime;
        minAngle = min;
        maxAngle = max;
        totalAngle = vAngle;
        items.clear();
        items.addAll(list);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        if (width == 0 || height == 0) {
            return;
        }

        canvas.drawColor(Color.WHITE);
        int size = items.size();
        int vw = width - paddingV;
        int vh = height - paddingH;

        float stepWidth = (float) vw / totalTime;
        float stepHeight = (float) vh / totalAngle;
        int offsetX = paddingV / 2;
        int offsetY = paddingH / 2;
        int maxX = width - offsetX;

        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(4);
        paint.setColor(Color.BLACK);
        canvas.drawLine(offsetX, offsetY, offsetX, height - offsetY, paint);
        canvas.drawLine(offsetX, height - offsetY, width - offsetX, height - offsetY, paint);

        // 垂直
        int lineW = 8;
        for (int i = 0; i < totalAngle; i += 20) {
            float r = i / totalAngle;
            float y = offsetY + r * vh + 1;
            String angle = String.valueOf((int) (totalAngle - i));
            paint.setStrokeWidth(2);
            paint.setColor(Color.BLACK);
            canvas.drawLine(offsetX - lineW, y, offsetX, y, paint);
            canvas.drawText(angle, offsetX - lineW - 40, y - 8, paint);

            paint.setStrokeWidth(1);
            paint.setColor(Color.parseColor("#9a333333"));
            canvas.drawLine(offsetX, y, offsetX + vw, y, paint);
        }

        // 水平
        for (int i = 1000; i <= totalTime; i += 1000) {
            float r = (i * 1.0f) / totalTime;
            float x = offsetX + r * vw;
            float y = height - offsetY;

            if (i % 5000 == 0) {
                paint.setStrokeWidth(2);
                paint.setColor(Color.BLACK);
                canvas.drawLine(x, y, x, y + lineW, paint);
                int ii = i / 1000;
                String text = ii < 10 ? "0" + ii : ii + "";
                canvas.drawText(text, x - 12, y + lineW + 24, paint);
            }

            paint.setStrokeWidth(1);
            paint.setColor(Color.parseColor("#9a333333"));
            canvas.drawLine(x, offsetY, x, offsetY + vh, paint);
        }

        // 范围
        int y1 = (int) (offsetY + ((totalAngle - maxAngle) / totalAngle * vh) + 1);
        int y2 = (int) (offsetY + ((totalAngle - minAngle) / totalAngle * vh) + 1);
        Rect rect = new Rect(offsetX, y1, offsetX + vw, y2);
        paint.setColor(Color.parseColor("#8f9AF8C9"));
        canvas.drawRect(rect, paint);

        // 内容

        path.reset();
        long lastTime = 0;
        List<PointF> points = new ArrayList<>();
        List<Boolean> colorList = new ArrayList<>();
        float lastX = 0;
        float lastY = 0;
        long ssTime = totalTime > 40 * 1000 ? 960 : 480;
        for (int i = 1; i < size; i += stepLen) {
            ContentDataItem item = items.get(i);

            int time = (int) item.time;
            float a = (float) item.angle;
            float x = Math.min(offsetX + time * stepWidth, maxX);
            float y = Math.max(offsetY + (totalAngle - a) * stepHeight, offsetY);
            if (i == 1) {
                path.moveTo(x, y);
            } else {
                float controlX = (lastX + x) / 2;
                float controlY = (lastY + y) / 2;
                path.quadTo(controlX, controlY, x, y);
                //path.lineTo(x, y);
            }

            // 上一个点
            lastX = x;
            lastY = y;

            if (item.time - lastTime >= ssTime) {// 960
                lastTime = item.time;
                points.add(new PointF(x, y));
                colorList.add(a >= minAngle && a <= maxAngle);
            }
        }

        //paint.setStrokeWidth(1.5f);
        //paint.setColor(Color.WHITE);
        //paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint2);

        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        for (int index = 0; index < points.size(); index++) {
            PointF p = points.get(index);
            paint.setColor(colorList.get(index) ? Color.parseColor("#34C759") : Color.RED);
            canvas.drawCircle(p.x, p.y, 6, paint);
        }
    }

}
