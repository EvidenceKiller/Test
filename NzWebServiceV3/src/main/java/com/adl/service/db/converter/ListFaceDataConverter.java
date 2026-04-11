package com.adl.service.db.converter;

import com.adl.service.entity.FaceDataEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListFaceDataConverter {

    @TypeConverter
    public static List<FaceDataEntity> stringToObject(String value) {
        Type listType = new TypeToken<List<FaceDataEntity>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<FaceDataEntity> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
