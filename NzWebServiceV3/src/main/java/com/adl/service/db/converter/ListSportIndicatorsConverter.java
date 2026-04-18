package com.adl.service.db.converter;

import com.adl.service.data.SportIndicatorsData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListSportIndicatorsConverter {

    @TypeConverter
    public static List<SportIndicatorsData> stringToObject(String value) {
        Type listType = new TypeToken<List<SportIndicatorsData>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<SportIndicatorsData> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
