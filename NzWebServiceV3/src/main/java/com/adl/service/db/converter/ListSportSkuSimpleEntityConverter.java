package com.adl.service.db.converter;

import com.adl.service.db.entity.SportSkuSimpleEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListSportSkuSimpleEntityConverter {

    @TypeConverter
    public static List<SportSkuSimpleEntity> stringToObject(String value) {
        Type listType = new TypeToken<List<SportSkuSimpleEntity>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<SportSkuSimpleEntity> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
