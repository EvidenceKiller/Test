package com.ai.test;

/**
 * Created by viwenzhang on 2022/1/24.
 * Description:
 */

import android.content.Context;
import android.media.SoundPool;

import com.adl.ts.general.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SoundUtil {
    //  添加的声音资源参数
    private HashMap<Integer, Integer> soundPoolMap;
    //  声音池
    private SoundPool mSoundPool;
    //  正在播放的资源
    private int playOrder = -1;
    //  是否加载成功
    private boolean isLoadSuccess;
    //  正在播放 序号列表
    private List<Integer> playOrderList;
    //  单例
    private static SoundUtil instance;

    private SoundUtil() {

    }

    public static SoundUtil getInstance() {
        if (instance == null) {
            instance = new SoundUtil();
        }
        return instance;
    }

    //  初始化声音
    public void init(Context context) {
        mSoundPool = new SoundPool.Builder().setMaxStreams(2)// 设置最大同时播放流
                .build();

        playOrderList = new ArrayList<>();
        soundPoolMap = new HashMap<>();
        //  运动开始
        putSound(context, SoundOrderConst.SPORT_START, R.raw.sport_start);
        //  运动计数
        putSound(context, SoundOrderConst.SPORT_COUNT, R.raw.sport_count);
        //  运动结束
        putSound(context, SoundOrderConst.SPORT_END, R.raw.sport_end);
        //  运动错误警告
        putSound(context, SoundOrderConst.SPORT_WARN, R.raw.sport_warn);
        //  运动倒计时
        putSound(context, SoundOrderConst.SPORT_COUNT_DOWN, R.raw.sport_count_down);
        //  成功
        putSound(context, SoundOrderConst.SUCCESS, R.raw.success);
        //  开关
        putSound(context, SoundOrderConst.SWITCH, R.raw.mswitch);
        //  按键
        putSound(context, SoundOrderConst.PRESS, R.raw.mpress);
//        //  运动违规
//        putSound(context, SoundOrderConst.SPORT_VIOLATION, R.raw.sport_violation);
//        //  运动倒计时(枪声)
//        putSound(context, SoundOrderConst.SPORT_COUNT_DOWN_GUN, R.raw.sport_count_down_gun);
//        //  运动倒计时（各就位+滴）
//        putSound(context, SoundOrderConst.SPORT_COUNT_DOWN_BALL, R.raw.sport_count_down_ball);
//        //  运动倒计时（各就位+预备+枪声）
//        putSound(context, SoundOrderConst.SPORT_COUNT_DOWN_GUN_PREPARE, R.raw.sport_count_down_gun_prepare);
//        //  运动倒计时(各就位+枪声)
//        putSound(context, SoundOrderConst.SPORT_COUNT_DOWN_GUN_HOLD, R.raw.sport_count_down_gun_hold);
//        //  冲线
//        putSound(context, SoundOrderConst.SPORT_BREAST_TAPE, R.raw.sport_breast_tape);
//        //  抢跑
//        putSound(context, SoundOrderConst.SPORT_GRAB_RUN, R.raw.sport_grab_run);
//        //  触点
//        putSound(context, SoundOrderConst.SPORT_CONTACT, R.raw.sport_contact);
//        putSound(context, SoundOrderConst.SPORT_CONTACT_2, R.raw.sport_contact_2);

        mSoundPool.setOnLoadCompleteListener((soundPool, i, i1) -> isLoadSuccess = true);
    }

    private void putSound(Context context, int order, int soundRes) {
        // 上下文，声音资源id，优先级
        soundPoolMap.put(order, mSoundPool.load(context, soundRes, 0));
    }

    //  根据序号播放声音,不需要停止
    public void playSound(int order) {
        playWithPriority(order, 0);
    }

    //  根据序号播放声音,设置优先级，是否能停止
    public void playSound(int order, int priority, boolean canStop) {
        int pOrder = playWithPriority(order, priority);
        playOrderList.add(pOrder);
        if (canStop) {
            playOrder = pOrder;
        }
    }

    //  根据序号播放声音
    private int playWithPriority(int order, int priority) {
        Integer res = soundPoolMap.get(order);
        if (res == null || !isLoadSuccess) {
            return 0;
        }
        return mSoundPool.play(res, 1f, 1f, priority, 0, 1);
    }

    //  停止播放所有声音
    public void stopAll() {
        for (int order : playOrderList) {
            stop(order);
        }
        playOrderList.clear();
    }

    //  停止正在播放的声音
    public void stop() {
        stop(playOrder);
    }

    //  停止播放的声音
    public void stop(int order) {
        if (order <= 0) return;
        mSoundPool.stop(order);
    }

    // 释放内存
    public void release() {
        if (mSoundPool != null) {
            mSoundPool.release();
            mSoundPool = null;
        }
        instance = null;
    }
}


