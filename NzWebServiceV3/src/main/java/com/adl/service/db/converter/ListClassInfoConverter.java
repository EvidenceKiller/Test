package com.adl.service.db.converter;

import com.adl.service.entity.ClassInfoEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListClassInfoConverter {

    @TypeConverter
    public static List<ClassInfoEntity> stringToObject(String value) {
        Type listType = new TypeToken<List<ClassInfoEntity>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<ClassInfoEntity> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
