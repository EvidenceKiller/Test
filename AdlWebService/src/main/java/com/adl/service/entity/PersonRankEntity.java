package com.adl.service.entity;

import java.io.Serializable;

public class PersonRankEntity implements Serializable {
    /**
     *  rankNum
     * integer
     * 排名
     *  sportResult复制
     * string
     * 运动结果
     */
    private int rankNum;
    private String sportResult;
    private String originalSportResult;

    public int getRankNum() {
        return rankNum;
    }

    public void setRankNum(int rankNum) {
        this.rankNum = rankNum;
    }

    public String getSportResult() {
        return sportResult;
    }

    public void setSportResult(String sportResult) {
        this.sportResult = sportResult;
    }

    public String getOriginalSportResult() {
        return originalSportResult;
    }

    public void setOriginalSportResult(String originalSportResult) {
        this.originalSportResult = originalSportResult;
    }
}
