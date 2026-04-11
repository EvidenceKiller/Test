package com.adl.service.common;

import android.text.TextUtils;

import com.adl.service.AdlService;
import com.adl.service.ConfigService;
import com.adl.service.db.FileDownDao;
import com.adl.service.entity.FileDownInfoEntity;
import com.adl.service.entity.StudentEntity;
import com.adl.service.entity.TeacherEntity;
import com.adl.service.internal.RetrofitClient;
import com.adl.service.log.NzLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 文件下载管理
 * 1. 学生人脸图片下载
 * 2. 视频下载
 * 3. 图片下载
 */
public final class FileDownManager {

    private static final String studentFaceDirName = "studentFace";
    private static final String teacherFaceDirName = "teacherFace";
    private static FileDownManager _instance;
    private ConfigService mConfig;
    private ExecutorService mExecutors;

    private FileDownManager() {
        mExecutors = Executors.newFixedThreadPool(2);
    }

    public static FileDownManager instance() {
        if (_instance == null) {
            _instance = new FileDownManager();
        }
        return _instance;
    }

    public void init(ConfigService config) {
        mConfig = config;
    }

    public void setMultiThread(int threadNum) {
        mExecutors = Executors.newFixedThreadPool(threadNum);
    }
    // 下载视频
    public void downVideo(List<String> list, FileListDownCallback callback) {
        List<FileDownTask> downTasks = new ArrayList<>();
        for (String url : list) {
            downTasks.add(new FileDownTask(IDefine.FileDownloadTypeVideo, url, ".mp4"));
        }
        downloadTask(FileUtil.createLocalVideo(), downTasks, callback);
    }

    // 下载图片
    public void downImage(List<String> list, FileListDownCallback callback) {
        List<FileDownTask> downTasks = new ArrayList<>();
        for (String url : list) {
            downTasks.add(new FileDownTask(IDefine.FileDownloadTypeImage, url, ".jpg"));
        }
        downloadTask(FileUtil.createLocalImage(), downTasks, callback);
    }

    // 下载文件
    public void downFile(List<String> list, String suffix, FileListDownCallback callback) {
        List<FileDownTask> downTasks = new ArrayList<>();
        for (String url : list) {
            downTasks.add(new FileDownTask(IDefine.FileDownloadTypeFile, url, suffix));
        }
        downloadTask(FileUtil.createLocalFile(), downTasks, callback);
    }

    // 下载学生人脸信息
    public void downStudentFaceImage(List<StudentEntity> list,
                                     FileListDownCallback callback) {

        File dataDir = AdlService.getService().getContext().getDataDir();
        File faceDir = new File(dataDir, studentFaceDirName);
        if (!faceDir.exists()) {
            faceDir.mkdirs();
        }

        List<FileDownTask> downTasks = new ArrayList<>();
        for (StudentEntity stu : list) {
            String userFaceImgUrl = stu.getUserFaceImgUrl();
            if (!TextUtils.isEmpty(userFaceImgUrl)) {
                String url = formatFaceUrl(userFaceImgUrl);
                downTasks.add(new FileDownTask(IDefine.FileDownloadTypeStudentHead, url, getFileSuffix(url), stu.getAccountId()));
            } else {
                downTasks.add(null);
            }
        }

        downloadTask(faceDir, downTasks, callback);
    }

    // 下载教师人脸信息
    public void downTeacherFaceImage(List<TeacherEntity> list,
                                     FileListDownCallback callback) {

        File dataDir = AdlService.getService().getContext().getDataDir();
        File faceDir = new File(dataDir, teacherFaceDirName);
        if (!faceDir.exists()) {
            faceDir.mkdirs();
        }

        List<FileDownTask> downTasks = new ArrayList<>();
        for (TeacherEntity teacher : list) {
            String accountAvatar = teacher.getAccountAvatar();
            if (!TextUtils.isEmpty(accountAvatar)) {
                String url = formatFaceUrl(accountAvatar);
                downTasks.add(new FileDownTask(IDefine.FileDownloadTypeTeacherHead, url, getFileSuffix(url)));
            } else {
                downTasks.add(null);
            }
        }

        downloadTask(faceDir, downTasks, callback);
    }

