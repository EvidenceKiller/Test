package com.adl.service.db.converter;

import com.adl.service.entity.SportPlanProjectEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListPlanSportConverter {

    @TypeConverter
    public static List<SportPlanProjectEntity> stringToObject(String value) {
        Type listType = new TypeToken<List<SportPlanProjectEntity>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<SportPlanProjectEntity> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
