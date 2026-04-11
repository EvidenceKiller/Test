package com.adl.service.outer;

import android.content.Context;
import android.text.TextUtils;

import com.adl.base.file.AdlFileHelper;
import com.adl.base.log.AdlLogger;
import com.adl.service.common.BaseService;
import com.adl.service.common.FileDownManager;
import com.adl.service.common.FileUtil;
import com.adl.service.common.IDefine;
import com.adl.service.common.InnerLogger;
import com.adl.service.common.InnerPreferences;
import com.adl.service.common.NzConfig;
import com.adl.service.common.NzConfigBusiness;
import com.adl.service.common.NzConfigNode;
import com.adl.service.db.CitizenDao;
import com.adl.service.db.DaoManager;
import com.adl.service.db.DictDao;
import com.adl.service.db.FileDownDao;
import com.adl.service.db.FileUploadDao;
import com.adl.service.db.MeetDao;
import com.adl.service.db.MeetGroupDetailsDao;
import com.adl.service.db.MeetGroupTeamDao;
import com.adl.service.db.PlanDao;
import com.adl.service.db.PlanStudentDao;
import com.adl.service.db.RecordLocalDao;
import com.adl.service.db.RecordUploadDao;
import com.adl.service.db.SceneDao;
import com.adl.service.db.SceneSportDao;
import com.adl.service.db.StandardConfigDao;
import com.adl.service.db.StudentDao;
import com.adl.service.db.TeacherDao;
import com.adl.service.db.TeacherSportDao;
import com.adl.service.db.migration.Migration_17_to_18;
import com.adl.service.upload.OssFileUploadService;
import com.adl.service.web.constants.Platform;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import androidx.room.Room;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 类描述
 */
public final class AdlService {

    private final static String dbName = "_local_service_1";
    private final static int dbVersion = 4;
    private static AdlService _instance;
    private ConfigService mConfig;
    private DaoManager mDaoManager;
    private Context mContext;
    private String orgId;
    private NzConfig mNzConfig;

    private AdlService(Context context) {
        mContext = context;
        mConfig = new ConfigService();

        //  设置默认参数
        setSdcard("zy.sport.cache");
        setHost("http://112.19.167.50:18082/gateway/api");
        setQiNiuHost(IDefine.DefaultHost);
        setQiNiuBucket(IDefine.QiNiuPublicBucket);
        setPlatform(Platform.K12);

        // preferences 存储
        InnerPreferences pf = InnerPreferences.instance();
        pf.init(context);
        int version = pf.readInt("_db_version");

        // 数据库文件, 清空数据库文件
        if (dbVersion > version) {
            File[] dataList = context.getDataDir().listFiles();
            if (dataList != null) {
                for (File dataFile : dataList) {
                    // 数据存储路径
                    if (!"databases".equals(dataFile.getName()))
                        continue;

                    File[] dbFileList = dataFile.listFiles();
                    if (dbFileList == null) continue;

                    for (File dbFile : dbFileList) {
                        if (dbFile.getName().startsWith("_local_service")) {
                            dbFile.delete();
                        }
                    }
                }
            }
        }

        // 数据库
        mDaoManager = Room.databaseBuilder(context, DaoManager.class, dbName)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addMigrations(new Migration_17_to_18())
                .build();
        pf.putInt("_db_version", dbVersion);
    }

    public static AdlService create(Context context) {
        if (_instance == null) {
            _instance = new AdlService(context.getApplicationContext());
        }
        return _instance;
    }

    public static AdlService getService() {
        return _instance;
    }

    public Context getContext() {
        return mContext;
    }

    public AdlService setDebug(boolean debug) {
        mConfig.setDebug(debug);
        return this;
    }

    public AdlService setReadConfig(boolean readConfig) {
        mConfig.setReadConfig(readConfig);
        return this;
    }

    public AdlService setSdcard(String root) {
        mConfig.setSdcardRoot(root);
        return this;
    }

    public AdlService setHost(String host) {
        mConfig.setHost(host);
        return this;
    }

    public AdlService setQiNiuHost(String host) {
        mConfig.setQiNiuHost(host);
        return this;
    }

    public AdlService setOssConfigHost(String host) {
        mConfig.setOssConfigHost(host);
        return this;
    }

