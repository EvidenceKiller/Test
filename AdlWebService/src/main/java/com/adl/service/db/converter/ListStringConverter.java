package com.adl.service.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListStringConverter {

    @TypeConverter
    public static List<String> stringToObject(String value) {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<String> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }

}

