package com.adl.service.db;

import com.adl.service.entity.PlanQueryInfo;
import com.adl.service.entity.SportRecordLocalEntity;
import com.adl.service.entity.SportRecordModifyEntity;
import com.adl.service.entity.SportRecordResult;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
@Dao
public interface RecordLocalDao {

    @Query("select * from _sport_record_local where _belong_id = :belongId and _app_code = :appCode")
    List<SportRecordLocalEntity> getAll(String belongId, String appCode);

    @Query("select * from _sport_record_local where _belong_id = :belongId and _app_code = :appCode and _scene_id = :sceneId and (:sceneSubId = '' or _scene_sub_id = :sceneSubId)")
    List<SportRecordLocalEntity> getAll(String belongId, String appCode, String sceneId, String sceneSubId);

    @Query("select count(*) from _sport_record_local where _belong_id = :belongId and _app_code = :appCode and _scene_id = :sceneId and (:sceneSubId = '' or _scene_sub_id = :sceneSubId)")
    int getAllCount(String belongId, String appCode, String sceneId, String sceneSubId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SportRecordLocalEntity> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SportRecordLocalEntity item);

    //  获取最新一条记录
    @Query("select * from _sport_record_local order by _sport_start_time desc limit 1 ")
    SportRecordLocalEntity queryNewest();

    @Query("select * from _sport_record_local where _belong_id = :belongId and _app_code = :appCode and _scene_id= :sceneId and (:sceneSubId = '' or _scene_sub_id = :sceneSubId) and _account_id= :accountId and _sport_sku_id= :sportId order by _sport_start_time desc")
    List<SportRecordLocalEntity> queryByAccountIdAndSportId(String belongId, String appCode, String sceneId, String sceneSubId, String accountId, String sportId);

    @Query("select * from _sport_record_local where _belong_id = :belongId and _app_code = :appCode and _scene_id= :sceneId and (:sceneSubId = '' or _scene_sub_id = :sceneSubId) and _account_id= :accountId order by _sport_start_time desc")
    List<SportRecordLocalEntity> queryByAccountId(String belongId, String appCode, String sceneId, String sceneSubId, String accountId);

    @Query("select * from _sport_record_local where _belong_id = :belongId and _app_code = :appCode and _scene_id= :sceneId and (:sceneSubId = '' or _scene_sub_id = :sceneSubId) and _multi_person_id= :multiPersonId ")
    List<SportRecordLocalEntity> queryByMultiPersonId(String belongId, String appCode, String sceneId, String sceneSubId, String multiPersonId);

    @Query("select distinct _sport_sku_id,_account_id from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "_scene_id = :sceneId and " +
            "(:sceneSubId = '' or _scene_sub_id = :sceneSubId)")
    List<SportRecordResult> queryAccountIdAndSportId(String belongId, String appCode, String sceneId, String sceneSubId);

    @Query("select * from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "_scene_id = :sceneId and " +
            "(:sceneSubId = '' or _scene_sub_id = :sceneSubId) and " +
            "_account_id = :accountId and " +
            "_sport_sku_id = :sportId " +
            "order by _sport_start_time asc"
    )
    List<SportRecordLocalEntity> queryRecordMeeting(String belongId, String appCode, String sceneId, String sceneSubId, String accountId, String sportId);

    @Query("select * from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "_scene_id = :sceneId and " +
            "(:sceneSubId = '' or _scene_sub_id = :sceneSubId) and " +
            "(:projectId = '' or _sport_sku_id = :projectId) and " +
            "(:accountId = '' or _account_id = :accountId) " +
            "order by _sport_start_time desc " +
            "limit :noOfRows,:rowNum"
    )
    List<SportRecordLocalEntity> queryRecordByPage(
            int noOfRows,
            int rowNum,
            String belongId,
            String appCode,
            String sceneId,
            String sceneSubId,
            String projectId,
            String accountId
    );

