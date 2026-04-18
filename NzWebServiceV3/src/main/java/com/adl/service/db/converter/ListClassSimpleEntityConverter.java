package com.adl.service.db.converter;

import com.adl.service.db.entity.ClassSimpleEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListClassSimpleEntityConverter {

    @TypeConverter
    public static List<ClassSimpleEntity> stringToObject(String value) {
        Type listType = new TypeToken<List<ClassSimpleEntity>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<ClassSimpleEntity> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
