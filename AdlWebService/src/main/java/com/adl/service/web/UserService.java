package com.adl.service.web;

import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.entity.CitizenEntity;
import com.adl.service.entity.OrganizationEntity;
import com.adl.service.entity.StudentEntity;
import com.adl.service.entity.StudentGradeClassEntity;
import com.adl.service.entity.TeacherEntity;
import com.alibaba.fastjson.JSON;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/1
 * Describe   : 用户数据查询
 * 1. 学生信息
 * 2. 老师信息
 * 3. 当前登录机构
 */
public class UserService extends BaseHttpService {

    ////////// 同步查询学生信息
    public static List<StudentEntity> queryStudent(long lastUpdateTime) {
        try {
            d("From Http 查询学生");

            // 最后更新时间
            Map<String, Object> map = new HashMap<>();
            if (lastUpdateTime > 1000) {
                //  yyyy-MM-dd HH:mm:ss
                map.put("lastUpdateTime", InnerUtil.formatByTimeCode(lastUpdateTime));
            }

            map.put("faceType", 2);
            String param = InnerUtil.toJson(map);

            // 增量更新学生信息
            String url = wrapperUrl("/busi/terminal/account/list");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return JSON.parseArray(result.getContent().toString(), StudentEntity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 分页下载
    public static List<StudentEntity> queryStudentByPage(long lastUpdateTime, int pageSize, int faceType) {
        try {

            //  yyyy-MM-dd HH:mm:ss
            d("From Http 查询学生");
            List<StudentEntity> outList = new ArrayList<>();
            int responsePageSize = 0;
            int pageIndex = 1;
            do {
                // 最后更新时间
                Map<String, Object> map = new HashMap<>();
                if (lastUpdateTime > 1000) {
                    map.put("lastUpdateTime", InnerUtil.formatByTimeCode(lastUpdateTime));
                }
                map.put("current", pageIndex);
                map.put("pageSize", pageSize);
                //  1 百度人脸，2 商汤人脸
                if (faceType == 1 || faceType == 2) {
                    map.put("faceType", faceType);
                }
                String param = InnerUtil.toJson(map);
                d("请求学生数据: index=" + pageIndex + ";param:" + param);

                // 增量更新学生信息
                String url = wrapperUrl("/busi/terminal/account/list_V1");
                Request request = new Request.Builder().url(url)
                        .post(RequestBody.create(param, jsonType)).build();

                List<StudentEntity> tempList = null;
                RequestResult result = parserPageResponse(getHttpClient().newCall(request).execute());

                if (result.isOk()) {
                    tempList = JSON.parseArray(result.getContent().toString(), StudentEntity.class);
                    if (tempList != null && tempList.size() > 0) {
                        outList.addAll(tempList);
                        responsePageSize = tempList.size();
                        pageIndex++;
                    } else {
                        break;
                    }

                } else {
                    break;
                }
            } while (responsePageSize == pageSize);
            return outList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 查询老师信息
    public static List<TeacherEntity> queryTeacher(long lastUpdateTime) {
        try {
            d("From Http 查询老师");

            // 最后更新时间
            Map<String, Object> map = new HashMap<>();
            if (lastUpdateTime > 1000) {
                //  yyyy-MM-dd HH:mm:ss
                map.put("lastUpdateTime", InnerUtil.formatByTimeCode(lastUpdateTime));
            }
            String param = InnerUtil.toJson(map);

            // 增量更新老师信息
            String url = wrapperUrl("/busi/terminal/account/teacher/list");

            // 请求
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return JSON.parseArray(result.getContent().toString(), TeacherEntity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 全量更新市民
     *
     * @param lastUpdateTime
     * @return
     */
    public static List<CitizenEntity> queryCitizen(long lastUpdateTime) {
        try {
            d("From Http 查询学生");

            // 最后更新时间
            Map<String, Object> map = new HashMap<>();
            if (lastUpdateTime > 1000) {
                //  yyyy-MM-dd HH:mm:ss
                map.put("lastUpdateTime", InnerUtil.formatByTimeCode(lastUpdateTime));
            }

            map.put("faceType", 2);
            String param = InnerUtil.toJson(map);

            // 增量更新学生信息
            String url = wrapperUrl("/busi/terminal/account/citizen/list");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return JSON.parseArray(result.getContent().toString(), CitizenEntity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 分页获取市民信息
     *
     * @param lastUpdateTime
     * @param pageSize
     * @param faceType
     * @return
     */
    public static List<CitizenEntity> queryCitizenByPage(long lastUpdateTime, int pageSize, int faceType) {
        try {

            //  yyyy-MM-dd HH:mm:ss
            d("From Http 查询学生");
            List<CitizenEntity> outList = new ArrayList<>();
            // 最大200页
            for (int pageIndex = 1; ; pageIndex++) {

                // 最后更新时间
                Map<String, Object> map = new HashMap<>();
                if (lastUpdateTime > 1000) {
                    map.put("lastUpdateTime", InnerUtil.formatByTimeCode(lastUpdateTime));
                }
                map.put("current", pageIndex);
                map.put("pageSize", pageSize);
                //  1 百度人脸，2 商汤人脸
                if (faceType == 1 || faceType == 2) {
                    map.put("faceType", faceType);
                }
                String param = InnerUtil.toJson(map);
                d("请求学生数据: index=" + pageIndex);

                // 增量更新学生信息/terminal/account/citizen/list
                String url = wrapperUrl("/busi/terminal/account/citizen/list");
                Request request = new Request.Builder().url(url)
                        .post(RequestBody.create(param, jsonType)).build();

                List<CitizenEntity> tempList = null;
                RequestResult result = parserPageResponse(getHttpClient().newCall(request).execute());
                if (result.isOk()) {
                    tempList = JSON.parseArray(result.getContent().toString(), CitizenEntity.class);
                    if (tempList != null && tempList.size() > 0) {
                        outList.addAll(tempList);
                    }
                }

                if (tempList == null || tempList.size() < pageSize) {
                    return outList;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 查询当前登录机构
    public static OrganizationEntity getLoginOrganization() {
        try {
            d("+++++++++++++++机构信息++++++++++++++++");

            // 当前登录机构信息
            String url = wrapperUrl("/busi/terminal/org/v1/login/info");

            // 请求
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create("{}", jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToObject(result.getContent().toString(), OrganizationEntity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static RequestResult queryLoginOrganization() {
        try {
            d("+++++++++++++++机构信息++++++++++++++++");

            // 当前登录机构信息
            String url = wrapperUrl("/busi/terminal/org/v1/login/info");

            // 请求
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create("{}", jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                d("机构同步成功");
            }

            return result;
        } catch (Exception e) {
            return RequestResult.error(e.toString());
        }
    }

    /**
     * 获取年级班级列表
     * ref:http://172.16.2.100:3000/#/view/Wz3oDkzR
     *
     * @return
     */
    public static List<StudentGradeClassEntity> queryStudentGradeClass() {
        try {
            d("From Http 查询年级班级列表");

            // 年级树
            String url = wrapperUrl("/busi/terminal/grade/v1/getGradeTree");

            // 请求
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create("{}", jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<ArrayList<StudentGradeClassEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
