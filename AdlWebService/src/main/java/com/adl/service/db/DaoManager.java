package com.adl.service.db;


import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.DeleteTable;
import androidx.room.RenameColumn;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.AutoMigrationSpec;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.adl.service.common.IDefine;
import com.adl.service.common.InnerPreferences;
import com.adl.service.db.converter.ListStringConverter;
import com.adl.service.entity.CitizenEntity;
import com.adl.service.entity.DictEntity;
import com.adl.service.entity.FileDownInfoEntity;
import com.adl.service.entity.FileUploadInfoEntity;
import com.adl.service.entity.MeetGroupDetailsEntity;
import com.adl.service.entity.MeetGroupTeamEntity;
import com.adl.service.entity.SceneEntity;
import com.adl.service.entity.SceneSportEntity;
import com.adl.service.entity.SportCompetitionEntity;
import com.adl.service.entity.SportMeetingEntity;
import com.adl.service.entity.SportPlanEntity;
import com.adl.service.entity.SportPlanStudentEntity;
import com.adl.service.entity.SportRecordEntity;
import com.adl.service.entity.SportRecordLocalEntity;
import com.adl.service.entity.StandardConfigEntity;
import com.adl.service.entity.StudentEntity;
import com.adl.service.entity.TeacherEntity;
import com.adl.service.entity.TeacherSportLocalEntity;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 数据库DAO 管理
 */
@Database(entities = {StudentEntity.class, TeacherEntity.class, CitizenEntity.class,
        FileDownInfoEntity.class, FileUploadInfoEntity.class,
        SportPlanEntity.class, SportPlanStudentEntity.class, StandardConfigEntity.class,
        SceneEntity.class, SceneSportEntity.class,
        SportMeetingEntity.class, MeetGroupTeamEntity.class, MeetGroupDetailsEntity.class,
        SportCompetitionEntity.class,
        SportRecordLocalEntity.class, SportRecordEntity.class,
        DictEntity.class, TeacherSportLocalEntity.class
},
        version = 24,
        autoMigrations = {
                @AutoMigration(
                        from = 1, to = 2, spec = DaoManager.AutoMigration1to2.class
                ),
                @AutoMigration(from = 2, to = 3),
                @AutoMigration(from = 3, to = 4),
                @AutoMigration(from = 4, to = 5, spec = DaoManager.AutoMigration4to5.class),
                @AutoMigration(from = 5, to = 6, spec = DaoManager.AutoMigration5to6.class),
                @AutoMigration(from = 6, to = 7),
                @AutoMigration(from = 7, to = 8),
                @AutoMigration(from = 8, to = 9, spec = DaoManager.AutoMigration8to9.class),
                @AutoMigration(from = 9, to = 10),
                @AutoMigration(from = 10, to = 11),
                @AutoMigration(from = 11, to = 12),
                @AutoMigration(from = 12, to = 13),
                @AutoMigration(from = 13, to = 14),
                @AutoMigration(from = 14, to = 15),
                @AutoMigration(from = 15, to = 16),
                @AutoMigration(from = 16, to = 17),
                @AutoMigration(from = 17, to = 18),
                @AutoMigration(from = 18, to = 19, spec = DaoManager.AutoMigration18to19.class),
                @AutoMigration(from = 19, to = 20),
                @AutoMigration(from = 20, to = 21),
                @AutoMigration(from = 21, to = 22),
                @AutoMigration(from = 22, to = 23),
                @AutoMigration(from = 23, to = 24),
        }
)
@TypeConverters(ListStringConverter.class)
public abstract class DaoManager extends RoomDatabase {

    public abstract StudentDao getStudentDao();

    public abstract TeacherDao getTeacherDao();

    public abstract CitizenDao getCitizenDao();

    public abstract FileDownDao getFileDownDao();

    public abstract FileUploadDao getFileUploadDao();

    public abstract PlanDao getPlanDao();

    public abstract PlanStudentDao getPlanStudentDao();

    public abstract StandardConfigDao getStandardConfigDao();

    public abstract SceneDao getSceneDao();

    public abstract SceneSportDao getSceneSportDao();

    public abstract RecordLocalDao getRecordLocalDao();

    public abstract RecordUploadDao getRecordUploadDao();

    public abstract DictDao getDictDao();

    public abstract MeetDao getMeetDao();

    public abstract MeetGroupTeamDao getMeetGroupTeamDao();

    public abstract MeetGroupDetailsDao getMeetGroupDetailsDao();

    public abstract TeacherSportDao getTeacherSportDao();


    @DeleteTable(tableName = "_sport_meeting")
    static class AutoMigration1to2 implements AutoMigrationSpec {
    }

    @DeleteTable(tableName = "_student")
    static class AutoMigration4to5 implements AutoMigrationSpec {
        @Override
        public void onPostMigrate(@NonNull SupportSQLiteDatabase db) {
            AutoMigrationSpec.super.onPostMigrate(db);

            InnerPreferences.instance().putLong(IDefine.LastStudentUpdateTime, -1);
            InnerPreferences.instance().putLong(IDefine.LastTeacherUpdateTime, -1);
        }
    }

    @DeleteTable(tableName = "_sport_plan_student")
    static class AutoMigration5to6 implements AutoMigrationSpec {
    }

    static class AutoMigration8to9 implements AutoMigrationSpec {
        @Override
        public void onPostMigrate(@NonNull SupportSQLiteDatabase db) {
            AutoMigrationSpec.super.onPostMigrate(db);
            InnerPreferences.instance().putLong(IDefine.LastTeacherUpdateTime, -1);
        }
    }


    //    @DeleteTable(tableName = "_sport_plan")
//    @DeleteColumn=( tableName = "_scene_sport", columnName = "_app_codes"  )
    @DeleteTable(tableName = "_scene_sport")
    static class AutoMigration14to15 implements AutoMigrationSpec {
//        @Override
//        public void onPostMigrate(@NonNull SupportSQLiteDatabase db) {
//            AutoMigrationSpec.super.onPostMigrate(db);
//            db.beginTransaction();
//            try {
//                migrateUsers(db);
////                migrateOrders(db);
////                updateRelationships(db);
//                db.setTransactionSuccessful();
//            } finally {
//                db.endTransaction();
//            }
//        }
//
//        private void migrateUsers(SupportSQLiteDatabase db) {
//            // 用户数据迁移
////            db.execSQL("DROP TABLE _scene_sport");
//            db.execSQL("DROP TABLE _sport_record_local");
//            db.execSQL("DROP TABLE _student_entity");
//            db.execSQL("DROP TABLE _sport_record_upload");
//            db.execSQL("DROP TABLE _sport_plan");
//        }

    }

    @RenameColumn(
            tableName = "_student_entity",
            fromColumnName = "_user_name_py",
            toColumnName = "_account_name_pinyin"
    )
    @RenameColumn(
            tableName = "_student_entity",
            fromColumnName = "_user_name_py_short",
            toColumnName = "_account_name_first_letter_pinyin"
    )
    static class AutoMigration18to19 implements AutoMigrationSpec {

    }
}
