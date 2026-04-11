package com.ai.test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.adl.base.activity.NextWriter;
import com.adl.base.common.AdlBitmapUtil;
import com.adl.base.common.AdlExecutor;
import com.adl.base.common.AdlSkeletonUtil;
import com.adl.base.common.AdlToast;
import com.adl.base.config.AdlUIConfig;
import com.adl.base.config.RoleConfigRopeSkip;
import com.adl.base.element.ElementBase;
import com.adl.base.overlay.OnRenderScene;
import com.adl.base.overlay.SportOverlayRopeSkip;
import com.adl.lib.AdlCamera;
import com.adl.lib.AdlCameraConfig;
import com.adl.lib.AdlCameraFrameProcessor;
import com.adl.lib.AdlCameraPreviewView;
import com.adl.lib.pose.AdlObject;
import com.adl.lib.pose.AdlPoseKeyPoint;
import com.adl.lib.pose.AdlPoseSkeleton;
import com.adl.mount.impl.TsSingleProcessor;
import com.adl.mount.impl.TsSportFrameSingle;
import com.adl.mount.impl.TsSportLifecycleSingle;
import com.adl.mount.sport.TsSportRegion;
import com.adl.mount.sport.TsYuvFrame;
import com.adl.mount.sport.TsYuvFrameRecCache;
import com.adl.processor.SportConfigSingle;
import com.adl.processor.SportModelType;
import com.adl.processor.SportProcessorSingle;
import com.adl.processor.SportProcessorStatus;
import com.adl.sport.general.EuHelper;
import com.adl.ts.general.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: AdlMountGeneral
 * Author     : 南山
 * Date       : 2025/7/16
 * Describe   : 类描述
 */
public class ContentActivity extends BaseActivity {

    public static ContentData contentData = new ContentData();
    public static final int TotalTime = 1 * 60 * 1000;
    public static int currentTime = 10 * 1000;

    AdlCameraPreviewView mPreviewView;
    SportOverlayRopeSkip overlay;
    SportProcessorSingle mProcessor;
    SportConfigSingle tsConfig;
    TsSportLifecycleSingle lifecycleSingle;
    AdlCameraFrameProcessor frameProcessor;
    Button bntStart;

    TextView wristLTv;
    TextView wristRTv;

    TextView elbowLTv;
    TextView elbowRTv;

    TextView shoulderLTv;
    TextView shoulderRTv;
    public static Bitmap shoulderLBitmap;
    public static Bitmap shoulderRBitmap;

    TextView hipLTv;
    TextView hipRTv;
    public static Bitmap hipLBitmap;
    public static Bitmap hipRBitmap;

    TextView kneeLTv;
    TextView kneeRTv;
    public static Bitmap kneeLBitmap;
    public static Bitmap kneeRBitmap;

    TextView ankleLTv;
    TextView ankleRTv;

    ZDrawer drawer;
    TextView timerTv;
    long startTime;
    long startFrameTime;
    Handler handler = new Handler();
    // 肩关节 75°-85°
    // 髋关节 145°-180°
    // 膝关节 145°-175°

