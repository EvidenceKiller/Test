package com.adl.service.db.converter;

import com.adl.service.entity.SportRecordEntity;
import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class SportTrainPlanConverter {
    @TypeConverter
    public static SportRecordEntity.SportTrainPlan stringToObject(String value) {
        return new Gson().fromJson(value, SportRecordEntity.SportTrainPlan.class);
    }

    @TypeConverter
    public static String objectToString(SportRecordEntity.SportTrainPlan json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
