package com.adl.service.db;

import com.adl.service.entity.ClassQueryInfo;
import com.adl.service.entity.CollegeQueryInfo;
import com.adl.service.entity.FacultyQueryInfo;
import com.adl.service.entity.GradeQueryInfo;
import com.adl.service.entity.MajorQueryInfo;
import com.adl.service.entity.StudentClassResult;
import com.adl.service.entity.StudentEntity;
import com.adl.service.entity.StudentGradeClassResult;

import org.jetbrains.annotations.NotNull;

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
public interface StudentDao {

    // 提取为常量
    String STUDENT_SELECT_FIELDS = "_user_id,_account_id,_account_name,_account_num," +
            "_account_phone_Num,_card_nums,_card_nums_str,_college_id,_college_name," +
            "_faculty_id,_faculty_name,_major_id,_major_name,_sec_code,_sec_name," +
            "_stage_type,_grade_name,_grade_id,_grade_num,_class_id,_class_name," +
            "_class_num,_user_face_img_url,_student_code,_acayear_sem_code," +
            "_user_sex,_photo_status,_create_time,_update_time,_del_flag," +
            "_student_num,_grade_stage_chinese_name,_thumbnail_user_face_imgUrl," +
            "_account_name_pinyin,_account_name_first_letter_pinyin,local_avatar_path";

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<StudentEntity> list);

    @Query("select count (*) from _student_entity")
    int countSize();

    @Query("select _account_id from _student_entity")
    List<StudentEntity> getAllAccountId();

    @Query("select * from _student_entity")
    List<StudentEntity> getAll();

    @Query("select _account_id,_account_name,_face_data_list from _student_entity")
    List<StudentEntity> getAllFaceData();

    @Query("select " + STUDENT_SELECT_FIELDS + " from _student_entity")
    List<StudentEntity> getAllWithOutFaceData();

    @Query("select " + STUDENT_SELECT_FIELDS + " from _student_entity  limit :noOfRows,:rowNum")
    List<StudentEntity> getAllWithOutFaceDataByPage(int noOfRows, int rowNum);

    @Query("select * from _student_entity limit :noOfRows,:rowNum")
    List<StudentEntity> getAllByPage(int noOfRows, int rowNum);

    @Query("select * from _student_entity where _account_id = :accountId limit 1")
    StudentEntity queryStudentByAccountId(String accountId);

    @Query("select " + STUDENT_SELECT_FIELDS + " from _student_entity where _account_id in (:accountIds)")
    List<StudentEntity> queryStudentByAccountIds(List<String> accountIds);

    @Query("select * from _student_entity where _account_id in (select _account_id from _sport_plan_student_1 where _plan_id=:planId order by _account_id limit :noOfRows,:rowNum)")
    List<StudentEntity> queryStudentByAccountIds1(int noOfRows, int rowNum, String planId);

    @Query("select distinct _grade_num,_class_num from _student_entity order by _grade_num,_class_num")
    List<StudentClassResult> queryStudentGradeAndClass();

    @Query("select distinct _grade_num,_class_num,_grade_id,_class_id,_grade_stage_chinese_name from _student_entity order by _grade_num,_class_num")
    List<StudentGradeClassResult> queryStudentClass();

    @Query("select * from _student_entity where _class_id in (:classIds)")
    List<StudentEntity> queryStudentByClassIds(List<String> classIds);

    @Query("select * from _student_entity where _face_data_list != 'null'")
    List<StudentEntity> queryStudentWithFaceData();

    @Query("select * from _student_entity where _card_nums is not Null and _card_nums like '%' || :cardNum || '%'")
    List<StudentEntity> queryStudentWithCardNum(String cardNum);

    /// -------------------------------------------------------------------------------------------------

    default List<StudentEntity> queryStudentByPageSafe(int noOfRows, int rowNum, List<String> accountIds, String collegeId, String majorId, String gradeId, String classId, String sex, String keyWord) {
        if (accountIds == null) {
            return queryStudentByPage(noOfRows, rowNum, collegeId, majorId, gradeId, classId, sex, keyWord);
        } else {
            return queryStudentByPage(noOfRows, rowNum, accountIds, collegeId, majorId, gradeId, classId, sex, keyWord);
        }
    }

    @Query("select " + STUDENT_SELECT_FIELDS + " from _student_entity where " +
            "(:collegeId = '' or _college_id = :collegeId) and " +
            "(:majorId = '' or _major_id = :majorId) and " +
            "(:gradeId = '' or _grade_id = :gradeId) and " +
            "(:classId = '' or _class_id = :classId) and " +
            "(:sex = '' or _user_sex = :sex) and " +
            "(:keyWord = '' or (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' ) or (_account_name_pinyin like '%' || :keyWord || '%') or (_account_name_first_letter_pinyin like '%' || :keyWord || '%')) " +
            "limit :noOfRows,:rowNum"
    )
    List<StudentEntity> queryStudentByPage(int noOfRows, int rowNum, String collegeId, String majorId, String gradeId, String classId, String sex, String keyWord);

    @Query("select " + STUDENT_SELECT_FIELDS + " from _student_entity where " +
            "(_account_id in (:accountIds)) and " +
            "(:collegeId = '' or _college_id = :collegeId) and " +
            "(:majorId = '' or _major_id = :majorId) and " +
            "(:gradeId = '' or _grade_id = :gradeId) and " +
            "(:classId = '' or _class_id = :classId) and " +
            "(:sex = '' or _user_sex = :sex) and " +
            "(:keyWord = '' or (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' ) or (_account_name_pinyin like '%' || :keyWord || '%') or (_account_name_first_letter_pinyin like '%' || :keyWord || '%')) " +
            "limit :noOfRows,:rowNum"
    )
    List<StudentEntity> queryStudentByPage(int noOfRows, int rowNum, List<String> accountIds, String collegeId, String majorId, String gradeId, String classId, String sex, String keyWord);

    @Query("select " + STUDENT_SELECT_FIELDS + " from _student_entity where " +
            "(_account_id in (:accountIds)) and " +
            "(:collegeId = '' or _college_id = :collegeId) and " +
            "(:majorId = '' or _major_id = :majorId) and " +
            "(:gradeId = '' or _grade_id = :gradeId) and " +
            "(:classId = '' or _class_id = :classId) and " +
            "(:sex = '' or _user_sex = :sex) and " +
            "(:keyWord = '' or (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' ) or (_account_name_pinyin like '%' || :keyWord || '%') or (_account_name_first_letter_pinyin like '%' || :keyWord || '%'))"
    )
    List<StudentEntity> queryStudent(List<String> accountIds, String collegeId, String majorId, String gradeId, String classId, String sex, String keyWord);

    @Query("select _student_entity.* from _student_entity " +
            "inner join _sport_plan_student_1 sps on _student_entity._account_id = sps._account_id " +
            "where sps._plan_id = :planId and " +
            "(:collegeId = '' or _student_entity._college_id = :collegeId) and " +
            "(:majorId = '' or _student_entity._major_id = :majorId) and " +
            "(:gradeId = '' or _student_entity._grade_id = :gradeId) and " +
            "(:classId = '' or _student_entity._class_id = :classId) and " +
            "(:sex = '' or _student_entity._user_sex = :sex) and " +
            "(:keyWord = '' or (_student_entity._account_name like '%' || :keyWord || '%') or (_student_entity._student_code like '%' || :keyWord || '%') or (_student_entity._account_name_pinyin like '%' || :keyWord || '%') or (_student_entity._account_name_first_letter_pinyin like '%' || :keyWord || '%')) " +
            "limit :noOfRows,:rowNum")
    List<StudentEntity> queryStudentByPageJoinStudentPlan(String planId,int noOfRows, int rowNum, String collegeId, String majorId, String gradeId, String classId, String sex, String keyWord);

    /// -------------------------------------------------------------------------------------------------

    @Query("select * from _student_entity where (:keyWord = '' or (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' ) or (_account_name_pinyin like '%' || :keyWord || '%') or (_account_name_first_letter_pinyin like '%' || :keyWord || '%'))"
    )
    List<StudentEntity> queryStudentByKeyWord(String keyWord);

    @Update
    void update(StudentEntity entity);

    @Query("delete from _student_entity")
    void clearAll();

    @Delete
    void clear(List<StudentEntity> list);

    @Query("select * from _student_entity where   (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' ) or (_account_name_pinyin like '%' || :keyWord || '%') or (_account_name_first_letter_pinyin like '%' || :keyWord || '%')")
    List<StudentEntity> queryStudentByAccountName(String keyWord);


    @Query("select * from _student_entity  order by _grade_num,_class_num asc limit :limit  OFFSET :offset")
    List<StudentEntity> getStudentsOrderedByGradeNumAsc(int limit, int offset);

    @Query("select * from _student_entity where _card_nums_str like '%' || :cardNum || '%'")
    List<StudentEntity> queryStudentByCardNum(String cardNum);

    /**
     * 获取 _student_entity 表中的第一个学生信息
     *
     * @return 第一个学生实体对象，如果表为空则返回 null
     */
    @Query("select * from _student_entity limit 1")
    StudentEntity getFirstStudent();

    @Query("select distinct  _grade_id, _grade_name from _student_entity  order by _grade_name")
    List<GradeQueryInfo> getGradeList();

    @Query("select distinct  _grade_id, _grade_name from _student_entity where _major_id=:majorId  order by _grade_name")
    List<GradeQueryInfo> getGradeListByMajorId(String majorId);

    @Query("select distinct  _class_id, _class_name from _student_entity  order by _grade_name, _class_name")
    List<ClassQueryInfo> getAllClass();

    @Query("select distinct _class_id, _class_name from _student_entity where _grade_id=:gradeId order by _class_name")
    List<ClassQueryInfo> getAllClassByGradeId(@NotNull String gradeId);

    @Query("select distinct  _major_id, _major_name from _student_entity  order by _major_name")
    List<MajorQueryInfo> getMajorList();

    /**
     * 通过studentCode 和studentNum 查询学生信息
     */
    @Query("select * from _student_entity where _student_code = :codeOrNum or _student_num = :codeOrNum")
    List<StudentEntity> queryStudentByStudentCodeAndNum(String codeOrNum);


    @Query("select * from _student_entity WHERE _grade_id=:gradeId")
    List<StudentEntity> getAllByGradeId(String gradeId);

    @Query("select distinct  _college_id, _college_name from _student_entity  order by _college_name")
    List<CollegeQueryInfo> getCollegeList();

    @Query("select distinct  _faculty_id, _faculty_name from _student_entity  order by _faculty_name")
    List<FacultyQueryInfo> getFacultyList();

    @Query("select distinct  _major_id, _major_name from _student_entity where _faculty_id=:id order by _major_name")
    List<MajorQueryInfo> getMajorListByFacultyId(String id);

    @Query("select distinct  _major_id, _major_name from _student_entity where _college_id=:id order by _major_name")
    List<MajorQueryInfo> getMajorListByCollegeId(String id);

    @Query("select * from _student_entity where " +
            "(:collegeName = '' or _college_name = :collegeName) and " +
            "(:majorName = '' or _major_name = :majorName) and " +
            "(:gradeName = '' or _grade_name = :gradeName) and " +
            "(:className = '' or _class_name = :className) and " +
            "(:sex = '' or _user_sex = :sex) and " +
            "(:keyWord = '' or (_account_name like '%' || :keyWord || '%' ) or (_student_code like '%' || :keyWord || '%' ) or (_account_name_pinyin like '%' || :keyWord || '%') or (_account_name_first_letter_pinyin like '%' || :keyWord || '%')) " +
            "limit :noOfRows,:rowNum"
    )
    List<StudentEntity> queryStudentByFilterByPage(int noOfRows, int rowNum, String collegeName, String majorName, String gradeName, String className, String sex, String keyWord);

    /*分页查找学生未有头像的数据*/
    @Query("select * from _student_entity where local_avatar_path = '' and _user_face_img_url <> '' and _user_face_img_url is not null limit :noOfRows,:rowNum")
    List<StudentEntity> queryStudentNoFaceImgByPage(int noOfRows, int rowNum);

    /**
     * 有头像的总数
     * */
    @Query("select count(*) from _student_entity where _user_face_img_url <> '' and _user_face_img_url is not null")
    int queryStudentHasFaceImgCount();

    /**
    * 下载头像的总数
    * */
    @Query("select count(*) from _student_entity where local_avatar_path <> '' and local_avatar_path is not null")
    int queryStudentHasDownFaceImgCount();

    /*
    * 获取最近的学生更新时间
    * */
    @Query("select _update_time from _student_entity order by _update_time desc limit 1")
    String getLastStudentUpdateTime();

    @Query("select distinct  _grade_id, _grade_name from _student_entity where (:collegeId = '' or _college_id=:collegeId) and (:majorId = '' or _major_id=:majorId) order by _grade_name")
    List<GradeQueryInfo> getGradeListByCollegeId(String collegeId, String majorId);

    @Query("select distinct  _class_id, _class_name from _student_entity where (:collegeId = '' or _college_id=:collegeId) and (:majorId = '' or _major_id=:majorId) and (:gradeId = '' or _grade_id=:gradeId) order by _class_name")
    List<ClassQueryInfo> getClassListByCollegeId(String collegeId, String majorId, String gradeId);

    /*
    * 通过_sport_plan_student_1表连表查询 学生
    * */

    @Query("select _student_entity.* from _student_entity " +
            "inner join _sport_plan_student_1 sps on _student_entity._account_id = sps._account_id " +
            "where sps._plan_id = :sceneId and sps._account_id = :accountId limit 1")
    StudentEntity queryStudentBySceneIdAndAccountId(String sceneId,String accountId);

}

