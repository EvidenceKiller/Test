package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class RecordDetailPageRequest extends BasePageRequest {

    /** 机构id */
    @SerializedName("orgId")
    private String orgId;

    /** 学院ID */
    @SerializedName("collegeId")
    private String collegeId;

    /** 学系Id */
    @SerializedName("facultyId")
    private String facultyId;

    /** 专业ID */
    @SerializedName("majorId")
    private String majorId;

    /** 学级ID */
    @SerializedName("gradeId")
    private String gradeId;

    /** 班级ID */
    @SerializedName("classId")
    private String classId;

    /** 性别 */
    @SerializedName("sex")
    private String sex;

    /** 项目ID */
    @SerializedName("sportSkuId")
    private String sportSkuId;

    /** 端 */
    @SerializedName("appCode")
    private String appCode;

    /** 关键字查询 */
    @SerializedName("keyword")
    private String keyword;

    /** 计划查询 */
    @SerializedName("planId")
    private String planId;

    /** 记录id */
    @SerializedName("recordId")
    private String recordId;

    /** 账号id列表 */
    @SerializedName("accountIds")
    private List<String> accountIds;

    /** 导出Excel标志 */
    @SerializedName("exportExcelFlag")
    private Boolean exportExcelFlag;

    /** 转换单位标志，默认为true */
    @SerializedName("convertUnitFlag")
    private Boolean convertUnitFlag;

    /** 无效标志 */
    @SerializedName("invalidFlag")
    private Integer invalidFlag;

    /** 是否违规不能为空 1:违规 */
    @SerializedName("sportViolationStatus")
    private Integer sportViolationStatus;

    /** 项目高低优 参考字典编码：adl_bms_sport_priority（1 高优 2 低优 9 其它） */
    @SerializedName("sportPriority")
    private Integer sportPriority;

    /** 是否查询报告 */
    @SerializedName("reportFlag")
    private Boolean reportFlag;

    /** 开始时间排序 */
    @SerializedName("startTimeOrder")
    private String startTimeOrder;

    /** 运动开始时间 */
    @SerializedName("sportStartTime")
    private String sportStartTime;

    /** 运动结束时间 */
    @SerializedName("sportEndTime")
    private String sportEndTime;

    /** 不显示视频URL标志，默认为false */
    @SerializedName("dontShowVideoUrl")
    private Boolean dontShowVideoUrl;

    private RecordDetailPageRequest(Builder builder) {
        super(builder);
        this.orgId = builder.orgId;
        this.collegeId = builder.collegeId;
        this.facultyId = builder.facultyId;
        this.majorId = builder.majorId;
        this.gradeId = builder.gradeId;
        this.classId = builder.classId;
        this.sex = builder.sex;
        this.sportSkuId = builder.sportSkuId;
        this.appCode = builder.appCode;
        this.keyword = builder.keyword;
        this.planId = builder.planId;
        this.recordId = builder.recordId;
        this.accountIds = builder.accountIds;
        this.exportExcelFlag = builder.exportExcelFlag;
        this.convertUnitFlag = builder.convertUnitFlag;
        this.invalidFlag = builder.invalidFlag;
        this.sportViolationStatus = builder.sportViolationStatus;
        this.sportPriority = builder.sportPriority;
        this.reportFlag = builder.reportFlag;
        this.startTimeOrder = builder.startTimeOrder;
        this.sportStartTime = builder.sportStartTime;
        this.sportEndTime = builder.sportEndTime;
        this.dontShowVideoUrl = builder.dontShowVideoUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String orgId;
        private String collegeId;
        private String facultyId;
        private String majorId;
        private String gradeId;
        private String classId;
        private String sex;
        private String sportSkuId;
        private String appCode;
        private String keyword;
        private String planId;
        private String recordId;
        private List<String> accountIds;
        private Boolean exportExcelFlag;
        private Boolean convertUnitFlag;
        private Integer invalidFlag;
        private Integer sportViolationStatus;
        private Integer sportPriority;
        private Boolean reportFlag;
        private String startTimeOrder;
        private String sportStartTime;
        private String sportEndTime;
        private Boolean dontShowVideoUrl;
        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }
        public Builder collegeId(String collegeId) {
            this.collegeId = collegeId;
            return this;
        }
        public Builder facultyId(String facultyId) {
            this.facultyId = facultyId;
            return this;
        }
        public Builder majorId(String majorId) {
            this.majorId = majorId;
            return this;
        }
        public Builder gradeId(String gradeId) {
            this.gradeId = gradeId;
            return this;
        }
        public Builder classId(String classId) {
            this.classId = classId;
            return this;
        }
        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }
        public Builder sportSkuId(String sportSkuId) {
            this.sportSkuId = sportSkuId;
            return this;
        }
        public Builder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }
        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }
        public Builder planId(String planId) {
            this.planId = planId;
            return this;
        }
        public Builder recordId(String recordId) {
            this.recordId = recordId;
            return this;
        }
        public Builder accountIds(List<String> accountIds) {
            this.accountIds = accountIds;
            return this;
        }
        public Builder exportExcelFlag(Boolean exportExcelFlag) {
            this.exportExcelFlag = exportExcelFlag;
            return this;
        }
        public Builder convertUnitFlag(Boolean convertUnitFlag) {
            this.convertUnitFlag = convertUnitFlag;
            return this;
        }
        public Builder invalidFlag(Integer invalidFlag) {
            this.invalidFlag = invalidFlag;
            return this;
        }
        public Builder sportViolationStatus(Integer sportViolationStatus) {
            this.sportViolationStatus = sportViolationStatus;
            return this;
        }
        public Builder sportPriority(Integer sportPriority) {
            this.sportPriority = sportPriority;
            return this;
        }
        public Builder reportFlag(Boolean reportFlag) {
            this.reportFlag = reportFlag;
            return this;
        }
        public Builder startTimeOrder(String startTimeOrder) {
            this.startTimeOrder = startTimeOrder;
            return this;
        }
        public Builder sportStartTime(String sportStartTime) {
            this.sportStartTime = sportStartTime;
            return this;
        }
        public Builder sportEndTime(String sportEndTime) {
            this.sportEndTime = sportEndTime;
            return this;
        }
        public Builder dontShowVideoUrl(Boolean dontShowVideoUrl) {
            this.dontShowVideoUrl = dontShowVideoUrl;
            return this;
        }

        public RecordDetailPageRequest build() { return new RecordDetailPageRequest(this); }
    }
    public String getOrgId() { return orgId; }
    public String getCollegeId() { return collegeId; }
    public String getFacultyId() { return facultyId; }
    public String getMajorId() { return majorId; }
    public String getGradeId() { return gradeId; }
    public String getClassId() { return classId; }
    public String getSex() { return sex; }
    public String getSportSkuId() { return sportSkuId; }
    public String getAppCode() { return appCode; }
    public String getKeyword() { return keyword; }
    public String getPlanId() { return planId; }
    public String getRecordId() { return recordId; }
    public List<String> getAccountIds() { return accountIds; }
    public Boolean getExportExcelFlag() { return exportExcelFlag; }
    public Boolean getConvertUnitFlag() { return convertUnitFlag; }
    public Integer getInvalidFlag() { return invalidFlag; }
    public Integer getSportViolationStatus() { return sportViolationStatus; }
    public Integer getSportPriority() { return sportPriority; }
    public Boolean getReportFlag() { return reportFlag; }
    public String getStartTimeOrder() { return startTimeOrder; }
    public String getSportStartTime() { return sportStartTime; }
    public String getSportEndTime() { return sportEndTime; }
    public Boolean getDontShowVideoUrl() { return dontShowVideoUrl; }
}