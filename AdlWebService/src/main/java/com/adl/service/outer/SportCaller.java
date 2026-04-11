package com.adl.service.outer;

import android.util.Pair;

import com.adl.service.common.BaseService;
import com.adl.service.common.IDefine;
import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestCallback;
import com.adl.service.common.RequestResult;
import com.adl.service.common.RequestWorker;
import com.adl.service.db.DictDao;
import com.adl.service.db.SceneDao;
import com.adl.service.db.SceneSportDao;
import com.adl.service.entity.DictEntity;
import com.adl.service.entity.SceneEntity;
import com.adl.service.entity.SceneSportEntity;
import com.adl.service.web.SportService;
import com.adl.service.web.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/5
 * Describe   : 场景/运动
 */
public class SportCaller extends BaseService {

    private static SportCaller _instance;

    private SportCaller() {

    }

    public static SportCaller instance() {
        if (_instance == null) {
            _instance = new SportCaller();
        }
        return _instance;
    }

    // 查询场景列表
    public void querySceneList(String appCode, boolean skuStatus, RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                ArrayList<SceneEntity> tempList = new ArrayList<>();

                SceneDao sceneDao = AdlService.getService().getSceneDao();
                sceneDao.clearAll();

                // 在线查询
                List<SceneEntity> list = SportService.getSceneList(appCode, skuStatus);
                if (list != null && !list.isEmpty()) {
                    tempList.addAll(list);
                }
                sceneDao.insertAll(list);

                return RequestResult.okContent(tempList);
            }
        });
    }

    // 查询运动列表 String appCode, String sceneId
    public void querySportSkuList(List<Pair<String, String>> params, RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                ArrayList<SceneSportEntity> tempList = new ArrayList<>();

                SceneSportDao sportSkuDao = AdlService.getService().getSceneSportDao();
                sportSkuDao.clearAll();

                // 在线查询
                for (Pair<String, String> param : params) {
                    List<SceneSportEntity> list = SportService.getSportSkuList(param.first, param.second, 1);
                    if (list != null && !list.isEmpty()) {
                        for (SceneSportEntity entity : list) {
                            entity.setSceneId(param.first);
                        }
                        tempList.addAll(list);
                    }
                }
                sportSkuDao.insertAll(tempList);

                return RequestResult.okContent(tempList);
            }
        });
    }

    // 查询运动单位
    public void getSportUnitDirct(RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                ArrayList<DictEntity> tempList = new ArrayList<>();

                DictDao dictDao = AdlService.getService().getDictDao();
                dictDao.clearByDictCode(IDefine.DirctSportUnit);

                // 在线查询
                List<DictEntity> list = SportService.getDicts(IDefine.DirctSportUnit);
                if (list != null && !list.isEmpty()) {
                    tempList.addAll(list);
                }

                dictDao.insertAll(tempList);

                return RequestResult.okContent(tempList);
            }
        });
    }

    //  同步场景以及场景下的运动 Map:key[String appCode],value[List<String> sceneIds]
    public void syncSceneData(Map<String, List<String>> map, RequestCallback callback) {
        postTask(new RequestWorker(callback) {

            @Override
            public RequestResult doWorking() {
                try {
                    AdlService adlService = AdlService.getService();
                    List<String> onlineSceneIds = new ArrayList<>();

                    // 服务器系统时间
                    long serverTime = UserService.currentSystemTime();
                    if (serverTime <= 0) {
                        return RequestResult.okContent("同步完成");
                    }
                    d("服务器时间: " + InnerUtil.formatByTimeCode(serverTime));

                    adlService.getSceneDao().clearAll();
                    adlService.getSceneSportDao().clearAll();

                    map.forEach((appCode, sceneIds) -> {
                        onlineSceneIds.clear();
                        ////////////// 1.同步场景列表 ////////////
                        List<SceneEntity> sceneList = SportService.getSceneList(appCode, false);
                        if (sceneList != null && !sceneList.isEmpty()) {
                            for (SceneEntity sceneEntity : sceneList) {
                                sceneEntity.setAppCode(appCode);
                            }
                            adlService.getSceneDao().insertAll(sceneList);

                            //  筛选场景
                            for (SceneEntity sceneEntity : sceneList) {
                                if (sceneIds.contains(sceneEntity.getId())) {
                                    onlineSceneIds.add(sceneEntity.getId());
                                }
                            }
                        }

                        ////////////// 2.同步场景下运动列表 ////////////
                        for (String sceneId : onlineSceneIds) {
                            List<SceneSportEntity> tempList = SportService.getSportSkuList(appCode, sceneId, 1);
                            if (tempList != null && !tempList.isEmpty()) {
                                for (SceneSportEntity entity : tempList) {
                                    entity.setAppCode(appCode);
                                    entity.setSceneId(sceneId);
                                }
                                adlService.getSceneSportDao().insertAll(tempList);
                            }
                        }
                    });

                    ////////////// 3.同步运动单位 ////////////
                    adlService.getDictDao().clearByDictCode(IDefine.DirctSportUnit);
                    List<DictEntity> dictList = SportService.getDicts(IDefine.DirctSportUnit);
                    if (dictList != null && !dictList.isEmpty()) {
                        adlService.getDictDao().insertAll(dictList);
                    }

                    return RequestResult.okContent("同步完成");
                } catch (Exception e) {
                    return parserException(e);
                }
            }
        });
    }
}