    public String getOssConfigHost() {
        return mConfig.getOssConfigHost();
    }

    public String getOssTokenHost() {
        return mConfig.getOssTokenHost();
    }

    public AdlService setOssTokenHost(String host) {
        mConfig.setOssTokenHost(host);
        return this;
    }

    public AdlService setQiNiuBucket(String bucket) {
        mConfig.setQiNiuBucket(bucket);
        return this;
    }

    public AdlService setFacePrefix(String prefix) {
        mConfig.setFacePrefix(prefix);
        return this;
    }

    public AdlService setPlatform(Platform platform) {
        mConfig.setPlatform(platform);
        return this;
    }

    public AdlService setDeviceToken(String token) {
        InnerPreferences.instance().putString(IDefine.SpToken, token);
        return this;
    }

    public AdlService setOrgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getSdcard() {
        return mConfig.getSdcardRoot();
    }

    public String getHost() {
        return mConfig.getHost();
    }

    public String getQiNiuHost() {
        return mConfig.getQiNiuHost();
    }

    public String getQiNiuBucket() {
        return mConfig.getQiNiuBucket();
    }

    public String getFacePrefix() {
        return mConfig.getFacePrefix();
    }

    public String getDeviceToken() {
        return InnerPreferences.instance().readString(IDefine.SpToken);
    }

    // 学生Dao
    public StudentDao getStudentDao() {
        return mDaoManager.getStudentDao();
    }

    public CitizenDao getCitizenDao() {
        return mDaoManager.getCitizenDao();
    }

    // 老师Dao
    public TeacherDao getTeacherDao() {
        return mDaoManager.getTeacherDao();
    }

    // 文件下载
    public FileDownDao getFileDownDao() {
        return mDaoManager.getFileDownDao();
    }

    // 文件上传
    public FileUploadDao getFileUploadDao() {
        return mDaoManager.getFileUploadDao();
    }

    // 计划Dao
    public PlanDao getPlanDao() {
        return mDaoManager.getPlanDao();
    }

    // 计划学生Dao
    public PlanStudentDao getPlanStudentDao() {
        return mDaoManager.getPlanStudentDao();
    }

    // 标准配置Dao
    public StandardConfigDao getStandardConfigDao() {
        return mDaoManager.getStandardConfigDao();
    }

    // 运动Dao
    public SceneSportDao getSceneSportDao() {
        return mDaoManager.getSceneSportDao();
    }

    // 运动本地记录Dao
    public RecordLocalDao getRecordLocalDao() {
        return mDaoManager.getRecordLocalDao();
    }

    // 运动上传记录Dao
    public RecordUploadDao getRecordUploadDao() {
        return mDaoManager.getRecordUploadDao();
    }

    // 字典Dao
    public DictDao getDictDao() {
        return mDaoManager.getDictDao();
    }

    // 运动会Dao
    public MeetDao getMeetDao() {
        return mDaoManager.getMeetDao();
    }

    // 运动会分组Dao
    public MeetGroupTeamDao getMeetGroupTeamDao() {
        return mDaoManager.getMeetGroupTeamDao();
    }

    // 运动会分组人员Dao
    public MeetGroupDetailsDao getMeetGroupDetailsDao() {
        return mDaoManager.getMeetGroupDetailsDao();
    }

    public TeacherSportDao getTeacherSportDao() {
        return mDaoManager.getTeacherSportDao();
    }

    // 场景Dao
    public SceneDao getSceneDao() {
        return mDaoManager.getSceneDao();
    }

