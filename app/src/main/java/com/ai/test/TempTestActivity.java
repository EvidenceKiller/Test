package com.ai.test;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adl.auth.common.AdlUpgradeInfo;
import com.adl.auth.core.AdlAuthFactory;
import com.adl.service.common.BaseService;
import com.adl.service.common.FileUtil;
import com.adl.service.common.IDefine;
import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestCallback;
import com.adl.service.common.RequestResult;
import com.adl.service.common.RequestUserCallback;
import com.adl.service.entity.BannerEntity;
import com.adl.service.entity.BestRankTopEntity;
import com.adl.service.entity.ClassCompetitionEntity;
import com.adl.service.entity.ClassInfoEntity;
import com.adl.service.entity.CompetitionRankEntity;
import com.adl.service.entity.DeviceFocusEntity;
import com.adl.service.entity.DeviceInfoEntity;
import com.adl.service.entity.DictEntity;
import com.adl.service.entity.DictExcelEntity;
import com.adl.service.entity.ExerciseSumTimeRankEntity;
import com.adl.service.entity.GradeInfoEntity;
import com.adl.service.entity.GroupTeachEntity;
import com.adl.service.entity.MeetGroupDetailsEntity;
import com.adl.service.entity.MeetGroupTeamEntity;
import com.adl.service.entity.MorePeopleRecordRankEntity;
import com.adl.service.entity.OrgResInfoEntity;
import com.adl.service.entity.OrganizationEntity;
import com.adl.service.entity.PersonRankEntity;
import com.adl.service.entity.PhysicalTrainingRankEntity;
import com.adl.service.entity.ResInfoEntity;
import com.adl.service.entity.SceneEntity;
import com.adl.service.entity.SceneSportEntity;
import com.adl.service.entity.SceneSportInfoEntity;
import com.adl.service.entity.SportCompetitionEntity;
import com.adl.service.entity.SportCompetitionInfoEntity;
import com.adl.service.entity.SportDetailEntity;
import com.adl.service.entity.SportMeetingEntity;
import com.adl.service.entity.SportOverviewEntity;
import com.adl.service.entity.SportPlanEntity;
import com.adl.service.entity.SportPlanStudentEntity;
import com.adl.service.entity.SportRankKingEntity;
import com.adl.service.entity.SportRecordEntity;
import com.adl.service.entity.SportRecordResultEntity;
import com.adl.service.entity.SportSkuRankEntity;
import com.adl.service.entity.StandardConfigEntity;
import com.adl.service.entity.StudentEntity;
import com.adl.service.entity.StudentGradeClassEntity;
import com.adl.service.entity.SumScoreRankEntity;
import com.adl.service.entity.TeacherEntity;
import com.adl.service.entity.TeacherResEntity;
import com.adl.service.entity.TeacherSportLocalEntity;
import com.adl.service.entity.TempGroupTeachEntity;
import com.adl.service.entity.TrainEntity;
import com.adl.service.entity.TrainSportInfoEntity;
import com.adl.service.entity.VictoryRankEntity;
import com.adl.service.entity.WarRecordRankEntity;
import com.adl.service.entity.WeatherEntity;
import com.adl.service.entity.WikiDetailEntity;
import com.adl.service.entity.WikiEntity;
import com.adl.service.entity.WikiTypeEntity;
import com.adl.service.outer.AdlService;
import com.adl.service.outer.ConfigService;
import com.adl.service.outer.PlanCaller;
import com.adl.service.outer.SportCaller;
import com.adl.service.outer.UserCaller;
import com.adl.service.web.BannerService;
import com.adl.service.web.CommonService;
import com.adl.service.web.DeviceService;
import com.adl.service.web.GradeService;
import com.adl.service.web.ResService;
import com.adl.service.web.SportCompetitionService;
import com.adl.service.web.SportMeetingService;
import com.adl.service.web.SportPlanService;
import com.adl.service.web.SportRankService;
import com.adl.service.web.SportRecordService;
import com.adl.service.web.SportService;
import com.adl.service.web.SportStatisticsService;
import com.adl.service.web.TeachService;
import com.adl.service.web.TrainService;
import com.adl.service.web.UserService;
import com.adl.service.web.WikiService;
import com.adl.ts.general.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TempTestActivity extends BaseActivity {

    private LinearLayout container;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_temp_test);
        container = findViewById(R.id.ll);

        String appCode = AdlAuthFactory.create(getApplicationContext()).getAppCode();

        addTextView("UserService");
        createButton("queryStudentByPage").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<StudentEntity> result = UserService.queryStudentByPage(-1, 1000, 2);
                flushResultToFile("UserService.queryStudentByPage.txt", result);
            });
        });
        createButton("queryTeacher").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<TeacherEntity> result = UserService.queryTeacher(-1);
                flushResultToFile("UserService.queryTeacher.txt", result);
            });
        });
        createButton("getLoginOrganization").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                OrganizationEntity result = UserService.getLoginOrganization();
                flushResultToFile("UserService.getLoginOrganization.txt", result);
            });
        });
        createButton("queryStudentGradeClass").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<StudentGradeClassEntity> result = UserService.queryStudentGradeClass();
                flushResultToFile("UserService.queryStudentGradeClass.txt", result);
            });
        });

        addTextView("SportService");
        createButton("getSceneList").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<SceneEntity> result = SportService.getSceneList(appCode, true);
                flushResultToFile("SportService.getSceneList.txt", result);
            });
        });
        createButton("getSportSkuList").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<SceneSportEntity> result = SportService.getSportSkuList(appCode, "1001", 1);
                flushResultToFile("SportService.getSportSkuList.txt", result);
            });
        });
        createButton("getSportSkuDetail").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                SceneSportInfoEntity result = SportService.getSportSkuDetail("0201010001");
                flushResultToFile("SportService.getSportSkuDetail.txt", result);
            });
        });
        createButton("getDicts").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                // TODO 参数需修改
                List<DictEntity> result = SportService.getDicts("");
                flushResultToFile("SportService.getDicts.txt", result);
            });
        });
        createButton("getDictMap").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<DictExcelEntity> result = SportService.getDictMap("0201010001");
                flushResultToFile("SportService.getDictMap.txt", result);
            });
        });
        createButton("getSportDetailList").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                // TODO 参数需修改
                List<SportDetailEntity> result = SportService.getSportDetailList("0201010001");
                flushResultToFile("SportService.getSportDetailList.txt", result);
            });
        });

        addTextView("SportRecordService");
        createButton("uploadRecord").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                // TODO 参数需修改
                RequestResult result = SportRecordService.uploadRecord(new SportRecordEntity());
                flushResultToFile("SportRecordService.uploadRecord.txt", result);
            });
        });
        createButton("updateRecord").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                // TODO 参数需修改
                RequestResult result = SportRecordService.updateRecord(new SportRecordEntity());
                flushResultToFile("SportRecordService.updateRecord.txt", result);
            });
        });
        createButton("uploadTeacherData").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                // TODO 参数需修改
                RequestResult result = SportRecordService.uploadTeacherData(new TeacherSportLocalEntity());
                flushResultToFile("SportRecordService.uploadTeacherData.txt", result);
            });
        });
        createButton("getSportResultRecord").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                // TODO 参数需修改
                List<SportRecordResultEntity> result = SportRecordService.getSportResultRecord("", "", "", "", "", "", 1, 10);
                flushResultToFile("SportRecordService.getSportResultRecord.txt", result);
            });
        });

        addTextView("SportPlanService");
        createButton("querySportPlan").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<SportPlanEntity> result = SportPlanService.querySportPlan("TC", 0, 1);
                flushResultToFile("SportPlanService.querySportPlan.txt", result);
            });
        });
        createButton("getSportPlanDetail").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                SportPlanEntity result = SportPlanService.getSportPlanDetail("");
                flushResultToFile("SportPlanService.getSportPlanDetail.txt", result);
            });
        });
        createButton("querySportPlanStudent").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<SportPlanStudentEntity> result = SportPlanService.querySportPlanStudent("", 1);
                flushResultToFile("SportPlanService.querySportPlanStudent.txt", result);
            });
        });
        createButton("checkStudent").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                Boolean result = SportPlanService.checkStudent("", "");
                flushResultToFile("SportPlanService.checkStudent.txt", result);
            });
        });
        createButton("querySportPlanClass").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<ClassInfoEntity> result = SportPlanService.querySportPlanClass("");
                flushResultToFile("SportPlanService.querySportPlanClass.txt", result);
            });
        });
        createButton("queryStandardConfig").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<StandardConfigEntity> result = SportPlanService.queryStandardConfig("", "");
                flushResultToFile("SportPlanService.queryStandardConfig.txt", result);
            });
        });

        addTextView("SportStatisticsService");
        createButton("physicalTrainingRank").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<PhysicalTrainingRankEntity> result = SportStatisticsService.physicalTrainingRank(null, "", "", "");
                flushResultToFile("SportPlanService.physicalTrainingRank.txt", result);
            });
        });
        createButton("exerciseSumTimeRank").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<ExerciseSumTimeRankEntity> result = SportStatisticsService.exerciseSumTimeRank(null, "", "", "");
                flushResultToFile("SportPlanService.exerciseSumTimeRank.txt", result);
            });
        });
        createButton("sportSkuRank").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<SportSkuRankEntity> result = SportStatisticsService.sportSkuRank(appCode, null, "");
                flushResultToFile("SportPlanService.sportSkuRank.txt", result);
            });
        });
        createButton("sportRankKing").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<SportRankKingEntity> result = SportStatisticsService.sportRankKing(appCode, null, "", "");
                flushResultToFile("SportPlanService.sportRankKing.txt", result);
            });
        });
        createButton("competitionRank").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                CompetitionRankEntity result = SportStatisticsService.competitionRank("", "", "", "", "", "");
                flushResultToFile("SportPlanService.competitionRank.txt", result);
            });
        });
        createButton("warRecordRank").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<WarRecordRankEntity> result = SportStatisticsService.warRecordRank(null, "", "");
                flushResultToFile("SportPlanService.warRecordRank.txt", result);
            });
        });
        createButton("bestRankTop").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<BestRankTopEntity> result = SportStatisticsService.bestRankTop("", "", "", "", "", "", "", "", 1, 1, 1);
                flushResultToFile("SportPlanService.bestRankTop.txt", result);
            });
        });
        createButton("morePeopleRecordRank").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<MorePeopleRecordRankEntity> result = SportStatisticsService.morePeopleRecordRank("", "", "");
                flushResultToFile("SportPlanService.morePeopleRecordRank.txt", result);
            });
        });
        createButton("victoryRankTop").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<VictoryRankEntity> result = SportStatisticsService.victoryRankTop("", 1, 1);
                flushResultToFile("SportPlanService.victoryRankTop.txt", result);
            });
        });

        addTextView("SportRankService");
        createButton("getPersonRank").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                PersonRankEntity result = SportRankService.getPersonRank("", "", "");
                flushResultToFile("SportRankService.getPersonRank.txt", result);
            });
        });
        createButton("getSumScoreRank").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<SumScoreRankEntity> result = SportRankService.getSumScoreRank("", "");
                flushResultToFile("SportRankService.getSumScoreRank.txt", result);
            });
        });
        createButton("getSportOverview").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                SportOverviewEntity result = SportRankService.getSportOverview();
                flushResultToFile("SportRankService.getSportOverview.txt", result);
            });
        });

        addTextView("SportMeetingService");
        createButton("getPersonRank").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<SportMeetingEntity> result = SportMeetingService.getSportMeetList(appCode, "", "", false, 1);
                flushResultToFile("SportMeetingService.getSportMeetList.txt", result);
            });
        });
        createButton("downloadGroupTeam").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<MeetGroupTeamEntity> result = SportMeetingService.downloadGroupTeam("", "", "", true);
                flushResultToFile("SportMeetingService.downloadGroupTeam.txt", result);
            });
        });
        createButton("downloadGroupTeamDetail").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<MeetGroupDetailsEntity> result = SportMeetingService.downloadGroupTeamDetail("", "", "", "", "");
                flushResultToFile("SportMeetingService.downloadGroupTeamDetail.txt", result);
            });
        });

        addTextView("SportCompetitionService");
        createButton("getCompetitionList").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<SportCompetitionEntity> result = SportCompetitionService.getCompetitionList(appCode, "", "",  1);
                flushResultToFile("SportCompetitionService.getCompetitionList.txt", result);
            });
        });
        createButton("getCompetitionRankDetail").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                SportCompetitionInfoEntity result = SportCompetitionService.getCompetitionRankDetail("", "");
                flushResultToFile("SportCompetitionService.getCompetitionRankDetail.txt", result);
            });
        });
        createButton("joinCompetitionRank").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                RequestResult result = SportCompetitionService.joinCompetitionRank("", "", "");
                flushResultToFile("SportCompetitionService.joinCompetitionRank.txt", result);
            });
        });

        addTextView("TeachService");
        createButton("getCourseDetailList").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<GroupTeachEntity> result = TeachService.getCourseDetailList("", "", "", 1, "");
                flushResultToFile("TeachService.getCourseDetailList.txt", result);
            });
        });
        createButton("getCourseTimes").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                int result = TeachService.getCourseTimes("", "", "", "");
                flushResultToFile("TeachService.getCourseTimes.txt", result);
            });
        });
        createButton("upTeachButtonClick").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                boolean result = TeachService.upTeachButtonClick("", "");
                flushResultToFile("TeachService.upTeachButtonClick.txt", result);
            });
        });
        createButton("getClassroomCompetitionRecordList").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                ClassCompetitionEntity result = TeachService.getClassroomCompetitionRecordList("", "", 0, 0);
                flushResultToFile("TeachService.getClassroomCompetitionRecordList.txt", result);
            });
        });
        createButton("getResList").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                TeacherResEntity result = TeachService.getResList("", 0, appCode);
                flushResultToFile("TeachService.getResList.txt", result);
            });
        });
        createButton("getResListByPage").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                TeacherResEntity result = TeachService.getResListByPage("", 0, appCode, 1, 10);
                flushResultToFile("TeachService.getResListByPage.txt", result);
            });
        });
        createButton("saveTempGroup").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                boolean result = TeachService.saveTempGroup(null);
                flushResultToFile("TeachService.saveTempGroup.txt", result);
            });
        });
        createButton("getTempGroup").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                TempGroupTeachEntity result = TeachService.getTempGroup("", "");
                flushResultToFile("TeachService.getTempGroup.txt", result);
            });
        });
        createButton("getDataDates").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                ArrayList<String> result = TeachService.getDataDates(null, null);
                flushResultToFile("TeachService.getDataDates.txt", result);
            });
        });

        addTextView("DeviceService");
        createButton("getDeviceInfo").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                DeviceInfoEntity result = DeviceService.getDeviceInfo();
                flushResultToFile("DeviceService.getDeviceInfo.txt", result);
            });
        });
        createButton("getDeviceQrcode").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                String result = DeviceService.getDeviceQrcode(appCode);
                flushResultToFile("DeviceService.getDeviceQrcode.txt", result);
            });
        });
        createButton("uploadDeviceName").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                Boolean result = DeviceService.uploadDeviceName("", "", "");
                flushResultToFile("DeviceService.uploadDeviceName.txt", result);
            });
        });
        createButton("getDeviceFocus").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<DeviceFocusEntity> result = DeviceService.getDeviceFocus(appCode, "");
                flushResultToFile("DeviceService.getDeviceFocus.txt", result);
            });
        });
        createButton("getUpgradeInfo").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<AdlUpgradeInfo> result = DeviceService.getUpgradeInfo(appCode, "");
                flushResultToFile("DeviceService.getUpgradeInfo.txt", result);
            });
        });

        addTextView("BannerService");
        createButton("getBannerDetail").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<BannerEntity> result = BannerService.getBannerDetail("");
                flushResultToFile("BannerService.getBannerDetail.txt", result);
            });
        });

        addTextView("WikiService");
        createButton("getWikiTypeList").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                WikiTypeEntity result = WikiService.getWikiTypeList();
                flushResultToFile("WikiService.getWikiTypeList.txt", result);
            });
        });
        createButton("getWikiList").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                WikiEntity result = WikiService.getWikiList("");
                flushResultToFile("WikiService.getWikiList.txt", result);
            });
        });
        createButton("getWikiListByPage").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                WikiEntity result = WikiService.getWikiListByPage("", 1, 10);
                flushResultToFile("WikiService.getWikiListByPage.txt", result);
            });
        });
        createButton("getWikiDetail").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                WikiDetailEntity result = WikiService.getWikiDetail("");
                flushResultToFile("WikiService.getWikiDetail.txt", result);
            });
        });
        createButton("getOrgResInfo").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                OrgResInfoEntity result = WikiService.getOrgResInfo("");
                flushResultToFile("WikiService.getOrgResInfo.txt", result);
            });
        });

        addTextView("ResService");
        createButton("getResList").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<WikiTypeEntity.WikiTypeInfo> result = ResService.getResList(1);
                flushResultToFile("ResService.getResList.txt", result);
            });
        });
        createButton("getResListByPage").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<ResInfoEntity> result = ResService.getResListByPage("", 1, "", "", 1, 10);
                flushResultToFile("ResService.getResListByPage.txt", result);
            });
        });

        addTextView("GradeService");
        createButton("getGradeTree").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<GradeInfoEntity> result = GradeService.getGradeTree();
                flushResultToFile("GradeService.getGradeTree.txt", result);
            });
        });

        addTextView("TraninService");
        createButton("getRunTrainPlanList").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                TrainEntity result = TrainService.getRunTrainPlanList("", 0);
                flushResultToFile("TrainService.getRunTrainPlanList.txt", result);
            });
        });
        createButton("getTrainPlanOneDayProjectList").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                List<TrainSportInfoEntity> result = TrainService.getTrainPlanOneDayProjectList("", "", 0);
                flushResultToFile("TrainService.getTrainPlanOneDayProjectList.txt", result);
            });
        });

        addTextView("CommonService");
        createButton("addBatch").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                RequestResult result = CommonService.addBatch(appCode, 0, "", "", 0, null, "");
                flushResultToFile("CommonService.addBatch.txt", result);
            });
        });
        createButton("getWeatherInfo").setOnClickListener(v -> {
            BaseService.postTask(() -> {
                WeatherEntity result = CommonService.getWeatherInfo("");
                flushResultToFile("CommonService.getWeatherInfo.txt", result);
            });
        });

    }

    private Button createButton(String btnText) {
        Button button = new Button(this);
        button.setText(btnText);
        button.setAllCaps(false);
        button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(button);
        return button;
    }

    private void addTextView(String text) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setText(text);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(Color.BLACK);
        container.addView(textView);
    }

    private void flushResultToFile(String fileName, Object result) {
        File dir = FileUtil.createDirectory("NzWebServiceV2Result");
        File file = new File(dir, fileName);
        
        FileOutputStream out = null;
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            String content = result.toString();
            out = new FileOutputStream(file, true);
            out.write(content.getBytes("UTF-8"));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
