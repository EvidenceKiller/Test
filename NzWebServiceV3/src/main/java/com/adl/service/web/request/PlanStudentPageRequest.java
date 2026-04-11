package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

public final class PlanStudentPageRequest extends BasePageRequest {

    /** 机构ID */
    @SerializedName("orgId")
    private String orgId;

    /** 计划标识码 */
    @SerializedName("planId")
    private String planId;

    /** 班级ID */
    @SerializedName("classId")
    private String classId;

    /** 关键词 */
    @SerializedName("keyword")
    private String keyword;

    private PlanStudentPageRequest(Builder builder) {
        super(builder);
        this.orgId = builder.orgId;
        this.planId = builder.planId;
        this.classId = builder.classId;
        this.keyword = builder.keyword;
    }

    public static Builder builder(String planId) {
        return new Builder(planId);
    }

    public static class Builder extends BasePageRequest.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }
        private String orgId;
        private String planId;
        private String classId;
        private String keyword;
        Builder(String planId) {
            this.planId = planId;
        }
        public Builder orgId(String orgId) {
            this.orgId = orgId;
            return this;
        }
        public Builder classId(String classId) {
            this.classId = classId;
            return this;
        }
        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public PlanStudentPageRequest build() { return new PlanStudentPageRequest(this); }
    }
    public String getOrgId() { return orgId; }
    public String getPlanId() { return planId; }
    public String getClassId() { return classId; }
    public String getKeyword() { return keyword; }
}