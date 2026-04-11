package com.adl.service.entity;

import java.util.List;

public class ClassCompetitionEntity {

    private int total;
    private int size;
    private int current;
    private int pages;
    private List<Record> records;


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

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public static class Record{
        private String orgId;
        private String orgName;
        private String gradeId;
        private String gradeName;
        private String classId;
        private String className;
        private List<String> accountIds;
        private List<String> avatars;
        private String userName;
        private String sex;
        private String studentCode;
        private String appCode;
        private String appName;
        private String sportSkuId;
        private String sportSkuName;
        private String sportResultTotal;
        private String sportResultAvg;
        private long sportTime;
        private String multiPersonId;
        private int multiPersonRank;
        private List<String> multiPersonRanks;
        private int multiPersonIndex;
        private String ptSportStandardScore;
        private long sportStartTime;
        private long sportEndTime;
        private String sportUnitCode;
        private String sportUnitName;

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getGradeId() {
            return gradeId;
        }

        public void setGradeId(String gradeId) {
            this.gradeId = gradeId;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public List<String> getAccountIds() {
            return accountIds;
        }

        public void setAccountIds(List<String> accountIds) {
            this.accountIds = accountIds;
        }

        public List<String> getAvatars() {
            return avatars;
        }

        public void setAvatars(List<String> avatars) {
            this.avatars = avatars;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getStudentCode() {
            return studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }

        public String getAppCode() {
            return appCode;
        }

        public void setAppCode(String appCode) {
            this.appCode = appCode;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getSportSkuId() {
            return sportSkuId;
        }

        public void setSportSkuId(String sportSkuId) {
            this.sportSkuId = sportSkuId;
        }

        public String getSportSkuName() {
            return sportSkuName;
        }

        public void setSportSkuName(String sportSkuName) {
            this.sportSkuName = sportSkuName;
        }

        public String getSportResultTotal() {
            return sportResultTotal;
        }

        public void setSportResultTotal(String sportResultTotal) {
            this.sportResultTotal = sportResultTotal;
        }

        public String getSportResultAvg() {
            return sportResultAvg;
        }

        public void setSportResultAvg(String sportResultAvg) {
            this.sportResultAvg = sportResultAvg;
        }

        public long getSportTime() {
            return sportTime;
        }

        public void setSportTime(long sportTime) {
            this.sportTime = sportTime;
        }

        public String getMultiPersonId() {
            return multiPersonId;
        }

        public void setMultiPersonId(String multiPersonId) {
            this.multiPersonId = multiPersonId;
        }

        public int getMultiPersonRank() {
            return multiPersonRank;
        }

        public List<String> getMultiPersonRanks() {
            return multiPersonRanks;
        }

        public void setMultiPersonRanks(List<String> multiPersonRanks) {
            this.multiPersonRanks = multiPersonRanks;
        }

        public void setMultiPersonRank(int multiPersonRank) {
            this.multiPersonRank = multiPersonRank;
        }

        public int getMultiPersonIndex() {
            return multiPersonIndex;
        }

        public void setMultiPersonIndex(int multiPersonIndex) {
            this.multiPersonIndex = multiPersonIndex;
        }

        public String getPtSportStandardScore() {
            return ptSportStandardScore;
        }

        public void setPtSportStandardScore(String ptSportStandardScore) {
            this.ptSportStandardScore = ptSportStandardScore;
        }

        public long getSportStartTime() {
            return sportStartTime;
        }

        public void setSportStartTime(long sportStartTime) {
            this.sportStartTime = sportStartTime;
        }

        public long getSportEndTime() {
            return sportEndTime;
        }

        public void setSportEndTime(long sportEndTime) {
            this.sportEndTime = sportEndTime;
        }

        public String getSportUnitCode() {
            return sportUnitCode;
        }

        public void setSportUnitCode(String sportUnitCode) {
            this.sportUnitCode = sportUnitCode;
        }

        public String getSportUnitName() {
            return sportUnitName;
        }

        public void setSportUnitName(String sportUnitName) {
            this.sportUnitName = sportUnitName;
        }
    }
}
