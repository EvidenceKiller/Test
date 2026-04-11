package com.ai.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSeekBar;

import com.adl.ts.general.R;

public class SeekBarView extends LinearLayout {
    private Button mBtnAuto;
    private TextView mTxMin;
    private TextView mTxMax;
    private TextView mTxCur;
    private AppCompatSeekBar mSeekbar;
    public SeekBarView(Context context) {
        this(context, null);
    }

    public SeekBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SeekBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);

    }

    public SeekBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.seekbar_view, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mBtnAuto = findViewById(R.id.sb_btn_auto);
        mTxMin = findViewById(R.id.sb_tx_min);
        mTxMax = findViewById(R.id.sb_tx_max);
        mTxCur = findViewById(R.id.sb_tx_cur);
        mSeekbar = findViewById(R.id.sb_seek_bar);


    }

    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener listener){
        mSeekbar.setOnSeekBarChangeListener(listener);
    }

    /*显示内容*/
    public void setContent(String text, boolean show, int min, int max, int cur){

        if (show){
            mBtnAuto.setVisibility(VISIBLE);
        } else {
            mBtnAuto.setVisibility(INVISIBLE);
        }
        mBtnAuto.setText(text);
        if (max <= 0 || min < max){//数值非法
            return;
        }
        if (min == max){
            mTxMin.setText(String.valueOf(0));
            mTxMax.setText(String.valueOf(0));
            mTxCur.setText(String.valueOf(0));
            mSeekbar.setProgress(0);
            return;
        }
        mTxMin.setText(String.valueOf(min));
        mTxMax.setText(String.valueOf(max));
        mTxCur.setText(String.valueOf(cur));
//        mSeekbar.setProgress((int) ((cur * 1.0 / (max - min)) * 100));
        mSeekbar.setMax(max);
        if (cur < max) {
            mSeekbar.setProgress((int) (cur));
        } else {
            mSeekbar.setProgress((int) ((cur * 1.0 / (max - min)) * 100));
        }

    }

}
