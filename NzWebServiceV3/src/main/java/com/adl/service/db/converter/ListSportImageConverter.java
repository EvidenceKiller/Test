package com.adl.service.db.converter;

import com.adl.service.data.SportImageData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListSportImageConverter {

    @TypeConverter
    public static List<SportImageData> stringToObject(String value) {
        Type listType = new TypeToken<List<SportImageData>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<SportImageData> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
