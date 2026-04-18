package com.adl.service.db.converter;

import com.adl.service.db.entity.SportProjectEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListSportProjectEntityConverter {

    @TypeConverter
    public static List<SportProjectEntity> stringToObject(String value) {
        Type listType = new TypeToken<List<SportProjectEntity>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<SportProjectEntity> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
