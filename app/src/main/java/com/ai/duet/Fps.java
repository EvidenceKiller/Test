package com.ai.duet;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;

public class Fps {
    private String tag;
    private String fpsStr = "";
    private String intervalStr = "";
    private String otherStr;
    public static float defaultStartX = 20.0F;
    public static float defaultStartY = 150.0F;
    public float startY;
    public float startX;
    private int fpsCount;
    private long startTime;
    private long lastFrameTime;
    private long maxIntervalTime;

    private FpsListener listener;

    public Fps() {
        this.startTime = -1L;
        this.lastFrameTime = -1L;
        this.maxIntervalTime = 0L;
        this.startY = defaultStartY;
        this.startX = defaultStartX;
    }

    public void setListener(FpsListener listener) {
        this.listener = listener;
    }

    public void updateFps() {
        if (this.startTime == -1L) {
            this.startTime = System.currentTimeMillis();
        }

        if (this.lastFrameTime == -1L) {
            this.lastFrameTime = System.currentTimeMillis();
        }

        this.maxIntervalTime = Math.max(this.maxIntervalTime, System.currentTimeMillis() - this.lastFrameTime);
        ++this.fpsCount;
        if (System.currentTimeMillis() - this.startTime >= 990L) {
            this.fpsStr = this.tag + " Fps: " + this.fpsCount + "/s";
//            Log.d("FPS", fpsStr);
            if (listener != null) {
                listener.onFps(this.fpsCount);
            }
            this.intervalStr = "间隔最大: " + this.maxIntervalTime + "ms";
            this.startTime = System.currentTimeMillis();
            this.fpsCount = 0;
            this.maxIntervalTime = 0L;
        }

        this.lastFrameTime = System.currentTimeMillis();
    }
    public void release(){
        listener = null;
    }

    public interface FpsListener {
        void onFps(int fps);
    }
}