    TsYuvFrame newFrame;
    boolean saveBitmapDoing;
    boolean startDoing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_content_support);

        mPreviewView = findViewById(R.id.preview_view);
        overlay = findViewById(R.id.overlay);
        overlay.enableDrawSkeleton(true);
        overlay.enableDrawBox(false);
        overlay.enableDebug(true);

        overlay.addElement(drawer = new ZDrawer());

        wristLTv = findViewById(R.id.wrist_left);
        wristRTv = findViewById(R.id.wrist_right);
        elbowLTv = findViewById(R.id.elbow_left);
        elbowRTv = findViewById(R.id.elbow_right);
        shoulderLTv = findViewById(R.id.shoulder_left);
        shoulderRTv = findViewById(R.id.shoulder_right);
        hipLTv = findViewById(R.id.hip_left);
        hipRTv = findViewById(R.id.hip_right);
        kneeLTv = findViewById(R.id.knee_left);
        kneeRTv = findViewById(R.id.knee_right);
        ankleLTv = findViewById(R.id.ankle_left);
        ankleRTv = findViewById(R.id.ankle_right);
        timerTv = findViewById(R.id.timer_tv);
        bntStart = findViewById(R.id.bnt_start);

        bntStart.setOnClickListener(view -> {

            if (!startDoing) {
                mProcessor.changeTo(SportProcessorStatus.Sporting);
                lifecycleSingle.toSporting();

                startTime = System.currentTimeMillis();
                currentTime = 0;
                timerTv.setText("00:59");
                contentData.clear();

                AdlBitmapUtil.recycle(shoulderLBitmap);
                AdlBitmapUtil.recycle(shoulderRBitmap);
                AdlBitmapUtil.recycle(hipLBitmap);
                AdlBitmapUtil.recycle(hipRBitmap);
                AdlBitmapUtil.recycle(kneeLBitmap);
                AdlBitmapUtil.recycle(kneeRBitmap);

                shoulderLBitmap = null;
                shoulderRBitmap = null;
                hipLBitmap = null;
                hipRBitmap = null;
                kneeLBitmap = null;
                kneeRBitmap = null;

                startFrameTime = 0;

                startDoing = true;
                bntStart.setText("停止运动");

                handler.postDelayed(timerExec, 1000);
            } else {
                AdlToast.show("运动结束");
                startDoing = false;
                bntStart.setText("开始运动");
                mProcessor.changeTo(SportProcessorStatus.Idle);
                lifecycleSingle.toIdle();
            }

        });

        findViewById(R.id.bnt_next).setOnClickListener(view -> {
            NextWriter.with(ContentActivity.this, ReportActivity.class).go();
        });

        findViewById(R.id.bnt_save).setOnClickListener(view -> {
            overlay.saveConfig();
            AdlToast.show("保存成功");
        });

        frameProcessor = (yuv, info) -> {
            long frameTime = System.currentTimeMillis();
            info.setImageId(String.valueOf(frameTime));
            info.setFrameTimestamp(frameTime);
            mProcessor.onFrame(yuv, info);
        };

        lifecycleSingle = new TsSportLifecycleSingle(new TsSingleProcessor() {

            @Override
            public void onInit() {

            }

            @Override
            public void onReset() {

            }

            @Override
            public void onRelease() {

            }

            @Override
            public String version() {
                return null;
            }

            @Override
            public void onAnalyzer(TsSportFrameSingle frame) {

                if (!startDoing) return;
                AdlPoseSkeleton skeleton = frame.skeleton;
                if (skeleton == null) return;
                if (startFrameTime <= 0) startFrameTime = Long.parseLong(frame.imageId);

                AdlPoseKeyPoint lShoulder = skeleton.getLShoulder();
                AdlPoseKeyPoint rShoulder = skeleton.getRShoulder();
                AdlPoseKeyPoint lHip = skeleton.getLHip();
                AdlPoseKeyPoint rHip = skeleton.getRHip();
                AdlPoseKeyPoint lZ = getZCenter(new AdlPoseKeyPoint((lShoulder.x + rShoulder.x) / 2, (lShoulder.y + rShoulder.y) / 2),
                        new AdlPoseKeyPoint((lHip.x + rHip.x) / 2, (lHip.y + rHip.y) / 2));
                drawer.updateZPoint(lZ);

                runOnUiThread(() -> {
                    caAngle(skeleton, false);
                    caAngle(skeleton, true);
                });
            }
        });

        // 运动配置
        tsConfig = new SportConfigSingle();
        tsConfig.debug = true;
        tsConfig.bodyModelId = 1003; // 通用人框
        tsConfig.poseModelId = 2005; // 通用骨骼
        tsConfig.drawAllBody = false;
        tsConfig.drawSingleBody = true;
        tsConfig.drawSkeleton = true;

        // 单引擎
        mProcessor = new SportProcessorSingle();
        mProcessor.setSportLifecycle(lifecycleSingle);
        mProcessor.setConfig(tsConfig);// 配置项，必须
        mProcessor.setPoseType(SportModelType.KunL);
        mProcessor.open(overlay); // ui
        mProcessor.setPrescriptionCache(40);// 运动处方开启
        mProcessor.setBodyFilter((list, region) -> {

            List<AdlObject> temp = new ArrayList<>();
            for (AdlObject obj : list) {
                if (obj.isPerson() && obj.score > 0.4f) {
                    temp.add(obj);
                }
            }

            if (temp.size() == 1) {
                return temp.get(0);
            }

            if (region != null) {
                int rx = region.centerX();
                int ry = region.centerY();
                AdlObject target = null;
                float minDis = Float.MAX_VALUE;
                for (AdlObject obj : temp) {
                    int cx = obj.centerX();
                    int cy = obj.bottom();
                    float dis = (float) EuHelper.distanceByPoint(rx, ry, cx, cy);
                    if (target == null || dis < minDis) {
                        target = obj;
                        minDis = dis;
                    }
                }
                return target;
            }

            return temp.size() > 0 ? temp.get(0) : null;
        });
        mProcessor.onInit();

        newFrame = new TsYuvFrame();
    }

    @Override
    protected void onResume() {
        super.onResume();

        RoleConfigRopeSkip uiConfig = AdlUIConfig.instance().readConfig(overlay.getConfigKey(), RoleConfigRopeSkip.class);
        if (uiConfig == null) uiConfig = new RoleConfigRopeSkip();
        overlay.setConfig(uiConfig, OnRenderScene.StatusSportSet);
        overlay.post(this::loadConfig);

        AdlCamera adlCamera = AdlCameraConfig.builder()
                .setCameraType(AdlCameraConfig.CameraType.CameraX)
                .setPreviewSize(1920, 1080)//需要根据相机支持的尺寸来设置,否则会报错
                .setPreviewFps(24)//预览帧率
                .build();

        mPreviewView.addFrameProcessor(frameProcessor);
        mPreviewView.startPreview(adlCamera);
    }

    private void loadConfig() {
        List<Point> points = overlay.getSportRegion().getPreviewPoints();
        tsConfig.sportRegion = new TsSportRegion(points);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPreviewView.stopPreview();
        mPreviewView.removeFrameProcessor(frameProcessor);
    }

    private void caAngle(AdlPoseSkeleton skeleton, boolean isLeft) {

        AdlPoseKeyPoint Finger;
        AdlPoseKeyPoint Wrist;
        AdlPoseKeyPoint Elbow;
        AdlPoseKeyPoint Shoulder;
        AdlPoseKeyPoint Hip;
        AdlPoseKeyPoint Knee;
        AdlPoseKeyPoint Ankle;
        AdlPoseKeyPoint Heel;
        long frameTime = Long.parseLong(skeleton.getKey());
        long time = frameTime - startFrameTime;

        // 肩关节 75°-85°
        // 髋关节 145°-180°
        // 膝关节 145°-175°

        if (isLeft) {
            Finger = skeleton.getLFinger();
            Wrist = skeleton.getLWrist();
            Elbow = skeleton.getLElbow();
            Shoulder = skeleton.getLShoulder();
            Hip = skeleton.getLHip();
            Knee = skeleton.getLKnee();
            Ankle = skeleton.getLAnkle();
            Heel = skeleton.getLHeel();

            double a1 = EuHelper.calculateAngle(Finger, Wrist, Elbow);
            double a2 = EuHelper.calculateAngle(Elbow, Shoulder, Wrist);
            double a3 = EuHelper.calculateAngle(Shoulder, Elbow, Hip);
            double a4 = EuHelper.calculateAngle(Hip, Shoulder, Knee);
            double a5 = EuHelper.calculateAngle(Knee, Hip, Ankle);
            double a6 = EuHelper.calculateAngle(Ankle, Knee, Heel);
            fTv(wristLTv, a1);
            fTv(elbowLTv, a2);
            fTv(shoulderLTv, a3);
            fTv(hipLTv, a4);
            fTv(kneeLTv, a5);
            fTv(ankleLTv, a6);

            contentData.leftShoulder.add(new ContentDataItem(time, a3));
            contentData.leftHip.add(new ContentDataItem(time, a4));
            contentData.leftKnee.add(new ContentDataItem(time, a5));

            // 肩关节 75°-110°
            if (!inRange(75, 110, a3)) {
                shoulderLTv.setBackgroundColor(Color.RED);
                drawer.drawPoint(Shoulder);

                if (time > 10 * 1000 && shoulderLBitmap == null && !saveBitmapDoing) {
                    AdlExecutor.postTask(() -> {
                        shoulderLBitmap = saveBitmap(skeleton);
                    });
                }

            } else {
                shoulderLTv.setBackgroundColor(Color.TRANSPARENT);
            }

            // 髋关节 145°-180°
            if (!inRange(160, 175, a4)) {
                hipLTv.setBackgroundColor(Color.RED);
                drawer.drawPoint(Hip);

                if (time > 10 * 1000 && hipLBitmap == null && !saveBitmapDoing) {
                    AdlExecutor.postTask(() -> {
                        hipLBitmap = saveBitmap(skeleton);
                    });
                }

            } else {
                hipLTv.setBackgroundColor(Color.TRANSPARENT);
            }

            // 膝关节 145°-180°
            if (!inRange(145, 180, a5)) {
                kneeLTv.setBackgroundColor(Color.RED);
                drawer.drawPoint(Knee);

                if (time > 10 * 1000 && kneeLBitmap == null && !saveBitmapDoing) {
                    AdlExecutor.postTask(() -> {
                        kneeLBitmap = saveBitmap(skeleton);
                    });
                }

            } else {
                kneeLTv.setBackgroundColor(Color.TRANSPARENT);
            }

        } else {
            Finger = skeleton.getRFinger();
            Wrist = skeleton.getRWrist();
            Elbow = skeleton.getRElbow();
            Shoulder = skeleton.getRShoulder();
            Hip = skeleton.getRHip();
            Knee = skeleton.getRKnee();
            Ankle = skeleton.getRAnkle();
            Heel = skeleton.getRHeel();

            double a1 = EuHelper.calculateAngle(Finger, Wrist, Elbow);
            double a2 = EuHelper.calculateAngle(Elbow, Shoulder, Wrist);
            double a3 = EuHelper.calculateAngle(Shoulder, Elbow, Hip);
            double a4 = EuHelper.calculateAngle(Hip, Shoulder, Knee);
            double a5 = EuHelper.calculateAngle(Knee, Hip, Ankle);
            double a6 = EuHelper.calculateAngle(Ankle, Knee, Heel);
            fTv(wristRTv, a1);
            fTv(elbowRTv, a2);
            fTv(shoulderRTv, a3);
            fTv(hipRTv, a4);
            fTv(kneeRTv, a5);
            fTv(ankleRTv, a6);

            contentData.rightShoulder.add(new ContentDataItem(time, a3));
            contentData.rightHip.add(new ContentDataItem(time, a4));
            contentData.rightKnee.add(new ContentDataItem(time, a5));

            // 肩关节 75°-110°
            if (!inRange(75, 110, a3)) {
                shoulderRTv.setBackgroundColor(Color.RED);
                drawer.drawPoint(Shoulder);

                if (time > 10 * 1000 && shoulderRBitmap == null && !saveBitmapDoing) {
                    AdlExecutor.postTask(() -> {
                        shoulderRBitmap = saveBitmap(skeleton);
                    });
                }

            } else {
                shoulderRTv.setBackgroundColor(Color.TRANSPARENT);
            }

            // 髋关节 145°-180°
            if (!inRange(160, 175, a4)) {
                hipRTv.setBackgroundColor(Color.RED);
                drawer.drawPoint(Hip);

                if (time > 10 * 1000 && hipRBitmap == null && !saveBitmapDoing) {
                    AdlExecutor.postTask(() -> {
                        hipRBitmap = saveBitmap(skeleton);
                    });
                }

            } else {
                hipRTv.setBackgroundColor(Color.TRANSPARENT);
            }

            // 膝关节 145°-175°
            if (!inRange(145, 180, a5)) {
                kneeRTv.setBackgroundColor(Color.RED);
                drawer.drawPoint(Knee);

                if (time > 10 * 1000 && kneeRBitmap == null && !saveBitmapDoing) {
                    AdlExecutor.postTask(() -> {
                        kneeRBitmap = saveBitmap(skeleton);
                    });
                }

            } else {
                kneeRTv.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    private void fTv(TextView tv, double angle) {
        tv.setText(String.format("%.1f", angle));
    }

    private AdlPoseKeyPoint getZCenter(AdlPoseKeyPoint shoulder, AdlPoseKeyPoint hip) {
        /////// 计算质心, 人体质心与地面的夹角 /////////
        double d1 = EuHelper.distance(shoulder, hip);
        double m = d1 * 0.59f;
        double n = d1 * 0.41f;
        double zx = (m * hip.x + n * shoulder.x) / (m + n);
        double zy = (m * hip.y + n * shoulder.y) / (m + n);

        // 质心
        return new AdlPoseKeyPoint((float) zx, (float) zy, 0.5f);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPreviewView.release();
        mProcessor.onRelease();
    }

    public static boolean inRange(double min, double max, double value) {
        return value >= min && value <= max;
    }

    private Bitmap saveBitmap(AdlPoseSkeleton skeleton) {
        saveBitmapDoing = true;
        try {
            String imageId = skeleton.getKey();
            TsYuvFrameRecCache cache = mProcessor.getPrescriptionCache();
            boolean isOk = cache.copyFrame(imageId, newFrame);
            if (isOk) {
                return AdlSkeletonUtil.mergeSkeleton(newFrame.yuv, newFrame.width, newFrame.height,
                        skeleton, null, null, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            saveBitmapDoing = false;
        }
        return null;
    }

    private static class ZDrawer extends ElementBase {

        AdlPoseKeyPoint point;
        Paint paint;

        final List<Point> drawPoints = new ArrayList<>();

        public ZDrawer() {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(10);
            paint.setColor(Color.YELLOW);
        }

        void updateZPoint(AdlPoseKeyPoint point) {
            this.point = scalePoint(point);
        }

        void drawPoint(AdlPoseKeyPoint point) {
            synchronized (drawPoints) {
                float sx = viewWidth / 1920f;
                float sy = viewHeight / 1080f;
                drawPoints.add(new Point((int) (point.x * sx), (int) (point.y * sy)));
            }
        }

        AdlPoseKeyPoint scalePoint(AdlPoseKeyPoint point) {
            float sx = viewWidth / 1920f;
            float sy = viewHeight / 1080f;
            return new AdlPoseKeyPoint(point.x * sx, point.y * sy);
        }

        @Override
        public void draw(Canvas canvas) {

            if (point != null) {
                paint.setColor(Color.YELLOW);
                canvas.drawCircle(point.x, point.y, 16, paint);
            }

            synchronized (drawPoints) {
                if (drawPoints.size() > 0) {
                    paint.setColor(Color.RED);
                    for (Point point : drawPoints) {
                        canvas.drawCircle(point.x, point.y, 18, paint);
                    }
                }
                drawPoints.clear();
            }
        }
    }

    private Runnable timerExec = new Runnable() {

        @Override
        public void run() {
            if (!startDoing) {
                timerTv.setText("00:00");
                return;
            }
            long t0 = System.currentTimeMillis() - startTime;
            long t1 = (TotalTime - t0) / 1000;
            currentTime = (int) t0;

            if (t1 >= 1) {
                long m = t1 / 60;
                long s = t1 % 60;
                timerTv.setText(String.format("%02d:%02d", m, s));
                handler.postDelayed(timerExec, 1000);
            } else {
                AdlToast.show("运动结束");
                timerTv.setText("00:00");
                startDoing = false;
                mProcessor.changeTo(SportProcessorStatus.Idle);
                lifecycleSingle.toIdle();
            }
        }
    };

}
