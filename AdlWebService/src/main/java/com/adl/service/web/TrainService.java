package com.adl.service.web;

import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.entity.TrainEntity;
import com.adl.service.entity.TrainSportInfoEntity;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

public class TrainService extends BaseHttpService {

    public static TrainEntity getRunTrainPlanList(String accountId,int timeStatus) {
        try {
            d("From Http 获取特训计划列表");
            Map<String, Object> map = new HashMap<>();
            map.put("accountId", accountId);
            map.put("timeStatus", timeStatus);
            String param = InnerUtil.toJson(map);

            String url = wrapperUrl("/busi/terminal/k12/train/plan/getTrainPlanInfoList");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<TrainEntity>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * "accountId":"1894231293273116673",
     * "trainPlanId":"fb59293d4c0d44eaa6b2f57ec481e5ff",
     * "trainPlanTime":1740931200000
     *
     * @param accountId
     * @return
     */
    public static List<TrainSportInfoEntity> getTrainPlanOneDayProjectList(String accountId, String trainPlanId, long trainPlanTime) {
        try {
            d("From Http 获取特训运动项目列表");
            Map<String, Object> map = new HashMap<>();
            map.put("accountId", accountId);
            map.put("trainPlanId", trainPlanId);
            if (trainPlanTime > 0){
                map.put("trainPlanTime", trainPlanTime);
            }
            String param = InnerUtil.toJson(map);
            // 增量更新学生信息
            //http://172.16.0.206:8082/gateway/api/busi/terminal/train/plan/v1/getTrainPlanOneDayProjectList
            String url = wrapperUrl("/busi/terminal/train/plan/v1/getTrainPlanOneDayProjectList");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<List<TrainSportInfoEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