    @Query("select * from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "_scene_id = :sceneId and " +
            "(:sceneSubId = '' or _scene_sub_id = :sceneSubId) and " +
            "(:projectId = '' or _sport_sku_id = :projectId) and " +
            "(:collegeId = '' or _college_id = :collegeId) and " +
            "(:majorId = '' or _major_id = :majorId) and " +
            "(:gradeId = '' or _grade_id = :gradeId) and " +
            "(:classId = '' or _class_id = :classId) and " +
            "(:sex = '' or _user_sex = :sex) and " +
            "((:startTime = 0 or :endTime = 0) or (_sport_start_time between :startTime and :endTime )) and " +
            "(:keyWord = '' or (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' ) or (_student_name_py like '%' || :keyWord || '%') or (_student_name_py_short like '%' || :keyWord || '%')) " +
            "order by _sport_start_time desc " +
            "limit :noOfRows,:rowNum"
    )
    List<SportRecordLocalEntity> queryRecordByPage(
            int noOfRows,
            int rowNum,
            String belongId,
            String appCode,
            String sceneId,
            String sceneSubId,
            String projectId,
            String collegeId,
            String majorId,
            String gradeId,
            String classId,
            String sex,
            long startTime,
            long endTime,
            String keyWord
    );

    @Query("select * from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "_scene_id = :sceneId and " +
            "(:sceneSubId = '' or _scene_sub_id = :sceneSubId) and " +
            "(:projectId = '' or _sport_sku_id = :projectId) and " +
            "(:collegeId = '' or _college_id = :collegeId) and " +
            "(:collegeName = '' or _college_name like '%' || :collegeName || '%') and " +
            "(:majorId = '' or _major_id = :majorId) and " +
            "(:gradeId = '' or _grade_id = :gradeId) and " +
            "(:classId = '' or _class_id = :classId) and " +
            "(:sex = '' or _user_sex = :sex) and " +
            "((:startTime = 0 or :endTime = 0) or (_sport_start_time between :startTime and :endTime )) and " +
            "(:keyWord = '' or (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' ) or (_student_name_py like '%' || :keyWord || '%') or (_student_name_py_short like '%' || :keyWord || '%')) " +
            "order by _sport_start_time desc " +
            "limit :noOfRows,:rowNum"
    )
    List<SportRecordLocalEntity> queryRecordByPage(
            int noOfRows,
            int rowNum,
            String belongId,
            String appCode,
            String sceneId,
            String sceneSubId,
            String projectId,
            String collegeId,
            String collegeName,
            String majorId,
            String gradeId,
            String classId,
            String sex,
            long startTime,
            long endTime,
            String keyWord
    );

    @Query("select distinct(_account_id) from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "_scene_id = :sceneId and " +
            "(:sceneSubId = '' or _scene_sub_id = :sceneSubId) and " +
            "(:projectId = '' or _sport_sku_id = :projectId) and " +
            "(:collegeId = '' or _college_id = :collegeId) and " +
            "(:majorId = '' or _major_id = :majorId) and " +
            "(:gradeId = '' or _grade_id = :gradeId) and " +
            "(:classId = '' or _class_id = :classId) and " +
            "(:sex = '' or _user_sex = :sex) and " +
            "((:startTime = 0 or :endTime = 0) or (_sport_start_time between :startTime and :endTime )) and " +
            "(:keyWord = '' or (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' )  or (_student_name_py like '%' || :keyWord || '%') or (_student_name_py_short like '%' || :keyWord || '%')) " +
            "order by _sport_start_time desc " +
            "limit :noOfRows,:rowNum"
    )
    List<String> queryRecordAccountId(
            int noOfRows,
            int rowNum,
            String belongId,
            String appCode,
            String sceneId,
            String sceneSubId,
            String projectId,
            String collegeId,
            String majorId,
            String gradeId,
            String classId,
            String sex,
            long startTime,
            long endTime,
            String keyWord
    );

