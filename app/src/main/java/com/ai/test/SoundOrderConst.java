package com.ai.test;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({SoundOrderConst.SPORT_START, SoundOrderConst.SPORT_COUNT, SoundOrderConst.SPORT_END,
        SoundOrderConst.SPORT_WARN, SoundOrderConst.SPORT_COUNT_DOWN, SoundOrderConst.SUCCESS,
        SoundOrderConst.SWITCH, SoundOrderConst.PRESS, SoundOrderConst.SPORT_VIOLATION, SoundOrderConst.SPORT_COUNT_DOWN_GUN,
        SoundOrderConst.SPORT_COUNT_DOWN_BALL, SoundOrderConst.SPORT_COUNT_DOWN_GUN_PREPARE, SoundOrderConst.SPORT_COUNT_DOWN_GUN_HOLD,
        SoundOrderConst.SPORT_BREAST_TAPE, SoundOrderConst.SPORT_GRAB_RUN, SoundOrderConst.SPORT_CONTACT, SoundOrderConst.SPORT_CONTACT_2})
@Retention(RetentionPolicy.SOURCE)
public @interface SoundOrderConst {
    //  运动开始
    int SPORT_START = 1;

    //  运动计数
    int SPORT_COUNT = 2;

    //  运动结束
    int SPORT_END = 3;

    //  运动错误警告
    int SPORT_WARN = 4;

    //  运动倒计时
    int SPORT_COUNT_DOWN = 5;

    //  成功
    int SUCCESS = 6;

    //  开关
    int SWITCH = 7;

    //  按键
    int PRESS = 8;

    //  运动违规
    int SPORT_VIOLATION = 9;

    //  运动倒计时（枪声）
    int SPORT_COUNT_DOWN_GUN = 10;

    //  运动倒计时（各就位+滴） 3000 ms
    int SPORT_COUNT_DOWN_BALL = 11;

    //  运动倒计时（各就位+预备+枪声） 5200 ms
    int SPORT_COUNT_DOWN_GUN_PREPARE = 12;

    //  运动倒计时（各就位+枪声） 4500 ms
    int SPORT_COUNT_DOWN_GUN_HOLD = 13;

    //  冲线
    int SPORT_BREAST_TAPE = 14;

    //  抢跑
    int SPORT_GRAB_RUN = 15;

    //  触点
    int SPORT_CONTACT = 16;

    //  触点2
    int SPORT_CONTACT_2 = 17;
}
