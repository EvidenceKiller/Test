package com.adl.service.entity;

import java.io.Serializable;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 * Path       : /busi/terminal/account/login/info
 */
public class LoginInfoEntity implements Serializable {

    // 租户机构id
    private String tenantOrgId;

    // 租户机构编码
    private String tenantOrgCode;

    // 用户id
    private String accountId;

    // 机构id
    private String orgId;

    // 机构编码
    private String orgCode;

    // 机构名称
    private String orgName;

    // 机构类型
    private String orgType;

    // 用户账号
    private String accountNum;

    // 用户名
    private String accountName;

    // 用户手机号
    private String accountPhoneNum;

    // 用户头像
    private String accountAvatar;

    // 工号
    private String jobNum;

    // 卡号
    private String cardNum;

    public String getTenantOrgId() {
        return tenantOrgId;
    }

    public void setTenantOrgId(String tenantOrgId) {
        this.tenantOrgId = tenantOrgId;
    }

    public String getTenantOrgCode() {
        return tenantOrgCode;
    }

    public void setTenantOrgCode(String tenantOrgCode) {
        this.tenantOrgCode = tenantOrgCode;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPhoneNum() {
        return accountPhoneNum;
    }

    public void setAccountPhoneNum(String accountPhoneNum) {
        this.accountPhoneNum = accountPhoneNum;
    }

    public String getAccountAvatar() {
        return accountAvatar;
    }

    public void setAccountAvatar(String accountAvatar) {
        this.accountAvatar = accountAvatar;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
}
