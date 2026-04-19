package com.adl.service.repository;

import com.adl.service.data.BasePageData;
import com.adl.service.data.ClassroomCompetitionRecordData;
import com.adl.service.data.SunshineRunningSportDetailData;
import com.adl.service.data.TeachCourseDetailData;
import com.adl.service.http.request.SunshineRunningCompetitionRecordPageRequest;
import com.adl.service.http.request.SunshineRunningSportDetailPageRequest;
import com.adl.service.http.request.TeachCourseButtonClickCountRequest;
import com.adl.service.http.request.TeachCourseDataDatesRequest;
import com.adl.service.http.request.TeachCourseRequest;
import com.adl.service.repository.fetch.BigScreenH5FetchHelper;
import com.adl.service.repository.persist.BigScreenH5PersistHelper;
import com.adl.service.repository.prepare.BigScreenH5PrepareHelper;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public final class BigScreenH5Repository {
    private final BigScreenH5PrepareHelper prepareHelper = new BigScreenH5PrepareHelper();
    private final BigScreenH5FetchHelper fetchHelper = new BigScreenH5FetchHelper();
    private final BigScreenH5PersistHelper persistHelper = new BigScreenH5PersistHelper();

    public BigScreenH5Repository() {
    }

    public Single<List<TeachCourseDetailData>> getTeachCourseDetailList(TeachCourseRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetTeachCourseDetailListFunc());
    }

    public Single<List<String>> getTeachCourseDataDates(TeachCourseDataDatesRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetTeachCourseDataDatesFunc());
    }

    public Single<Integer> getTeachCourseTimes(TeachCourseRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetTeachCourseTimesFunc());
    }

    public Single<Boolean> getTeachCourseButtonClickable(TeachCourseButtonClickCountRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetTeachCourseButtonClickableFunc());
    }

    public Single<BasePageData<SunshineRunningSportDetailData>> getSunshineRunningSportDetailPage(SunshineRunningSportDetailPageRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetSunshineRunningSportDetailPageFunc());
    }

    public Single<BasePageData<ClassroomCompetitionRecordData>> getSunshineRunningCompetitionRecordPage(SunshineRunningCompetitionRecordPageRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetSunshineRunningCompetitionRecordPageFunc());
    }
}
