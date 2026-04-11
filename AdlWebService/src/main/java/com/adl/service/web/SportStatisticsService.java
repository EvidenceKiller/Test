package com.adl.service.web;

import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.entity.BestRankTopEntity;
import com.adl.service.entity.CompetitionRankEntity;
import com.adl.service.entity.ExerciseSumTimeRankEntity;
import com.adl.service.entity.MorePeopleRecordRankEntity;
import com.adl.service.entity.PhysicalTrainingRankEntity;
import com.adl.service.entity.SportRankKingEntity;
import com.adl.service.entity.SportSkuRankEntity;
import com.adl.service.entity.VictoryRankEntity;
import com.adl.service.entity.WarRecordRankEntity;
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
 * Date       : 2024/7/2
 * Describe   : 统计
 * 1. 场景锻炼项目排行（以及历史最佳）
 * 2. 场景下项目锻炼总时长排行榜（锻炼之星）
 * 3. 场景下的热门项目
 * 4. 场景的项目霸榜之王
 * 5. 场景的项目赛事排行榜
 * 6. 场景的项目双人对战排行榜
 */
public class SportStatisticsService extends BaseHttpService {

    ////////// 场景锻炼项目排行（以及历史最佳）
    public static List<PhysicalTrainingRankEntity> physicalTrainingRank(List<String> sportSceneCodes, String sportSkuId, String dayType, String rankNumber) {
        try {
            d("From Http 场景锻炼项目排行（以及历史最佳）");

            Map<String, Object> map = new HashMap<>();
            map.put("sportSceneCodes", sportSceneCodes);            //  场景代码集合
            map.put("sportSkuId", sportSkuId);                      //  是否查询运动
            map.put("dayType", dayType);                            //  日期类型 1：今日  2：七日   3：三十日（默认30日）
            map.put("rankNumber", rankNumber);                      //  排名数量

            String param = InnerUtil.toJson(map);

            // 请求
            String url = wrapperUrl("/busi/terminal/statistics/sport/physicalTrainingRank");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<PhysicalTrainingRankEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 场景下项目锻炼总时长排行榜（锻炼之星）
    public static List<ExerciseSumTimeRankEntity> exerciseSumTimeRank(List<String> sportSceneCodes,String appCode, String dayType, String rankNumber) {
        try {
            d("From Http 场景下项目锻炼总时长排行榜（锻炼之星）");

            Map<String, Object> map = new HashMap<>();
            map.put("sportSceneCodes", sportSceneCodes);            //  场景代码集合
            map.put("appCode", appCode);                            //  appcode
            map.put("dayType", dayType);                            //  日期类型 1：今日  2：七日   3：三十日（默认30日）
            map.put("rankNumber", rankNumber);                      //  排名的数量（传多少名排多少名）

            String param = InnerUtil.toJson(map);

            // 请求
            String url = wrapperUrl("/busi/terminal/statistics/sport/exerciseSumTimeRank");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<ExerciseSumTimeRankEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 场景下的热门项目
    public static List<SportSkuRankEntity> sportSkuRank(String appCode, List<String> sportSceneCodes, String rankNumber) {
        try {
            d("From Http 场景下的热门项目");

            Map<String, Object> map = new HashMap<>();
            map.put("appCode", appCode);                            //  应用编码
            map.put("sportSceneCodes", sportSceneCodes);            //  场景代码集合
            map.put("rankNumber", rankNumber);                      //  排名的数量（传多少名排多少名）

            String param = InnerUtil.toJson(map);

            // 请求
            String url = wrapperUrl("/busi/terminal/statistics/sport/sportSkuRank");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<SportSkuRankEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 场景的项目霸榜之王
    public static List<SportRankKingEntity> sportRankKing(String appCode, List<String> sportSceneCodes, String dayType, String rankNumber) {
        try {
            d("From Http 场景的项目霸榜之王");

            Map<String, Object> map = new HashMap<>();
            map.put("appCode", appCode);                            //  应用编码
            map.put("sportSceneCodes", sportSceneCodes);            //  场景代码集合
            map.put("dayType", dayType);                            //  日期类型 1：今日  2：七日   3：三十日（默认30日）
            map.put("rankNumber", rankNumber);                      //  排名的数量（传多少名排多少名）

            String param = InnerUtil.toJson(map);

            // 请求
            String url = wrapperUrl("/busi/terminal/statistics/sport/sportRankKing");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<SportRankKingEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 场景的项目赛事排行榜
    public static CompetitionRankEntity competitionRank(String rankType, String rankId, String sportSkuId, String competitionId, String rankNumber, String accountId) {
        try {
            d("From Http 场景的项目赛事排行榜");

            Map<String, Object> map = new HashMap<>();
            map.put("rankType", rankType);            //  排行类型 1：总榜2：学校 3：年级 4：班级
            map.put("rankId", rankId);                //  排行id （学校/年级/班级）id 根据排行类型传对应的值，总榜不传
            map.put("sportSkuId", sportSkuId);        //  运动SKUid
            map.put("competitionId", competitionId);  //  赛事id
            map.put("rankNumber", rankNumber);        //  排名的数量（传多少名排多少名）
            map.put("accountId", accountId);          //  账号id

            String param = InnerUtil.toJson(map);

            // 请求
            String url = wrapperUrl("/busi/terminal/statistics/sport/competitionRank");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToObject(result.getContent().toString(), CompetitionRankEntity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 场景的项目双人对战排行榜
    public static List<WarRecordRankEntity> warRecordRank(List<String> sportSceneCodes, String sportSkuId, String rankNumber) {
        try {
            d("From Http 场景的项目双人对战排行榜");

            Map<String, Object> map = new HashMap<>();
            map.put("sportSceneCodes", sportSceneCodes);            //  场景代码集合
            map.put("sportSkuId", sportSkuId);                      //  运动id
            map.put("rankNumber", rankNumber);                      //  排名的数量（传多少名排多少名）

            String param = InnerUtil.toJson(map);

            // 请求
            String url = wrapperUrl("/busi/terminal/statistics/sport/warRecordRank");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<WarRecordRankEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 场景的项目最佳榜单
    public static List<BestRankTopEntity> bestRankTop(
            String appCode,     // 应用编码
            String sportSkuId,  // 运动sku标识码
            String orgId,       // 机构标识码
            String collegeId,   // 学院标识码
            String facultyId,   // 学系标识码
            String majorId,     // 专业标识码
            String gradeId,     // 学级标识码
            String classId,     // 班级Id
            int sex,            // 性别
            int top,            // 前几的排行榜
            int timeType        // 日期类型 1-今日 2-本周 3-本月 4-本季度 5-本年度
    ) {
        try {
            d("From Http 场景的项目最佳榜单");

            Map<String, Object> map = new HashMap<>();
            map.put("appCode", appCode);
            map.put("sportSkuId", sportSkuId);
            map.put("orgId", orgId);
            map.put("collegeId", collegeId);
            map.put("facultyId", facultyId);
            map.put("majorId", majorId);
            map.put("gradeId", gradeId);
            map.put("classId", classId);
            if (sex == 1 || sex == 2 || sex == 9) {
                map.put("sex", sex);
            }
            map.put("top", top);
            map.put("timeType", timeType);

            String param = InnerUtil.toJson(map);

            // 请求
            String url = wrapperUrl("/busi/terminal/statistics/sport/bestRankTop");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<BestRankTopEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    ////////// 场景的项目多人对战排行榜
    public static List<MorePeopleRecordRankEntity> morePeopleRecordRank(String orgId, String sportSkuId, String rankNumber) {
        try {
            d("From Http 场景的项目多人对战排行榜");

            Map<String, Object> map = new HashMap<>();
            map.put("orgId", orgId);                      //  机构id
            map.put("sportSkuId", sportSkuId);            //  运动id
            map.put("rankNumber", rankNumber);            //  排名的数量（传多少名排多少名）

            String param = InnerUtil.toJson(map);

            // 请求
            String url = wrapperUrl("/busi/terminal/statistics/sport/morePeopleRecordRank");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<MorePeopleRecordRankEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取场景的项目对战胜率榜
     * @param skuId
     * @param maxRank
     * @param top
     * @return
     */
    public static List<VictoryRankEntity> victoryRankTop(String skuId, int maxRank, int top) {
        try {
            d("From Http 场景的项目对战胜率榜");

            Map<String, Object> map = new HashMap<>();
            map.put("skuId", skuId);                      //  机构id
            map.put("maxRank", maxRank);            //  运动id
            map.put("top", top);            //  排名的数量（传多少名排多少名）
            String param = InnerUtil.toJson(map);
            // 请求
            String url = wrapperUrl("/busi/terminal/statistics/sport/morePeopleVictoryRank");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<VictoryRankEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
