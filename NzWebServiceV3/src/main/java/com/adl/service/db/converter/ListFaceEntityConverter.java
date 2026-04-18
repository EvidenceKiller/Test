package com.adl.service.db.converter;

import com.adl.service.db.entity.FaceEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListFaceEntityConverter {

    @TypeConverter
    public static List<FaceEntity> stringToObject(String value) {
        Type listType = new TypeToken<List<FaceEntity>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<FaceEntity> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