    // 执行下载
    public void downloadTask(File dir, List<FileDownTask> list, FileListDownCallback listCallback) {
        if (list == null || list.isEmpty() || dir == null) {
            if (listCallback != null) {
                listCallback.onComplete();
            }
            return;
        }

        mExecutors.submit(() -> {
            int total = list.size();
            int downSuccess = 0;
            int downFail = 0;

            if (listCallback != null) {
                listCallback.onStart();
            }

            FileDownDao fileDao = AdlService.getService().getFileDownDao();
            List<FileDownInfoEntity> infoList = fileDao.getAll();

            FileDownSampleCallback callback = new FileDownSampleCallback() {
                @Override
                public void onEnd(File file) {

                }
            };

            for (FileDownTask task : list) {

                if (task == null || TextUtils.isEmpty(task.url)) {
                    downSuccess++;
                    if (listCallback != null) {
                        listCallback.onProgress(total, downSuccess, downFail);
                    }
                    continue;
                }

                String fileName = InnerUtil.md5(task.url);
                String suffix = TextUtils.isEmpty(task.suffix) ? getFileSuffix(task.url) : task.suffix;

                File file = new File(dir, fileName + suffix);
                task.fileName = fileName;
                task.localFile = file;

                FileDownInfoEntity entity = findDownInfo(infoList, fileName);
                if (entity != null && file.exists() && file.length() == entity.getContentLength()) {
                    NzLog.d("文件已经存在: " + file.getAbsolutePath());

                    downSuccess++;
                    if (listCallback != null) {
                        listCallback.onProgress(total, downSuccess, downFail);
                    }
                    // 通知成功
                    if (listCallback != null) {
                        listCallback.onDownSuccess(task);
                    }
                    continue;
                }

                if (downloadCommonFile(task, callback)) {
                    // 入库
                    if (entity != null) {
                        entity.setContentLength(task.contentLength);
                        fileDao.update(entity);
                    } else {
                        entity = new FileDownInfoEntity();
                        entity.setType(task.type);
                        entity.setFileName(fileName);
                        entity.setUrl(task.url);
                        entity.setContentLength(task.contentLength);
                        entity.setLocalPath(file.getAbsolutePath());
                        fileDao.insert(entity);
                    }
                    // 通知成功
                    if (listCallback != null) {
                        listCallback.onDownSuccess(task);
                    }
                    downSuccess++;
                } else {
                    downFail++;
                }

                if (listCallback != null) {
                    listCallback.onProgress(total, downSuccess, downFail);
                }
            }

            if (listCallback != null) {
                listCallback.onComplete();
            }
        });
    }

    // 下载学生人脸信息
    public void downStudentThumbnailFaceImage(Boolean useThumbnail, List<StudentEntity> list,
                                     FileListDownCallback callback) {

        File dataDir = AdlService.getService().getContext().getDataDir();
        File faceDir = new File(dataDir, studentFaceDirName);
        if (!faceDir.exists()) {
            faceDir.mkdirs();
        }

        List<FileDownTask> downTasks = new ArrayList<>();
        for (StudentEntity stu : list) {
            String userFaceImgUrl = useThumbnail ? stu.getThumbnailUserFaceImgUrl() : stu.getUserFaceImgUrl();
            if (!TextUtils.isEmpty(userFaceImgUrl)) {
                String url = formatFaceUrl(userFaceImgUrl);
                downTasks.add(new FileDownTask(IDefine.FileDownloadTypeStudentHead, url, getFileSuffix(url), stu.getAccountId()));
            }
        }

        downloadTaskCheckDB(faceDir, downTasks, callback);
    }

