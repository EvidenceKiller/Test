package com.ai.power.normal;

import android.util.Log;

import com.adl.service.data.BasePageData;
import com.adl.service.data.BaseResponse;
import com.adl.service.data.SportSkuData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BasePageDataGenericDeserializeTest {

    @Test
    public void basePageData_records_should_deserialize_to_sportSkuData() {
        String json = "{\"code\":200,\"msg\":null,\"data\":{\"records\":[{\"sportSkuId\":\"0001010001\",\"sportNatureCodes\":\"4\",\"sportNatureNames\":\"素质\",\"sportTypeCode\":\"0001\",\"sportTypeName\":\"身高\",\"sportItemCode\":\"000101\",\"sportItemName\":\"身高\",\"sportSkuName\":\"身高\",\"measureMode\":\"3\",\"measureUnitCode\":\"cm\",\"rangeEndValue\":9999.0,\"rangeStartValue\":-9999.0,\"accuracyType\":0,\"endType\":99,\"accuracyMethod\":1,\"endValue\":null,\"endRemark\":null,\"iconUrl\":null,\"coverUrl\":\"V2/oper/skuDefaultCover.png\",\"actionVideoUrl\":null,\"audioUrl\":null,\"audioName\":null,\"videoUrl\":\"V2/oper/ProjectLibrary/Video/2025-01-20/1737355759455_cbe49ee5.mp4\",\"videoCoverUrl\":\"V2/oper/ProjectLibrary/Video/2025-01-20/1737355759455_cbe49ee5.mp4\",\"sportSkuDesc\":\"[{\\\"id\\\":\\\"1\\\",\\\"title\\\":\\\"\\\",\\\"context\\\":\\\"\\\"},{\\\"id\\\":\\\"2\\\",\\\"title\\\":\\\"\\\",\\\"context\\\":\\\"\\\"},{\\\"id\\\":\\\"3\\\",\\\"title\\\":\\\"\\\",\\\"context\\\":\\\"\\\"}]\",\"testTemplateUrl\":null,\"testTemplateName\":null,\"qusTemplateUrl\":null,\"qusTemplateName\":null,\"txVid\":null,\"remark\":null,\"enabled\":true,\"delFlag\":false,\"sportPriority\":1,\"testCount\":-1,\"sceneSkuId\":null,\"sceneCode\":null,\"sceneSkuSort\":0,\"appCodes\":[\"101101\",\"106101\",\"107102\",\"107202\"],\"qualities\":[5],\"sceneSkuType\":null},{\"sportSkuId\":\"0001010002\",\"sportNatureCodes\":\"4\",\"sportNatureNames\":\"素质\",\"sportTypeCode\":\"0001\",\"sportTypeName\":\"身高\",\"sportItemCode\":\"000101\",\"sportItemName\":\"身高\",\"sportSkuName\":\"身高\",\"measureMode\":\"3\",\"measureUnitCode\":\"cm\",\"rangeEndValue\":9.9999999E7,\"rangeStartValue\":-9999.0,\"accuracyType\":1,\"endType\":99,\"accuracyMethod\":1,\"endValue\":null,\"endRemark\":null,\"iconUrl\":null,\"coverUrl\":\"V2/oper/skuDefaultCover.png\",\"actionVideoUrl\":null,\"audioUrl\":null,\"audioName\":null,\"videoUrl\":null,\"videoCoverUrl\":null,\"sportSkuDesc\":\"[{\\\"id\\\":\\\"1\\\",\\\"title\\\":\\\"关联肌肉\\\",\\\"context\\\":\\\"\\\"},{\\\"id\\\":\\\"2\\\",\\\"title\\\":\\\"动作要领\\\",\\\"context\\\":\\\"\\\"},{\\\"id\\\":\\\"3\\\",\\\"title\\\":\\\"注意事项\\\",\\\"context\\\":\\\"\\\"}]\",\"testTemplateUrl\":null,\"testTemplateName\":null,\"qusTemplateUrl\":null,\"qusTemplateName\":null,\"txVid\":null,\"remark\":null,\"enabled\":true,\"delFlag\":false,\"sportPriority\":9,\"testCount\":1,\"sceneSkuId\":null,\"sceneCode\":null,\"sceneSkuSort\":0,\"appCodes\":[\"107102\",\"107202\"],\"qualities\":[5],\"sceneSkuType\":null}],\"total\":0,\"size\":2,\"current\":1,\"pages\":0},\"succeed\":true}\n";

        Type type = new TypeToken<BaseResponse<BasePageData<SportSkuData>>>() {
        }.getType();
        BaseResponse<BasePageData<SportSkuData>> result = new Gson().fromJson(json, type);
        assertNotNull(result);
        assertTrue(result.getSucceed());
        BasePageData<SportSkuData> basePageData = result.getData();
        System.out.println("ZXN_TEST::page data : " + result);
        assertNotNull(basePageData.getRecords());
        assertFalse(basePageData.getRecords().isEmpty());
        System.out.println("ZXN_TEST::record size : " + basePageData.getRecords().size());
        assertNotNull(basePageData.getRecords().get(0));
        System.out.println("ZXN_TEST::first data : " + basePageData.getRecords().get(0));
        assertTrue(basePageData.getRecords().get(0) instanceof SportSkuData);
        assertEquals("0001010001", basePageData.getRecords().get(0).getSportSkuId());
    }
}

