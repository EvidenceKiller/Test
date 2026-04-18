package com.adl.service.db;

import android.content.Context;

import com.adl.service.common.InnerPreferences;

import java.io.File;

import androidx.annotation.RestrictTo;
import androidx.room.Room;

/**
 * 封装 {@link DaoManager}（Room 数据库），统一通过本类获取各 DAO。
 * <p>
 * 单例：先由 {@link com.adl.service.AdlService} 通过 {@link #init} 初始化，其它处可用 {@link #getInstance()}。
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class DaoManagerProxy {

    private static final String DB_NAME = "_local_service_v3";
    private static final int DB_VERSION = 4;

    private static volatile DaoManagerProxy sInstance;

    private final DaoManager mDaoManager;

    private DaoManagerProxy(Context context) {
        mDaoManager = DaoManagerProxyFactory.buildRoom(context);
    }

    /**
     * 初始化单例（幂等）。应由 {@link com.adl.service.AdlService} 在 {@link InnerPreferences#init} 之后调用。
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (DaoManagerProxy.class) {
                if (sInstance == null) {
                    Context app = context.getApplicationContext();
                    sInstance = new DaoManagerProxy(app);
                }
            }
        }
    }

    /**
     * 返回已初始化的单例；若尚未 {@link #init}，抛出 {@link IllegalStateException}。
     */
    public static DaoManagerProxy getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("DaoManagerProxy not initialized; call create(Context) first");
        }
        return sInstance;
    }

    private static final class DaoManagerProxyFactory {

        private DaoManagerProxyFactory() {}

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
                    .build();
            pf.putInt("_db_version", DB_VERSION);
            return dm;
        }
    }

    public StudentDao getStudentDao() {
        return mDaoManager.getStudentDao();
    }

    public CompetitionDao getCompetitionDao() {
        return mDaoManager.getCompetitionDao();
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

    public DictDao getDictDao() {
        return mDaoManager.getDictDao();
    }

    public SportMeetDao getSportMeetDao() {
        return mDaoManager.getSportMeetDao();
    }

    public SportMeetGroupTeamDao getSportMeetGroupTeamDao() {
        return mDaoManager.getSportMeetGroupTeamDao();
    }

    public SportMeetGroupDetailDao getSportMeetGroupDetailsDao() {
        return mDaoManager.getSportMeetGroupDetailDao();
    }

    public SceneDao getSceneDao() {
        return mDaoManager.getSceneDao();
    }

    public LoginInfoDao getLoginInfoDao() {
        return mDaoManager.getLoginInfoDao();
    }
}
