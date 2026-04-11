package com.adl.service.entity;

public class SportRankKingEntity {

    //  账号Id
    private String accountId;
    //  账号名
    private String accountName;
    //  性别 1：男2 ：女
    private String sex;
    //  用户头像
    private String userAvatar;
    //  霸榜运动数
    private String sportRankCount;

    private int count;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getSportRankCount() {
        return sportRankCount;
    }

    public void setSportRankCount(String sportRankCount) {
        this.sportRankCount = sportRankCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
