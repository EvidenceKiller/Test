package com.adl.service.entity;

import java.io.Serializable;
import java.util.List;

public class TrainEntity implements Serializable {

    private List<TrainInfoEntity> records;
    private int total;
    private int size;
    private int current;
    private int pages;

    public List<TrainInfoEntity> getRecords() {
        return records;
    }

    public void setRecords(List<TrainInfoEntity> records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * "orgId": "1861325708924096512",
     * "createTime": 1740808486000,
     * "orgName": "京东东小初",
     * "endTime": 1742140799000,
     * "startTime": 1740758400000,
     * "title": "特训测试",
     * "uid": "fb59293d4c0d44eaa6b2f57ec481e5ff",
     * "timeStatus": "2",
     * "trainPlanTimeList": [
     * 1740758400000,
     * 1740844800000,
     * 1740931200000,
     * 1741190400000
     * ],
     * "trainPlanDays": 16,
     * "accountNumber": 1,
     * "trainPlanDesc": "哈哈哈哈",
     * "checkData": false
     */
    public static class TrainInfoEntity implements Serializable{
        private String orgId;
        private long createTime;
        private String orgName;
        private long endTime;
        private long startTime;
        private String title;
        private String uid;
        private String timeStatus;
        private List<Long> trainPlanTimeList;
        private int trainPlanDays;
        private int accountNumber;
        private String trainPlanDesc;
        private boolean checkData;

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTimeStatus() {
            return timeStatus;
        }

        public void setTimeStatus(String timeStatus) {
            this.timeStatus = timeStatus;
        }

        public List<Long> getTrainPlanTimeList() {
            return trainPlanTimeList;
        }

        public void setTrainPlanTimeList(List<Long> trainPlanTimeList) {
            this.trainPlanTimeList = trainPlanTimeList;
        }

        public int getTrainPlanDays() {
            return trainPlanDays;
        }

        public void setTrainPlanDays(int trainPlanDays) {
            this.trainPlanDays = trainPlanDays;
        }

        public int getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(int accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getTrainPlanDesc() {
            return trainPlanDesc;
        }

        public void setTrainPlanDesc(String trainPlanDesc) {
            this.trainPlanDesc = trainPlanDesc;
        }

        public boolean isCheckData() {
            return checkData;
        }

        public void setCheckData(boolean checkData) {
            this.checkData = checkData;
        }
    }
}