    @Query("select distinct(_account_id) from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "_scene_id = :sceneId and " +
            "(:sceneSubId = '' or _scene_sub_id = :sceneSubId) and " +
            "(:projectId = '' or _sport_sku_id = :projectId) and " +
            "(:collegeId = '' or _college_id = :collegeId) and " +
            "(:collegeName = '' or _college_name like '%' || :collegeName || '%') and " +
            "(:majorId = '' or _major_id = :majorId) and " +
            "(:gradeId = '' or _grade_id = :gradeId) and " +
            "(:classId = '' or _class_id = :classId) and " +
            "(:sex = '' or _user_sex = :sex) and " +
            "((:startTime = 0 or :endTime = 0) or (_sport_start_time between :startTime and :endTime )) and " +
            "(:keyWord = '' or (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' )  or (_student_name_py like '%' || :keyWord || '%') or (_student_name_py_short like '%' || :keyWord || '%')) " +
            "order by _sport_start_time desc " +
            "limit :noOfRows,:rowNum"
    )
    List<String> queryRecordAccountId(
            int noOfRows,
            int rowNum,
            String belongId,
            String appCode,
            String sceneId,
            String sceneSubId,
            String projectId,
            String collegeId,
            String collegeName,
            String majorId,
            String gradeId,
            String classId,
            String sex,
            long startTime,
            long endTime,
            String keyWord
    );

    // 记录按运动分组，并获取每组分数最大的记录
    // SELECT COALESCE(MAX(my_column), 'default_value') FROM my_table; 空字符串或NULL默认值
    @Query(
            "select a.* from _sport_record_local as a where coalesce(cast(_sport_score as decimal),0) = " +
                    "(select max(coalesce(cast(_sport_score as decimal),0)) from _sport_record_local where " +
                    "_belong_id = :belongId and " +
                    "_app_code = :appCode and " +
                    "_scene_id = :sceneId and " +
                    "(:sceneSubId = '' or _scene_sub_id = :sceneSubId) and " +
                    "_account_id = :accountId and " +
                    "a._sport_sku_id = _sport_sku_id)"
    )
    List<SportRecordLocalEntity> getMaxScoreRecordGroupByAccount(
            String belongId,
            String appCode,
            String sceneId,
            String sceneSubId,
            String accountId
    );

    // 记录按运动分组，并获取每组分数最大的记录，相加得出总分数
    // 如果某列中包含空字符串或NULL值，在使用SUM函数时，这些值会被忽略，不会影响最终的求和结果。
    @Query(
            "select sum(s1.maxSource) from " +
                    "(select max(coalesce(cast(_sport_score as decimal),0)) as 'maxSource' from _sport_record_local where " +
                    "_belong_id = :belongId and " +
                    "_app_code = :appCode and " +
                    "_scene_id = :sceneId and " +
                    "(:sceneSubId = '' or _scene_sub_id = :sceneSubId) and " +
                    "_account_id = :accountId " +
                    "group by _sport_sku_id) s1"
    )
    double getMaxScoreGroupByAccount(
            String belongId,
            String appCode,
            String sceneId,
            String sceneSubId,
            String accountId
    );

    //  获取项目
    @Query("select * from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "_scene_id = :sceneId and " +
            "_scene_sub_id = :sceneSubId and " +
            "_account_id = :accountId " +
            "group by _sport_sku_id"
    )
    List<SportRecordLocalEntity> queryProjects(
            String belongId,
            String appCode,
            String sceneId,
            String sceneSubId,
            String accountId);

    @Query("select count(*) from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "_scene_id = :sceneId and " +
            "(:sceneSubId = '' or _scene_sub_id = :sceneSubId) and " +
            "(_sport_start_time between :startTime and :endTime) and " +
            "(_upload_file_status = 1 and _upload_data_status = 1 and _upload_modify_status = 0)"
    )
    int queryCurrentDayUploadSuccess(String belongId, String appCode, String sceneId, String sceneSubId, long startTime, long endTime);

    @Query("select count(*) from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "_scene_id = :sceneId and " +
            "(:sceneSubId = '' or _scene_sub_id = :sceneSubId) and " +
            "(_upload_file_status = 1 and _upload_data_status = 1 and _upload_modify_status = 0)"
    )
    int queryUploadSuccess(String belongId, String appCode, String sceneId, String sceneSubId);

    @Query("select count(*) from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "_scene_id = :sceneId and " +
            "(:sceneSubId = '' or _scene_sub_id = :sceneSubId) and " +
            "_scene_id = :sceneId and " +
            "_account_id like 'youke_%' and " +
            "(_sport_start_time between :startTime and :endTime)"
    )
    int queryCurrentDayYkUpload(String belongId, String appCode, String sceneId, String sceneSubId, long startTime, long endTime);

