package com.adl.service.db;

import com.adl.service.db.entity.StudentEntity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Update;
import io.reactivex.rxjava3.core.Completable;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
@Dao
public interface StudentDao {

    // 提取为常量
    String STUDENT_SELECT_FIELDS = "_user_id,_account_id,_account_name,_account_num," +
            "_account_phone_Num,_card_nums,_card_num_str,_college_id,_college_name," +
            "_faculty_id,_faculty_name,_major_id,_major_name,_sec_code,_sec_name," +
            "_stage_type,_grade_name,_grade_id,_grade_num,_class_id,_class_name," +
            "_class_num,_user_face_img_url,_student_code,_acayear_sem_code," +
            "_user_sex,_photo_status,_create_time,_update_time,_del_flag," +
            "_student_num,_grade_stage_chinese_name,_thumbnail_user_face_img_url," +
            "_account_name_pin_yin,_account_name_first_letter_pin_yin";

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<StudentEntity> list);

    @Query("select count (*) from _student")
    int countSize();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select _account_id from _student")
    List<StudentEntity> getAllAccountId();

    @Query("select * from _student")
    List<StudentEntity> getAll();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select _account_id,_account_name,_face_data_list from _student")
    List<StudentEntity> getAllFaceData();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select " + STUDENT_SELECT_FIELDS + " from _student")
    List<StudentEntity> getAllWithOutFaceData();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select " + STUDENT_SELECT_FIELDS + " from _student  limit :noOfRows,:rowNum")
    List<StudentEntity> getAllWithOutFaceDataByPage(int noOfRows, int rowNum);

    @Query("select * from _student limit :noOfRows,:rowNum")
    List<StudentEntity> getAllByPage(int noOfRows, int rowNum);

    @Query("select * from _student where _account_id = :accountId limit 1")
    StudentEntity queryStudentByAccountId(String accountId);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select " + STUDENT_SELECT_FIELDS + " from _student where _account_id in (:accountIds)")
    List<StudentEntity> queryStudentByAccountIds(List<String> accountIds);

    @Query("select * from _student where _account_id in (select _account_id from _plan_student where _plan_id=:planId order by _account_id limit :noOfRows,:rowNum)")
    List<StudentEntity> queryStudentByAccountIds1(int noOfRows, int rowNum, String planId);

    @Query("select * from _student where _class_id in (:classIds)")
    List<StudentEntity> queryStudentByClassIds(List<String> classIds);

    @Query("select * from _student where _face_data_list != 'null'")
    List<StudentEntity> queryStudentWithFaceData();

    @Query("select * from _student where _card_nums is not Null and _card_nums like '%' || :cardNum || '%'")
    List<StudentEntity> queryStudentWithCardNum(String cardNum);

    /// -------------------------------------------------------------------------------------------------

    default List<StudentEntity> queryStudentByPageSafe(int noOfRows, int rowNum, List<String> accountIds, String collegeId, String majorId, String gradeId, String classId, String sex, String keyWord) {
        if (accountIds == null) {
            return queryStudentByPage(noOfRows, rowNum, collegeId, majorId, gradeId, classId, sex, keyWord);
        } else {
            return queryStudentByPage(noOfRows, rowNum, accountIds, collegeId, majorId, gradeId, classId, sex, keyWord);
        }
    }

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select " + STUDENT_SELECT_FIELDS + " from _student where " +
            "(:collegeId = '' or _college_id = :collegeId) and " +
            "(:majorId = '' or _major_id = :majorId) and " +
            "(:gradeId = '' or _grade_id = :gradeId) and " +
            "(:classId = '' or _class_id = :classId) and " +
            "(:sex = '' or _user_sex = :sex) and " +
            "(:keyWord = '' or (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' ) or (_account_name_pin_yin like '%' || :keyWord || '%') or (_account_name_first_letter_pin_yin like '%' || :keyWord || '%')) " +
            "limit :noOfRows,:rowNum"
    )
    List<StudentEntity> queryStudentByPage(int noOfRows, int rowNum, String collegeId, String majorId, String gradeId, String classId, String sex, String keyWord);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select " + STUDENT_SELECT_FIELDS + " from _student where " +
            "(_account_id in (:accountIds)) and " +
            "(:collegeId = '' or _college_id = :collegeId) and " +
            "(:majorId = '' or _major_id = :majorId) and " +
            "(:gradeId = '' or _grade_id = :gradeId) and " +
            "(:classId = '' or _class_id = :classId) and " +
            "(:sex = '' or _user_sex = :sex) and " +
            "(:keyWord = '' or (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' ) or (_account_name_pin_yin like '%' || :keyWord || '%') or (_account_name_first_letter_pin_yin like '%' || :keyWord || '%')) " +
            "limit :noOfRows,:rowNum"
    )
    List<StudentEntity> queryStudentByPage(int noOfRows, int rowNum, List<String> accountIds, String collegeId, String majorId, String gradeId, String classId, String sex, String keyWord);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select " + STUDENT_SELECT_FIELDS + " from _student where " +
            "(_account_id in (:accountIds)) and " +
            "(:collegeId = '' or _college_id = :collegeId) and " +
            "(:majorId = '' or _major_id = :majorId) and " +
            "(:gradeId = '' or _grade_id = :gradeId) and " +
            "(:classId = '' or _class_id = :classId) and " +
            "(:sex = '' or _user_sex = :sex) and " +
            "(:keyWord = '' or (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' ) or (_account_name_pin_yin like '%' || :keyWord || '%') or (_account_name_first_letter_pin_yin like '%' || :keyWord || '%'))"
    )
    List<StudentEntity> queryStudent(List<String> accountIds, String collegeId, String majorId, String gradeId, String classId, String sex, String keyWord);

    @Query("select _student.* from _student " +
            "inner join _plan_student sps on _student._account_id = sps._account_id " +
            "where sps._plan_id = :planId and " +
            "(:collegeId = '' or _student._college_id = :collegeId) and " +
            "(:majorId = '' or _student._major_id = :majorId) and " +
            "(:gradeId = '' or _student._grade_id = :gradeId) and " +
            "(:classId = '' or _student._class_id = :classId) and " +
            "(:sex = '' or _student._user_sex = :sex) and " +
            "(:keyWord = '' or (_student._account_name like '%' || :keyWord || '%') or (_student._student_code like '%' || :keyWord || '%') or (_student._account_name_pin_yin like '%' || :keyWord || '%') or (_student._account_name_first_letter_pin_yin like '%' || :keyWord || '%')) " +
            "limit :noOfRows,:rowNum")
    List<StudentEntity> queryStudentByPageJoinStudentPlan(String planId,int noOfRows, int rowNum, String collegeId, String majorId, String gradeId, String classId, String sex, String keyWord);

    /// -------------------------------------------------------------------------------------------------

    @Query("select * from _student where (:keyWord = '' or (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' ) or (_account_name_pin_yin like '%' || :keyWord || '%') or (_account_name_first_letter_pin_yin like '%' || :keyWord || '%'))"
    )
    List<StudentEntity> queryStudentByKeyWord(String keyWord);

    @Update
    void update(StudentEntity entity);

    @Query("delete from _student")
    void clearAll();

    @Delete
    void clear(List<StudentEntity> list);

    @Query("select * from _student where   (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' ) or (_account_name_pin_yin like '%' || :keyWord || '%') or (_account_name_first_letter_pin_yin like '%' || :keyWord || '%')")
    List<StudentEntity> queryStudentByAccountName(String keyWord);


    @Query("select * from _student  order by _grade_num,_class_num asc limit :limit  OFFSET :offset")
    List<StudentEntity> getStudentsOrderedByGradeNumAsc(int limit, int offset);

    @Query("select * from _student where _card_num_str like '%' || :cardNum || '%'")
    List<StudentEntity> queryStudentByCardNum(String cardNum);

    /**
     * 获取 _student 表中的第一个学生信息
     *
     * @return 第一个学生实体对象，如果表为空则返回 null
     */
    @Query("select * from _student limit 1")
    StudentEntity getFirstStudent();

    /**
     * 通过studentCode 和studentNum 查询学生信息
     */
    @Query("select * from _student where _student_code = :codeOrNum or _student_num = :codeOrNum")
    List<StudentEntity> queryStudentByStudentCodeAndNum(String codeOrNum);


    @Query("select * from _student WHERE _grade_id=:gradeId")
    List<StudentEntity> getAllByGradeId(String gradeId);

    @Query("select * from _student where " +
            "(:collegeName = '' or _college_name = :collegeName) and " +
            "(:majorName = '' or _major_name = :majorName) and " +
            "(:gradeName = '' or _grade_name = :gradeName) and " +
            "(:className = '' or _class_name = :className) and " +
            "(:sex = '' or _user_sex = :sex) and " +
            "(:keyWord = '' or (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' ) or (_account_name_pin_yin like '%' || :keyWord || '%') or (_account_name_first_letter_pin_yin like '%' || :keyWord || '%')) " +
            "limit :noOfRows,:rowNum"
    )
    List<StudentEntity> queryStudentByFilterByPage(int noOfRows, int rowNum, String collegeName, String majorName, String gradeName, String className, String sex, String keyWord);

    /*分页查找学生未有头像的数据*/
    @Query("select * from _student where _user_face_img_url <> '' and _user_face_img_url is not null limit :noOfRows,:rowNum")
    List<StudentEntity> queryStudentNoFaceImgByPage(int noOfRows, int rowNum);

    /**
     * 有头像的总数
     * */
    @Query("select count(*) from _student where _user_face_img_url <> '' and _user_face_img_url is not null")
    int queryStudentHasFaceImgCount();

    /*
    * 获取最近的学生更新时间
    * */
    @Query("select _update_time from _student order by _update_time desc limit 1")
    String getLastStudentUpdateTime();

    /*
    * 通过_plan_student表连表查询 学生
    * */

    @Query("select _student.* from _student " +
            "inner join _plan_student sps on _student._account_id = sps._account_id " +
            "where sps._plan_id = :sceneId and sps._account_id = :accountId limit 1")
    StudentEntity queryStudentBySceneIdAndAccountId(String sceneId,String accountId);

}

