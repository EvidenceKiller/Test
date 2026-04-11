package com.adl.service.web;

import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.entity.GradeInfoEntity;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.RequestBody;

public class GradeService extends BaseHttpService {
    public static List<GradeInfoEntity> getGradeTree() {
        try {
            d("From Http 获取年级树");

            // 增量更新学生信息
            //http://172.16.0.206:8082/gateway/api/busi/terminal/grade/v1/getGradeTree
            String url = wrapperUrl("/busi/terminal/grade/v1/getGradeTree");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create("{}", jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<ArrayList<GradeInfoEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