    public void build() {
        // 全局配置对象
        InnerPreferences.instance().putObject(IDefine.SpConfigService, mConfig);
        // 服务统一配置
        BaseService.setConfigService(mConfig);
        // 根目录
        FileUtil.setSportRoot(AdlFileHelper.SportRoot);
        // 日志
        InnerLogger.logger().setDebug(mConfig.isDebug());
        // 人脸管理
        FileDownManager.instance().init(mConfig);

        new Thread(() -> {

            // TODO 读取本地配置, add by wei.zhou, 2026.02.02
            mNzConfig = mConfig.isReadConfig() ? readNzConfig() : null;
            if (mNzConfig != null && mNzConfig.isUseLocalize()) {
                String ec = mNzConfig.getEffectiveConfig();
                d("~~~~~~~~~ 本地化配置 ~~~~~~~~~~");
                NzConfigNode eConfig = null;
                if (ec.equalsIgnoreCase("saasDebug")) {
                    eConfig = mNzConfig.getSaasDebug();
                } else if (ec.equalsIgnoreCase("saasRelease")) {
                    eConfig = mNzConfig.getSaasRelease();
                } else if (ec.equalsIgnoreCase("localDebug")) {
                    eConfig = mNzConfig.getLocalDebug();
                } else if (ec.equalsIgnoreCase("localRelease")) {
                    eConfig = mNzConfig.getLocalRelease();
                }
                if (eConfig == null) {
                    d("错误: 本地配置解析错误!!!");
                } else {
                    NzConfigBusiness business = eConfig.getBusiness();
                    String busHost = business.getBusHost();
                    String busV3Host = business.getBusHostV3();
                    String busType = business.getBusType(); // k12,gx
                    String ossType = business.getOssType(); // qn, minio

                    d("useLocalize:" + mNzConfig.isUseLocalize());
                    d("busHost:" + busHost);
                    d("busV3Host:" + busV3Host);
                    d("busType:" + busType);
                    d("ossType:" + ossType);

                    if ("qn".equalsIgnoreCase(ossType)) {
                        String qnHost = business.getQnHost();
                        String qnBucket = business.getQnBucket();
                        String qnPrefix = business.getQnPrefix();
                        setQiNiuHost(qnHost);
                        setQiNiuBucket(qnBucket);
                        setFacePrefix(qnPrefix);
                    } else {
                        String ossConfigHost = business.getOssConfigHost();
                        String ossTokenHost = business.getOssTokenHost();
                        String ossBucket = business.getOssBucket();
                        d("ossConfigHost:" + ossConfigHost);
                        d("ossTokenHost:" + ossTokenHost);
                        d("ossBucket:" + ossBucket);
                        setOssConfigHost(ossConfigHost);
                        setOssTokenHost(ossTokenHost);
                    }

                    setDebug(business.isEnableDebug());
                    setPlatform(Platform.fromString(busType));
                    setHost(busHost);
                }
                d("~~~~~~~~~ 本地化配置 ~~~~~~~~~~");
            }

            // 如果oss 配置未空, 使用测试环境
            if (TextUtils.isEmpty(getOssConfigHost())) {
                setOssConfigHost("http://172.16.0.206:8082/gateway/api/busi/oss/v1/config");
            }
            if (TextUtils.isEmpty(getOssTokenHost())) {
                setOssTokenHost("http://172.16.0.206:8082/gateway/api/busi/oss/v1/token");
            }

            d("host:" + getHost());
            d("ossConfigHost:" + getOssConfigHost());
            d("ossTokenHost:" + getOssTokenHost());
            d("facePrefix:" + getFacePrefix());
            d("platform:" + Platform.toString(mConfig.getPlatform()));

            String prefix = OssFileUploadService.instance().getPrefix();
            if (!TextUtils.isEmpty(prefix) && TextUtils.isEmpty(AdlService.getService().getFacePrefix())) {
                if (!prefix.endsWith("/")) {
                    prefix = prefix + "/";
                }
                AdlService.getService().setFacePrefix(prefix);
            }
        }).start();

        ////////////// 服务初始化 /////////////
        // 初始化文件上传服务
        //FileUploadService.instance();
    }

    private NzConfig readNzConfig() {
        try {
            File root = AdlFileHelper.createSportRootDir();
            File config = AdlFileHelper.createDir(root, "config");
            File cf = new File(config, "nzConfig.json");
            if (!cf.exists()) {
                InputStream input = mContext.getAssets().open("nzConfig.json");
                FileOutputStream out = new FileOutputStream(cf);
                byte[] buf = new byte[input.available()];
                input.read(buf);
                out.write(buf);
                AdlFileHelper.close(input);
                AdlFileHelper.close(out);
            }
            String json = AdlFileHelper.readStream(new FileInputStream(cf));
            if (!TextUtils.isEmpty(json)) {
                return new Gson().fromJson(json, NzConfig.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void release() {
        UserCaller.instance().release();
        OssFileUploadService.instance().release();
    }

    private void d(String msg) {
        if (mConfig.isDebug()) AdlLogger.d("NzService:" + msg);
    }
}
