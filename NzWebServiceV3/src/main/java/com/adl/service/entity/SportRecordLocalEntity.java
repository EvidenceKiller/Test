package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.adl.service.common.IDefine;
import com.adl.service.common.InnerUtil;
import com.adl.service.db.converter.ListSportImageConverter;
import com.adl.service.db.converter.ListSportIndicatorsConverter;
import com.adl.service.db.converter.ListSportVideoConverter;
import com.adl.service.db.converter.RecordCompetitionConverter;
import com.adl.service.db.converter.RecordMeetingConverter;
import com.adl.service.db.converter.RecordPlanConverter;
import com.adl.service.db.converter.SportTrainPlanConverter;

import java.io.Serializable;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 运动记录
 */
@Entity(tableName = "_sport_record_local")
@TypeConverters(value = {ListSportImageConverter.class, ListSportVideoConverter.class,
        RecordCompetitionConverter.class, RecordMeetingConverter.class,
        RecordPlanConverter.class, ListSportIndicatorsConverter.class,
        SportTrainPlanConverter.class})
public class SportRecordLocalEntity extends SportRecordEntity implements Serializable {
    //  图片/视频状态:0（未上传）、1（已上传）
    @ColumnInfo(name = "_upload_file_status")
    private int uploadFileStatus;

    //  业务数据状态:0（未上传）、1（已上传）、2（上传失败，后续不再上传）
    @ColumnInfo(name = "_upload_data_status")
    private int uploadDataStatus;

    //  修改状态:0（未修改）、1（修改）
    @ColumnInfo(name = "_upload_modify_status", defaultValue = "0")
    private int uploadModifyStatus;

    //  上传错误信息
    @ColumnInfo(name = "_upload_error_msg", defaultValue = "")
    private String uploadErrorMsg;

    //  业务数据上传次数
    @ColumnInfo(name = "_upload_data_fail_num", defaultValue = "0")
    private int uploadDataFailNum;

    //  上传日期【yyyy-MM-dd】
    @ColumnInfo(name = "_upload_date", defaultValue = "")
    private String uploadDate;

    //  归属Id(机构id)
    @ColumnInfo(name = "_belong_id")
    private String belongId;

    //  场景Id（赛事/运动会/组织测试...）
    @ColumnInfo(name = "_scene_id")
    private String sceneId;

    //  场景名称（赛事/运动会/组织测试...）
    @ColumnInfo(name = "_scene_name")
    private String sceneName;

    //  具体某个赛事/运动会/组织测试...的id
    @ColumnInfo(name = "_scene_sub_id")
    private String sceneSubId;

    //  具体某个赛事/运动会/组织测试...的名称
    @ColumnInfo(name = "_scene_sub_name")
    private String sceneSubName;

    //  业务类型：PTE-体测 PTR-体锻 PET-体育教学 CPT-赛事
    @ColumnInfo(name = "_business_type")
    private String businessType;

    //  运动性质：1-锻炼 2-测试 3-趣味
    @ColumnInfo(name = "_sport_nature")
    private String sportNature;

    /// 可通过【setSportInfo】设置
    // 运动单项代码
    @ColumnInfo(name = "_sport_item_code")
    private String sportItemCode;
    // 运动sku名称
    @ColumnInfo(name = "_sport_sku_name")
    private String sportSkuName;
    //  运动单位名称
    @ColumnInfo(name = "_sport_unit_name")
    private String sportUnitName;

    /// 可通过【setStudentInfo】设置
    // 姓名
    @ColumnInfo(name = "_account_name")
    private String accountName;

    // 院系id
    @ColumnInfo(name = "_college_id")
    private String collegeId;

    // 院系名称
    @ColumnInfo(name = "_college_name")
    private String collegeName;

    // 学系id
    @ColumnInfo(name = "_faculty_id")
    private String facultyId;

    // 学系名称
    @ColumnInfo(name = "_faculty_name")
    private String facultyName;

    // 专业id
    @ColumnInfo(name = "_major_id")
    private String majorId;

    // 专业名称
    @ColumnInfo(name = "_major_name")
    private String majorName;

