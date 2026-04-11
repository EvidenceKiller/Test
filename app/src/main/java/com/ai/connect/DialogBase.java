package com.ai.connect;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.adl.ts.general.R;

public class DialogBase extends Dialog {

    public DialogBase(Context context) {
        super(context, R.style.NoTitleDialog);
    }

    protected final int autoWidth(float ratio) {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (dm.widthPixels * ratio);
        window.setAttributes(lp);
        return lp.width;
    }

    protected final int autoWidth(int value) {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = value;
        window.setAttributes(lp);
        return lp.width;
    }

    protected final int autoHeight(double ratio) {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.height = (int) (dm.heightPixels * ratio);
        window.setAttributes(lp);
        return lp.height;
    }

    protected void showText(String txt) {
        //UIHandler.showText(txt);
    }
}