    @Query("select count(*) from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "_scene_id = :sceneId and " +
            "(:sceneSubId = '' or _scene_sub_id = :sceneSubId) and " +
            "(_sport_start_time between :startTime and :endTime) and " +
            "(_upload_file_status = 0 or _upload_data_status = 0 or _upload_modify_status != 0)"
    )
    int queryCurrentDayUploadFail(String belongId, String appCode, String sceneId, String sceneSubId, long startTime, long endTime);

    @Query("select count(*) from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "_scene_id = :sceneId and " +
            "(:sceneSubId = '' or _scene_sub_id = :sceneSubId) and " +
            "(_upload_file_status = 0 or _upload_data_status = 0 or _upload_modify_status != 0)"
    )
    int queryUploadFail(String belongId, String appCode, String sceneId, String sceneSubId);

    //  图片/视频状态:0（未上传）、1（已上传）
    //  业务数据状态:0（未上传）、1（已上传）、2（上传失败，后续不再上传）
    //  修改状态:0（未修改）、1（修改）
    @Query("select * from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "(_upload_file_status = 0 or _upload_data_status = 0 or _upload_modify_status = 1)"
    )
    List<SportRecordLocalEntity> queryNotUpload(String belongId, String appCode);

    @Query("select * from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "(_upload_data_status = 1 and _upload_modify_status = 0) and " +
            "_account_name like '%' || :studentName || '%'"
    )
    List<SportRecordLocalEntity> queryUploadSuccessByStudentName(String belongId, String appCode, String studentName);


    /*
     *
     * 查询为上传或者修改的记录
     * */
    @Query("select * from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "(_upload_data_status = 0 or _upload_modify_status = 1) and " +
            "_account_name like '%' || :studentName || '%'"
    )
    List<SportRecordLocalEntity> queryNotUploadByStudentName(String belongId, String appCode, String studentName);

    @Query("select * from _sport_record_local where " +
            "_belong_id = :belongId and " +
            "_app_code = :appCode and " +
            "_account_name like '%' || :studentName || '%'"
    )
    List<SportRecordLocalEntity> queryRecordByStudentName(String belongId, String appCode, String studentName);

    //  通过_sport_start_time获取记录
    @Query("SELECT * FROM _sport_record_local WHERE _sport_start_time =:sportStartTime")
    List<SportRecordLocalEntity> queryBySportStartTime(String sportStartTime);

    @Update(entity = SportRecordLocalEntity.class)
    void updateModifyResult(SportRecordModifyEntity entity);

    @Update
    void update(SportRecordLocalEntity entity);

    @Update
    void update(List<SportRecordLocalEntity> entity);

    @Query("delete from _sport_record_local")
    void clearAll();

    @Query("delete from _sport_record_local where _belong_id = :belongId")
    void clearAll(String belongId);

    @Delete
    void clear(List<SportRecordLocalEntity> list);

    /**
     * 通过 RecordPlan 中的 ptPlanId 查询运动记录
     *
     * @param ptPlanId 组织测试 id
     */
    @Query("SELECT * FROM _sport_record_local WHERE _sport_plan LIKE '%\"ptPlanId\":\"' || :ptPlanId || '%'")
    List<SportRecordLocalEntity> queryRecordByPtPlanId(String ptPlanId);

    /**
     * 通过 RecordPlan 中的 ptPlanId 查询符合条件的运动记录数量
     *
     * @param ptPlanId 组织测试 id
     */
    @Query("SELECT COUNT(*) FROM _sport_record_local WHERE  _upload_data_status=1 AND _upload_modify_status=0 AND _sport_plan LIKE '%\"ptPlanId\":\"' || :ptPlanId || '%'")
    int countRecordsUploadSuccessByPtPlanId(String ptPlanId);

    @Query("SELECT COUNT(*) FROM _sport_record_local WHERE  (_upload_data_status=0 OR _upload_modify_status=1) AND _sport_plan LIKE '%\"ptPlanId\":\"' || :ptPlanId || '%'")
    int countRecordsUploadFailByPtPlanId(String ptPlanId);


