package com.adl.service.entity;

public class MorePeopleRecordRankInfoEntity {

    //  账号id
    private String accountId;
    //  账号名
    private String accountName;
    //  头像
    private String accountFacePath;
    //  成绩
    private String achievement;
    //  个数
    private String count;
    //  时间
    private String time;
    //  多人排行
    private String multiPersonRank;

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

    public String getAccountFacePath() {
        return accountFacePath;
    }

    public void setAccountFacePath(String accountFacePath) {
        this.accountFacePath = accountFacePath;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMultiPersonRank() {
        return multiPersonRank;
    }

    public void setMultiPersonRank(String multiPersonRank) {
        this.multiPersonRank = multiPersonRank;
    }
}
