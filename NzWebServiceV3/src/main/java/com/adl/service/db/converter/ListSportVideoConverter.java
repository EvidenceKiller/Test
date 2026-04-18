package com.adl.service.db.converter;

import com.adl.service.data.SportVideoData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListSportVideoConverter {

    @TypeConverter
    public static List<SportVideoData> stringToObject(String value) {
        Type listType = new TypeToken<List<SportVideoData>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<SportVideoData> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
