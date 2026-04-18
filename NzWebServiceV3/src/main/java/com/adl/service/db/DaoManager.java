package com.adl.service.db;


import com.adl.service.db.converter.ListIntegerConverter;
import com.adl.service.db.converter.ListStringConverter;
import com.adl.service.db.entity.CompetitionEntity;
import com.adl.service.db.entity.DictEntity;
import com.adl.service.db.entity.FileDownInfoEntity;
import com.adl.service.db.entity.FileUploadInfoEntity;
import com.adl.service.db.entity.LoginInfoEntity;
import com.adl.service.db.entity.PlanEntity;
import com.adl.service.db.entity.PlanStudentEntity;
import com.adl.service.db.entity.SceneEntity;
import com.adl.service.db.entity.SportMeetEntity;
import com.adl.service.db.entity.SportMeetGroupDetailEntity;
import com.adl.service.db.entity.SportMeetGroupTeamEntity;
import com.adl.service.db.entity.SportSkuEntity;
import com.adl.service.db.entity.StandardConfigEntity;
import com.adl.service.db.entity.StudentEntity;
import com.adl.service.db.entity.TeacherEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 数据库DAO 管理
 */
@Database(entities = {StudentEntity.class, TeacherEntity.class,
        FileDownInfoEntity.class, FileUploadInfoEntity.class,
        PlanEntity.class, PlanStudentEntity.class, StandardConfigEntity.class,
        SceneEntity.class, SportSkuEntity.class,
        SportMeetEntity.class, SportMeetGroupTeamEntity.class, SportMeetGroupDetailEntity.class,
        CompetitionEntity.class,
        DictEntity.class, LoginInfoEntity.class
},
        version = 1,
        autoMigrations = {}
)
@TypeConverters(value = {ListStringConverter.class, ListIntegerConverter.class})
public abstract class DaoManager extends RoomDatabase {

    public abstract StudentDao getStudentDao();

    public abstract TeacherDao getTeacherDao();

    public abstract CompetitionDao getCompetitionDao();

    public abstract FileDownDao getFileDownDao();

    public abstract FileUploadDao getFileUploadDao();

    public abstract PlanDao getPlanDao();

    public abstract PlanStudentDao getPlanStudentDao();

    public abstract StandardConfigDao getStandardConfigDao();

    public abstract SceneDao getSceneDao();

    public abstract SceneSportDao getSceneSportDao();

    public abstract DictDao getDictDao();

    public abstract SportMeetDao getSportMeetDao();

    public abstract SportMeetGroupTeamDao getSportMeetGroupTeamDao();

    public abstract SportMeetGroupDetailDao getSportMeetGroupDetailDao();

    public abstract LoginInfoDao getLoginInfoDao();
}
