package com.adl.service.entity;


import java.io.Serializable;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 机构
 * Path       : /busi/terminal/org/v1/login/info
 */
public class OrganizationEntity implements Serializable {

    // 上级机构
    private String pOrgName;

    // 上级id
    private String pid;

    // 机构名称
    private String orgName;

    // 机构代码
    private String orgCode;

    // 机构类型
    private int orgType;

    // 区域
    private String areaCodes;

    // 区域名
    private String areaNames;

    // 详细地址
    private String address;

    // 管理员名称
    private String accountName;

    // 管理员账号
    private String accountNum;

    // 机构LOGO
    private String orglogo;

    // 联系方式
    private String tel;

    // 邮箱
    private String email;

    // 机构id
    private String orgId;

    // 学制编码
    private String eduCode;

    // 已激活设备数量
    private int activeDeviceTotalNum;

    // 已激活设备数量
    private int activeDeviceNum;

    // 设备激活码
    private String orgActiveCode;

    private String deviceCommonPassword;

    public String getpOrgName() {
        return pOrgName;
    }

    public void setpOrgName(String pOrgName) {
        this.pOrgName = pOrgName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public int getOrgType() {
        return orgType;
    }

    public void setOrgType(int orgType) {
        this.orgType = orgType;
    }

    public String getAreaCodes() {
        return areaCodes;
    }

    public void setAreaCodes(String areaCodes) {
        this.areaCodes = areaCodes;
    }

    public String getAreaNames() {
        return areaNames;
    }

    public void setAreaNames(String areaNames) {
        this.areaNames = areaNames;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getOrglogo() {
        return orglogo;
    }

    public void setOrglogo(String orglogo) {
        this.orglogo = orglogo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getEduCode() {
        return eduCode;
    }

    public void setEduCode(String eduCode) {
        this.eduCode = eduCode;
    }

    public int getActiveDeviceTotalNum() {
        return activeDeviceTotalNum;
    }

    public void setActiveDeviceTotalNum(int activeDeviceTotalNum) {
        this.activeDeviceTotalNum = activeDeviceTotalNum;
    }

    public int getActiveDeviceNum() {
        return activeDeviceNum;
    }

    public void setActiveDeviceNum(int activeDeviceNum) {
        this.activeDeviceNum = activeDeviceNum;
    }

    public String getOrgActiveCode() {
        return orgActiveCode;
    }

    public void setOrgActiveCode(String orgActiveCode) {
        this.orgActiveCode = orgActiveCode;
    }

    public String getDeviceCommonPassword() {
        return deviceCommonPassword;
    }

    public void setDeviceCommonPassword(String deviceCommonPassword) {
        this.deviceCommonPassword = deviceCommonPassword;
    }
}