    @Query("SELECT COUNT(*) FROM _sport_record_local WHERE _belong_id = :belongId AND (_sport_plan IS NULL OR _sport_plan = 'null')")
    int countRecordsWithEmptySportPlan(String belongId);

    @Query("SELECT COUNT(*) FROM _sport_record_local WHERE _belong_id = :belongId AND _upload_data_status=1 AND _upload_modify_status=0 AND (_sport_plan IS NULL OR _sport_plan = 'null' )")
    int countRecordsWithEmptyUploadSuccessSportPlan(String belongId);

    @Query("SELECT * FROM _sport_record_local WHERE  _upload_data_status=1 AND _upload_modify_status=0 AND _account_id =:accountId")
    List<SportRecordLocalEntity> queryUploadSuccessByAccountId(String accountId);

    @Query("SELECT * FROM _sport_record_local WHERE  (_upload_data_status=0 OR _upload_modify_status=1) AND _account_id =:accountId")
    List<SportRecordLocalEntity> queryNotUploadByAccountId(String accountId);

//    @Query("SELECT * FROM _sport_record_local WHERE  :num = _number AND _sport_start_time=:sportStartTime")
//    SportRecordLocalEntity getAllByNumber(int num, String sportStartTime);

    @Query("SELECT * FROM _sport_record_local WHERE _belong_id = :belongId AND  :num = _number AND _sport_start_time=:sportStartTime")
    SportRecordLocalEntity getByNum(String belongId, int num, String sportStartTime);

    @Query("SELECT count(*) FROM _sport_record_local WHERE _belong_id = :belongId AND _sport_start_time =:sportStartTime AND _sport_item_code=:sportItemCode AND _status=:sportStatus")
    int countCurrentSport(String belongId, String sportItemCode, String sportStartTime, int sportStatus);

    @Query("SELECT * FROM _sport_record_local WHERE _belong_id = :belongId AND _sport_start_time =:sportStartTime AND _sport_item_code=:sportItemCode")
    List<SportRecordLocalEntity> getCurrentSport(String belongId, String sportItemCode, String sportStartTime);

    // 获取非当前场次的 没有成绩的记录
    @Query("SELECT * FROM _sport_record_local WHERE _belong_id = :belongId AND _sport_start_time !=:sportStartTime AND _sport_end_time<=0 order by _sport_start_time asc")
    List<SportRecordLocalEntity> getUnNormalNotCurrent(String belongId, String sportStartTime);

    // 查询未出成绩成员
    @Query("SELECT * FROM _sport_record_local WHERE _belong_id = :belongId AND (:sportStartTime='0' or :sportStartTime=_sport_start_time) AND _sport_result<=0 order by _sport_start_time asc")
    List<SportRecordLocalEntity> getBySportTotalUnNormal(String belongId, String sportStartTime);

    // 查询已有成绩的成员(运动结果页面)
    @Query("SELECT * FROM _sport_record_local WHERE _belong_id = :belongId AND (:sportStartTime='0' or :sportStartTime=_sport_start_time) AND _sport_result>0 order by _sport_start_time asc")
    List<SportRecordLocalEntity> getBySportTotalNormal(String belongId, String sportStartTime);

    @Delete
    void clearItem(SportRecordLocalEntity info);

    //  根据组织测试计划ID获取有成绩的学生运动记录，根据学生studentUid去重
    @Query("SELECT COUNT(DISTINCT _account_id) FROM _sport_record_local WHERE _belong_id= :belongId" +
            " AND _sport_plan like '%' || :planUid || '%'" +
            " AND _sport_end_time>0"
    )
    int getMemberInfoCountInPlan(String belongId, String planUid);