    public void downloadTaskCheckDB(File dir, List<FileDownTask> list, FileListDownCallback listCallback) {
        if (list == null || list.isEmpty() || dir == null) {
            if (listCallback != null) {
                listCallback.onComplete();
            }
            return;
        }

        mExecutors.submit(() -> {
            int total = list.size();
            int downSuccess = 0;
            int downFail = 0;

            if (listCallback != null) {
                listCallback.onStart();
            }

            FileDownDao fileDao = AdlService.getService().getFileDownDao();

            FileDownSampleCallback callback = new FileDownSampleCallback() {
                @Override
                public void onEnd(File file) {

                }
            };

            for (FileDownTask task : list) {

                if (task == null || TextUtils.isEmpty(task.url)) {
                    downSuccess++;
                    if (listCallback != null) {
                        listCallback.onProgress(total, downSuccess, downFail);
                    }
                    continue;
                }

                String fileName = InnerUtil.md5(task.url);
                String suffix = TextUtils.isEmpty(task.suffix) ? getFileSuffix(task.url) : task.suffix;

                File file = new File(dir, fileName + suffix);
                task.fileName = fileName;
                task.localFile = file;

                FileDownInfoEntity entity = queryDownInfo(task.url);
                if (entity != null && file.exists() && file.length() == entity.getContentLength()) {
                    NzLog.d("文件已经存在: " + file.getAbsolutePath());

                    downSuccess++;
                    if (listCallback != null) {
                        listCallback.onProgress(total, downSuccess, downFail);
                    }
                    if (listCallback != null) {
                        listCallback.onDownSuccess(task);
                    }
                    continue;
                }

                if (downloadCommonFile(task, callback)) {
                    // 入库
                    if (entity != null) {
                        entity.setContentLength(task.contentLength);
                        fileDao.update(entity);
                    } else {
                        entity = new FileDownInfoEntity();
                        entity.setType(task.type);
                        entity.setFileName(fileName);
                        entity.setUrl(task.url);
                        entity.setContentLength(task.contentLength);
                        entity.setLocalPath(file.getAbsolutePath());
                        fileDao.insert(entity);
                    }
                    // 通知成功
                    if (listCallback != null) {
                        listCallback.onDownSuccess(task);
                    }
                    downSuccess++;
                } else {
                    downFail++;
                }

                if (listCallback != null) {
                    listCallback.onProgress(total, downSuccess, downFail);
                }
            }

            if (listCallback != null) {
                listCallback.onComplete();
            }
        });
    }

    private String getFileSuffix(String path) {
        if (TextUtils.isEmpty(path)) return "";

        int index = path.lastIndexOf(".");
        return index >= 0 ? path.substring(index) : "";
    }

    private FileDownInfoEntity findDownInfo(List<FileDownInfoEntity> list, String fileName) {
        if (list != null) {
            for (FileDownInfoEntity bean : list) {
                if (fileName.equals(bean.getFileName())) return bean;
            }
        }
        return null;
    }

    // 人脸路径拼接
    public String formatFaceUrl(String url) {
        if (mConfig == null) return url;
        String prefix = mConfig.getFacePrefix();
        if (TextUtils.isEmpty(prefix)) return url;
        if (TextUtils.isEmpty(url)) return prefix;
        boolean pe = prefix.endsWith("/");
        boolean us = url.startsWith("/");
        if (pe && us) {
            return prefix + url.substring(1);
        } else if (!pe && !us) {
            return prefix + "/" + url;
        } else {
            return prefix + url;
        }
    }

    // 查询下载信息
    public FileDownInfoEntity queryDownInfo(String url) {
        if (TextUtils.isEmpty(url)) return null;
        String fileName = InnerUtil.md5(formatFaceUrl(url));
        return AdlService.getService().getFileDownDao().queryInfoByFileName(fileName);
    }

