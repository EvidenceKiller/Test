package com.adl.service.entity;

import java.util.List;

public class CompetitionRankEntity {

    //  我的排名
    private SportRankEntity myRank;
    //  排行榜
    private List<SportRankEntity> rankList;

    public SportRankEntity getMyRank() {
        return myRank;
    }

    public void setMyRank(SportRankEntity myRank) {
        this.myRank = myRank;
    }

    public List<SportRankEntity> getRankList() {
        return rankList;
    }

    public void setRankList(List<SportRankEntity> rankList) {
        this.rankList = rankList;
    }
}



