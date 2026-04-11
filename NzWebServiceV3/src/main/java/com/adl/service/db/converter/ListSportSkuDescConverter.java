package com.adl.service.db.converter;

import com.adl.service.entity.SportSkuDescEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListSportSkuDescConverter {

    @TypeConverter
    public static List<SportSkuDescEntity> stringToObject(String value) {
        Type listType = new TypeToken<List<SportSkuDescEntity>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<SportSkuDescEntity> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