    // 根据Url删除
    public void clearByUrls(List<String> urls) {
        if (urls == null || urls.isEmpty()) return;

        List<String> fileNames = new ArrayList<>();
        for (String url : urls) {
            if (TextUtils.isEmpty(url)) continue;
            fileNames.add(InnerUtil.md5(formatFaceUrl(url)));
        }

        if (fileNames.isEmpty()) return;
        AdlService.getService().getFileDownDao().clearByFileNames(fileNames);
    }

    // 清空保存的人脸文件
    public void clearAllFace() {
        //  清空学生人脸
        clearStudentFace();
        //  清空教师人脸
        clearTeacherFace();
    }

    //  清空学生人脸
    public void clearStudentFace() {
        //  清空数据库
        AdlService.getService().getFileDownDao().clearAllByType(IDefine.FileDownloadTypeStudentHead);
        //  清空文件
        File dataDir = AdlService.getService().getContext().getDataDir();
        //  清空人脸文件
        File studentFaceDir = new File(dataDir, studentFaceDirName);
        clearFileDir(studentFaceDir);
    }

    //  清空教师人脸
    public void clearTeacherFace() {
        //  清空数据库
        AdlService.getService().getFileDownDao().clearAllByType(IDefine.FileDownloadTypeTeacherHead);
        //  清空文件
        File dataDir = AdlService.getService().getContext().getDataDir();
        //  清空人脸文件
        File teacherFaceDir = new File(dataDir, teacherFaceDirName);
        clearFileDir(teacherFaceDir);
    }

    private void clearFileDir(File fileDir) {
        if (!fileDir.exists()) return;

        File[] list = fileDir.listFiles();
        if (list != null) {
            for (File f : list) {
                f.delete();
            }
        }
    }

    public static class FileDownTask {

        public int type;
        // 需要下载的文件内容
        public String url;
        public String suffix;

        public String fileName;
        public File localFile;
        public long contentLength;

        public String userId;


        public FileDownTask(int type, String url, String suffix) {
            this.type = type;
            this.url = url;
            this.suffix = suffix;
        }

        public FileDownTask(int type, String url, String suffix, String userId) {
            this.type = type;
            this.url = url;
            this.suffix = suffix;
            this.userId = userId;
        }
    }

    // TODO 需要修改为使用Retrofit实现，目前先不做
    // 下载公共文件，通过 Get 可直接访问
    public static boolean downloadCommonFile(FileDownManager.FileDownTask bean,
                                             FileDownCallback callback) {
        try {
            String url = bean.url;
            Request request = new Request.Builder().url(url).build();
            // TODO 临时写法，后续需优化
            Response response = RetrofitClient.getInstance("", false).getOkHttpClient().newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                ResponseBody respBody = response.body();
                MediaType mediaType = respBody.contentType();
                if (mediaType == null) return false;

                long length = respBody.contentLength();
                InputStream input = respBody.byteStream();

                File file = bean.localFile;
                bean.contentLength = length;

                // 文件是否存在
                if (file.exists()) {
                    file.delete();
                }

                OutputStream out = new FileOutputStream(file);
                long totalRead = 0;
                int read = -1;
                byte[] buffer = new byte[64 * 1024];
                while ((read = input.read(buffer)) > 0) {
                    out.write(buffer, 0, read);
                    out.flush();
                    totalRead += read;
                    if (callback != null) {
                        callback.onProgress(length, totalRead);
                    }
                }
                InnerUtil.close(out);
                InnerUtil.close(input);
                closeResponseBody(respBody);

                // 是否读完
                if (totalRead != length) {
                    file.delete();
                    if (callback != null) {
                        callback.onError("文件下载失败");
                    }
                    return false;
                }

                NzLog.d("文件下载完成");

                return true;
            } else {
                if (response.body() != null) {
                    String content = response.body().string();
                    callback.onError(content);
                }
            }
        } catch (Exception e) {
            if (callback != null) {
                callback.onError(e.getMessage());
            }
        }

        return false;
    }

    protected static void closeResponseBody(ResponseBody body) {
        try {
            if (body != null) {
                body.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
