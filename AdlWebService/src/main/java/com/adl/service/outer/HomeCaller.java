package com.adl.service.outer;

import com.adl.service.common.BaseService;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/4
 * Describe   : 首页服务
 * 1. 首页Banner
 * 2. 首页其他信息
 */
public class HomeCaller extends BaseService {

    private static HomeCaller _instance;

    private HomeCaller() {

    }

    public static HomeCaller instance() {
        if (_instance == null) {
            _instance = new HomeCaller();
        }
        return _instance;
    }

    // Banner
    public void queryBanner() {

    }

}
