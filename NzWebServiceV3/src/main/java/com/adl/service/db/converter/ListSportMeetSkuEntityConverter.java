package com.adl.service.db.converter;

import com.adl.service.db.entity.SportMeetSkuEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListSportMeetSkuEntityConverter {

    @TypeConverter
    public static List<SportMeetSkuEntity> stringToObject(String value) {
        Type listType = new TypeToken<List<SportMeetSkuEntity>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<SportMeetSkuEntity> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
