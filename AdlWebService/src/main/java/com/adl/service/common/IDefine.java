package com.adl.service.common;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 常量定义
 */
public interface IDefine {

    String DefaultHost = "https://www.nezhasport.com/";
    String FacePrefix = "https://f.nezhasport.com/";

    //
    String SpConfigService = "sp_config_service";
    //  token
    String SpToken = "sp_token";

    //  普教
    String PlatformK12 = "k12";
    //  高教
    String PlatformGx = "gx";

    // 七牛公共资源目录
    String QiNiuPublicBucket = "adl-ssp";
    String QiNiuRemovePathSmartScreen = "/bigScreen/";
    String QiNiuRemovePathPad = "/pad/";
    String QiNiuRemovePathExam = "/exam/";
    String QiNiuRemovePathAthletics = "/athletics/";

    // 时间单位
    long Minute = 60 * 1000;
    long UpdateIntervalTime = 2 * Minute;

    // 公共目录
    String DefSportRoot = "zy.sport.cache";
    String DirLog = "log";
    String DirVideo = "localVideo";
    String DirImage = "localImage";
    String DirFile = "localFile";

    // 七牛
    String LastQiNiuToken = "_last_qi_niu_token";
    String LastQiNiuTime = "_last_qi_niu_time";

    // 登录组织id
    String LoginOrgId = "_login_org_id";
    String LoginOrgInfo = "_login_org_info";

    // 上次更新时间
    String LastStudentUpdateTime = "_last_student_update_time";
    String LastTeacherUpdateTime = "_last_teacher_update_time";
    String LastCitizenUpdateTime = "_last_citizen_update_time";

    // 文件下载类型
    int FileDownloadTypeVideo = 0; // 视频
    int FileDownloadTypeImage = 1; // 图片
    int FileDownloadTypeFile = 2; // 文件
    int FileDownloadTypeStudentHead = 3; // 学生头像
    int FileDownloadTypeTeacherHead = 4; // 教师头像

    // 文件上传状态
    int FileUploadStatusNew = 0; // 新文件
    int FileUploadStatusDoing = 1; // 上传中
    int FileUploadStatusDone = 2; // 上传完成
    int FileUploadStatusError = 10; // 上传错误

    // 基础信息同步状态
    int SyncOrgStatus = 1;  // 同步机构
    int SyncStudentStatus = 2; // 同步学生
    int SyncTeacherStatus = 3; // 同步老师
    int SyncCitizenStatus = 4; // 同步老师
    int SyncCompleteStatus = 99; // 同步完成

    //  测试类型
    String standardTypeALL = "";//
    String standardTypeTC = "TC";// 体测：TC
    String standardTypeZK = "ZK";// 中考：ZK

    // 运动计划状态 0全部 1未开始 2进行中 3已结束
    int SportPlanStatusAll = 0; // 全部
    int SportPlanStatusNew = 1; // 未开始
    int SportPlanStatusDoing = 2; // 进行中
    int SportPlanStatusDone = 3; // 已完成

    // 运动记录图片/视频上传状态
    int RecordUploadFileNew = 0; // 未上传
    int RecordUploadFileDone = 1; // 已上传

    // 运动记录业务数据上传状态
    int RecordUploadDataNew = 0; // 未上传
    int RecordUploadDataDone = 1; // 已上传
    int RecordUploadDataFail = 2; // 上传失败，后续不再上传
    //int RecordUploadDataModify = 3; // 修改后未上传

    int RecordUploadModifyNot = 0; // 未修改
    int RecordUploadModifyExist = 1; // 修改

    //  运动记录上传状态
    int RecordUploadStatusNoNetwork = 0; // 无网络
    int RecordUploadStatusDoing = 1; // 上传中
    int RecordUploadStatusDone = 2; // 上传完毕

    //  用户类型 0：游客 1：普通用户
    String AccountTypeVisitor = "0";
    String AccountTypeNormal = "1";

    //  业务类型代码
    String BusinessTypePTE = "PTE";// PTE-体测
    String BusinessTypePTR = "PTR";// PTR-体锻
    String BusinessTypePET = "PET";// PET-体育教学
    String BusinessTypeCPT = "CPT";// CPT-赛事

    //  运动性质代码
    String SportNatureExercise = "1";// 锻炼
    String SportNatureTest = "2";// 测试
    String SportNatureInterest = "3";// 趣味

    //  字典
    String DirctSportUnit = "adl_sport_sport_unit";// 单位

}
