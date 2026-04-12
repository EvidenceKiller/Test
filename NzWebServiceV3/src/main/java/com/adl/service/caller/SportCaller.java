package com.adl.service.caller;

import android.util.Pair;

import com.adl.service.AdlService;
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
import com.adl.service.log.NzLog;
import com.adl.service.web.BaseHttpService;
import com.adl.service.web.SportService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 场景 / 运动 SKU / 字典同步（含 Dao）。
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

    public void querySceneList(String appCode, boolean skuStatus, RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                ArrayList<SceneEntity> tempList = new ArrayList<>();

                SceneDao sceneDao = AdlService.getService().getDaoManagerProxy().getSceneDao();
                sceneDao.clearAll();

                List<SceneEntity> list = SportService.getSceneList(appCode, skuStatus);
                if (list != null && !list.isEmpty()) {
                    tempList.addAll(list);
                }
                sceneDao.insertAll(list);

                return RequestResult.okContent(tempList);
            }
        });
    }

    public void querySportSkuList(List<Pair<String, String>> params, RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                ArrayList<SceneSportEntity> tempList = new ArrayList<>();

                SceneSportDao sportSkuDao = AdlService.getService().getDaoManagerProxy().getSceneSportDao();
                sportSkuDao.clearAll();

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

    public void getSportUnitDirct(RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                ArrayList<DictEntity> tempList = new ArrayList<>();

                DictDao dictDao = AdlService.getService().getDaoManagerProxy().getDictDao();
                dictDao.clearByDictCode(IDefine.DirctSportUnit);

                List<DictEntity> list = SportService.getDicts(IDefine.DirctSportUnit);
                if (list != null && !list.isEmpty()) {
                    tempList.addAll(list);
                }

                dictDao.insertAll(tempList);

                return RequestResult.okContent(tempList);
            }
        });
    }

    public void syncSceneData(Map<String, List<String>> map, RequestCallback callback) {
        postTask(new RequestWorker(callback) {

            @Override
            public RequestResult doWorking() {
                try {
                    AdlService adlService = AdlService.getService();
                    List<String> onlineSceneIds = new ArrayList<>();

                    long serverTime = BaseHttpService.currentSystemTime();
                    if (serverTime <= 0) {
                        return RequestResult.okContent("同步完成");
                    }
                    NzLog.d("服务器时间: " + InnerUtil.formatByTimeCode(serverTime));

                    adlService.getDaoManagerProxy().getSceneDao().clearAll();
                    adlService.getDaoManagerProxy().getSceneSportDao().clearAll();

                    map.forEach((appCode, sceneIds) -> {
                        onlineSceneIds.clear();
                        List<SceneEntity> sceneList = SportService.getSceneList(appCode, false);
                        if (sceneList != null && !sceneList.isEmpty()) {
                            for (SceneEntity sceneEntity : sceneList) {
                                sceneEntity.setAppCode(appCode);
                            }
                            adlService.getDaoManagerProxy().getSceneDao().insertAll(sceneList);

                            for (SceneEntity sceneEntity : sceneList) {
                                if (sceneIds.contains(sceneEntity.getId())) {
                                    onlineSceneIds.add(sceneEntity.getId());
                                }
                            }
                        }

                        for (String sceneId : onlineSceneIds) {
                            List<SceneSportEntity> tempList = SportService.getSportSkuList(appCode, sceneId, 1);
                            if (tempList != null && !tempList.isEmpty()) {
                                for (SceneSportEntity entity : tempList) {
                                    entity.setAppCode(appCode);
                                    entity.setSceneId(sceneId);
                                }
                                adlService.getDaoManagerProxy().getSceneSportDao().insertAll(tempList);
                            }
                        }
                    });

                    adlService.getDaoManagerProxy().getDictDao().clearByDictCode(IDefine.DirctSportUnit);
                    List<DictEntity> dictList = SportService.getDicts(IDefine.DirctSportUnit);
                    if (dictList != null && !dictList.isEmpty()) {
                        adlService.getDaoManagerProxy().getDictDao().insertAll(dictList);
                    }

                    return RequestResult.okContent("同步完成");
                } catch (Exception e) {
                    return parserException(e);
                }
            }
        });
    }
}