    // 学段类型 小学1 初中2 高中3 大学4
    @ColumnInfo(name = "_stage_type")
    private String stageType;

    // 年级id
    @ColumnInfo(name = "_grade_id")
    private String gradeId;

    // 年级名称
    @ColumnInfo(name = "_grade_name")
    private String gradeName;

    // 年级编号
    @ColumnInfo(name = "_grade_num")
    private String gradeNum;

    //  班级id
    @ColumnInfo(name = "_class_id")
    private String classId;

    //  班级名称
    @ColumnInfo(name = "_class_name")
    private String className;

    //  班级编号
    @ColumnInfo(name = "_class_num")
    private String classNum;

    // 用户人脸照片
    @ColumnInfo(name = "_user_face_img_url")
    private String userFaceImgUrl;

    @ColumnInfo(name = "_student_code")
    private String studentCode;
    /// /    var studentNamePY: String? = "",//姓名拼音
    /// /    var studentNamePYShort: String? = "",//姓名拼音简体
    @ColumnInfo(name = "_student_name_py")
    private String studentNamePY;

    // 学号
    @ColumnInfo(name = "_student_name_py_short")
    private String studentNamePYShort;

    // 1男2女9未知
    @ColumnInfo(name = "_user_sex")
    private String userSex;

    // 预留字段
    @ColumnInfo(name = "_extra")
    private String extra;

    @ColumnInfo(name = "_sport_groupId")
    private String sportGroupId;
    @ColumnInfo(name = "_sport_groupName")
    private String sportGroupName;
    @ColumnInfo(name = "_status", defaultValue = "0")
    private int status;// 0,未添加 (ui状态: 1，添加中 2，已添加 正常) 4，已取消 5,违规 可取消, 6.超时结束. 10.运动结束
    @ColumnInfo(name = "_sex")
    private String sex;//性别, 1男2女9未知
    @ColumnInfo(name = "_orgId")
    private String orgId;//
    @ColumnInfo(name = "_orgName")
    private String orgName;//
    @ColumnInfo(name = "_sportItemName")
    private String sportItemName;//
    @ColumnInfo(name = "_planUid")
    private String planUid;//
    @ColumnInfo(name = "_projectUid")
    private String projectUid;//
    @ColumnInfo(name = "_uploadStatus", defaultValue = "0")
    private int uploadStatus;//uploadStatus: Int = 0,//数据上传状态 0--未上传  1--已上传  2--上传失败, 3--没有匹配的学生, 4--未完成比赛,6--更新数据, 7--数据更新中(阳光跑) 10--服务器入库失败, 20--
    @ColumnInfo(name = "_sportSort", defaultValue = "0")
    private int sportSort;

    @ColumnInfo(name = "_spendTime", defaultValue = "")
    private String spendTime;

    @ColumnInfo(name = "_shouldLines", defaultValue = "0")
    private int shouldLines;

    @ColumnInfo(name = "_testGroupId", defaultValue = "")
    private String testGroupId;

    @ColumnInfo(name = "_round", defaultValue = "0")
    private int round;

    @ColumnInfo(name = "_startRound", defaultValue = "0")
    private int startRound;

    @ColumnInfo(name = "_groupNumber", defaultValue = "")
    private String groupNumber;


    /// /每次撞线时间，”,“拼接, 对于阳光跑, 每次记录总时长

    public String getStudentNamePY() {
        return studentNamePY;
    }

    public void setStudentNamePY(String studentNamePY) {
        this.studentNamePY = studentNamePY;
    }

    public String getStudentNamePYShort() {
        return studentNamePYShort;
    }

    public void setStudentNamePYShort(String studentNamePYShort) {
        this.studentNamePYShort = studentNamePYShort;
    }

    public int getSportSort() {
        return sportSort;
    }

    public void setSportSort(int sportSort) {
        this.sportSort = sportSort;
    }

