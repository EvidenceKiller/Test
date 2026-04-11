package com.adl.service.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.adl.service.entity.SportPlanStudentEntity;

import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
@Dao
public interface PlanStudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SportPlanStudentEntity> list);

    @Query("select count (*) from _sport_plan_student_1 where '' = :planId or _plan_id = :planId")
    int countSize(String planId);

    @Query("select * from _sport_plan_student_1 where _plan_id = :planId")
    List<SportPlanStudentEntity> queryStudentByPlanId(String planId);

    @Query("select * from _sport_plan_student_1 where _plan_id = :planId limit :noOfRows,:rowNum")
    List<SportPlanStudentEntity> queryStudentByPlanId(int noOfRows, int rowNum, String planId);

    @Query("select _account_id from _sport_plan_student_1 where _plan_id = :planId")
    List<String> queryAccountIdByPlanId(String planId);

    @Query("select _account_id from _sport_plan_student_1 where _plan_id = :planId limit :noOfRows,:rowNum")
    List<String> queryAccountIdByPlanId(int noOfRows, int rowNum, String planId);

    @Query("delete from _sport_plan_student_1 where _plan_id = :planId")
    void clearStudentByPlanId(String planId);

    @Query("delete from _sport_plan_student_1")
    void clearAll();

    @Query("select _account_id from _sport_plan_student_1 where _plan_id = :planId AND _admission_num like '%' || :admissionNum || '%'")
    List<String> queryAccountIdByPlanIdAdnAdmission(String planId, String admissionNum);

    @Query("select _account_id from _sport_plan_student_1 where _plan_id = :planId AND _account_id in (:accountIds)")
    List<String> queryAccountIdByPlanIdAdnIds(String planId, List<String> accountIds);

    @Query("select * from _sport_plan_student_1 where _plan_id = :planId AND _account_id =:accountId")
    List<SportPlanStudentEntity> queryAccountIdByPlanIdAdnId(String planId, String accountId);

    @Query("select * from _sport_plan_student_1 where _plan_id = :planId AND (_student_name like '%'|| :studentName || '%')")
    List<SportPlanStudentEntity> queryStudentByStudentName(String planId, String studentName);

    @Query("select * from _sport_plan_student_1 where _account_id = :accountId limit 1")
    SportPlanStudentEntity queryStudentByStudentName(String accountId);

    @Query("select _account_id from _sport_plan_student_1 where _admission_num like '%' || :admissionNum || '%'")
    List<String> queryStudentByAdmissionNum(String admissionNum);

    /**
     * 通过studentCode 和studentNum 查询学生信息
     */
    @Query("select _account_id from _sport_plan_student_1 where _plan_id = :planId and (_student_code = :codeOrNum or _student_num = :codeOrNum)")
    List<String> queryStudentByStudentCodeAndNum(String planId, String codeOrNum);
}