    @Query(
            "SELECT * FROM _sport_record_local WHERE :belongId = _belong_id and _sport_scene_code IN (2001, 3002) " +
                    "and (:keyword='' or _account_name like '%' || :keyword || '%' " +
                    "or _student_name_py like '%' || :keyword || '%'" +
                    "or _student_name_py_short like '%' || :keyword || '%')" +
                    "and _sport_meet like '%' || :sportMeetContent || '%'  " +
                    "and _sport_plan like '%' || :sportPlanContent || '%'  " +
                    "and (:gradeId='' or :gradeId = _grade_id)" +
                    "and (:classId='' or :classId = _class_id)" +
                    "and (:facultyName='' or :facultyName = _faculty_name)" +
                    "and (:majorName='' or :majorName = _major_name)" +
                    "and (:sex='' or :sex = _sex)" +
                    "and (:projectName='' or :projectName = _sport_sku_name)" +
                    "and ((:startTime = 0 OR :endTime = 0) OR (:startTime <= _sport_start_time AND :endTime >= _sport_start_time)) " +
                    "order by _sport_start_time DESC " +
                    "LIMIT :pageIndex,:pageSize "
    )
    List<SportRecordLocalEntity> getAllByPage(String belongId,
                                              String keyword,
                                              String sportMeetContent,
                                              String sportPlanContent,
                                              String facultyName, String majorName,
                                              String gradeId,
                                              String classId,
                                              String sex,
                                              String projectName,
                                              Long startTime,
                                              Long endTime,
                                              int pageIndex,
                                              int pageSize);


    // 获取长跑成绩列表（排序规则差别）
    @Query(
            "select * from _sport_record_local where  " +
                    ":belongId = _belong_id " +
                    "and (:classId = '' or :classId = _class_id)" +
                    "and _sport_start_time <> :sportStartTime " +
                    "and ((:startTime = 0 or :endTime = 0) OR (:startTime <= _sport_start_time AND :endTime >= _sport_start_time)) " +
                    "and :sportSkuId = _sport_sku_id order by _sport_start_time desc, _sportSort asc LIMIT :pageIndex,:pageSize"
    )
    List<SportRecordLocalEntity> getAllHis3(
            String belongId,
            String classId,
            Long startTime,
            Long endTime,
            Long sportStartTime,
            String sportSkuId,
            int pageIndex,
            int pageSize
    );

    @Query(
            "SELECT * FROM _sport_record_local WHERE :belongId = _belong_id " +
                    "and (:keyword='' or _account_name like '%' || :keyword || '%' " +
                    "or _student_name_py like '%' || :keyword || '%'" +
                    "or _student_name_py_short like '%' || :keyword || '%')" +
                    "and _sport_meet like '%' || :sportMeetContent || '%'  " +
                    "and _sport_plan not like '%' || :sportPlanContent || '%'  " +
                    "and (:collegeName='' or :collegeName = _college_name)" +
                    "and (:majorName='' or :majorName = _major_name)" +
                    "and (:gradeId='' or :gradeId = _grade_id)" +
                    "and (:classId='' or :classId = _class_id)" +
                    "and (:sex='' or :sex = _sex)" +
                    "and (:projectName='' or :projectName = _sport_sku_name)" +
                    "and ((:startTime = 0 OR :endTime = 0) OR (:startTime <= _sport_start_time AND :endTime >= _sport_start_time)) " +
                    "order by _sport_start_time DESC " +
                    "LIMIT :pageIndex,:pageSize "
    )
    List<SportRecordLocalEntity> getAllPlanByPage(String belongId,
                                                  String keyword,
                                                  String sportMeetContent,
                                                  String sportPlanContent,
                                                  String collegeName, String majorName,
                                                  String gradeId,
                                                  String classId,
                                                  String sex,
                                                  String projectName,
                                                  Long startTime,
                                                  Long endTime,
                                                  int pageIndex,
                                                  int pageSize);

