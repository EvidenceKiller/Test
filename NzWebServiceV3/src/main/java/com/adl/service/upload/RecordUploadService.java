package com.adl.service.upload;

import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;

import com.adl.service.AdlService;
import com.adl.service.ConfigService;
import com.adl.service.common.BaseService;
import com.adl.service.common.CommonUtil;
import com.adl.service.common.IDefine;
import com.adl.service.common.InnerUtil;
import com.adl.service.common.OssType;
import com.adl.service.db.FileUploadDao;
import com.adl.service.db.RecordLocalDao;
import com.adl.service.db.RecordUploadDao;
import com.adl.service.db.TeacherSportDao;
import com.adl.service.entity.FileUploadInfoEntity;
import com.adl.service.entity.SportRecordEntity;
import com.adl.service.entity.SportRecordLocalEntity;
import com.adl.service.entity.TeacherSportLocalEntity;
import com.adl.service.entity.TempUploadRecord;
import com.adl.service.exception.NzBaseException;
import com.adl.service.exception.NzEmptyDataException;
import com.adl.service.exception.NzNetworkException;
import com.adl.service.exception.NzUnknownException;
import com.adl.service.log.NzLog;
import com.adl.service.utils.DirectBufferMD5;
import com.adl.service.utils.VideoThumbnailUtils;
import com.adl.service.web.request.ReportStudentSportRequest;
import com.adl.service.web.request.ReportTeacherSportRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;
import com.ssp.oss.sdk.model.SyncUploadResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/4
 * Describe   : 运动记录上传
 * 图片路径格式如下：
 * /{业务类型代码}/sport/indicator/images/{运动性质}/{运动类型}/{记录标识码}_{序列码}.png
 * 视频封面路径：
 * /{业务类型代码}/sport/indicator/images/cover/{运动性质}/{运动类型}/{记录标识码}_{序列码}.png
 * 视频路径格式如下：
 * /{业务类型代码}/sport/indicator/videos/{运动性质}/{运动类型}/{记录标识码}_{序列码}.mp4
 * <p>
 * 运动性质代码：PTE-体测 PTR-体锻 PET-体育教学
 * 运动类型：四位
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class RecordUploadService extends BaseService {
    private final String TAG = "RecordUpload";
    private final String errorRecordIdRepeat = "运动成绩上报记录id重复";

    private final long sleepTime = 15 * 1000;
    private static RecordUploadService _instance;

    private List<FileUploadCallback> mCallbacks = new ArrayList<>();
    private final UploadManager uploadManager;

    private final RecordLocalDao recordLocalDao;
    private final RecordUploadDao recordUploadDao;
    private final FileUploadDao fileUploadDao;

    private final TeacherSportDao teacherSportDao;

    private boolean mStart;
    private String mBelongId;
    private String mAppCode;
    private Thread workerThread;

    //  每日上传重试次数
    private final int DAILY_UPLOAD_RETRY_LIMIT = 3;

    //  0-请检查网络 1-上传中 2-上传完毕
    public final MutableLiveData<Integer> statusLD;

    public static RecordUploadService instance() {
        if (_instance == null) {
            _instance = new RecordUploadService();
        }
        return _instance;
    }

    private RecordUploadService() {
        Configuration config = new Configuration.Builder()
                .connectTimeout(90)                 // 链接超时。默认90秒
                .useHttps(false)                    // 是否使用https上传域名
                .useConcurrentResumeUpload(true)    // 使用并发上传，使用并发上传时，除最后一块大小不定外，其余每个块大小固定为4M，
                .concurrentTaskCount(3)             // 并发上传线程数量为3
                .responseTimeout(90)                // 服务器响应超时。默认90秒
                .zone(FixedZone.zone2)              // 设置区域，不指定会自动选择。指定不同区域的上传域名、备用域名、备用IP。
                .build();

        // 重用uploadManager，一般地，只需要创建一个uploadManager对象
        uploadManager = new UploadManager(config);

        // dao
        recordLocalDao = AdlService.getService().getRecordLocalDao();
        recordUploadDao = AdlService.getService().getRecordUploadDao();
        fileUploadDao = AdlService.getService().getFileUploadDao();
        teacherSportDao = AdlService.getService().getTeacherSportDao();

        statusLD = new MutableLiveData<>();
    }

    public void setAppCode(String appCode) {
        this.mAppCode = appCode;
    }

    public void start(String belongId, String appCode) {
        this.mBelongId = belongId;
        this.mAppCode = appCode;
        synchronized (this) {
            if (workerThread != null && workerThread.isAlive()) {
                mStart = true;
                return;
            }
            mStart = true;
        }
        workerThread = new Thread(() -> {

            while (mStart) {
                if (Thread.currentThread().isInterrupted() || !mStart) {
                    postStatus(IDefine.RecordUploadStatusDone);
                    NzLog.d(TAG + " no start");
                    break;
                }
                //  网络未连接
                if (!CommonUtil.isNetworkConnected(AdlService.getService().getContext())) {
                    postStatus(IDefine.RecordUploadStatusNoNetwork);
                    NzLog.d(TAG + " No Network Connected");
                    waitSleep(sleepTime);
                    continue;
                }

                //  未配置参数
                ConfigService configService = getConfigService();

                if (configService == null || (TextUtils.isEmpty(configService.getQiNiuHost())
                        || TextUtils.isEmpty(configService.getOssConfigHost()))) {
                    NzLog.d(TAG + " 配置失败");
                    postStatus(IDefine.RecordUploadStatusDone);
                    waitSleep(sleepTime);
                    continue;
                }
                if (mPauseUploadFlag) {
                    waitSleep(sleepTime);
                    continue;
                }
                try {
                    // 上传老师成绩
                    updateTeacherRecord();

                    //  未上传记录
                    List<SportRecordLocalEntity> sportRecordList = recordLocalDao.queryNotUpload(mBelongId, mAppCode);

                    if (sportRecordList == null || sportRecordList.isEmpty()) {
                        NzLog.d(TAG + " 记录 为空 ");
                        postStatus(IDefine.RecordUploadStatusDone);
                        waitSleep(sleepTime);
                        continue;
                    }
                    NzLog.d(TAG + " 记录size = " + sportRecordList.size());

                    postStatus(IDefine.RecordUploadStatusDoing);

                    for (SportRecordLocalEntity sportRecord : sportRecordList) {
                        TempUploadRecord tempUploadRecord = getTempUploadRecord(sportRecord);
                        //  图片/视频状态:0（未上传）、1（已上传）
                        if (sportRecord.getUploadFileStatus() == IDefine.RecordUploadFileNew) {
                            boolean fileSuccess = uploadFile(configService, tempUploadRecord.getFilePath());
                            if (fileSuccess) {
                                sportRecord.setUploadFileStatus(IDefine.RecordUploadFileDone);
                                updateData(sportRecord, tempUploadRecord);
                            } else {
                                NzLog.d(TAG + " 视频上传失败");
                                sportRecord.setUploadErrorMsg("视频上传失败");
                                recordLocalDao.update(sportRecord);
                            }
                        } else {
                            updateData(sportRecord, tempUploadRecord);
                        }
                    }
                    postStatus(IDefine.RecordUploadStatusDone);
                    waitSleep(sleepTime);
                } catch (Exception e) {
                    e.printStackTrace();
                    NzLog.d(TAG + "上传失败" + e.getMessage().toString());
                    postStatus(IDefine.RecordUploadStatusDoing);
                }
                if (Thread.currentThread().isInterrupted() || !mStart) {
                    postStatus(IDefine.RecordUploadStatusDone);
                    break;
                }
            }
            synchronized (RecordUploadService.this) {
                workerThread = null;
            }
        }, "RecordUploadService-Worker");
        workerThread.start();
    }

    private void updateData(SportRecordLocalEntity sportRecord, TempUploadRecord tempUploadRecord) {
        //  业务数据状态:0（未上传）、1（已上传）、2（上传失败，后续不再上传）
        int uploadDataStatus = sportRecord.getUploadDataStatus();
        //  修改状态:0（未修改）、1（修改）
        int uploadModifyStatus = sportRecord.getUploadModifyStatus();

        //  上传日期
        String uploadDate = sportRecord.getUploadDate();
        //  上传失败次数
        int uploadDataFailNum = sportRecord.getUploadDataFailNum();

        if (TextUtils.isEmpty(uploadDate)) {
            sportRecord.setTodayUploadDate();
        }

        //  对于失败的数据重传,每天失败三次后不再重传
        if (sportRecord.isTodayUploadDate() && uploadDataFailNum >= DAILY_UPLOAD_RETRY_LIMIT) {
            NzLog.d(TAG + " 记录 " + sportRecord.getId() + ": 达到今日重试上限，明日自动恢复");
            if (uploadDataFailNum == DAILY_UPLOAD_RETRY_LIMIT) {
                sportRecord.addUploadDataFailNum();
                String uploadErrorMsg = sportRecord.getUploadErrorMsg();
                sportRecord.setUploadErrorMsg(uploadErrorMsg + "（达到今日重试上限，明日自动恢复）");
                recordLocalDao.update(sportRecord);
            }
            return;
        }

        //  不是当天，重置上传日期和上传失败次数
        if (!sportRecord.isTodayUploadDate()) {
            sportRecord.setTodayUploadDate();
            sportRecord.restUploadDataFailNum();
        }

        // TODO 需重新优化
        //  注：上传过程中，可能修改成绩，所以需要上传过后再修改成绩
        if (uploadDataStatus == IDefine.RecordUploadDataNew) {
            //  未上传
            try {
                boolean requestResult = uploadRecord(tempUploadRecord.getSportUploadRecord());
                if (requestResult) {
                    //  上传成功
                    sportRecord.setUploadErrorMsg("");
                    sportRecord.setUploadDataStatus(IDefine.RecordUploadDataDone);
                    recordUploadDao.insert(tempUploadRecord.getSportUploadRecord());
                }
            } catch (NzNetworkException e) {
                if (e.getHttpCode() == 500) {
                    //  {"code":500,"msg":"运动成绩上报记录id重复"}
                    if (errorRecordIdRepeat.equals(e.getMessage())) {
                        //  运动成绩上报记录id重复 -> 处理为已上传
                        sportRecord.setUploadErrorMsg("");
                        sportRecord.setUploadDataStatus(IDefine.RecordUploadDataDone);
                        recordUploadDao.insert(tempUploadRecord.getSportUploadRecord());
                    } else {
                        //  运动配置问题导致数据不合法
                        //  上传失败次数+1
                        sportRecord.setUploadErrorMsg(e.getMessage());
                        sportRecord.addUploadDataFailNum();
                    }
                } else if (e.getHttpCode() > 50000) {
                    //  脏数据(用户数据被删除或者其他目前数据错误),对于返回的3000,本地数据可以删除这条记录
                    sportRecord.setUploadErrorMsg(e.getMessage());
                    sportRecord.setUploadDataStatus(IDefine.RecordUploadDataFail);
                    sportRecord.setUploadModifyStatus(IDefine.RecordUploadModifyNot);
                }
            } catch (NzBaseException e) {
                // TODO
            }
        } else if (uploadDataStatus == IDefine.RecordUploadDataDone) {
            //  已上传，且修改成绩
            if (uploadModifyStatus == IDefine.RecordUploadModifyExist) {
                try {
                    boolean requestResult = updateRecord(tempUploadRecord.getSportUploadRecord());
                    if (requestResult) {
                        //  修改成功
                        sportRecord.setUploadErrorMsg("");
                        sportRecord.setUploadModifyStatus(IDefine.RecordUploadModifyNot);
                        recordUploadDao.insert(tempUploadRecord.getSportUploadRecord());
                    }
                } catch (NzNetworkException e) {
                    if (e.getHttpCode() == 500) {
                        //  运动配置问题导致数据不合法
                        //  上传失败次数+1
                        sportRecord.setUploadErrorMsg(e.getMessage());
                        sportRecord.addUploadDataFailNum();
                    } else if (e.getHttpCode() > 50000) {
                        //  脏数据(用户数据被删除或者其他目前数据错误),对于返回的3000,本地数据可以删除这条记录
                        sportRecord.setUploadErrorMsg(e.getMessage());
                        sportRecord.setUploadDataStatus(IDefine.RecordUploadDataFail);
                        sportRecord.setUploadModifyStatus(IDefine.RecordUploadModifyNot);
                    }
                } catch (NzBaseException e) {
                    // TODO
                }
            }
        } else if (uploadDataStatus == IDefine.RecordUploadDataFail) {
            sportRecord.setUploadModifyStatus(IDefine.RecordUploadModifyNot);
        }
        recordLocalDao.update(sportRecord);
    }

    private void updateTeacherRecord() {
        List<TeacherSportLocalEntity> teacherSports = teacherSportDao.getSportRecordByNotUpload(mAppCode);

        if (teacherSports == null || teacherSports.isEmpty()) {
            return;
        }
        try {
            for (TeacherSportLocalEntity teacherSport : teacherSports) {
                updateTeacherRecord(teacherSport);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTeacherRecord(TeacherSportLocalEntity teacherSport) {
        //  业务数据状态:0（未上传）、1（已上传）、2（上传失败，后续不再上传）
        int uploadDataStatus = teacherSport.getUploadDataStatus();
        //  修改状态:0（未修改）、1（修改）
        int uploadModifyStatus = teacherSport.getUploadModifyStatus();

        //  上传日期
        String uploadDate = teacherSport.getUploadDate();
        //  上传失败次数
        int uploadDataFailNum = teacherSport.getUploadDataFailNum();

        if (TextUtils.isEmpty(uploadDate)) {
            teacherSport.setTodayUploadDate();
        }

        //  对于失败的数据重传,每天失败三次后不再重传
        if (teacherSport.isTodayUploadDate() && uploadDataFailNum == DAILY_UPLOAD_RETRY_LIMIT) {
            NzLog.d(TAG + " 记录 " + teacherSport.getAccountName() + ": 今日上传失败三次，不再重传");
            return;
        }

        //  不是当天，重置上传日期和上传失败次数
        if (!teacherSport.isTodayUploadDate()) {
            teacherSport.setTodayUploadDate();
            teacherSport.restUploadDataFailNum();
        }

        teacherSport.setOriginalSportResult(teacherSport.getSportResult());

        // TODO
        //  注：上传过程中，可能修改成绩，所以需要上传过后再修改成绩
        if (uploadDataStatus == IDefine.RecordUploadDataNew) {
            //  未上传
            ReportTeacherSportRequest request = ReportTeacherSportRequest.builder(teacherSport.getAccountId(), teacherSport.getAppCode(), teacherSport.getAppName(), 0, Long.parseLong(teacherSport.getSportCount()), teacherSport.getSportStartTime(), teacherSport.getSportEndTime(), teacherSport.getSportResult(), teacherSport.getSportSceneCode(), teacherSport.getSportSkuId(), "", Long.parseLong(teacherSport.getSportTime()))
                    .build();
            try {
                boolean result = AdlService.getService().getSportInfoCaller().reportTeacherSportSync(request);
                if (result) {
                    //  上传成功
                    teacherSport.setUploadDataStatus(IDefine.RecordUploadDataDone);
                    NzLog.d(teacherSport.getAccountName() + "教师记录已上传");
                }
            } catch (NzNetworkException e) {
                if (e.getHttpCode() == 500) {
                    //  运动配置问题导致数据不合法
                    //  上传失败次数+1
                    teacherSport.addUploadDataFailNum();
                } else if (e.getHttpCode() > 50000) {
                    //  脏数据(用户数据被删除或者其他目前数据错误),对于返回的3000,本地数据可以删除这条记录
                    teacherSport.setUploadDataStatus(IDefine.RecordUploadDataFail);
                    teacherSport.setUploadModifyStatus(IDefine.RecordUploadModifyNot);
                }
            } catch (NzBaseException e) {
                // TODO
            }
        } else if (uploadDataStatus == IDefine.RecordUploadDataDone) {
            //  已上传，且修改成绩
            if (uploadModifyStatus == IDefine.RecordUploadModifyExist) {
                ReportTeacherSportRequest request = ReportTeacherSportRequest.builder(teacherSport.getAccountId(), teacherSport.getAppCode(), teacherSport.getAppName(), 0, Long.parseLong(teacherSport.getSportCount()), teacherSport.getSportStartTime(), teacherSport.getSportEndTime(), teacherSport.getSportResult(), teacherSport.getSportSceneCode(), teacherSport.getSportSkuId(), "", Long.parseLong(teacherSport.getSportTime()))
                        .build();
                try {
                    boolean result = AdlService.getService().getSportInfoCaller().reportTeacherSportSync(request);
                    if (result) {
                        //  上传成功
                        teacherSport.setUploadModifyStatus(IDefine.RecordUploadModifyNot);
                    }
                } catch (NzNetworkException e) {
                    if (e.getHttpCode() == 500) {
                        //  运动配置问题导致数据不合法
                        //  上传失败次数+1
                        teacherSport.addUploadDataFailNum();
                    } else if (e.getHttpCode() > 50000) {
                        //  脏数据(用户数据被删除或者其他目前数据错误),对于返回的3000,本地数据可以删除这条记录
                        teacherSport.setUploadDataStatus(IDefine.RecordUploadDataFail);
                        teacherSport.setUploadModifyStatus(IDefine.RecordUploadModifyNot);
                    }
                } catch (NzBaseException e) {
                    // TODO
                }
            }
        } else if (uploadDataStatus == IDefine.RecordUploadDataFail) {
            teacherSport.setUploadModifyStatus(IDefine.RecordUploadModifyNot);
        }
        teacherSportDao.updateTeacherSport(teacherSport);
    }

    //  业务类型代码
    private String businessType;
    //  运动性质
    private String sportNature;
    //  运动类型
    private String sportType;
    //  记录标识码
    private String recordId;
    //  序列码
    private int videoOrder = 0;
    private int imageOrder = 0;
    //暂停上传
    private boolean mPauseUploadFlag = false;

    public void setPauseFlag(boolean mPauseUploadFlag) {
        this.mPauseUploadFlag = mPauseUploadFlag;
    }

    //  获取记录的文件地址和上传实体
    private TempUploadRecord getTempUploadRecord(SportRecordLocalEntity sportRecord) {
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(sportRecord));

        //  业务类型代码
        businessType = sportRecord.getBusinessType();
        if (TextUtils.isEmpty(businessType)) {
            businessType = IDefine.BusinessTypePTE;
        }
        //  运动性质
        sportNature = sportRecord.getSportNature();
        if (TextUtils.isEmpty(sportNature)) {
            sportNature = IDefine.SportNatureTest;
        }
        //  运动类型
        sportType = sportRecord.getSportSkuId();
        if (sportType.length() > 4) {
            sportType = sportType.substring(0, 4);
        }
        //  记录标识码
        recordId = sportRecord.getId();
        //  序列码
        videoOrder = 0;
        imageOrder = 0;

        List<Pair<String, String>> pathList = new ArrayList<>();

        if (jsonObject.containsKey("sportImages")) {
            extractMediaPathsFromIndicatorsValue(jsonObject.get("sportImages"), pathList);
        }

        if (jsonObject.containsKey("sportVideos")) {
            extractMediaPathsFromIndicatorsValue(jsonObject.get("sportVideos"), pathList);
        }

        if (jsonObject.containsKey("sportIndicators")) {
            extractMediaPathsFromIndicatorsValue(jsonObject.get("sportIndicators"), pathList);
        }

        return new TempUploadRecord(pathList, JSONObject.toJavaObject(jsonObject, SportRecordEntity.class));
    }

    // 辅助方法：从 indicatorsValue 中提取路径
    private void extractMediaPathsFromIndicatorsValue(Object obj, List<Pair<String, String>> pathList) {
        if (obj instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) obj;
            for (String key : jsonObject.keySet()) {
                Object value = jsonObject.get(key);
                if (value instanceof String) {
                    String path = (String) value;
                    String filePath = "";
                    if (key.equals("videoCover")) {
                        filePath = path.replace(".mp4", ".jpg");
                    } else {
                        filePath = path;
                    }
                    //  相同文件路径赋值相同远端路径
                    String finalFilePath = filePath;
                    Optional<Pair<String, String>> firstMatch = pathList.stream()
                            .filter(pair -> pair.second.equals(finalFilePath))
                            .findFirst();
                    String fileMd5 = "";
                    try {
                        fileMd5 = DirectBufferMD5.getMD5Optimized(path);
                    } catch (Exception e) {
                        fileMd5 = InnerUtil.md5(path);
                    } finally {
                        if (TextUtils.isEmpty(fileMd5)) {
                            fileMd5 = InnerUtil.md5(path);
                        }
                    }

                    if (firstMatch.isPresent()) {
                        String remoteName = firstMatch.get().first;
                        pathList.add(Pair.create(remoteName, path));
                        jsonObject.put(key, buildUploadUrl(remoteName));
                        continue;
                    }

                    if (path.endsWith(".jpg") || path.endsWith(".png")) {
                        String remoteFileName = "videoCover".equals(key) ?
                                buildImageCover(businessType, sportNature, sportType, recordId, imageOrder, fileMd5) :
                                buildImage(businessType, sportNature, sportType, recordId, imageOrder, fileMd5);

                        pathList.add(Pair.create(remoteFileName, path));
                        imageOrder++;

                        jsonObject.put(key, buildUploadUrl(remoteFileName));
                    } else if (path.endsWith(".mp4")) {
                        if ("videoCover".equals(key) && path.endsWith(".mp4")) {
                            String remoteCoverFileName = buildImageCover(businessType, sportNature, sportType, recordId, imageOrder, fileMd5);
                            // 文件存在，则不处理
                            if (!TextUtils.isEmpty(finalFilePath) && !(new File(finalFilePath).exists())) {
                                VideoThumbnailUtils.saveVideoThumbnailToInternal(path, finalFilePath, 100);
                            }
                            pathList.add(Pair.create(remoteCoverFileName, finalFilePath));
                            videoOrder++;
                            jsonObject.put(key, buildUploadUrl(remoteCoverFileName));
                        } else {
                            String remoteVideoFileName = buildVideo(businessType, sportNature, sportType, recordId, videoOrder, fileMd5);
                            pathList.add(Pair.create(remoteVideoFileName, path));
                            videoOrder++;
                            jsonObject.put(key, buildUploadUrl(remoteVideoFileName));
                        }
                    }
                } else {
                    extractMediaPathsFromIndicatorsValue(value, pathList);
                }
            }
        } else if (obj instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) obj;
            for (int i = 0; i < jsonArray.size(); i++) {
                Object value = jsonArray.get(i);
                if (value instanceof String) {
                    String path = (String) value;

                    //  相同文件路径赋值相同远端路径
                    Optional<Pair<String, String>> firstMatch = pathList.stream()
                            .filter(pair -> pair.second.equals(path))
                            .findFirst();
                    if (firstMatch.isPresent()) {
                        String remoteName = firstMatch.get().first;
                        pathList.add(Pair.create(remoteName, path));
                        jsonArray.set(i, buildUploadUrl(remoteName));
                        continue;
                    }
                    String fileMd5 = "";
                    try {
                        fileMd5 = DirectBufferMD5.getMD5Optimized(path);
                    } catch (Exception e) {
                        fileMd5 = InnerUtil.md5(path);
                    } finally {
                        if (TextUtils.isEmpty(fileMd5)) {
                            fileMd5 = InnerUtil.md5(path);
                        }
                    }
                    if (path.endsWith(".jpg") || path.endsWith(".png")) {
                        String remoteFileName = buildImage(businessType, sportNature, sportType, recordId, imageOrder, fileMd5);
                        pathList.add(Pair.create(remoteFileName, path));
                        imageOrder++;

                        jsonArray.set(i, buildUploadUrl(remoteFileName));
                    } else if (path.endsWith(".mp4")) {
                        String remoteVideoFileName = buildVideo(businessType, sportNature, sportType, recordId, videoOrder, fileMd5);

                        pathList.add(Pair.create(remoteVideoFileName, path));
                        videoOrder++;

                        jsonArray.set(i, buildUploadUrl(remoteVideoFileName));
                    }
                } else {
                    extractMediaPathsFromIndicatorsValue(jsonArray.get(i), pathList);
                }
            }
        }
    }

    //  上传文件
    private boolean uploadFile(ConfigService configService, List<Pair<String, String>> recordFilePath) {
        boolean uploadStatus = true;
        for (Pair<String, String> pair : recordFilePath) {
            String remoteFileName = pair.first;
            String localPath = pair.second;
            /*
             * 因为minio 本地部署，上传记录时的文件地址必须带buketName,但是在上传文件时又不需要buketName
             * */
            String newRemoteFileName = remoteFileName.replace(createBuketName(), "");
            FileUploadInfoEntity entity = fileUploadDao.queryByRemoteFileName(remoteFileName);
            if (entity == null) {
                entity = new FileUploadInfoEntity();
                entity.setOrgId(mBelongId);
                entity.setRemoteFileName(newRemoteFileName);
                entity.setLocalPath(localPath);
                entity.setUploadStatus(FileUploadStatusNew);
                entity.setCreateTime(System.currentTimeMillis());
                entity.setRemoteBucket(configService.getQiNiuBucket());
            }

            //  未上传
            if (entity.getUploadStatus() == FileUploadStatusNew) {
                uploadStatus &= uploadOss(entity);
            }
        }

        //  上传成功删除在文件上传数据库删除
        if (uploadStatus) {
            List<String> list = recordFilePath.stream().map(m -> m.first).collect(Collectors.toList());
            fileUploadDao.clearByRemoteFileName(list);
        }
        return uploadStatus;
    }

    //  上传七牛云
    private boolean uploadQiNiuYun(FileUploadInfoEntity entity) {
        File file = new File(entity.getLocalPath());
        boolean isSuccess;
        if (file.exists()) {

            String remoteFileName = entity.getRemoteFileName();
            String bucket = !TextUtils.isEmpty(entity.getRemoteBucket()) ? entity.getRemoteBucket() : IDefine.QiNiuPublicBucket;
            String token = getQiNiuToken(bucket);

            String url = syncUploadFileToQiNiuYun(file, remoteFileName, token);
            if (url != null) {
                entity.setRemoteUrl(url);
                entity.setUploadStatus(FileUploadStatusDone);
                entity.setRemark("上传完成");
                NzLog.d(TAG + " 文件上传成功 QiNiuYun: " + url);
                isSuccess = true;
            } else {
                entity.setUploadStatus(FileUploadStatusNew);
                entity.setRemark("上传失败，等待下次上传");
                isSuccess = false;
            }
        } else {
            entity.setUploadStatus(FileUploadStatusError);
            entity.setRemark("文件路径不存在");
            isSuccess = true;
        }

        fileUploadDao.insert(entity);
        return isSuccess;
    }

    private boolean uploadOss(FileUploadInfoEntity entity) {
        File file = new File(entity.getLocalPath());
        boolean isSuccess;
        if (file.exists()) {
            String remoteFileName = entity.getRemoteFileName();
            File remoteFile = new File(remoteFileName);
            SyncUploadResult result = OssFileUploadService.instance().syncUploadFile(file.getAbsolutePath(), remoteFile.getParent(), remoteFile.getName());
            String url = result.getFileUrl();
            if (url != null) {
                entity.setRemoteUrl(url);
                entity.setUploadStatus(FileUploadStatusDone);
                entity.setRemark("上传完成");
                NzLog.d(TAG + " 文件上传成功 Oss: " + url);
                isSuccess = true;
            } else {
                entity.setUploadStatus(FileUploadStatusNew);
                entity.setRemark("上传失败，等待下次上传");
                NzLog.d(TAG + " 文件上传失败 Oss:");
                isSuccess = false;
            }
        } else {
            entity.setUploadStatus(FileUploadStatusError);
            entity.setRemark("文件路径不存在");
            NzLog.d(TAG + " 文件上传失败 Oss: 文件不存在");
            isSuccess = true;
        }

        fileUploadDao.insert(entity);
        return isSuccess;
    }

    //  上传记录
    private boolean uploadRecord(SportRecordEntity sportRecord) throws NzBaseException {
        /// 上传之前的数据处理
        //  游客
        if (IDefine.AccountTypeVisitor.equals(sportRecord.getAccountType())) {
            sportRecord.setAccountId("");
        }

//        return SportRecordService.uploadRecord(sportRecord);
        ReportStudentSportRequest request = ReportStudentSportRequest.builder(sportRecord.getAccountId(), sportRecord.getAppCode(), sportRecord.getInvalidFlag(), Long.parseLong(sportRecord.getSportCount()), sportRecord.getSportStartTime(), sportRecord.getSportEndTime(), sportRecord.getSportResult(), sportRecord.getSportSceneCode(), sportRecord.getSportSkuId(), sportRecord.getSportSkuSubType(), Long.parseLong(sportRecord.getSportTime()))
                .build();
        return AdlService.getService().getSportInfoCaller().reportStudentSportSync(request);
    }

    //  修改记录
    private boolean updateRecord(SportRecordEntity sportRecord) throws NzBaseException {
        /// 上传之前的数据处理
        //  游客
        if (IDefine.AccountTypeVisitor.equals(sportRecord.getAccountType())) {
            sportRecord.setAccountId("");
        }

//        return SportRecordService.updateRecord(sportRecord);
        ReportStudentSportRequest request = ReportStudentSportRequest.builder(sportRecord.getAccountId(), sportRecord.getAppCode(), sportRecord.getInvalidFlag(), Long.parseLong(sportRecord.getSportCount()), sportRecord.getSportStartTime(), sportRecord.getSportEndTime(), sportRecord.getSportResult(), sportRecord.getSportSceneCode(), sportRecord.getSportSkuId(), sportRecord.getSportSkuSubType(), Long.parseLong(sportRecord.getSportTime()))
                .build();
        return AdlService.getService().getSportInfoCaller().updateStudentSportSync(request);
    }


    private String buildUploadUrl(String url) {
        // 上报不需要添加/
        return /*"/" + */url;
    }

    // /{业务类型代码}/sport/indicator/images/{运动性质}/{运动类型}/{记录标识码}_{序列码}.png
    private String buildImage(String businessType, String sportNature, String sportType, String recordId, int order, String fileMd5) {
        return createBuketName() + businessType + "/sport/indicator/images/" + sportNature + "/" + sportType + "/" + fileMd5 + ".png";
    }

    // /{业务类型代码}/sport/indicator/images/cover/{运动性质}/{运动类型}/{记录标识码}_{序列码}.png
    private String buildImageCover(String businessType, String sportNature, String sportType, String recordId, int order, String fileMd5) {
        return createBuketName() + businessType + "/sport/indicator/images/cover/" + sportNature + "/" + sportType + "/" + fileMd5 + ".png";
    }

    // /{业务类型代码}/sport/indicator/videos/{运动性质}/{运动类型}/{记录标识码}_{序列码}.mp4
    private String buildVideo(String businessType, String sportNature, String sportType, String recordId, int order, String fileMd5) {
        return createBuketName() + businessType + "/sport/indicator/videos/" + sportNature + "/" + sportType + "/" + fileMd5 + ".mp4";
    }

    /*
     * 因为minio 本地部署，上传记录时的文件地址必须带buketName,但是在上传文件时又不需要buketName
     * */
    private String createBuketName() {
        try {
            OssFileUploadService oss = OssFileUploadService.instance();
            String currentOssType = oss.getCurrentOssType();
            String buketName = "";
            if (TextUtils.equals(currentOssType, OssType.MINIO.value())) {
                buketName = oss.getOssConfigList().findConfig(currentOssType).getBucket();
                return buketName + "/";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private String syncUploadFileToQiNiuYun(File file, String name, String token) {
        try {
            NzLog.e(TAG + " 七牛上传: " + file.getAbsolutePath() + "; name: " + name + "; token: " + token);
            ResponseInfo info = uploadManager.syncPut(file, name, token, null);
            if (info.isOK()) {
                //  绝对路径
                String facePrefix = getConfigService().getFacePrefix();
                return facePrefix + info.response.getString("key");
            } else {
                NzLog.e(TAG + " 七牛上传: " + info.error);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //  发送状态
    private void postStatus(int code) {
        statusLD.postValue(code);
    }

    public void addCallback(FileUploadCallback callback) {
        if (callback != null && !mCallbacks.contains(callback)) {
            mCallbacks.add(callback);
        }
    }

    public void removeCallback(FileUploadCallback callback) {
        if (callback != null) {
            mCallbacks.remove(callback);
        }
    }

    private void startCallback(File file) {
        for (FileUploadCallback callback : mCallbacks) {
            callback.onStart(file);
        }
    }

    private void completeCallback(File file, String url) {
        for (FileUploadCallback callback : mCallbacks) {
            callback.onComplete(file, url);
        }
    }

    public void stop() {
        mStart = false;
        synchronized (this) {
            if (workerThread != null) {
                workerThread.interrupt();
            }
        }
    }

}
