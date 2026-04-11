package com.ai.test;

import com.adl.base.AdlApp;
import com.adl.service.AdlService;

public class DemoApp extends AdlApp {

    @Override
    public void onCreate() {
        super.onCreate();
        loadXfSpeech();
        //ZySportContext.init(this);
        SoundUtil.getInstance().init(this);
        AdlService.create(this).setDebug(true);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        AdlService.getService().release();
    }
}
