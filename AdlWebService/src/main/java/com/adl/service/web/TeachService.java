package com.adl.service.web;

import android.text.TextUtils;

import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.entity.ClassCompetitionEntity;
import com.adl.service.entity.GroupTeachEntity;
import com.adl.service.entity.TeacherResEntity;
import com.adl.service.entity.TempGroupTeachEntity;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

public class TeachService extends BaseHttpService {

    public static List<GroupTeachEntity> getCourseDetailList(String classId, String sportSkuId,String sportItemCode, int times, String date) {
        try {
            d("From Http 获取分组教学成绩");
            Map<String, Object> map = new HashMap<>();
            map.put("classId", classId);
            map.put("sportSkuId", sportSkuId);
            if (!TextUtils.isEmpty(sportItemCode)) {
                map.put("sportItemCode", sportItemCode);
            }
            map.put("times", times);
            map.put("date", date);
            String param = InnerUtil.toJson(map);
            String url = wrapperUrl("/busi/terminal/teach/course/v1/detail/list");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<List<GroupTeachEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static int getCourseTimes(String classId, String sportSkuId, String sportItemCode, String date) {
        try {
            d("From Http 获取分组教学总次数");
            Map<String, Object> map = new HashMap<>();
            map.put("classId", classId);
            map.put("sportSkuId", sportSkuId);
            if (!TextUtils.isEmpty(sportItemCode)) {
                map.put("sportItemCode", sportItemCode);
            }
            map.put("date", date);
            String param = InnerUtil.toJson(map);
            String url = wrapperUrl("/busi/terminal/teach/course/v1/courseTimes");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<Integer>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean upTeachButtonClick(String classId, String date) {
        try {
            d("From Http 上报按钮点击次数");
            Map<String, Object> map = new HashMap<>();
            map.put("classId", classId);
            map.put("date", date);
            String param = InnerUtil.toJson(map);
            String url = wrapperUrl("/busi/terminal/teach/course/v1/click/teachButton");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<Boolean>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ClassCompetitionEntity getClassroomCompetitionRecordList(String classId, String sportSkuId, long startDate, long endDate) {
        try {
            d("From Http 获取随堂赛多人成绩列表");
            Map<String, Object> map = new HashMap<>();
            map.put("classId", classId);
            map.put("sportSkuId", sportSkuId);
            map.put("startDate", startDate);
            map.put("endDate", endDate);
            String param = InnerUtil.toJson(map);
            String url = wrapperUrl("/busi/terminal/physical/teaching/v1/getClassroomCompetitionRecordList");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<ClassCompetitionEntity>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取资源列表
     *
     * @param createNum
     * @param resType
     * @return
     */
    public static TeacherResEntity getResList(String createNum, int resType, String appCode) {
        try {
            d("From Http 获取教师资源列表");
            Map<String, Object> map = new HashMap<>();
            map.put("createNum", createNum);
            map.put("resType", resType);
            map.put("appCode", appCode);
            String param = InnerUtil.toJson(map);
            //http://172.16.0.206:8082/gateway/api/busi/terminal/org/res/v1/list
            String url = wrapperUrl("/busi/terminal/org/res/v1/list");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<TeacherResEntity>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取资源列表
     *
     * @param createNum
     * @param resType
     * @return
     */
    public static TeacherResEntity getResListByPage(String createNum, int resType, String appCode, int current, int pageSize) {
        try {
            d("From Http 获取教师资源列表");
            Map<String, Object> map = new HashMap<>();
            map.put("createNum", createNum);
            map.put("resType", resType);
            map.put("appCode", appCode);
            map.put("current", current);// 当前页
            map.put("pageSize", pageSize); // 每页大小
            String param = InnerUtil.toJson(map);
            //http://172.16.0.206:8082/gateway/api/busi/terminal/org/res/v1/list
            String url = wrapperUrl("/busi/terminal/org/res/v1/list");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<TeacherResEntity>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param tempGroupTeachEntity
     * @return
     */
    public static boolean saveTempGroup(TempGroupTeachEntity tempGroupTeachEntity) {
        try {
            d("From Http 保存临时分组");
            String param = InnerUtil.toJson(tempGroupTeachEntity);
            String url = wrapperUrl("/busi/terminal/k12/class/group/v1/save/group");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<Boolean>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取临时分组
     *
     * @param classId
     * @param sceneCode
     * @return
     */
    public static TempGroupTeachEntity getTempGroup(String classId, String sceneCode) {
        try {
            d("From Http 获取临时分组");
            Map<String, Object> map = new HashMap<>();
            map.put("classId", classId);
            map.put("sceneCode", sceneCode);
            String param = InnerUtil.toJson(map);
            String url = wrapperUrl("/busi/terminal/k12/class/group/v1/get/group");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<TempGroupTeachEntity>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取有数据的日期
     *
     * @param skuIds
     * @param sceneCodes
     * @return
     */
    public static ArrayList<String> getDataDates(List<String> skuIds, List<String> sceneCodes) {
        try {
            d("From Http 获取临时分组");
            Map<String, Object> map = new HashMap<>();
            map.put("skuIds", skuIds);
            map.put("sceneCodes", sceneCodes);
            String param = InnerUtil.toJson(map);
            String url = wrapperUrl("/busi/terminal/teach/course/v1/dataDates");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<ArrayList<String>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
