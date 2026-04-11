package com.adl.service.db.converter;

import androidx.room.TypeConverter;

import com.adl.service.entity.SportRecordEntity;
import com.google.gson.Gson;

public class RecordPlanConverter {

    @TypeConverter
    public static SportRecordEntity.RecordPlan stringToObject(String value) {
        return new Gson().fromJson(value, SportRecordEntity.RecordPlan.class);
    }

    @TypeConverter
    public static String objectToString(SportRecordEntity.RecordPlan json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
