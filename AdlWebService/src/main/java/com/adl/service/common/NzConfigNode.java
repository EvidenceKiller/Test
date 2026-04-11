package com.adl.service.common;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2026/2/2
 * Describe   : 类描述
 */
public class NzConfigNode {

    private NzConfigAuth auth;
    private NzConfigBusiness business;

    public NzConfigNode() {

    }

    public NzConfigAuth getAuth() {
        return auth;
    }

    public void setAuth(NzConfigAuth auth) {
        this.auth = auth;
    }

    public NzConfigBusiness getBusiness() {
        return business;
    }

    public void setBusiness(NzConfigBusiness business) {
        this.business = business;
    }
}
