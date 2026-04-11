package com.adl.service.entity;

import android.util.Pair;

import java.util.List;

public class TempUploadRecord {

    //  Pair<RemoteFileName,LocalPath>
    private List<Pair<String, String>> filePath;
    private SportRecordEntity sportUploadRecord;

    public TempUploadRecord(List<Pair<String, String>> filePath, SportRecordEntity sportUploadRecord) {
        this.filePath = filePath;
        this.sportUploadRecord = sportUploadRecord;
    }

    public List<Pair<String, String>> getFilePath() {
        return filePath;
    }

    public void setFilePath(List<Pair<String, String>> filePath) {
        this.filePath = filePath;
    }

    public SportRecordEntity getSportUploadRecord() {
        return sportUploadRecord;
    }

    public void setSportUploadRecord(SportRecordEntity sportUploadRecord) {
        this.sportUploadRecord = sportUploadRecord;
    }
}
