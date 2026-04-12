package com.adl.service.caller;

import android.text.TextUtils;
import android.util.Pair;

import com.adl.service.AdlService;
import com.adl.service.common.BaseService;
import com.adl.service.common.IDefine;
import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestCallback;
import com.adl.service.common.RequestResult;
import com.adl.service.common.RequestWorker;
import com.adl.service.db.PlanDao;
import com.adl.service.db.PlanStudentDao;
import com.adl.service.db.StandardConfigDao;
import com.adl.service.entity.SportPlanEntity;
import com.adl.service.entity.SportPlanProjectEntity;
import com.adl.service.entity.SportPlanStudentEntity;
import com.adl.service.entity.StandardConfigEntity;
import com.adl.service.web.BaseHttpService;
import com.adl.service.web.SportPlanService;
import com.adl.service.web.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 运动计划与标准配置同步（含 Dao）。
 */
public class PlanCaller extends BaseService {

    private static PlanCaller _instance;

    private PlanCaller() {

    }

    public static PlanCaller instance() {
        if (_instance == null) {
            _instance = new PlanCaller();
        }
        return _instance;
    }

    public void querySportPlan(String standardType, int status, RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                int requestIndex = 1;
                boolean isRequest = true;
                ArrayList<SportPlanEntity> tempList = new ArrayList<>();

                PlanDao planDao = AdlService.getService().getDaoManagerProxy().getPlanDao();
                planDao.clearAll();

                while (isRequest) {
                    List<SportPlanEntity> list = SportPlanService.querySportPlan(standardType, status, requestIndex);
                    if (list != null && !list.isEmpty()) {
                        tempList.addAll(list);
                        requestIndex++;
                    } else {
                        isRequest = false;
                    }
                }
                planDao.insertAll(tempList);

                return RequestResult.okContent(tempList);
            }
        });
    }

    public void querySportPlanStudent(List<String> planIds, RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                int requestIndex;
                boolean isRequest;
                ArrayList<SportPlanStudentEntity> tempList = new ArrayList<>();

                PlanStudentDao studentDao = AdlService.getService().getPlanStudentDao();
                studentDao.clearAll();

                for (String planId : planIds) {
                    requestIndex = 1;
                    isRequest = true;

                    while (isRequest) {
                        List<SportPlanStudentEntity> list = SportPlanService.querySportPlanStudent(planId, requestIndex);
                        if (list != null && !list.isEmpty()) {
                            for (SportPlanStudentEntity plan : list) {
                                plan.setPlanId(planId);
                            }

                            tempList.addAll(list);
                            requestIndex++;
                        } else {
                            isRequest = false;
                        }
                    }
                }
                studentDao.insertAll(tempList);

                return RequestResult.okContent(tempList);
            }
        });
    }

    public void queryStandardConfig(List<Pair<String, String>> params, RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                ArrayList<StandardConfigEntity> tempList = new ArrayList<>();

                StandardConfigDao standardConfigDao = AdlService.getService().getStandardConfigDao();
                standardConfigDao.clearAll();

                for (Pair<String, String> param : params) {
                    List<StandardConfigEntity> list = SportPlanService.queryStandardConfig(param.first, param.second);
                    if (list != null && !list.isEmpty()) {
                        tempList.addAll(list);
                    }
                }
                standardConfigDao.insertAll(tempList);

                return RequestResult.okContent(tempList);
            }
        });
    }

    public void syncPlanData(boolean syncStandard, RequestCallback callback) {
        postTask(new RequestWorker(callback) {

            @Override
            public RequestResult doWorking() {
                try {
                    AdlService adlService = AdlService.getService();
                    int requestIndex = 1;
                    boolean isRequest = true;

                    long serverTime = BaseHttpService.currentSystemTime();
                    if (serverTime <= 0) {
                        return RequestResult.okContent("同步完成");
                    }
                    d("服务器时间: " + InnerUtil.formatByTimeCode(serverTime));

                    ArrayList<SportPlanEntity> planList = new ArrayList<>();

                    adlService.getPlanDao().clearAll();

                    List<Integer> status = Arrays.asList(IDefine.SportPlanStatusNew, IDefine.SportPlanStatusDoing);
                    int statusIndex = 0;

                    while (isRequest) {
                        List<SportPlanEntity> planTempList = SportPlanService.querySportPlan(IDefine.standardTypeALL, status.get(statusIndex), requestIndex);
                        if (planTempList != null && !planTempList.isEmpty()) {
                            planList.addAll(planTempList);
                            requestIndex++;
                        } else {
                            if (statusIndex < status.size() - 1) {
                                statusIndex++;
                                requestIndex = 1;
                            } else {
                                isRequest = false;
                            }
                        }
                    }
                    adlService.getPlanDao().insertAll(planList);

                    adlService.getPlanStudentDao().clearAll();
                    for (SportPlanEntity plan : planList) {
                        if (TextUtils.isEmpty(plan.getEndTime()) || serverTime > Long.parseLong(plan.getEndTime()))
                            continue;

                        requestIndex = 1;
                        isRequest = true;

                        while (isRequest) {
                            List<SportPlanStudentEntity> studentTempList = SportPlanService.querySportPlanStudent(plan.getPlanId(), requestIndex);
                            if (studentTempList != null && !studentTempList.isEmpty()) {
                                for (SportPlanStudentEntity entity : studentTempList) {
                                    plan.setPlanId(entity.getPlanId());
                                }

                                adlService.getPlanStudentDao().insertAll(studentTempList);
                                requestIndex++;
                            } else {
                                isRequest = false;
                            }
                        }
                    }

                    adlService.getStandardConfigDao().clearAll();
                    if (syncStandard) {
                        ArrayList<Pair<String, String>> standardList = new ArrayList<>();

                        for (SportPlanEntity plan : planList) {
                            if (plan.getSportProjects() == null) continue;

                            if (TextUtils.isEmpty(plan.getEndTime()) || serverTime > Long.parseLong(plan.getEndTime()))
                                continue;

                            for (SportPlanProjectEntity project : plan.getSportProjects()) {
                                Pair<String, String> newPair = Pair.create(plan.getStandardId(), project.getSportProjectCode());
                                if (standardList.stream().noneMatch(standard -> standard.equals(newPair))) {
                                    standardList.add(newPair);
                                }
                            }
                        }

                        for (Pair<String, String> param : standardList) {
                            List<StandardConfigEntity> standardTempList = SportPlanService.queryStandardConfig(param.first, param.second);
                            if (standardTempList != null && !standardTempList.isEmpty()) {
                                adlService.getStandardConfigDao().insertAll(standardTempList);
                            }
                        }
                    }

                    return RequestResult.okContent("同步完成");
                } catch (Exception e) {
                    return parserException(e);
                }
            }
        });
    }
}