    public int getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(int uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(String spendTime) {
        this.spendTime = spendTime;
    }

    public int getShouldLines() {
        return shouldLines;
    }

    public void setShouldLines(int shouldLines) {
        this.shouldLines = shouldLines;
    }

    public String getTestGroupId() {
        return testGroupId;
    }

    public void setTestGroupId(String testGroupId) {
        this.testGroupId = testGroupId;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getStartRound() {
        return startRound;
    }

    public void setStartRound(int startRound) {
        this.startRound = startRound;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getPlanUid() {
        return planUid;
    }

    public void setPlanUid(String planUid) {
        this.planUid = planUid;
    }

    public String getProjectUid() {
        return projectUid;
    }

    public void setProjectUid(String projectUid) {
        this.projectUid = projectUid;
    }

    public String getSportItemName() {
        return sportItemName;
    }

    public void setSportItemName(String sportItemName) {
        this.sportItemName = sportItemName;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


//    public SportRecordLocalEntity(@NonNull String id) {
//        super(id);
//    }

    public String getSportGroupId() {
        return sportGroupId;
    }

    public void setSportGroupId(String sportGroupId) {
        this.sportGroupId = sportGroupId;
    }

    public String getSportGroupName() {
        return sportGroupName;
    }

    public void setSportGroupName(String sportGroupName) {
        this.sportGroupName = sportGroupName;
    }

    public int getUploadFileStatus() {
        return uploadFileStatus;
    }

    public void setUploadFileStatus(int uploadFileStatus) {
        this.uploadFileStatus = uploadFileStatus;
    }

    public int getUploadDataStatus() {
        return uploadDataStatus;
    }

    public void setUploadDataStatus(int uploadDataStatus) {
        this.uploadDataStatus = uploadDataStatus;
    }

    public int getUploadModifyStatus() {
        return uploadModifyStatus;
    }

    public void setUploadModifyStatus(int uploadModifyStatus) {
        this.uploadModifyStatus = uploadModifyStatus;
    }

    public String getUploadErrorMsg() {
        return uploadErrorMsg;
    }

    public void setUploadErrorMsg(String uploadErrorMsg) {
        this.uploadErrorMsg = uploadErrorMsg;
    }

    public String getBelongId() {
        return belongId;
    }

    public void setBelongId(String belongId) {
        this.belongId = belongId;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getSceneSubId() {
        return sceneSubId;
    }

    public void setSceneSubId(String sceneSubId) {
        this.sceneSubId = sceneSubId;
    }

    public String getSceneSubName() {
        return sceneSubName;
    }

    public void setSceneSubName(String sceneSubName) {
        this.sceneSubName = sceneSubName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getSportNature() {
        return sportNature;
    }

    public void setSportNature(String sportNature) {
        this.sportNature = sportNature;
    }

    public String getSportItemCode() {
        return sportItemCode;
    }

    public void setSportItemCode(String sportItemCode) {
        this.sportItemCode = sportItemCode;
    }

    public String getSportSkuName() {
        return sportSkuName;
    }

    public void setSportSkuName(String sportSkuName) {
        this.sportSkuName = sportSkuName;
    }

    public String getSportUnitName() {
        return sportUnitName;
    }

    public void setSportUnitName(String sportUnitName) {
        this.sportUnitName = sportUnitName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getStageType() {
        return stageType;
    }

    public void setStageType(String stageType) {
        this.stageType = stageType;
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

    public String getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(String gradeNum) {
        this.gradeNum = gradeNum;
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

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getUserFaceImgUrl() {
        return userFaceImgUrl;
    }

    public void setUserFaceImgUrl(String userFaceImgUrl) {
        this.userFaceImgUrl = userFaceImgUrl;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public int getUploadDataFailNum() {
        return uploadDataFailNum;
    }

    public void setUploadDataFailNum(int uploadDataFailNum) {
        this.uploadDataFailNum = uploadDataFailNum;
    }

    public void addUploadDataFailNum() {
        this.uploadDataFailNum += 1;
    }

    public void restUploadDataFailNum() {
        this.uploadDataFailNum = 0;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public void setTodayUploadDate() {
        this.uploadDate = InnerUtil.formatYMDNow();
    }

    public boolean isTodayUploadDate() {
        return InnerUtil.formatYMDNow().equals(this.uploadDate);
    }

    //  设置项目信息
    public void setSportInfo(SceneSportEntity sceneSport, String sportUnitName) {
        this.setSportItemCode(sceneSport.getSportItemCode());
        this.setSportSkuId(sceneSport.getSportSkuId());
        this.setSportSkuName(sceneSport.getSportSkuName());
        this.setSportUnit(sceneSport.getMeasureUnitCode());
        this.setSportUnitName(sportUnitName);
    }

    //  设置学生信息
    public void setStudentInfo(StudentEntity student, String accountType) {
        //  用户类型 0：游客 1：普通用户
        this.setAccountType(accountType);
        this.setAccountId(student.getAccountId());
        this.setAccountName(student.getAccountName());
        this.setStudentNamePY(student.getAccountNamePinYin());
        this.setStudentNamePYShort(student.getAccountNameFirstLetterPinYin());
        this.setCollegeName(student.getCollegeName());
        this.setFacultyId(student.getFacultyId());
        this.setFacultyName(student.getFacultyName());
        this.setMajorId(student.getMajorId());
        this.setMajorName(student.getMajorName());
        this.setStageType(student.getStageType());
        this.setGradeId(student.getGradeId());
        this.setGradeName(student.getGradeName());
        this.setGradeNum(student.getGradeNum());
        this.setClassId(student.getClassId());
        this.setClassName(student.getClassName());
        this.setClassNum(student.getClassNum());
        this.setUserFaceImgUrl(student.getUserFaceImgUrl());
        this.setStudentCode(student.getStudentCode());
        this.setUserSex(student.getUserSex());
    }

    //  设置组织测试信息[确保记录已经设置了 是否违规、运动违规次数]
    public void setPlanInfo(SportPlanEntity plan, int ptTestType, String batch) {
        setPlanInfo(plan, ptTestType, batch, getSportViolationStatus(), getSportViolationNumber());
    }

    public void setPlanInfo(SportPlanEntity plan, int ptTestType, String batch, int sportViolationStatus, String sportViolationNumber) {
        RecordPlan recordPlan = new RecordPlan();
        recordPlan.setPtPlanId(plan.getPlanId());
        recordPlan.setPtBatch(batch);
        recordPlan.setPtStandardId(plan.getStandardId());
//        recordPlan.setPtSportStandardScore(score);
        recordPlan.setSportViolationStatus(sportViolationStatus);
        recordPlan.setSportViolationNumber(sportViolationNumber);
        recordPlan.setPtTestType(ptTestType);
        recordPlan.setPtPlanName(plan.getPlanName());
//        recordPlan.setPtFlag(ptFlag);
        this.setSportPlan(recordPlan);
    }

    //  设置组织测试信息[确保记录已经设置了 sceneSubId]
    public void setMeetInfo(MeetGroupDetailsEntity details, String meetSportSkuId, String meetRound, String teamUnitCode, String meetTeamNo) {
        RecordMeeting recordMeeting = new RecordMeeting();
        recordMeeting.setMeetGroupType("2");
        recordMeeting.setMeetId(sceneSubId);
        recordMeeting.setMeetRound(meetRound);
        recordMeeting.setMeetSportSkuId(meetSportSkuId);
        recordMeeting.setTeamUnitCode(teamUnitCode);
        recordMeeting.setMeetTeamNo(meetTeamNo);
        recordMeeting.setSerialNumber(details.getSerialCode());
        recordMeeting.setMeetNumberCode(details.getNumberCode());
        recordMeeting.setMeetRunwayCode(details.getRunwayCode());
        this.setSportMeet(recordMeeting);
    }

    public void update(SportRecordModifyEntity entity) {
        this.setSportResult(entity.getSportResult());
        this.setSportScore(entity.getSportScore());
        this.setSportLevel(entity.getSportLevel());
        this.setUploadDataStatus(entity.getUploadDataStatus());
        this.setUploadModifyStatus(entity.getUploadModifyStatus());
        this.setMultiPersonId(entity.getMultiPersonId());
        this.setMultiPersonRank(entity.getMultiPersonRank());
    }

    public boolean isUploadSuccess() {
        return uploadFileStatus == IDefine.RecordUploadFileDone &&
                uploadDataStatus == IDefine.RecordUploadDataDone &&
                uploadModifyStatus == IDefine.RecordUploadModifyNot;
    }
}