    @Query(
            "SELECT * FROM _sport_record_local WHERE :belongId = _belong_id " +
                    "and (:keyword='' or _account_name like '%' || :keyword || '%' " +
                    "or _student_name_py like '%' || :keyword || '%'" +
                    "or _student_name_py_short like '%' || :keyword || '%')" +
                    "and _sport_meet like '%' || :sportMeetContent || '%'  " +
                    "and _sport_plan like '%' || :sportPlanId || '%'  " +
                    "and (:collegeName='' or :collegeName = _college_name)" +
                    "and (:majorName='' or :majorName = _major_name)" +
                    "and (:gradeId='' or :gradeId = _grade_id)" +
                    "and (:classId='' or :classId = _class_id)" +
                    "and (:sex='' or :sex = _sex)" +
                    "and (:projectName='' or :projectName = _sport_sku_name)" +
                    "and ((:startTime = 0 OR :endTime = 0) OR (:startTime <= _sport_start_time AND :endTime >= _sport_start_time)) " +
                    "order by _sport_start_time DESC " +
                    "LIMIT :pageIndex,:pageSize "
    )
    List<SportRecordLocalEntity> getAllPlanById(String belongId,
                                                String keyword,
                                                String sportMeetContent,
                                                String sportPlanId,
                                                String collegeName, String majorName,
                                                String gradeId,
                                                String classId,
                                                String sex,
                                                String projectName,
                                                Long startTime,
                                                Long endTime,
                                                int pageIndex,
                                                int pageSize);

    @Query(
            "SELECT * FROM _sport_record_local WHERE :belongId = _belong_id " +
                    "and (:keyword='' or _account_name like '%' || :keyword || '%' " +
                    "or _student_name_py like '%' || :keyword || '%'" +
                    "or _student_name_py_short like '%' || :keyword || '%')" +
                    "and _sport_meet not like '%' || :sportMeetContent || '%'  " +
                    "and (:gradeId='' or :gradeId = _grade_id)" +
                    "and (:classId='' or :classId = _class_id)" +
                    "and (:sex='' or :sex = _sex)" +
                    "and (:projectName='' or :projectName = _sport_sku_name)" +
                    "order by _sport_start_time DESC " +
                    "LIMIT :pageIndex,:pageSize "
    )
    List<SportRecordLocalEntity> getAllMeetByPage(String belongId,
                                                  String keyword,
                                                  String sportMeetContent,
                                                  String gradeId,
                                                  String classId,
                                                  String sex,
                                                  String projectName,
                                                  int pageIndex,
                                                  int pageSize);

    @Query(
            "SELECT * FROM _sport_record_local WHERE :belongId = _belong_id " +
                    "and (:keyword='' or _account_name like '%' || :keyword || '%' " +
                    "or _student_name_py like '%' || :keyword || '%'" +
                    "or _student_name_py_short like '%' || :keyword || '%')" +
                    "and _sport_meet not like '%' || :sportMeetContent || '%'  " +
                    "and (:collegeId='' or :collegeId = _college_id)" +
                    "and (:majorId='' or :majorId = _major_id)" +
                    "and (:gradeId='' or :gradeId = _grade_id)" +
                    "and (:classId='' or :classId = _class_id)" +
                    "and (:sex='' or :sex = _sex)" +
                    "and (:projectName='' or :projectName = _sport_sku_name)" +
                    "order by _sport_start_time DESC " +
                    "LIMIT :pageIndex,:pageSize "
    )
    List<SportRecordLocalEntity> getAllMeetByPage(String belongId,
                                                  String keyword,
                                                  String sportMeetContent,
                                                  String collegeId,
                                                  String majorId,
                                                  String gradeId,
                                                  String classId,
                                                  String sex,
                                                  String projectName,
                                                  int pageIndex,
                                                  int pageSize);

    @Query(
            "SELECT * FROM _sport_record_local WHERE :belongId = _belong_id " +
                    "and (:keyword='' or _account_name like '%' || :keyword || '%' " +
                    "or _student_name_py like '%' || :keyword || '%'" +
                    "or _student_name_py_short like '%' || :keyword || '%')" +
                    "and _sport_meet like '%' || :sportMeetId || '%'  " +
                    "and (:gradeId='' or :gradeId = _grade_id)" +
                    "and (:classId='' or :classId = _class_id)" +
                    "and (:sex='' or :sex = _sex)" +
                    "and (:projectName='' or :projectName = _sport_sku_name)" +
                    "order by _sport_start_time DESC " +
                    "LIMIT :pageIndex,:pageSize "
    )
    List<SportRecordLocalEntity> getAllMeetById(String belongId,
                                                String keyword,
                                                String sportMeetId,
                                                String gradeId,
                                                String classId,
                                                String sex,
                                                String projectName,
                                                int pageIndex,
                                                int pageSize);

