package com.adl.service.caller;

import com.adl.service.callback.RequestCallback;
import com.adl.service.exception.NzBaseException;
import com.adl.service.http.request.RequestScope;
import com.adl.service.http.request.SunshineRunningCompetitionRecordPageRequest;
import com.adl.service.http.request.SunshineRunningSportDetailPageRequest;
import com.adl.service.http.request.TeachCourseButtonClickCountRequest;
import com.adl.service.http.request.TeachCourseDataDatesRequest;
import com.adl.service.http.request.TeachCourseRequest;
import com.adl.service.data.BasePageData;
import com.adl.service.data.ClassroomCompetitionRecordData;
import com.adl.service.data.SunshineRunningSportDetailData;
import com.adl.service.data.TeachCourseDetailData;

import java.util.List;

/**
 * BigScreenH5Caller。
 */
public interface BigScreenH5Caller {

    /**
     * 分组教学列表
     * <p>异步调用。</p>
     */
    long getTeachCourseDetailListAsync(RequestScope scope, TeachCourseRequest request, RequestCallback<List<TeachCourseDetailData>> callback);

    /**
     * 分组教学列表
     * <p>同步调用。</p>
     */
    List<TeachCourseDetailData> getTeachCourseDetailListSync(TeachCourseRequest request) throws NzBaseException;

    /**
     * 有数据的日期列表
     * <p>异步调用。</p>
     */
    long getTeachCourseDataDatesAsync(RequestScope scope, TeachCourseDataDatesRequest request, RequestCallback<List<String>> callback);

    /**
     * 有数据的日期列表
     * <p>同步调用。</p>
     */
    List<String> getTeachCourseDataDatesSync(TeachCourseDataDatesRequest request) throws NzBaseException;

    /**
     * 最大次数
     * <p>异步调用。</p>
     */
    long getTeachCourseTimesAsync(RequestScope scope, TeachCourseRequest request, RequestCallback<Integer> callback);

    /**
     * 最大次数
     * <p>同步调用。</p>
     */
    Integer getTeachCourseTimesSync(TeachCourseRequest request) throws NzBaseException;

    /**
     * 教学按钮点击计数
     * <p>异步调用。</p>
     */
    long getTeachCourseButtonClickableAsync(RequestScope scope, TeachCourseButtonClickCountRequest request, RequestCallback<Boolean> callback);

    /**
     * 教学按钮点击计数
     * <p>同步调用。</p>
     */
    Boolean getTeachCourseButtonClickableSync(TeachCourseButtonClickCountRequest request) throws NzBaseException;

    /**
     * 历史记录
     * <p>异步调用。</p>
     */
    long getSunshineRunningSportDetailPageAsync(RequestScope scope, SunshineRunningSportDetailPageRequest request, RequestCallback<BasePageData<SunshineRunningSportDetailData>> callback);

    /**
     * 历史记录
     * <p>同步调用。</p>
     */
    BasePageData<SunshineRunningSportDetailData> getSunshineRunningSportDetailPageSync(SunshineRunningSportDetailPageRequest request) throws NzBaseException;

    /**
     * 历史记录
     * <p>异步调用。</p>
     */
    long getSunshineRunningCompetitionRecordPageAsync(RequestScope scope, SunshineRunningCompetitionRecordPageRequest request, RequestCallback<BasePageData<ClassroomCompetitionRecordData>> callback);

    /**
     * 历史记录
     * <p>同步调用。</p>
     */
    BasePageData<ClassroomCompetitionRecordData> getSunshineRunningCompetitionRecordPageSync(SunshineRunningCompetitionRecordPageRequest request) throws NzBaseException;

}
