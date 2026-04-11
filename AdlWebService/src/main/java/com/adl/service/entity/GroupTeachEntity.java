package com.adl.service.entity;

import java.util.List;

/**
 * {
 * "code": 200,
 * "msg": "操作成功",
 * "data": [
 * {
 * <p>
 * "list": [
 * {
 * "indicatorsCode": "03010001",
 * "indicatorsName": "跳远距离",
 * "indicatorsRemark": null,
 * "indicatorsUnit": "mm",
 * "indicatorsValue": "1146",
 * "indicatorsImages": null,
 * "indicatorsVideos": null,
 * "reportCrosswiseUrl": null,
 * "reportVerticalUrl": null
 * },
 * {
 * "indicatorsCode": "03010006",
 * "indicatorsName": "上摆幅度",
 * "indicatorsRemark": {
 * "image": {
 * "imageDesc": "前摆帧",
 * "imageUrl": "https://f.nezhasport.com//PTE/sport/indicator/images/1,2/0301/9cab286c-9406-4619-a756-d7f296128e3f_1741837084349.png"
 * },
 * "sportStatus": "未达标",
 * "level": "2",
 * "title": "您的上摆幅度不足",
 * "remark": "上摆幅度不足可能意味着您在预蹲前未能充分激活身体的核心肌群和下肢肌肉。这将影响您的起跳动力链的完整性，从而减少起跳时的爆发力。您需要通过增加上摆幅度来加强肌肉的预激活，确保在后续环节中释放肌肉的爆发力。此外，加强肩部和背部肌肉的柔韧性训练，也有助于提高上摆的幅度和质量。"
 * },
 * "indicatorsUnit": "°",
 * "indicatorsValue": "90.22",
 * "indicatorsImages": [
 * {
 * "imageDesc": "前摆帧",
 * "imageUrl": "https://f.nezhasport.com//PTE/sport/indicator/images/1,2/0301/9cab286c-9406-4619-a756-d7f296128e3f_1741837084349.png"
 * }
 * ],
 * "indicatorsVideos": null,
 * "reportCrosswiseUrl": null,
 * "reportVerticalUrl": null
 * },
 * {
 * "indicatorsCode": "03010007",
 * "indicatorsName": "预蹲后摆",
 * "indicatorsRemark": {
 * "image": {
 * "imageDesc": "预摆帧",
 * "imageUrl": "https://f.nezhasport.com//PTE/sport/indicator/images/1,2/0301/9cab286c-9406-4619-a756-d7f296128e3f_1741837084836.png"
 * },
 * "sportStatus": "未达标",
 * "level": "2",
 * "title": "您的预蹲后摆幅度不足",
 * "remark": "预蹲时上肢后摆幅度不足，将限制起跳时上肢的发力空间，这会减弱上肢在起跳过程中带动身体向上的助力。您需要在预蹲阶段加大上肢后摆的幅度，确保有足够的活动范围来优化上肢的前摆发力。此外，加强肩关节的柔韧性和上肢力量，将有助于实现更加充分和有力的起跳动作。"
 * },
 * "indicatorsUnit": "°",
 * "indicatorsValue": "14.37",
 * "indicatorsImages": [
 * {
 * "imageDesc": "预摆帧",
 * "imageUrl": "https://f.nezhasport.com//PTE/sport/indicator/images/1,2/0301/9cab286c-9406-4619-a756-d7f296128e3f_1741837084836.png"
 * }
 * ],
 * "indicatorsVideos": null,
 * "reportCrosswiseUrl": null,
 * "reportVerticalUrl": null
 * },
 * ]
 * }
 * ],
 * "succeed": true
 * }
 * <p>
 * "accountId": "1894231293273116673",
 * *             "accountName": "小海",
 * *             "faceImgUrl": "V2/busi/userFaceImgUrl/2025-02-25/1740454904211_53bb28cc.jpg",
 * *             "sportScore": 0.0,
 * *             "sportResult": "114.6 厘米",
 * *             "originalSportResult": "1146",
 * *             "lastSportResult": null,
 * *             "lastOriginalSportResult": null,
 * *             "recordId": "9cab286c-9406-4619-a756-d7f296128e3f",
 * *             "courseTimes": 1,
 * sportStartTime": 1741837074560,
 *             "sportEndTime": 1741837084209,
 *             "sportTestTime": 1741837074560,
 *             "sex": 1,
 */
public class GroupTeachEntity {
    private String accountId;
    private String accountName;
    private String faceImgUrl;
    private String sportScore;
    private String sportResult;
    private String originalSportResult;
    private String lastSportResult;
    private String lastOriginalSportResult;
    private String recordId;
    private int courseTimes;
    private long sportStartTime;
    private long sportEndTime;
    private long sportTestTime;
    private String sportVideoUrls;
    private String sportUnitCode;
    /**
     * 横向报告
     */
    private String reportCrosswiseUrl;
    /**
     * 竖向报告
     */
    private String reportVerticalUrl;

    private int sex;
    private boolean isExpanded;
    private List<SportIndicatorsEntity<Object, Object>> list;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public String getSportUnitCode() {
        return sportUnitCode;
    }

    public void setSportUnitCode(String sportUnitCode) {
        this.sportUnitCode = sportUnitCode;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getSportVideoUrls() {
        return sportVideoUrls;
    }

    public void setSportVideoUrls(String sportVideoUrls) {
        this.sportVideoUrls = sportVideoUrls;
    }

    public String getReportCrosswiseUrl() {
        return reportCrosswiseUrl;
    }

    public void setReportCrosswiseUrl(String reportCrosswiseUrl) {
        this.reportCrosswiseUrl = reportCrosswiseUrl;
    }

    public String getReportVerticalUrl() {
        return reportVerticalUrl;
    }

    public void setReportVerticalUrl(String reportVerticalUrl) {
        this.reportVerticalUrl = reportVerticalUrl;
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

    public long getSportTestTime() {
        return sportTestTime;
    }

    public void setSportTestTime(long sportTestTime) {
        this.sportTestTime = sportTestTime;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getFaceImgUrl() {
        return faceImgUrl;
    }

    public void setFaceImgUrl(String faceImgUrl) {
        this.faceImgUrl = faceImgUrl;
    }

    public String getSportScore() {
        return sportScore;
    }

    public void setSportScore(String sportScore) {
        this.sportScore = sportScore;
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

    public String getLastSportResult() {
        return lastSportResult;
    }

    public void setLastSportResult(String lastSportResult) {
        this.lastSportResult = lastSportResult;
    }

    public String getLastOriginalSportResult() {
        return lastOriginalSportResult;
    }

    public void setLastOriginalSportResult(String lastOriginalSportResult) {
        this.lastOriginalSportResult = lastOriginalSportResult;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public int getCourseTimes() {
        return courseTimes;
    }

    public void setCourseTimes(int courseTimes) {
        this.courseTimes = courseTimes;
    }

    public List<SportIndicatorsEntity<Object, Object>> getList() {
        return list;
    }

    public void setList(List<SportIndicatorsEntity<Object, Object>> list) {
        this.list = list;
    }
}
