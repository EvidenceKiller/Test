package com.adl.service.db;

import android.content.Context;

import com.adl.service.common.InnerPreferences;
import com.adl.service.db.migration.Migration_17_to_18;

import java.io.File;

import androidx.annotation.RestrictTo;
import androidx.room.Room;

/**
 * 封装 {@link DaoManager}（Room 数据库），统一通过本类获取各 DAO。
 * <p>
 * 请通过 {@link com.adl.service.AdlService} 获取实例；勿在宿主 App 中直接调用 {@link #create}。
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class DaoManagerProxy {

    private static final String DB_NAME = "_local_service_1";
    private static final int DB_VERSION = 4;

    private final DaoManager mDaoManager;

    private DaoManagerProxy(Context context) {
        mDaoManager = DaoManagerProxyFactory.buildRoom(context);
    }

    /**
     * 仅供本 library 内部（由 {@link com.adl.service.AdlService} 的私有工厂）初始化使用。
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static DaoManagerProxy create(Context context) {
        return DaoManagerProxyFactory.create(context);
    }

    private static final class DaoManagerProxyFactory {

        private DaoManagerProxyFactory() {}

        static DaoManagerProxy create(Context context) {
            return new DaoManagerProxy(context);
        }

        private static DaoManager buildRoom(Context context) {
            InnerPreferences pf = InnerPreferences.instance();
            int version = pf.readInt("_db_version");

            if (DB_VERSION > version) {
                File[] dataList = context.getDataDir().listFiles();
                if (dataList != null) {
                    for (File dataFile : dataList) {
                        if (!"databases".equals(dataFile.getName())) {
                            continue;
                        }
                        File[] dbFileList = dataFile.listFiles();
                        if (dbFileList == null) {
                            continue;
                        }
                        for (File dbFile : dbFileList) {
                            if (dbFile.getName().startsWith("_local_service")) {
                                dbFile.delete();
                            }
                        }
                    }
                }
            }

            DaoManager dm = Room.databaseBuilder(context, DaoManager.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addMigrations(new Migration_17_to_18())
                    .build();
            pf.putInt("_db_version", DB_VERSION);
            return dm;
        }
    }

    public StudentDao getStudentDao() {
        return mDaoManager.getStudentDao();
    }

    public CitizenDao getCitizenDao() {
        return mDaoManager.getCitizenDao();
    }

    public TeacherDao getTeacherDao() {
        return mDaoManager.getTeacherDao();
    }

    public FileDownDao getFileDownDao() {
        return mDaoManager.getFileDownDao();
    }

    public FileUploadDao getFileUploadDao() {
        return mDaoManager.getFileUploadDao();
    }

    public PlanDao getPlanDao() {
        return mDaoManager.getPlanDao();
    }

    public PlanStudentDao getPlanStudentDao() {
        return mDaoManager.getPlanStudentDao();
    }

    public StandardConfigDao getStandardConfigDao() {
        return mDaoManager.getStandardConfigDao();
    }

    public SceneSportDao getSceneSportDao() {
        return mDaoManager.getSceneSportDao();
    }

    public RecordLocalDao getRecordLocalDao() {
        return mDaoManager.getRecordLocalDao();
    }

    public RecordUploadDao getRecordUploadDao() {
        return mDaoManager.getRecordUploadDao();
    }

    public DictDao getDictDao() {
        return mDaoManager.getDictDao();
    }

    public MeetDao getMeetDao() {
        return mDaoManager.getMeetDao();
    }

    public MeetGroupTeamDao getMeetGroupTeamDao() {
        return mDaoManager.getMeetGroupTeamDao();
    }

    public MeetGroupDetailsDao getMeetGroupDetailsDao() {
        return mDaoManager.getMeetGroupDetailsDao();
    }

    public TeacherSportDao getTeacherSportDao() {
        return mDaoManager.getTeacherSportDao();
    }

    public SceneDao getSceneDao() {
        return mDaoManager.getSceneDao();
    }
}