    // 获取长跑成绩列表（排序规则差别）
    @Query(
            "select * from _sport_record_local where  " +
                    ":belongId = _belong_id " +
                    "and (:classId = '' or :classId = _class_id)" +
                    "and (:collegeId = '' or :collegeId = _college_id)" +
                    "and (:majorId = '' or :majorId = _major_id)" +
                    "and _sport_start_time <> :sportStartTime " +
                    "and ((:startTime = 0 or :endTime = 0) OR (:startTime <= _sport_start_time AND :endTime >= _sport_start_time)) " +
                    "and :sportSkuId = _sport_sku_id order by _sport_start_time desc, _sportSort asc LIMIT :pageIndex,:pageSize"
    )
    List<SportRecordLocalEntity> getAllHis3(
            String belongId,
            String classId,
            String collegeId,
            String majorId,
            Long startTime,
            Long endTime,
            Long sportStartTime,
            String sportSkuId,
            int pageIndex,
            int pageSize
    );

    /*
     * 只查询PlanQueryInfo
     * */
    @Query(
            "SELECT distinct  _scene_sub_id, _scene_sub_name FROM _sport_record_local WHERE _belong_id = :belongId and _app_code = :appCode and _scene_id = :sceneId order by _sport_end_time DESC"
    )
    List<PlanQueryInfo> getPlanQueryInfo(String belongId,
                                         String appCode,
                                         String sceneId);


    @Query("SELECT * FROM _sport_record_local WHERE :belongId = _belong_id and _sport_scene_code IN (:sceneCodes) and (:keyword='' or _account_name like '%' || :keyword || '%' or _student_name_py like '%' || :keyword || '%'or _student_name_py_short like '%' || :keyword || '%')and _sport_meet like '%' || :sportMeetContent || '%'  and _sport_plan like '%' || :sportPlanContent || '%'  and (:collegeId='' or :collegeId = _college_id)and (:majorId='' or :majorId = _major_id)and (:gradeId='' or :gradeId = _grade_id)and (:classId='' or :classId = _class_id)and (:sex='' or :sex = _sex)and (:projectName='' or :projectName = _sport_sku_name)and ((:startTime = 0 OR :endTime = 0) OR (:startTime <= _sport_start_time AND :endTime >= _sport_start_time)) order by _sport_start_time DESC LIMIT :pageIndex,:pageSize ")
    List<SportRecordLocalEntity> getAllByPage(String belongId, String keyword, String sportMeetContent, String sportPlanContent, String collegeId, String majorId, String gradeId, String classId, String sex, String projectName, Long startTime, Long endTime, List<String> sceneCodes, int pageIndex, int pageSize);

    /**
     *
     * @return
     */
    @Query("select * from _sport_record_local where  :belongId = _belong_id and (:classId = '' or :classId = _class_id)and (:collegeId = '' or :collegeId = _college_id)and (:majorId = '' or :majorId = _major_id)and _sport_start_time not in (:sportStartTimeList) and ((:startTime = 0 or :endTime = 0) OR (:startTime <= _sport_start_time AND :endTime >= _sport_start_time)) and :sportSkuId = _sport_sku_id order by _sport_start_time desc, _sportSort asc LIMIT :pageIndex,:pageSize")
    List<SportRecordLocalEntity> getAllHis3(String belongId, String classId, String collegeId, String majorId, Long startTime, Long endTime, List<Long> sportStartTimeList, String sportSkuId, int pageIndex, int pageSize);

    /**
     *
     * 获取指定运动运动数
     * @param belongId
     * @param sportSkuId
     * @return
     */
    @Query("select count(_id) from _sport_record_local where  :belongId = _belong_id and :sportSkuId = _sport_sku_id")
    int getAllHisCount(String belongId, String sportSkuId);

    @Query("select * from _sport_record_local where  :startSignalTime = _sport_start_time and :accountId = _account_id")
    SportRecordLocalEntity getRecordByUserId(String accountId, String startSignalTime);

}
