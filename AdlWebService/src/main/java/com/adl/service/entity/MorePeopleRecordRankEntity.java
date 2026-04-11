package com.adl.service.entity;

import java.util.List;

public class MorePeopleRecordRankEntity {

    //  记录id
    private String uid;

    //  多人对战最新战况
    private List<MorePeopleRecordRankInfoEntity> morePeopleRecordRank;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<MorePeopleRecordRankInfoEntity> getMorePeopleRecordRank() {
        return morePeopleRecordRank;
    }

    public void setMorePeopleRecordRank(List<MorePeopleRecordRankInfoEntity> morePeopleRecordRank) {
        this.morePeopleRecordRank = morePeopleRecordRank;
    }
}